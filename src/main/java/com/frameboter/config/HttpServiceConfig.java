package com.frameboter.config;

import com.frameboter.service.HttpService;
import com.frameboter.service.HttpServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class HttpServiceConfig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public HttpService httpService(RestTemplate restTemplate) {
        return new HttpServiceImpl(restTemplate);
    }
}
