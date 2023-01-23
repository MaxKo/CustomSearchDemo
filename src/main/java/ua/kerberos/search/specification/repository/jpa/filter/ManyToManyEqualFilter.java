package ua.kerberos.search.specification.repository.jpa.filter;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;

/**
 * Created by Maksym Kovieshnikov on 24/06/2020
 */

public class ManyToManyEqualFilter extends AbstractInJpaFilter {

    @Override
    public Predicate getPredicate() {
        Subquery cq = criteriaQuery.subquery(this.entityType);
        Root root = cq.from(this.entityType);
        Join join = root.join(this.propertyName);

        Predicate pred = calculateMultipleOrSingleEqualClause(join, this.destinationPropertyName);

        cq.where(pred);

        return this.criteriaBuilder.in(this.root.get(ID)).value(cq.select(root.get(ID)));
    }
}
