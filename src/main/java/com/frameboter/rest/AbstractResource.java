package org.keycloak.quickstart;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

public class AbstractResource {

  private final String getUserName(@AuthenticationPrincipal Jwt jwt){
    return jwt.getClaimAsString("username");
  }
}
