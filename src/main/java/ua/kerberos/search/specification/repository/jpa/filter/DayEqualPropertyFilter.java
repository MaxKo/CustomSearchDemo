package ua.kerberos.search.specification.repository.jpa.filter;

import jakarta.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Created by Maksym Kovieshnikov on 02/06/2020
 */
public class DayEqualPropertyFilter extends AbstractJpaFilter {
    @Override
    public Predicate getPredicate() {
        var startOfDayPredicate = criteriaBuilder
                .greaterThanOrEqualTo(this.root.get(this.propertyName), LocalDateTime.of((LocalDate) this.value, LocalTime.MIN));

        var endOfDayPredicate = criteriaBuilder
                .lessThanOrEqualTo(this.root.get(this.propertyName), LocalDateTime.of((LocalDate) this.value, LocalTime.MAX));

        return criteriaBuilder
                .and(new Predicate[] {startOfDayPredicate, endOfDayPredicate});
    }
}

