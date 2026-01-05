package com.educandoweb.workshopmongo.resources.exception;

import com.educandoweb.workshopmongo.services.exception.ObjectNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler extends RuntimeException {

  @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> ObjectNotFound(ObjectNotFoundException e, HttpServletRequest request){
      HttpStatus status = HttpStatus.NOT_FOUND;
      StandardError  error = new StandardError(System.currentTimeMillis(), status.value(), "Não encontrado", e.getMessage(), request.getRequestURI());

      return ResponseEntity.status(status).body(error);
    }
}
