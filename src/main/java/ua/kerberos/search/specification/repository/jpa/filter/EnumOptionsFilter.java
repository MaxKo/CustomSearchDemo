package ua.kerberos.search.specification.repository.jpa.filter;

import javax.persistence.criteria.Predicate;
import java.util.List;

/**
 * Created by Maksym Kovieshnikov on 02/06/2020
 */

public class EnumOptionsFilter extends AbstractJpaFilter {
    @Override
    public Predicate getPredicate()  {
        List<Enum> statuses = (List) value;

        Predicate[] predicates = statuses.stream()
                .map(s -> this.criteriaBuilder.equal(this.root.get(this.propertyName), s))
                .toArray(Predicate[]::new);

        return this.criteriaBuilder.or(predicates);
    }
}
