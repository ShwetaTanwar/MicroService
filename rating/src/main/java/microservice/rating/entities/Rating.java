package microservice.rating.entities;


import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("User_ratings")
public class Rating {
    @Id
    private String ratingId;
    private String userId;
    private  String platformId;
    private  int rating;
    private  String feedback;
}
