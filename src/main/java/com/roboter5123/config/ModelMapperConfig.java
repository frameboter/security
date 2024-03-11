package com.roboter5123.config;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@ConditionalOnMissingBean
public class ModelMapperConfig {

    @Bean
    ModelMapper getModelMapper() {

        return new ModelMapper();
    }
}