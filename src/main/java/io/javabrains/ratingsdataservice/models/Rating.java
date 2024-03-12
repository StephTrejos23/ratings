package io.javabrains.ratingsdataservice.models;

import java.util.Objects;

public class Rating {
    private String movieId;
    private int rating;
    private boolean valid;

    public Rating() {
    }

    public Rating(String movieId, int rating) {
        this.movieId = movieId;
        this.rating = rating;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating1 = (Rating) o;
        return rating == rating1.rating && valid == rating1.valid && Objects.equals(movieId, rating1.movieId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, rating, valid);
    }
}
