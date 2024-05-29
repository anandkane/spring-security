package org.opus.explore.spring.springsecurityoauthcustomerserviceclient;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class OAuth2PasswordGrantExample {

    public static void main(String[] args) {
        String username = "anandkane";
        String password = "your_password";
        String clientId = "your_client_id";
        String clientSecret = "your_client_secret";
        String tokenUrl = "https://example.com/oauth/token";

        // Prepare request data
        PasswordGrantRequest request = new PasswordGrantRequest(username, password, "password", clientId, clientSecret,
                                                                "openid profile");

        // Prepare headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Prepare HTTP entity
        HttpEntity<PasswordGrantRequest> httpEntity = new HttpEntity<>(request, headers);

        // Create RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // Make POST request to get access token
        ResponseEntity<AccessTokenResponse> response = restTemplate.postForEntity(tokenUrl, httpEntity,
                                                                                  AccessTokenResponse.class);

        // Handle response
        if (response.getStatusCode().is2xxSuccessful()) {
            AccessTokenResponse accessTokenResponse = response.getBody();
            System.out.println("Access Token: " + accessTokenResponse.getAccessToken());
            System.out.println("Expires In: " + accessTokenResponse.getExpiresIn());
            // Handle other fields as needed
        } else {
            System.out.println("Error: " + response.getStatusCode());
        }
    }
}
