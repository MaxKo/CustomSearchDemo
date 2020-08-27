package ua.kerberos.search.specification.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Formula;
import ua.kerberos.search.specification.entity.enumerators.UserStatuses;

import javax.persistence.*;


/**
 * Created by Maksym Kovieshnikov on 13/08/2020
 */
@Entity
@Table(name = "USERS")
@Data
@Accessors(chain = true)
public class User extends SystemUser {

    private String employeeNumber;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;

    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;

    private boolean reviewer;

//    @Formula("(SELECT us.status FROM USER_STATUSES us WHERE us.user_id = id ORDER BY us.created_date DESC LIMIT 1)")
//    @Enumerated(EnumType.STRING)
//    private UserStatuses status;

}