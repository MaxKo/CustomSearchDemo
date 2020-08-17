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

    @Data
    private class JoinContainer {
        Join join;
    }

    @Override
    public Predicate getPredicate() {
        var j = new JoinContainer();

        for (String p : propertyChain) {
            if (j.getJoin() == null) {
                j.setJoin(root.join(p, JoinType.LEFT));
            } else {
                j.setJoin(j.getJoin().join(p, JoinType.LEFT));
            }
        }

        return this
                .criteriaBuilder
                .like(this.criteriaBuilder.lower(j.getJoin().get(this.destinationPropertyName)), "%" + this.value.toString().toLowerCase() + "%");

    }
}