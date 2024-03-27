package io.javabrains.ratingsdataservice.models;

/**
 * (enter description here)
 * <p>
 *
 * @author JChaves
 * @author Copyright (c) 2024 MountainView Software, Corp.
 */
public class ErrorResponse {

  private String message;

  public ErrorResponse(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
