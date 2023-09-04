package microservice.user.controller;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import microservice.user.dto.UserDto;
import microservice.user.payloads.ApiResponse;
import microservice.user.repo.UserRepo;
import microservice.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserRepo userRepo;
    @Autowired
    UserService userService;

    @GetMapping("/alluser")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }
    int retry_count=1;
    @GetMapping("/{id}")
    //  @CircuitBreaker(name = "ratingPlatformCircuitBreaker", fallbackMethod = "ratingPlatformFallback")
    @Retry(name = "rating_platformService" ,fallbackMethod = "ratingPlatformFallback")
    //@RateLimiter(name = "userRateLimiter",fallbackMethod = "ratingPlatformFallback")
    public ResponseEntity<UserDto> getUserById(@PathVariable String id)
    {
        log.info("Retry Count: {}",retry_count);
        retry_count++;
        return ResponseEntity.ok(this.userService.getUserById(id));
//        UserDto user = userService.getUserById(id);
//        if (user == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(user);
    }

    public ResponseEntity<UserDto>ratingPlatformFallback(String id,Exception ex)
    {
 //      log.info("fallback method is excecuted as service is down", ex.getMessage());
                UserDto userDto = UserDto.builder().name("dummy")
               .about("This response is coming from fallback method as service is down")
               .userId("dymmy_id123").build();
       return new ResponseEntity<>(userDto,HttpStatus.OK);
    }


    @PostMapping("/insert")
    public ResponseEntity<UserDto> createUser( @RequestBody UserDto userDTO) {
        UserDto savedUser = userService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable String id,  @RequestBody UserDto updatedUserDTO) {
        UserDto updatedUser = userService.updateUser(id, updatedUserDTO);
        if (updatedUser == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedUser);
    }

//    @DeleteMapping("delete/{id}")
//    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
//        boolean deleted = userService.deleteUser(id);
//        if (deleted) {
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.notFound().build();
//    }
@DeleteMapping("delete/{id}")
public ResponseEntity<ApiResponse> deleteUser(@PathVariable String id) {
    ApiResponse response = userService.deleteUser(id);

    if (response.isSuccess()) {
        return ResponseEntity.noContent().build();
    } else {
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}


}



