package microservice.user.service.impl;
import lombok.extern.slf4j.Slf4j;
import microservice.user.dto.UserDto;
import microservice.user.entity.Platform;
import microservice.user.entity.Rating;
import microservice.user.entity.User;
import microservice.user.exception.ResourceNotFoundException;
import microservice.user.external.ex_services.PlatformService;
import microservice.user.payloads.ApiResponse;
import microservice.user.repo.UserRepo;
import microservice.user.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PlatformService platformService;


    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public UserDto getUserById(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User is not found on server " + id)
        );

        Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(),
                Rating[].class
        );
        List<Rating> ratings = Arrays.asList(ratingsOfUser);
        List<Rating> ratingList = ratings.stream().map(rating -> {
            Platform[] platforms = platformService.getPlatform(rating.getPlatformId());
            if (platforms != null && platforms.length > 0) {
                rating.setPlatform(platforms[0]);
            }
            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingList);
        return convertToDTO(user);
    }








//    List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();
//    List<Rating> ratingList = ratings.stream().map(rating ->
//    { //http://localhost:8082/platforms/search/Simplilearn
//        ResponseEntity<Platform> platformRes=restTemplate.getForEntity("http://localhost:8082/platforms/find/Udemy_id1", Platform.class);
//        Platform platform= platformRes.getBody();
//        log.info("Status code "+platformRes.getStatusCode());
//        rating.setPlatform(platform);
//        return rating;
//    }).collect(Collectors.toList());
//    user.setRatings(ratingList);
//    return convertToDTO(user);
//}
//User user =this.userRepo.findById(userId)
//            .orElseThrow(() -> new ResourceNotFoundException("User","  Id  " , userId));
//		return this.userToDto(user);

    @Override
    public UserDto createUser(UserDto userDTO) {
        User user = DtoToEntity(userDTO);
        String randomUserId= UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return convertToDTO(userRepository.save(user));
    }


    @Override
    public UserDto updateUser(String id, UserDto updatedUserDTO) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    User updatedUser = DtoToEntity(updatedUserDTO);
                    updatedUser.setUserId(existingUser.getUserId());
                    return convertToDTO(userRepository.save(updatedUser));
                })
                .orElseThrow(()-> new ResourceNotFoundException("User not found"));
    }

   // @Override
  //  public boolean deleteUser(String id) {
//        User user = userRepository.findById(id).orElse(null);
//        if (user != null) {
//            userRepository.delete(user);
//            return true;
//        }
//        return false;
   @Override
        public ApiResponse deleteUser(String id) {
            User user = userRepository.findById(id).orElse(null);
            if (user != null) {
                userRepository.delete(user);
                return ApiResponse.builder()
                        .message("User with ID " + id + " has been deleted.")
                        .success(true)
                        .timestamp(new Date())
                        .status(HttpStatus.OK)
                        .build();
            } else {
                return ApiResponse.builder()
                        .message("User with ID " + id + " not found. No action taken.")
                        .success(false)
                        .timestamp(new Date())
                        .status(HttpStatus.NOT_FOUND)
                        .build();
            }
        }



    private UserDto convertToDTO(User user) {
        UserDto userDTO = new UserDto();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }

    private User DtoToEntity(UserDto userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        return user;
    }

}