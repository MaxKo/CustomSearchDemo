package ua.kerberos.search.specification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kerberos.search.specification.entity.Region;

public interface RegionRepository  extends JpaRepository<Region, Long> {
	Region findByName(String name);
}
