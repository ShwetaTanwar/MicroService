package microservice.user.dto;

import javax.persistence.Id;

public class PlatfromDto {
    @Id
    private  String id;
    private  String name;
    private String medium;
    private  String about;
}
