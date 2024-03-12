package com.frameboter.rest;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;

public class AbstractResource {

  protected final String getUserId(Jwt jwt){
    return jwt.getSubject();
  }

    // @AuthenticationPrincipal 
  protected final String getUserName(Jwt jwt){
    return jwt.getClaimAsString("preferred_username");
  }

  protected final List<String> getRoles(Jwt jwt){
    return jwt.getClaimAsStringList("roles");
  }
}
