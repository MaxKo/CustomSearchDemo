package ua.kerberos.search.specification.repository.jpa.filter;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;

/**
 * Created by Maksym Kovieshnikov on 02/06/2020
 */
@NoArgsConstructor
public class RelatedPropertyMultipleJoinLikeFilter extends AbstractInJpaFilter {


    @Override
    public Predicate getPredicate() {
        Join j = root.join(propertyChain[0], JoinType.LEFT);

        for (int pI = 1 ; pI < propertyChain.length; pI++ ) {
            j = j.join(propertyChain[pI], JoinType.LEFT);
        }

        return this
                .criteriaBuilder
                .like(this.criteriaBuilder.lower(j.get(this.destinationPropertyName)), "%" + this.value.toString().toLowerCase() + "%");
    }
}