package ua.kerberos.search.specification.repository.jpa.filter;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.Data;
import lombok.val;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ReflectionUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.time.LocalDate;
import java.util.stream.Stream;

import static org.apache.logging.log4j.util.Strings.EMPTY;
import static org.springframework.util.StringUtils.isEmpty;
import static ua.kerberos.search.specification.repository.jpa.filter.ReflectionHelper.*;

/**
 * Created by Maksym Kovieshnikov on 16/12/2019
 */
@Data
@ApiResponses(value = {@ApiResponse(code = 400, message = "Bad request. Wrong parameter value(s) format")})
public abstract class AbstractSearchSpecification<T> implements Specification<T> {

    public static final String CLASS = "class";

    private Stream<AbstractJpaFilter> prepareFilterStream() {
        Field[] fields = this.getClass().getDeclaredFields();
        Field[] fieldsSuperclass = this.getClass().getSuperclass().getDeclaredFields();

        return Stream.of(fields, fieldsSuperclass)
                .flatMap(Stream::of)
                .peek(ReflectionHelper::setAccessible)
                .filter(ReflectionHelper::checkFieldNotStatic)
                .filter(fieldValueNotEmpty(this))
                .map(this::createHandlerFilter);
    }

    private AbstractJpaFilter createHandlerFilter(Field f) {
        Class<? extends AbstractJpaFilter> handlerType = getDefaultHandler(f);

        JpaCriteria jpaCriteriaAnnotation = f.getAnnotation(JpaCriteria.class);
        String propertyName = f.getName();
        String searchFieldName = f.getName();
        String relatedEntityPropertyName = EMPTY;
        Object value = ReflectionUtils.getField(f, this);
        SearchDirectionEnum direction = SearchDirectionEnum.EQUAL;
        String destinationPropertyName = EMPTY;
        boolean includeNullRelatedEntity = false;
        String[] searchAmongProperties = {};
        String[] propertyChain = {};

        if (jpaCriteriaAnnotation != null) {
            handlerType = jpaCriteriaAnnotation.handlerClass().equals(JpaCriteria.DefaultFilter.class) ? handlerType : jpaCriteriaAnnotation.handlerClass();
            propertyName = isEmpty(jpaCriteriaAnnotation.propertyName()) ? propertyName : jpaCriteriaAnnotation.propertyName();
            direction = jpaCriteriaAnnotation.searchDirection();
            relatedEntityPropertyName = jpaCriteriaAnnotation.relatedEntityPropertyName();
            includeNullRelatedEntity = jpaCriteriaAnnotation.includeNullRelatedEntity();
            destinationPropertyName = jpaCriteriaAnnotation.destinationPropertyName();
            searchAmongProperties = jpaCriteriaAnnotation.searchAmongProperties();
            propertyChain = jpaCriteriaAnnotation.propertyChain();
        }

        AbstractJpaFilter handler = newInstance(handlerType);

        handler.setSearchDirection(direction);
        handler.setPropertyName(propertyName);
        handler.setValue(value);
        handler.setRelatedEntityPropertyName(relatedEntityPropertyName);
        if (searchAmongProperties.length == 0 && !CLASS.equals(propertyName)) {
            handler.setFieldType(extractFieldType(getTargetClass(), propertyName));
        }
        handler.setPropertyChain(propertyChain);
        handler.setIncludeNullRelatedEntity(includeNullRelatedEntity);
        handler.setSearchFieldName(searchFieldName);
        handler.setDestinationPropertyName(destinationPropertyName);
        handler.setEntityType(getTargetClass());
        handler.setSearchAmongProperties(searchAmongProperties);

        return handler;
    }

    private Class getTargetClass() {
        return (Class) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    private Class getDefaultHandler(Field field) {
        val type = field.getType();

        if (isEnumListType(field.getGenericType())) return EnumOptionsFilter.class;

        if (LocalDate.class.equals(type)) return DayEqualPropertyFilter.class;

        if (Long.class.equals(type)) return EqualFilter.class;

        if (Boolean.class.equals(type)) return EqualFilter.class;

        return StringPropertyLikeFilter.class;
    }

    /**
     * Implements Specification requirement to provide single predicate for whole search parameter
     *
     * @param root            - Root of query
     * @param query           - query
     * @param criteriaBuilder - CriteriaBuilder
     * @return Predicate for Specification
     */
    @Override
    public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
        Predicate[] predicates = this
                .prepareFilterStream()
                .map(f -> f.setQueryContext(query, criteriaBuilder, root).getPredicate())
                .toArray(Predicate[]::new);

        return criteriaBuilder.and(predicates);
    }
}
