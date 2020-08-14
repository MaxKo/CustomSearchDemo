package ua.kerberos.search.specification.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maksym Kovieshnikov on 13/08/2020
 */


@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "SYSTEM_USERS")
@Entity
@Accessors(chain = true)
public class SystemUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String middleName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private int age;

    private LocalDate birthDate;

    private boolean active;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "USER_ROLES",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    @Fetch(FetchMode.SUBSELECT)
    private List<UserRole> roles = new ArrayList<>();


}
