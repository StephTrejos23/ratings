package io.javabrains.ratingsdataservice.exception;

import io.javabrains.ratingsdataservice.models.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * (enter description here)
 * <p>
 *
 * @author JChaves
 * @author Copyright (c) 2024 MountainView Software, Corp.
 */
@RestControllerAdvice
public class ControllerAdvice {

  @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ErrorResponse> handleIllegalArgumentException(
      final IllegalArgumentException illegalArgumentException) {
    return new ResponseEntity<>(
        new ErrorResponse("Invalid client exception: " + illegalArgumentException.getMessage()),
        HttpStatus.BAD_REQUEST);
  }

  @ResponseStatus(HttpStatus.NOT_FOUND) // 404
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleResourceNotFoundException(
      final ResourceNotFoundException resourceNotFoundException) {
    return new ResponseEntity<>(
        new ErrorResponse("Resource not found exception: " + resourceNotFoundException.getMessage()),
        HttpStatus.NOT_FOUND);
  }
}