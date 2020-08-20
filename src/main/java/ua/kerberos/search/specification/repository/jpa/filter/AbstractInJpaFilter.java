package ua.kerberos.search.specification.repository.jpa.filter;

import javax.persistence.criteria.From;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.ArrayUtils.subarray;

/**
 * Created by Maksym Kovieshnikov on 18/06/2020
 */

public abstract class AbstractInJpaFilter extends AbstractJpaFilter {

    protected Predicate calculateMultipleOrSingleEqualClause(Path relatedEntityRoot, String destinationPropertyName) {
        if (!this.value.getClass().isArray()) {
            return this.criteriaBuilder.equal(relatedEntityRoot.get(destinationPropertyName), this.value);
        }

        var inClause = this.criteriaBuilder.in(relatedEntityRoot.get(destinationPropertyName));
        for (Object v : (Object[]) this.value) {
            inClause.value(v);
        }
        return inClause;
    }

    protected Predicate calculateTypeClause(Path relatedEntityRoot) {
        if (!this.value.getClass().isArray()) {
            return this.criteriaBuilder.equal(relatedEntityRoot.type(), ((ClassContainer) this.value).getClazz());
        }

        ClassContainer[] cc = (ClassContainer[]) value;

        var inClause = this.criteriaBuilder.in(relatedEntityRoot.type());
        for (ClassContainer v : cc) {
            inClause.value(v.getClazz());
        }
        return inClause;
    }


    protected List<Predicate> getResultPredicate() {
        List<Predicate> result = new ArrayList<>();

        if (this.value.getClass().isArray()) {
            if (((Object[]) this.value).length == 0) {
                result.add(isNullPredicate());
            }
            for (var v : (Object[]) this.value) {
                if (v == null) {
                    result.add(isNullPredicate());
                }
            }
        }

        if (this.includeNullRelatedEntity) result.add(isNullPredicate());

        return result;
    }

    protected From joinByFieldRecursive(From element, String[] properties) {
        if (properties.length < 1) return element;

        String[] remaining = subarray(properties, 1, properties.length);

        return joinByFieldRecursive(element.join(properties[0], JoinType.LEFT), remaining);
    }


    protected Predicate isNullPredicate() {
        return this.criteriaBuilder.isNull(root.get(this.propertyName));
    }
}
