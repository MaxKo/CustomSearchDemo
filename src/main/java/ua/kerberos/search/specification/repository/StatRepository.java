package ua.kerberos.search.specification.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import ua.kerberos.search.specification.dto.Stat;
import ua.kerberos.search.specification.dto.StatSpecification;
import ua.kerberos.search.specification.entity.User;
import java.util.List;

/**
 * Created by Maksym Kovieshnikov on 13/08/2020
 */
public interface StatRepository extends JpaRepository<Stat, Long>, JpaSpecificationExecutor<Stat> /*EntityGraphJpaSpecificationExecutor<User> */ {

	List<Stat> findAll(@Nullable Specification<Stat> spec);
//	List<Stat> findAll(StatSpecification searchSpecification);
}
