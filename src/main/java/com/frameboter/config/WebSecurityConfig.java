package com.frameboter.config;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@AutoConfiguration
@EnableWebSecurity
@EnableMethodSecurity(jsr250Enabled = true)
public class WebSecurityConfig {

  private final JwtAuthConverter jwtAuthConverter = new JwtAuthConverter();

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      http.cors().and().csrf().disable();
      http.authorizeHttpRequests()
              .anyRequest().authenticated();
      http.oauth2ResourceServer()
              .jwt()
              .jwtAuthenticationConverter(jwtAuthConverter);
      http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
      return http.build();
  }
}
