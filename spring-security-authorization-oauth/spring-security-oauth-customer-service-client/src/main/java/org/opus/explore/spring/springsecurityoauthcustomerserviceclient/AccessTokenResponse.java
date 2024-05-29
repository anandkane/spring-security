package org.opus.explore.spring.springsecurityoauthcustomerserviceclient;

import lombok.Data;

@Data
public class AccessTokenResponse {
    private String accessToken;
    private int expiresIn;
    private int refreshExpiresIn;
    private String refreshToken;
    private String tokenType;
    private String idToken;
    private int notBeforePolicy;
    private String sessionState;
    private String scope;
}
