package ua.kerberos.search.specification.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import ua.kerberos.search.specification.entity.enumerators.SystemRoles;

import jakarta.persistence.*;

/**
 * Created by Maksym Kovieshnikov on 13/08/2020
 */
@Entity
@Table(name = "ROLES")
@Data
@Accessors(chain = true)
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private SystemRoles role;
}
