package com.frameboter.config;

import com.frameboter.config.JwtAuthConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(jsr250Enabled = true)
public class WebSecurityConfig {

    private static final Logger log = LoggerFactory.getLogger(WebSecurityConfig.class);

    private static final JwtAuthConverter jwtAuthConverter = new JwtAuthConverter();

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

        log.info("swaggerPath: {}", swaggerPath);
        log.info("openApiPath: {}", openApiPath);
        log.info("contextPath: {}", contextPath);
        log.info("publicPaths: {}", List.of(publicPaths));

        http.authorizeHttpRequests(auth -> {

            // swagger + openapi
            log.info("Adding paths to permitted paths: {}", List.of(swaggerPath, swaggerPath + "/*", swaggerPath + "-ui", swaggerPath + "-ui/*"));
            auth.requestMatchers(swaggerPath, swaggerPath + "/*", swaggerPath + "-ui", swaggerPath + "-ui/*").permitAll();
            log.info("Adding paths to permitted paths: {}", List.of(openApiPath, openApiPath + "/*"));
            auth.requestMatchers(openApiPath, openApiPath + "/*").permitAll();

            // same but with context path
            log.info("Adding paths to permitted paths: {}", List.of(contextPath + swaggerPath, contextPath + swaggerPath + "/*", contextPath + swaggerPath + "-ui"));
            auth.requestMatchers(contextPath + swaggerPath).permitAll();
            auth.requestMatchers(contextPath + swaggerPath + "/*").permitAll();
            auth.requestMatchers(contextPath + swaggerPath + "-ui").permitAll();
            auth.requestMatchers(contextPath + swaggerPath + "-ui/*").permitAll();
            log.info("Adding paths to permitted paths: {}", List.of(contextPath + openApiPath, contextPath + openApiPath + "/*"));
            auth.requestMatchers(contextPath + openApiPath).permitAll();
            auth.requestMatchers(contextPath + openApiPath + "/*").permitAll();

            // Public paths (fixed)
            for (String p : publicPaths) {
                log.info("Adding paths to permitted paths: {}", List.of(p, p + "/**"));
                auth.requestMatchers(p).permitAll();
                auth.requestMatchers(p + "/**").permitAll();

                // automatically prepend context path
                log.info("Adding paths to permitted paths: {}", List.of(contextPath + p, contextPath + p + "/**"));
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


