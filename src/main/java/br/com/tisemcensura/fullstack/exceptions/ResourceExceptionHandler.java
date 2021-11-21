package br.com.tisemcensura.fullstack.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {
    @ExceptionHandler(ResourceFindByIdNotFoundException.class)
    public ResponseEntity<StandarError> resourceNotFound(ResourceFindByIdNotFoundException e, HttpServletRequest request){
        String error = "Excetion Not Found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        String message = e.getMessage();
        StandarError err = new StandarError(Instant.now(), status.value(), error, message, request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
