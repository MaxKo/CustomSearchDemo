package ua.kerberos.search.specification.repository.jpa.filter;

import lombok.NoArgsConstructor;
import lombok.val;

import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;

//import javax.persistence.criteria.Predicate;
//import javax.persistence.criteria.Root;
//import javax.persistence.criteria.Subquery;

/**
 * Created by Maksym Kovieshnikov on 02/06/2020
 */
@NoArgsConstructor
public class RelatedPropertyEqualFilter extends AbstractInJpaFilter {
    @Override
    public Predicate getPredicate() {
        Subquery subQuery = this.criteriaQuery.subquery(this.fieldType);
        Root relatedEntityRoot = subQuery.from(this.fieldType);

        Predicate clause = calculateMultipleOrSingleEqualClause(relatedEntityRoot, this.relatedEntityPropertyName);

        subQuery.select(relatedEntityRoot)
                .distinct(true)
                .where(clause);

        Predicate condition = this.criteriaBuilder.in(this.root.get(this.propertyName)).value(subQuery);

        val result = getResultPredicate();
        result.add(condition);

        return this.criteriaBuilder.or(result.toArray(new Predicate[result.size()]));
    }
}