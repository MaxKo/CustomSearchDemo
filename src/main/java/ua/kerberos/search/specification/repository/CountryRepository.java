package ua.kerberos.search.specification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.kerberos.search.specification.entity.Country;
import ua.kerberos.search.specification.entity.enumerators.Countries;

/**
 * Created by Maksym Kovieshnikov on 13/08/2020
 */
@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
	Country getCountryByName(String name);
}
