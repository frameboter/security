package com.frameboter.rest;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;

public class AbstractResource {

  // @AuthenticationPrincipal 
  protected final Object getUserName(Jwt jwt){
    return jwt.getClaimAsString("upn");
  }

  protected final Object getRoles(Jwt jwt){
    return jwt.getClaimAsStringList("roles");
  }
}
