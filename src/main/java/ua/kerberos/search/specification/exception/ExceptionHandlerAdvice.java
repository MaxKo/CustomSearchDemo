package ua.kerberos.search.specification.exception;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import java.time.LocalDateTime;

import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * Created by Maksym Kovieshnikov on 13/08/2020
 */

@Slf4j
@RestControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

    private static final String EXCEPTION_LOG = "{} has occurred during processing request {}";


    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolation(BadRequestException ex, HttpServletRequest request) {
        return errorResponse(BAD_REQUEST, ex.getMessage());
    }


    private ResponseEntity<ErrorResponse> errorResponse(HttpStatus status, String message) {
        return new ResponseEntity<>(new ErrorResponse(status, message), status);
    }

    @Getter
    private static class ErrorResponse {
        public String timestamp;
        private String status;
        private int error;
        private String message;

        ErrorResponse(HttpStatus httpStatus, String message) {
            this.timestamp = LocalDateTime.now().format(ISO_DATE_TIME);
            this.error = httpStatus.value();
            this.status = httpStatus.getReasonPhrase();
            this.message = message;
        }
    }
}