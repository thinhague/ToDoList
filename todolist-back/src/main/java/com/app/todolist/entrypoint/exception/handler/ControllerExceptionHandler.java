package com.app.todolist.entrypoint.exception.handler;

import com.app.todolist.entrypoint.exception.handler.dto.ErrorDTO;
import com.app.todolist.entrypoint.exception.handler.dto.MethodArgumentNotValidDTO;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.persistence.EntityNotFoundException;
import java.time.ZonedDateTime;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity ResourceBadRequestException(
            MethodArgumentNotValidException ex
    ) {
        MethodArgumentNotValidDTO errorDTO = new MethodArgumentNotValidDTO(
                ZonedDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                ex.getFieldErrors()
        );
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorDTO);
    }
    @ExceptionHandler({ EntityNotFoundException.class, NoSuchElementException.class })
    public ResponseEntity ResourceNotFoundException(
            Exception ex
    ){
        ErrorDTO errorDTO = new ErrorDTO(
                ZonedDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage()
        );
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorDTO);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity ResourceEmptyResultDataAccessException(
            EmptyResultDataAccessException ex
    ){
        return ResponseEntity
                .noContent().build();
    }
}
