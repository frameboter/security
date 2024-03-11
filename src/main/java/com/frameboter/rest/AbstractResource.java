package com.frameboter.rest;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;

public class AbstractResource {

  private final String getUserName(@AuthenticationPrincipal Jwt jwt){
    return jwt.getName();
  }

  private final String getRoles(@AuthenticationPrincipal Jwt jwt){
    return jwt.getAuthorities();
  }
}
