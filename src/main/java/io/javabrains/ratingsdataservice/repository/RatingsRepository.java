package io.javabrains.ratingsdataservice.repository;

import io.javabrains.ratingsdataservice.models.Rating;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingsRepository {
    Rating getRating(String movieId);
    void save(Rating rating);
}
