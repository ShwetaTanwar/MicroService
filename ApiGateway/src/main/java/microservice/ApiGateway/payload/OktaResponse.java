package microservice.ApiGateway.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OktaResponse {
    private String userId;
    private String  accessToken;
    private Long expireAt;
    private Collection<String> authorities;
}
