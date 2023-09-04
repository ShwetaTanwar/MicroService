package microservice.rating.impl;

import microservice.rating.Repo.RatingRepository;
import microservice.rating.entities.Rating;
import microservice.rating.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RatingServiceImpl implements RatingService {
    @Autowired
    private RatingRepository ratingRepository;
    @Override
    public Rating create(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getRating() {
        return ratingRepository.findAll();
    }

    @Override
    public List<Rating> getRatingByUserId(String userId) {
        return ratingRepository.findByUserId(userId);

    }

    @Override
    public List<Rating> getRatingByPlatformId(String platformId) {
        return ratingRepository.findByPlatformId(platformId);
    }
}
