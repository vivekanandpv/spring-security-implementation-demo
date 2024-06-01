package com.vivekanandpv.springsecurityimplementationdemo.apis;

import com.vivekanandpv.springsecurityimplementationdemo.exceptions.GeneralAuthenticationException;
import com.vivekanandpv.springsecurityimplementationdemo.exceptions.LoginFailedException;
import com.vivekanandpv.springsecurityimplementationdemo.exceptions.RecordNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class AppExceptionHandlerAdvice {
    private final Logger logger;

    public AppExceptionHandlerAdvice() {
        this.logger = LoggerFactory.getLogger(this.getClass());
    }

    @ExceptionHandler(GeneralAuthenticationException.class)
    public ResponseEntity<Map<String, String>> handleGeneralAuthenticationException(GeneralAuthenticationException exception) {
        logger.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Authentication failed"));
    }

    @ExceptionHandler(LoginFailedException.class)
    public ResponseEntity<Map<String, String>> handleLoginFailedException(LoginFailedException exception) {
        logger.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Could not process login"));
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleRecordNotFoundException(RecordNotFoundException exception) {
        logger.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", exception.getMessage()));
    }
}
