package ua.kerberos.search.specification.repository.jpa.filter;

import lombok.NoArgsConstructor;

import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;

/**
 * Created by Maksym Kovieshnikov on 02/06/2020
 */
@NoArgsConstructor
public class RelatedPropertyMultipleJoinLikeFilter extends AbstractInJpaFilter {

    @Override
    public Predicate getPredicate() {
        From joinFrom = joinByFieldRecursive(root, propertyChain);

        return this
                .criteriaBuilder
                .like(this.criteriaBuilder.lower(joinFrom.get(this.destinationPropertyName)), "%" + this.value.toString().toLowerCase() + "%");
    }
}