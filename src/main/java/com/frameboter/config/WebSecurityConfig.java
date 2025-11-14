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
    private String contextPath;

    @Value("${security.public.paths:}")
    private String[] publicPaths;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(auth -> {

            // swagger + openapi
            auth.requestMatchers(swaggerPath, swaggerPath + "/*", swaggerPath + "-ui", swaggerPath + "-ui/*").permitAll();
            auth.requestMatchers(openApiPath, openApiPath + "/*").permitAll();

            // same but with context path
            auth.requestMatchers(contextPath + swaggerPath).permitAll();
            auth.requestMatchers(contextPath + swaggerPath + "/*").permitAll();
            auth.requestMatchers(contextPath + swaggerPath + "-ui").permitAll();
            auth.requestMatchers(contextPath + swaggerPath + "-ui/*").permitAll();
            auth.requestMatchers(contextPath + openApiPath).permitAll();
            auth.requestMatchers(contextPath + openApiPath + "/*").permitAll();

            // Public paths (fixed)
            for (String p : publicPaths) {
                auth.requestMatchers(p).permitAll();
                auth.requestMatchers(p + "/**").permitAll();

                // automatically prepend context path
                auth.requestMatchers(contextPath + p).permitAll();
                auth.requestMatchers(contextPath + p + "/**").permitAll();
            }

            auth.anyRequest().authenticated();
        });

        http.oauth2ResourceServer(oauth -> oauth.jwt().jwtAuthenticationConverter(jwtAuthConverter));
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}


