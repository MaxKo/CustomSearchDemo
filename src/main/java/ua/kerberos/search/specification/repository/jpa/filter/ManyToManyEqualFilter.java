package ua.kerberos.search.specification.repository.jpa.filter;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

/**
 * Created by Maksym Kovieshnikov on 24/06/2020
 */

public class ManyToManyEqualFilter extends AbstractInJpaFilter {

    @Override
    public Predicate getPredicate() {
        Subquery cq = criteriaQuery.subquery(this.entityType);
        Root root = cq.from(this.entityType);
        Join join = root.join(this.propertyName);

        Predicate pred = calculateClause(join, this.destinationPropertyName);

        cq.where(pred);

        return this.criteriaBuilder.in(this.root.get(ID)).value(cq.select(root.get(ID)));
    }
}
