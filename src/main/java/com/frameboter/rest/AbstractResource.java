package com.frameboter.rest;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;

public class AbstractResource {

  # @AuthenticationPrincipal 
  private final String getUserName(Jwt jwt){
    return jwt.getName();
  }

  private final String getRoles(Jwt jwt){
    return jwt.getAuthorities();
  }
}
