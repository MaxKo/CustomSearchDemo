package ua.kerberos.search.specification.repository.jpa.filter;

import jakarta.persistence.criteria.Predicate;

/**
 * Created by Maksym Kovieshnikov on 02/06/2020
 */

public class EqualFilter extends AbstractJpaFilter {
    @Override
    public Predicate getPredicate() {
        return this.criteriaBuilder.equal(this.root.get(this.propertyName),  this.value);
    }

}
