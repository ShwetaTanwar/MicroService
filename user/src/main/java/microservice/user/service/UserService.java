package microservice.user.service;
import microservice.user.dto.UserDto;
import microservice.user.payloads.ApiResponse;

import java.util.List;

public interface UserService {

    List<UserDto> getAllUsers();

    UserDto getUserById(String userId);

    UserDto createUser(UserDto userDTO);

    UserDto updateUser(String id, UserDto updatedUserDTO);

    //boolean deleteUser(String id);
    public ApiResponse deleteUser(String id);

}
