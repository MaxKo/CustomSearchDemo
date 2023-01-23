package ua.kerberos.search.specification.repository.jpa.filter;

import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

/**
 * Created by Maksym Kovieshnikov on 02/06/2020
 */

@NoArgsConstructor
@Setter
public abstract class AbstractJpaFilter {
    public static final String ID = "id";

    protected Class entityType;
    protected String propertyName;
    protected String searchFieldName;
    protected Object value;
    protected Class fieldType;
    protected SearchDirectionEnum searchDirection;
    protected String relatedEntityPropertyName;
    protected String destinationPropertyName;
    protected boolean includeNullRelatedEntity;
    protected String[] searchAmongProperties;
    protected String[] propertyChain;

    protected CriteriaQuery criteriaQuery;
    protected CriteriaBuilder criteriaBuilder;
    protected Root<?> root;

    public abstract Predicate getPredicate();

    public AbstractJpaFilter setQueryContext(CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder, Root<?> root) {
        this.criteriaQuery = criteriaQuery;
        this.criteriaBuilder = criteriaBuilder;
        this.root = root;

        return this;
    }
}
