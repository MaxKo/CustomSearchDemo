package ua.kerberos.search.specification.repository.jpa.filter;

import lombok.NoArgsConstructor;

import jakarta.persistence.criteria.*;

/**
 * Created by Maksym Kovieshnikov on 02/06/2020
 */
@NoArgsConstructor
public class RelatedPropertyChainLikeFilter extends AbstractInJpaFilter {
    @Override
    public Predicate getPredicate() {
        Subquery subQuery = this.criteriaQuery.subquery(this.fieldType);
        Root relatedFieldRoot = subQuery.from(this.fieldType);

        Join join = relatedFieldRoot.join(this.relatedEntityPropertyName, JoinType.LEFT);

        Predicate clause = this.criteriaBuilder.like( this.criteriaBuilder.lower( join.get(this.destinationPropertyName)), "%" + this.value.toString().toLowerCase() + "%");
        subQuery.select(relatedFieldRoot)
                .distinct(true)
                .where(clause);

        return this.criteriaBuilder.in(this.root.get(this.propertyName)).value(subQuery);
    }
}