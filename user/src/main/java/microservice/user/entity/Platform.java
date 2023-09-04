package microservice.user.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Platform {

        @Id
        private  String id;
        private  String name;
        private String medium;
        private  String about;
    }

