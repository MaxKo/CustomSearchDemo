package ua.kerberos.search.specification.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import ua.kerberos.search.specification.entity.enumerators.UserStatuses;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by Maksym Kovieshnikov on 19/08/2020
 */

@Entity
@Data
@Table(name = "USER_STATUSES")
@Accessors(chain = true)
public class UserStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDateTime createdDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 25, nullable = false)
    private UserStatuses status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private SystemUser user;
}
