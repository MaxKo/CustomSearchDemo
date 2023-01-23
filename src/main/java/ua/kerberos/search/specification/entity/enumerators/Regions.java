package ua.kerberos.search.specification.entity.enumerators;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by Maksym Kovieshnikov on 13/08/2020
 */
@RequiredArgsConstructor
@Getter
public enum Regions {
    BERLIN(Countries.GERMANY),
    CHERKASY(Countries.UKRAINE),
    MINSK(Countries.BELARUS),
    NEW_YORK(Countries.US),
    LONDON(Countries.UK),
    WARSAW( Countries.POLAND);

    private final Countries country;
}
