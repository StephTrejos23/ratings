package io.javabrains.ratingsdataservice.resources;

import io.javabrains.ratingsdataservice.models.Rating;
import io.javabrains.ratingsdataservice.services.RatingsService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsResource {

    private final RatingsService ratingsService;

    public RatingsResource(RatingsService ratingsService) {
        this.ratingsService = ratingsService;
    }

    @RequestMapping("/{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId) {



        return ratingsService.getRating(movieId);
//        return new Rating(movieId, 4);
    }
}
