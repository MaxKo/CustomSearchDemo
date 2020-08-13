package ua.kerberos.search.specification.dto.search;

import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import ua.kerberos.search.specification.entity.enumerators.SystemRoles;
import ua.kerberos.search.specification.entity.User;
import ua.kerberos.search.specification.repository.jpa.filter.*;

/**
 * Created by Maksym Kovieshnikov on 13/08/2020
 */


@EqualsAndHashCode(callSuper = false)
@Data
@Accessors(chain = true)
public class UserSearchSpecificationRequest extends AbstractSearchSpecification<User> {

    @ApiParam(name = "name", value = "Filter Users by first, middle and last name combined with 'or' clause")
    @JpaCriteria(propertyName = "name", handlerClass = MultipleStringLikeFilter.class, searchAmongProperties = {"firstName", "middleName", "lastName"})
    private String name;

    @ApiParam(name = "firstName", value = "Filter Users by first name")
    private String firstName;

    @ApiParam(name = "middleName", value = "Filter Users by middle name")
    private String middleName;

    @ApiParam(name = "lastName", value = "Filter Users by last name")
    private String lastName;

    @ApiParam(name = "countryId", value = "Filter Users by Country. Please use <b>country.id</b> for sort criteria section")
    @JpaCriteria(propertyName = "country", handlerClass = RelatedPropertyEqualFilter.class, relatedEntityPropertyName = "id")
    private Long[] countryId;

    @ApiParam(name = "departmentId", value = "Filter Users by Department. Please use <b>department.id</b> for sort criteria section")
    @JpaCriteria(propertyName = "department", handlerClass = RelatedPropertyEqualFilter.class, relatedEntityPropertyName = "id")
    private Long[] departmentId;

    @ApiParam(name = "positionId", value = "Filter Users by Position. Please use <b>position.id</b> for sort criteria section")
    @JpaCriteria(propertyName = "position", handlerClass = RelatedPropertyEqualFilter.class, relatedEntityPropertyName = "id")
    private Long[] positionId;

    @ApiParam(name = "role", value = "Filter Users by Role")
    @JpaCriteria(propertyName = "roles", handlerClass = ManyToManyEqualFilter.class, destinationPropertyName = "role")
    private SystemRoles[] role;

    private Boolean active;
}
