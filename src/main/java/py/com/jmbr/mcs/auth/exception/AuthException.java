package py.com.jmbr.mcs.auth.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import py.com.jmbr.java.commons.exceptions.ExceptionResponse;
import py.com.jmbr.java.commons.exceptions.JMBRException;

@ControllerAdvice
public class AuthException {
    @ExceptionHandler( value = {JMBRException.class})
    public ResponseEntity<ExceptionResponse> handleException(JMBRException e){
        return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(e.getMessage(),e.getType()),e.getCode());
    }
}
