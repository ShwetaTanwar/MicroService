package microservice.user.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import microservice.user.entity.Rating;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {


    @Id
    private String userId;
    @Column(name="name")
    @NotBlank(message = "Name Should not ne null")
    private String name;
    private  String about;
    @Email(message="Please Enetr the Vaild Email Address")
    private String email;
    private List<Rating> ratings=new ArrayList<>();

}
