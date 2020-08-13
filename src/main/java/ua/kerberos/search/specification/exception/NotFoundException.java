package ua.kerberos.search.specification.exception;

import lombok.NoArgsConstructor;

/**
 * Created by Maksym Kovieshnikov on 05/06/2020
 */

@NoArgsConstructor
public class NotFoundException extends RuntimeException{
    public NotFoundException(String message){
        super(message);
    }
}
