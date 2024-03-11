package org.keycloak.quickstart;

import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

public class AbstractResource {

  private final String getUserName(@AuthenticationPrincipal Jwt jwt){
    return auth.getName();
  }

  private final String getRoles(@AuthenticationPrincipal Jwt jwt){
    return auth.getAuthorities();
  }
}
