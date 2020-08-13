package ua.kerberos.search.specification.repository.jpa.filter;

import javax.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Created by Maksym Kovieshnikov on 02/06/2020
 */
public class DatePropertyFilter extends AbstractJpaFilter {
    @Override
    public Predicate getPredicate() {
        switch (this.searchDirection) {
            case AFTER:
                return criteriaBuilder
                        .greaterThanOrEqualTo(this.root.get(this.propertyName), LocalDateTime.of((LocalDate)this.value, LocalTime.MIN));
            case BEFORE:
                return criteriaBuilder
                        .lessThanOrEqualTo(this.root.get(this.propertyName), LocalDateTime.of((LocalDate)this.value, LocalTime.MAX));
            default:
                return criteriaBuilder
                        .equal(this.root.get(this.propertyName), LocalDateTime.of((LocalDate)this.value, LocalTime.MIN));
        }
    }
}
