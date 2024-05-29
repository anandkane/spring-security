package org.opus.explore.spring.springsecurityoauthcustomerserviceclient;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.*;

import java.nio.charset.StandardCharsets;
import java.util.*;

@Component
public class MainClass {
    RestTemplate restTemplate;

    @Autowired
    public MainClass(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void getToken() throws JsonProcessingException {
        String clientSecret = "6oILN1WPLj4SlkEOpObevVDdyDLZGmOR";
        String clientId = "simple-client1";
        PasswordGrantRequest request = new PasswordGrantRequest("anandkane", "password", "password", clientId,
                                                                clientSecret, "openid profile");
        String tokenUrl = "http://localhost:10101/realms/opussecurity/protocol/openid-connect/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString()); //Optional in case server sends back JSON data
        headers.add("Authorization", createAuthHeaderString(clientId, clientSecret));

        // Create a map of the key/value pairs that we want to supply in the body of the request
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
//        map.add("response_type","token");
        map.add("client_id", clientId);
        map.add("username", "anandkane");
        map.add("password", "password");
        map.add("scope", "openid profile");
        map.add("grant_type", "password");

        // Create an HttpEntity object, wrapping the body and headers of the request
//        HttpEntity<PasswordGrantRequest> entity = new HttpEntity<>(request, headers);
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

        ResponseEntity<AccessTokenResponse> response = restTemplate.exchange(tokenUrl, HttpMethod.POST, entity,
                                                                             AccessTokenResponse.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            AccessTokenResponse accessTokenResponse = response.getBody();
            System.out.println(accessTokenResponse.getAccessToken());
        } else {
            System.out.println("Error: " + response.getStatusCode());
        }
    }

    private String createAuthHeaderString(String username, String password) {
        String auth = username + ":" + password;
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.US_ASCII));
        String authHeader = "Basic " + new String(encodedAuth);
        return authHeader;
    }
}
