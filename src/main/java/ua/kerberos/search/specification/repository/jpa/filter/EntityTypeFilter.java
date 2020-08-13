package ua.kerberos.search.specification.repository.jpa.filter;

import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

/**
 * Created by Maksym Kovieshnikov on 08/07/2020
 */

public class EntityTypeFilter extends AbstractInJpaFilter {

    @Override
    public Predicate getPredicate() {
        Subquery cq = criteriaQuery.subquery(this.entityType);
        Root root = cq.from(this.entityType);

        var inClause = calculateTypeClause(root);

        cq.select(root)
                .where(inClause);

        return this.criteriaBuilder.in(this.root.get(ID)).value(cq.select(root.get(ID)));
    }
}
