package ua.kerberos.search.specification.dto.search;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import ua.kerberos.search.specification.entity.User;
import ua.kerberos.search.specification.entity.enumerators.SystemRoles;
import ua.kerberos.search.specification.entity.enumerators.UserStatuses;
import ua.kerberos.search.specification.repository.jpa.filter.*;

/**
 * Created by Maksym Kovieshnikov on 13/08/2020
 */


@EqualsAndHashCode(callSuper = false)
@Data
@Accessors(chain = true)
public class UserSearchSpecificationRequest extends AbstractSearchSpecification<User> {

	//    @ApiParam(name = "name", value = "Filter Users by first, middle and last name combined with 'or' clause")
	@JpaCriteria(propertyName = "name", handlerClass = MultipleStringLikeFilter.class, searchAmongProperties = {"firstName", "middleName", "lastName"})
	private String name;

	//    @ApiParam(name = "firstName", value = "Filter Users by first name")
	private String firstName;

	//    @ApiParam(name = "middleName", value = "Filter Users by middle name")
	private String middleName;

	//    @ApiParam(name = "lastName", value = "Filter Users by last name")
	private String lastName;

	//    @ApiParam(name = "countryId", value = "Filter Users by Country. Please use <b>region.country.id</b> for sort criteria section")
	@JpaCriteria(propertyName = "region", handlerClass = RelatedPropertyChainEqualFilter.class, relatedEntityPropertyName = "country", destinationPropertyName = "id")
	private Long[] countryId;

//    @ApiParam(name = "countryName", value = "Filter Users by Country name. Please use <b>region.country.id</b> for sort criteria section")
//    @JpaCriteria(propertyName = "region", handlerClass = RelatedPropertyChainLikeFilter.class, relatedEntityPropertyName = "country", destinationPropertyName = "name")
//    private String countryName;

	//    @ApiParam(name = "countryName", value = "Filter Users by Country name. Please use <b>region.country.id</b> for sort criteria section")
	@JpaCriteria(propertyName = "region", handlerClass = RelatedPropertyMultipleJoinLikeFilter.class, propertyChain = {"region", "country"}, destinationPropertyName = "name")
	private String countryName;

	//    @ApiParam(name = "regionId", value = "Filter Users by Department. Please use <b>department.id</b> for sort criteria section")
	@JpaCriteria(propertyName = "region", handlerClass = RelatedPropertyEqualFilter.class, relatedEntityPropertyName = "id")
	private Long[] regionId;

	//    @ApiParam(name = "regionName", value = "Filter Users by Department. Please use <b>department.id</b> for sort criteria section")
	@JpaCriteria(propertyName = "region", handlerClass = RelatedPropertyJoinLikeFilter.class, destinationPropertyName = "name")
	private String regionName;

//    @ApiParam(name = "regionName", value = "Filter Users by Department. Please use <b>department.id</b> for sort criteria section")
//    @JpaCriteria(propertyName = "region", handlerClass = RelatedPropertyMultipleJoinLikeFilter.class, destinationPropertyName = "name", propertyChain = "region")
//    private String regionName;


	//    @ApiParam(name = "positionId", value = "Filter Users by Position. Please use <b>position.id</b> for sort criteria section")
	@JpaCriteria(propertyName = "position", handlerClass = RelatedPropertyEqualFilter.class, relatedEntityPropertyName = "id")
	private Long[] positionId;

	//    @ApiParam(name = "role", value = "Filter Users by Role")
	@JpaCriteria(propertyName = "roles", handlerClass = ManyToManyEqualFilter.class, destinationPropertyName = "role")
	private SystemRoles[] role;

	private Boolean active;


	//    @ApiParam(name = "status", value = "Filter Incidents by Status")
	@JpaCriteria(propertyName = "status", handlerClass = MaxElementOfRelatedPropertyEqualFilter.class, relatedEntityPropertyName = "user", destinationPropertyName = "status", searchAmongProperties = {"ua.kerberos.search.specification.entity.UserStatus", "createdDate"})
	private UserStatuses[] status;


}
