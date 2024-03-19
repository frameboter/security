package com.frameboter.config;

import java.util.List;

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

	@Value("${security.public.paths}")
	private String[] publicPaths;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.cors().and().csrf().disable();
        configureOpenApiSecurity(http);
        http.authorizeHttpRequests().anyRequest().authenticated();
        http.oauth2ResourceServer().jwt().jwtAuthenticationConverter(jwtAuthConverter);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }

    private void configureOpenApiSecurity(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests()
                .requestMatchers(swaggerPath).permitAll()
                .requestMatchers(swaggerPath + "/*").permitAll()
                .requestMatchers(swaggerPath +"-ui").permitAll()
                .requestMatchers(swaggerPath +"-ui/*").permitAll()
                .requestMatchers(openApiPath).permitAll()
                .requestMatchers(openApiPath + "/*").permitAll();
        
        		for (String publicPath : publicPaths) {
			http.authorizeHttpRequests()
				.requestMatchers(publicPath).permitAll()
				.requestMatchers(publicPath + "/*").permitAll();
		}
    }
}
