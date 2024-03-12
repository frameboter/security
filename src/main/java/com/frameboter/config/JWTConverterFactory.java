package com.frameboter.config;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class JWTConverterFactory {

    @Bean
    JwtAuthConverter jwtAuthConverter(){
        return new JwtAuthConverter();
    }
}
