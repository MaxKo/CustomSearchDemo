package ua.kerberos.search.specification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.kerberos.search.specification.entity.UserStatus;

/**
 * Created by Maksym Kovieshnikov on 19/08/2020
 */
@Repository
public interface UserStatusRepository extends JpaRepository<UserStatus, Long> {
}
