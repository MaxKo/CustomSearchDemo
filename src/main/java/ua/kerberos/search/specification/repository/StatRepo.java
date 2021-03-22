package ua.kerberos.search.specification.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import ua.kerberos.search.specification.dto.Stat;
import ua.kerberos.search.specification.dto.search.UserSearchSpecificationRequest;
import ua.kerberos.search.specification.entity.User;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class StatRepo {

	private final EntityManager entityManager;

	public List<Stat> getUserStatBySpecification(Specification specification) {
        var cb = entityManager.getCriteriaBuilder();
		var q = cb.createQuery(Object[].class);

		Root<User> c = q.from(User.class);

		q.multiselect(c.get("region"), cb.count(c.get("id")).alias("users_count"));
		q.where(specification.toPredicate(c, q, entityManager.getCriteriaBuilder()));
		q.groupBy(c.get("region"));
		q.having(cb.gt(cb.count(c), 1));

		Query query = entityManager.createQuery(q);
		return query.getResultList();
	}
}
