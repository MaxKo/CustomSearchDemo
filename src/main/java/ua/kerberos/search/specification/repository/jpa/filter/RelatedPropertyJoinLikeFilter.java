package ua.kerberos.search.specification.repository.jpa.filter;

import lombok.NoArgsConstructor;

import javax.persistence.criteria.*;

/**
 * Created by Maksym Kovieshnikov on 02/06/2020
 */
@NoArgsConstructor
public class RelatedPropertyJoinLikeFilter extends AbstractInJpaFilter {
    @Override
    public Predicate getPredicate() {
        Join join = root.join(this.propertyName, JoinType.LEFT);

        return this
                .criteriaBuilder
                .like( this.criteriaBuilder.lower( join.get(this.destinationPropertyName)), "%" + this.value.toString().toLowerCase() + "%");
    }
}