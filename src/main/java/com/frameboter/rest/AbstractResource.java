package com.frameboter.rest;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
public class AbstractResource {

  protected final String getUserId(Jwt jwt){
    return jwt.getSubject();
  }

  protected final String getUserName(Jwt jwt){
    return jwt.getClaimAsString("preferred_username");
  }

  protected final List<String> getRoles(Jwt jwt){
    return jwt.getClaimAsStringList("roles");
  }
}
