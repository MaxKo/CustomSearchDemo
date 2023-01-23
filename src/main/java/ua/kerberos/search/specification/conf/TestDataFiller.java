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
    private final RegionRepository regionRepository;

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

        initUser("John", "Fridrikh", "Doe", 18, 1l, Regions.BERLIN,  "john.doeh@email.com", 1l);
        initUser("Thomas", "", "Selvidge",18, 1l, Regions.CHERKASY,  "Thomas.Selvidge@email.com", 2l);
        initUser("Latosha", "", "Knick",28, 2l, Regions.LONDON,  "Latosha.Knick@email.com", 3l);
        initUser("Nakia", "Fridrikh", "Wroblewski",38, 3l,  Regions.MINSK, "Nakia.Wroblewski@email.com", 4l);
        initUser("Jerrica", "", "Iman",48, 1l, Regions.NEW_YORK,  "Jerrica.Iman@email.com", 5l);
        initUser("Kaila", "", "Musselman",58, 2l, Regions.WARSAW,  "Kaila.Musselman@email.com", 2l);
        initUser("Debrah", "Fridrikh", "Roark",68, 3l,  Regions.BERLIN, "Debrah.Roark@email.com", 3l);
        initUser("Tyrell", "", "Welter",19, 1l, Regions.CHERKASY,  "Tyrell.Welter@email.com", 4l);
        initUser("Gilberto", "", "Reith",25, 2l, Regions.LONDON,  "Gilberto.Reith@email.com", 5l);
        initUser("Reta", "", "Haskell",44, 3l, Regions.NEW_YORK,  "Reta.Haskell@email.com", 1l);
        initUser("Deirdre", "Fridrikh", "Innis",11, 1l,  Regions.LONDON, "Deirdre.Innis@email.com", 2l);
        initUser("Philomena", "", "Benn",72, 2l, Regions.CHERKASY,  "Philomena.Benn@email.com", 3l);
        initUser("Kanisha", "", "Layman",33, 3l, Regions.CHERKASY, "Kanisha.Layman@email.com", 4l);
        initUser("Sarina", "Fridrikh", "Huffines",44, 1l, Regions.WARSAW, "Sarina.Huffines@email.com", 5l);
        initUser("Trista", "", "Maire",55, 2l, Regions.NEW_YORK,  "Trista.Maire@email.com", 1l);
        initUser("Susann", "el", "Urbanski",66, 1l, Regions.CHERKASY,  "Susann.Urbanski@email.com", 1l);
        initUser("Gerry", "", "Burnes",42, 3l, Regions.NEW_YORK,  "Gerry.Burnes@email.com", 2l);
        initUser("Lucile", "", "Jent",46, 1l, Regions.NEW_YORK,  "Lucile.Jent@email.com", 3l);
        initUser("Annette", "", "Liefer",36, 2l, Regions.NEW_YORK,  "Annette.Liefer@email.com", 4l);
        initUser("Rosario", "", "Tinkler",37, 3l, Regions.CHERKASY,  "Rosario.Tinkler@email.com", 5l);
        var u = initUser("Latrina", "", "Grossman",29, 1l, Regions.CHERKASY,  "Latrina.Grossman@email.com", 1l);
        addStatus(u, UserStatuses.NOT_ACTIVE);


    }

    private User initUser(String first, String middle, String last, int age, long positionId, Regions region, String email, long roleId) {
        User user = new User();
        user.setRegion(regionRepository.findByName(region.name()));
        user.setPosition(positionRepository.findByName(Positions.values()[(int) positionId - 1].name()));
        user.setFirstName(first);
        user.setMiddleName(middle);
        user.setLastName(last);
        user.setEmail(email);
        user.setBirthDate(LocalDate.now().minusYears(age));
        user.setAge(age);
        user.setRoles(singletonList(new UserRole().setId(roleId)));

        var result = userRepository.save(user);
        users.add(result);

        //user.setStatus(addStatus(user, UserStatuses.ACTIVE).getStatus());

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
                        .setCountry(new Country().setId(countryRepository.getCountryByName(region.getCountry().name()).getId())));
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
