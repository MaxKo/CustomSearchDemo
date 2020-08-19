package ua.kerberos.search.specification.conf;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.kerberos.search.specification.entity.*;
import ua.kerberos.search.specification.entity.enumerators.*;
import ua.kerberos.search.specification.repository.*;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;

/**
 * Created by Maksym Kovieshnikov on 13/08/2020
 */

@Component
@RequiredArgsConstructor
@Getter
public class TestDataFiller {

    private final UserRoleRepository userRoleRepository;
    private final DepartmentRepository departmentRepository;
    private final CountryRepository countryRepository;
    private final PositionRepository positionRepository;
    private final UserRepository userRepository;
    private final UserStatusRepository userStatusRepository;

    List<UserRole> roles;
    List<Region> regions;
    List<Country> countries;
    List<Position> positions;

    List<User> users = new ArrayList<>();


    @PostConstruct
    public void init() {
        initRoles();
        initCountries();
        initRegions();
        initPositions();

        initUsers();

    }


    private void initUsers() {

        initUser("John", "Fridrikh", "Doe", 18, 1l, 1l,  "john.doeh@email.com", 1l);
        initUser("Thomas", "", "Selvidge",18, 1l, 1l,  "Thomas.Selvidge@email.com", 2l);
        initUser("Latosha", "", "Knick",28, 2l, 2l,  "Latosha.Knick@email.com", 3l);
        initUser("Nakia", "Fridrikh", "Wroblewski",38, 3l,  3l, "Nakia.Wroblewski@email.com", 4l);
        initUser("Jerrica", "", "Iman",48, 1l, 4l,  "Jerrica.Iman@email.com", 5l);
        initUser("Kaila", "", "Musselman",58, 2l, 5l,  "Kaila.Musselman@email.com", 2l);
        initUser("Debrah", "Fridrikh", "Roark",68, 3l,  1l, "Debrah.Roark@email.com", 3l);
        initUser("Tyrell", "", "Welter",19, 1l, 1l,  "Tyrell.Welter@email.com", 4l);
        initUser("Gilberto", "", "Reith",25, 2l, 2l,  "Gilberto.Reith@email.com", 5l);
        initUser("Reta", "", "Haskell",44, 3l, 3l,  "Reta.Haskell@email.com", 1l);
        initUser("Deirdre", "Fridrikh", "Innis",11, 1l,  5l, "Deirdre.Innis@email.com", 2l);
        initUser("Philomena", "", "Benn",72, 2l, 5l,  "Philomena.Benn@email.com", 3l);
        initUser("Kanisha", "", "Layman",33, 3l, 6l, "Kanisha.Layman@email.com", 4l);
        initUser("Sarina", "Fridrikh", "Huffines",44, 1l, 1l, "Sarina.Huffines@email.com", 5l);
        initUser("Trista", "", "Maire",55, 2l, 2l,  "Trista.Maire@email.com", 1l);
        initUser("Susann", "el", "Urbanski",66, 1l, 3l,  "Susann.Urbanski@email.com", 1l);
        initUser("Gerry", "", "Burnes",42, 3l, 4l,  "Gerry.Burnes@email.com", 2l);
        initUser("Lucile", "", "Jent",46, 1l, 5l,  "Lucile.Jent@email.com", 3l);
        initUser("Annette", "", "Liefer",36, 2l, 6l,  "Annette.Liefer@email.com", 4l);
        initUser("Rosario", "", "Tinkler",37, 3l, 1l,  "Rosario.Tinkler@email.com", 5l);
        var u = initUser("Latrina", "", "Grossman",29, 1l, 1l,  "Latrina.Grossman@email.com", 1l);
        addStatus(u, UserStatuses.NOT_ACTIVE);


    }

    private User initUser(String first, String middle, String last, int age, long positionId, long regionId, String email, long roleId) {
        User user = new User();
        user.setRegion(new Region().setId(regionId));
        user.setPosition(new Position().setId(positionId));
        user.setFirstName(first);
        user.setMiddleName(middle);
        user.setLastName(last);
        user.setEmail(email);
        user.setBirthDate(LocalDate.now().minusYears(age));
        user.setAge(age);
        user.setRoles(singletonList(new UserRole().setId(roleId)));

        var result = userRepository.save(user);
        users.add(result);

        user.setStatus(addStatus(user, UserStatuses.ACTIVE).getStatus());

        return result;
    }

    private UserStatus addStatus(User user, UserStatuses userStatus) {
        var status = new UserStatus()
                .setStatus(userStatus)
                .setUser(user)
                .setCreatedDate(LocalDateTime.now());

        return userStatusRepository.save(status);

    }

    private void initCountries() {
        countries = Stream
                .of(Countries.values())
                .map(this::saveCountry)
                .collect(toList());
    }

    private Country saveCountry(Countries country) {
        return countryRepository.save(new Country().setName(country.name()));
    }


    private void initRegions() {
        regions = Stream
                .of(Regions.values())
                .map(this::saveRegion)
                .collect(toList());
    }

    private Region saveRegion(Regions region) {
        return departmentRepository
                .save(new Region()
                        .setName(region.name())
                        .setCountry(new Country().setId(region.getCountryId())));
    }

    private void initRoles() {
        roles = Stream
                .of(SystemRoles.values())
                .map(this::saveRole)
                .collect(toList());
    }

    private UserRole saveRole(SystemRoles systemRole) {
        return userRoleRepository.save(new UserRole().setRole(systemRole));
    }


    private void initPositions() {
        positions = Stream
                .of(Positions.values())
                .map(this::savePosition)
                .collect(toList());
    }

    private Position savePosition(Positions position) {
        return positionRepository.save(new Position().setName(position.name()));
    }

}
