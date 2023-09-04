package microservice.learningplatform.entities;

//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "platforms")
public class Platform {
    @Id
    private  String id;
    private  String name;
    private String medium;
    private  String about;
}
