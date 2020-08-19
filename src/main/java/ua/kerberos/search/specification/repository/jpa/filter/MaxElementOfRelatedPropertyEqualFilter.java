package ua.kerberos.search.specification.repository.jpa.filter;

import lombok.SneakyThrows;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

/**
 * Created by Maksym Kovieshnikov on 19/08/2020
 */

public class MaxElementOfRelatedPropertyEqualFilter extends AbstractInJpaFilter {
    @Override
    @SneakyThrows
    public Predicate getPredicate() {
        //first element is type of related entity
        Class subQueryClass = Class.forName(searchAmongProperties[0]);
        //second element is the field by which latest/max element will be matched
        String maxConditionField = searchAmongProperties[1];
        //Todo make custom annotation or fields to not use raw array

        Subquery cq = criteriaQuery.subquery(subQueryClass);
        Root root = cq.from(subQueryClass);

        Subquery sq = cq.subquery(Object.class);
        Root sqFrom = sq.from(subQueryClass);
        Expression maxExpr = criteriaBuilder.max(sqFrom.get(maxConditionField));
        sq
                .select(maxExpr)
                .where(criteriaBuilder.equal(sqFrom.get(relatedEntityPropertyName), root.get(relatedEntityPropertyName)));

        Predicate pSameSubQueryEntity = criteriaBuilder.equal(this.root, root.get(relatedEntityPropertyName));

        Predicate pCondition = calculateClause(root, this.destinationPropertyName);

        Predicate pSameEntity = criteriaBuilder.equal(root.get(maxConditionField), sq);

        cq.where(criteriaBuilder.and(pSameEntity, pCondition, pSameSubQueryEntity));

        return this.criteriaBuilder.in(this.root.get(ID)).value(cq.select(root.get(relatedEntityPropertyName)));
    }
}
