package ua.kerberos.search.specification.dto;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import ua.kerberos.search.specification.entity.Region;
import ua.kerberos.search.specification.entity.User;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;

@RequiredArgsConstructor
public class StatSpecification implements Specification<Stat> {

	private final Specification specification;

	@Override
	public Predicate toPredicate(Root<Stat> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

//		Predicate p = Specification.where(specification).toPredicate(root, query, criteriaBuilder);

//		Subquery subQuery = query.subquery(p.getJavaType()).where(p);
//		var from = query.from(Region.class)
//		query.multiselect(from.get("id"), criteriaBuilder.count(from)).(subQuery).



//		return null;

//		CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
		CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(User.class);
		Root<User> from = criteriaQuery.from(User.class);
		Predicate p = Specification.where(specification).toPredicate(from, criteriaQuery, criteriaBuilder);
		criteriaQuery.where(p);

		var a = criteriaQuery.multiselect(
				from.get("region"),//.as(java.sql.Date.class),
				criteriaBuilder.count(from)
		);

		//criteriaQuery.where(p);
		/*Add order by and group by clause*/
//		criteriaQuery.orderBy(criteriaBuilder.desc(from.get("region")));
		a.groupBy(from.get("region"));

		return a.getRestriction();
//		criteriaQuery.

//		TypedQuery<Object[]> query1 = em.createQuery(criteriaQuery);
//		List<Object[]> results = query1.getResultList();


	}
}
