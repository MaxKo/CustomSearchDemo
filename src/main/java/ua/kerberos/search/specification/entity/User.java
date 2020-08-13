package ua.kerberos.search.specification.entity;

import lombok.Data;
import lombok.experimental.Accessors;

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
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;

    private boolean reviewer;
}