package io.javabrains.ratingsdataservice.exception;

/**
 * (enter description here)
 * <p>
 *
 * @author JChaves
 * @author Copyright (c) 2024 MountainView Software, Corp.
 */
public class ResourceNotFoundException extends RuntimeException {
  public ResourceNotFoundException(String message) {
    super(message);
  }
}
