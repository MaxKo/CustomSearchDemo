package ua.kerberos.search.specification.repository.jpa.filter;

import lombok.NoArgsConstructor;

import jakarta.persistence.criteria.*;

/**
 * Created by Maksym Kovieshnikov on 02/06/2020
 */
@NoArgsConstructor
public class RelatedPropertyChainEqualFilter extends AbstractInJpaFilter {
    @Override
    public Predicate getPredicate() {
        Subquery subQuery = this.criteriaQuery.subquery(this.fieldType);
        Root relatedFieldRoot = subQuery.from(this.fieldType);

        Join join = relatedFieldRoot.join(this.relatedEntityPropertyName, JoinType.LEFT);

        Predicate clause = calculateMultipleOrSingleEqualClause(join, this.destinationPropertyName);

        subQuery.select(relatedFieldRoot)
                .distinct(true)
                .where(clause);

        return this.criteriaBuilder.in(this.root.get(this.propertyName)).value(subQuery);
    }
}