package microservice.rating.controller;

import microservice.rating.entities.Rating;
import microservice.rating.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {
    @Autowired
    private RatingService ratingService;
    @PostMapping
    public ResponseEntity<Rating> create(@RequestBody Rating rating)
    {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ratingService.create(rating));
    }
    @GetMapping
    public ResponseEntity<List<Rating>>getRatings()
    {
        return ResponseEntity.ok(ratingService.getRating());
    }
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>>getRatingByUserId(@PathVariable String userId)
    {
        return ResponseEntity.ok(ratingService.getRatingByUserId(userId));
    }

    @GetMapping("/platform/{platforId}")
    public ResponseEntity<List<Rating>>getRatingByHotelId(@PathVariable String platforId)
    {
        return ResponseEntity.ok(ratingService.getRatingByPlatformId(platforId));
    }

}
