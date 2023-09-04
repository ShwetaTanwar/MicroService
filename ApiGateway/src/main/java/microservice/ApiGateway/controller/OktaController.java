package microservice.ApiGateway.controller;

import lombok.extern.slf4j.Slf4j;
import microservice.ApiGateway.payload.OktaResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@Slf4j
public class OktaController {
    @GetMapping("/login")
    public ResponseEntity<OktaResponse> login
            (
               @RegisteredOAuth2AuthorizedClient("okta")
               OAuth2AuthorizedClient client,
               @AuthenticationPrincipal OidcUser user,
              Model model
            )
    {
       log.info("user email id : {} ",user.getEmail());
       OktaResponse response = new OktaResponse();
       response.setUserId(user.getEmail());
       response.setAccessToken(client.getAccessToken().getTokenValue());
       response.setExpireAt(client.getAccessToken().getExpiresAt().getEpochSecond());

       List<String> authorities = user.getAuthorities().stream().map(grantedAuthority ->
       {
           return grantedAuthority.getAuthority();

       }).collect(Collectors.toList());

       response.setAuthorities(authorities);
       return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
