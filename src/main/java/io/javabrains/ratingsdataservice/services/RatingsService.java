package io.javabrains.ratingsdataservice.services;

import io.javabrains.ratingsdataservice.models.Rating;
import io.javabrains.ratingsdataservice.repository.RatingsRepository;
import org.springframework.stereotype.Service;

@Service
public class RatingsService {

    private final RatingsRepository ratingsRepository;

    public RatingsService(RatingsRepository ratingsRepository) {
        this.ratingsRepository = ratingsRepository;
    }

    public Rating getRating(String movieId) {
        return ratingsRepository.getRating(movieId);
    }

    public int getRatingValue(String movieId) {
        if ("movieId".equals(movieId)) {
            throw new IllegalArgumentException("sea mongolo");
        } else if ("movie-1".equals(movieId)) {
            movieId = movieId + "123";
            // "movie-1" + "123"
            // "movie-1123"
        }
        return ratingsRepository.getRating(movieId).getRating();
    }

    public int sum(int value1, int value2) {

        return value1 + value2;
    }

    public int division(int value1, int value2) {

        if (value2 == 0) {
            throw new IllegalArgumentException("no se puede dividir por 0");
        }

        return value1 / value2;
    }

    public void saveNewRating(Rating rating) {
        if (rating.getRating() < 1 || rating.getRating() > 5) {
            throw new IllegalArgumentException("rating tiene que ser de 1 a 5");
        }
        Rating newRating = new Rating();
        newRating.setValid(true);
        ratingsRepository.save(newRating);
    }
}
