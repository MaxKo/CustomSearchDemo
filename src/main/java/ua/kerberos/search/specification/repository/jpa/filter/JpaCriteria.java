package ua.kerberos.search.specification.repository.jpa.filter;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Maksym Kovieshnikov on 02/06/2020
 * <p>
 * Represent custom mapper configuration between Jpa Query Criteria builder and property of search request parameter object
 * <p>
 * When {@link ua.kerberos.search.specification.repository.jpa.filter.JpaCriteria} is bound to a
 * field, this allows you to manually define a parameter in a fine-tuned manner.
 * This is the only way to define parameters when using Request Parameters mapped to your custom object.
 * Object should be nested from AbstractSearchParameters
 * <p>
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface JpaCriteria {
    /**
     * if request parameter field name is differ from destination @Entity field .
     */
    String propertyName();

    /**
     * If search functionality is complex than simple field search this class allow you to configure custom search conditions through Criteria
     */
    Class<? extends AbstractJpaFilter> handlerClass() default DefaultFilter.class;

    /**
     * Used for sending additional conditions to custom criteria class
     * For example if BEGIN selected it sets the condition greater than value for DatePropertyFilter
     */
    SearchDirectionEnum searchDirection() default SearchDirectionEnum.EQUAL;

    /**
     * Used for sending additional parameter to custom criteria class
     * Mostly used as field name of related entity by which search should be conducted
     */
    String relatedEntityPropertyName() default "";

    String destinationPropertyName() default  "";

    /**
     * In case default filter class handler is necessary
     */
    abstract class DefaultFilter extends AbstractJpaFilter {
    }

    /**
     * In some specific cases it might be required to include in search rows where related entity`s property has some value with rows which does not have related entity at all.
     * Please ensure specific JPA filter supports this property correctly
     */
    boolean includeNullRelatedEntity() default false;

    /**
     * Used for searching the value among several other properties combined by or predicate.
     * The value is passed in one parameter, the search occurs among all listed parameters of the same entity.
     * Use it with MultipleStringLikeFilter.
     *
     * @see ua.kerberos.search.specification.repository.jpa.filter.MultipleStringLikeFilter
     */
    String[] searchAmongProperties() default {};


    /**
     * Used for joining/sub-query related entity when search field placed in 2-3-n chain of relation from searched entity.
     * first element is property of search
     *
     * @see ua.kerberos.search.specification.repository.jpa.filter.MultipleStringLikeFilter
     */
    String[] propertyChain() default {};

}
