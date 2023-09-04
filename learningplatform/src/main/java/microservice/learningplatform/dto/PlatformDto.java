package microservice.learningplatform.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlatformDto {

        @Id
        private  String id;
        private  String name;
        private String medium;
        private  String about;
    }

