package ua.kerberos.search.specification.repository.jpa.filter;

import lombok.NoArgsConstructor;

import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

/**
 * Created by Maksym Kovieshnikov on 02/06/2020
 */
@NoArgsConstructor
public class RelatedPropertyLikeFilter extends AbstractJpaFilter {

    @Override
    public Predicate getPredicate() {
        Subquery subQuery = this.criteriaQuery.subquery(this.fieldType);
        Root productRoot = subQuery.from(this.fieldType);
        subQuery.select(productRoot)
                .distinct(true)
                .where(this.criteriaBuilder.like( this.criteriaBuilder.lower( productRoot.get(this.relatedEntityPropertyName)), "%" + this.value.toString().toLowerCase() + "%"));

        return this.criteriaBuilder.in(this.root.get(this.propertyName)).value(subQuery);
    }
}