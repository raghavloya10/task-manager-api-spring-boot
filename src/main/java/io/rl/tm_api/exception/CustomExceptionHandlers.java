package io.rl.tm_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandlers {
    
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(EntityNotFoundException exc) {

        ErrorResponse error = new ErrorResponse(
											HttpStatus.NOT_FOUND.value(),
											exc.getMessage(),
											System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

	@ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(BadCredentialsException exc) {

        ErrorResponse error = new ErrorResponse(
											HttpStatus.FORBIDDEN.value(),
											exc.getMessage(),
											System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(Exception exc) {
		
		ErrorResponse error = new ErrorResponse(
											HttpStatus.BAD_REQUEST.value(),
											exc.getMessage(),
											System.currentTimeMillis());
				
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

}