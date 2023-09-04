package microservice.rating.service;

import microservice.rating.entities.Rating;
import org.springframework.stereotype.Service;

import java.util.List;


public interface RatingService {
    Rating create(Rating rating);
    List<Rating> getRating();
    List<Rating> getRatingByUserId(String userId);
    List<Rating> getRatingByPlatformId(String platformId);
}
