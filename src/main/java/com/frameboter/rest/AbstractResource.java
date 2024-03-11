package com.frameboter.rest;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;

public class AbstractResource {

  // @AuthenticationPrincipal 
  private final Object getUserName(Jwt jwt){
    return jwt.getClaims().get("upn");
  }

  private final Object getRoles(Jwt jwt){
    return jwt.getClaims().get("role_list");
  }
}
