package ua.kerberos.search.specification.repository.jpa.filter;

import javax.persistence.criteria.Predicate;
import java.util.Arrays;

/**
 * Created by Dmitry Naumovich on 03/07/2020
 */
public class MultipleStringLikeFilter extends AbstractJpaFilter {
    @Override
    public Predicate getPredicate() {
        var predicates = Arrays.stream(searchAmongProperties)
                .map(prop -> this.criteriaBuilder.like(this.criteriaBuilder.lower(this.root.get(prop)), "%" + this.value.toString().toLowerCase() + "%"))
                .toArray(Predicate[]::new);

        return this.criteriaBuilder.or(predicates);
    }
}
