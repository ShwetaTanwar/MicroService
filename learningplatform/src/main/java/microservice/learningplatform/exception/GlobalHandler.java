package microservice.learningplatform.exception;
import microservice.learningplatform.payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.Date;



@RestControllerAdvice
public class GlobalHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex)
    {
        String message=ex.getMessage();
        ApiResponse response = ApiResponse.builder()
                .message(message)
                .success(true)
                .timestamp(new Date())
                .status(HttpStatus.NOT_FOUND)
                .build();

        return new ResponseEntity <ApiResponse>(response, HttpStatus.NOT_FOUND);
    }
}
