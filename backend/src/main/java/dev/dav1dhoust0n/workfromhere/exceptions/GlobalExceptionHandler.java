package dev.dav1dhoust0n.workfromhere.exceptions;

import dev.dav1dhoust0n.workfromhere.spaces.exception.SpaceResourceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SpaceResourceException.class)
    public ResponseEntity<String> handleSpaceResourceException (SpaceResourceException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST );
    }
}
