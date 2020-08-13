package ua.kerberos.search.specification.repository.jpa.filter;

import javax.persistence.criteria.Predicate;

/**
 * Created by Maksym Kovieshnikov on 02/06/2020
 */

public class StringPropertyLikeFilter extends AbstractJpaFilter {
    @Override
    public Predicate getPredicate() {
        return this.criteriaBuilder.like(this.criteriaBuilder.lower(this.root.get(this.propertyName)), "%" + this.value.toString().toLowerCase() + "%");
    }
}
