package ua.kerberos.search.specification.dto;

import lombok.Data;
import ua.kerberos.search.specification.entity.enumerators.SystemRoles;

import javax.validation.constraints.*;
import java.util.List;

/**
 * Created by Maksym Kovieshnikov on 13/08/2020
 */


@Data
public class CreateUpdateUserRequest {

    @Size(min=2, max=20)
    @NotEmpty
    private String employeeNumber;

    @Size(min=1, max=30)
    @NotEmpty
    private String firstName;

    private String middleName;

    @Size(min=1, max=30)
    @NotEmpty
    private String lastName;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Pattern(regexp="^\\d{12}$")
    private String phoneNumber;
//
//    @NotNull
//    private CountryDto country;
//
//    @NotNull
//    private DepartmentDto department;
//
//    @NotNull
//    private PositionDto position;

    private boolean active;

    private boolean reviewer;

    private List<SystemRoles> roles;
}
