package ua.kerberos.search.specification.repository.jpa.filter;

import javax.persistence.criteria.Predicate;

/**
 * Created by Maksym Kovieshnikov on 02/06/2020
 */
public class NotNullFilter extends AbstractJpaFilter {
    @Override
    public Predicate getPredicate() {
        Boolean val = (Boolean) this.value;

        return val
                ? this.criteriaBuilder.isNotNull(this.root.get(this.propertyName))
                : this.criteriaBuilder.isNull(this.root.get(this.propertyName));
    }
}
