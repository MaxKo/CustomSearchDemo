package ua.kerberos.search.specification.exception;

import lombok.NoArgsConstructor;

/**
 * Created by Maksym Kovieshnikov on 09/06/2020
 */
@NoArgsConstructor
public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
