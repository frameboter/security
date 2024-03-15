package com.frameboter.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(jsr250Enabled = true)
public class WebSecurityConfig {

    private final JwtAuthConverter jwtAuthConverter = new JwtAuthConverter();

    @Value("${springdoc.swagger-ui.path:/swagger}")
    private String swaggerPath;

    @Value("${springdoc.api-docs.path:/api-docs}")
    private String openApiPath;

    @Value("${server.servlet.context-path:}")
    private String pathPrefix;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.cors().and().csrf().disable();
        http.authorizeHttpRequests()
                .requestMatchers(swaggerPath + "/*").permitAll()
                .requestMatchers(swaggerPath + "-ui/*").permitAll()
                .requestMatchers(openApiPath + "/*").permitAll()
                .anyRequest().authenticated();
        http.oauth2ResourceServer().jwt().jwtAuthenticationConverter(jwtAuthConverter);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }
}
