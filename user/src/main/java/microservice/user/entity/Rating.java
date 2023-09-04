package microservice.user.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rating {
    private String ratingId;
    private String userId;
    private  String platformId;
    private  int rating;
    private  String feedback;
    private Platform platform;
}
