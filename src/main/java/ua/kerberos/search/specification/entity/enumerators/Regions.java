package ua.kerberos.search.specification.entity.enumerators;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by Maksym Kovieshnikov on 13/08/2020
 */
@RequiredArgsConstructor
@Getter
public enum Regions {
    MOSCOW(3),
    CHERKASY(1),
    MINSK(2),
    NEW_YORK(6),
    LONDON(5),
    WARSAW(4);

    private final long countryId;
}
