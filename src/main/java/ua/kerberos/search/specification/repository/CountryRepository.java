package ua.kerberos.search.specification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.kerberos.search.specification.entity.Country;

/**
 * Created by Maksym Kovieshnikov on 13/08/2020
 */
@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
}
