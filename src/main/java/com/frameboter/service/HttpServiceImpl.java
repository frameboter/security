package com.frameboter.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class HttpServiceImpl implements HttpService {

    private final RestTemplate restTemplate;

    @Override
    public <T> T sendGetRequest(String url, Jwt jwt, Class<T> responseClass) {

        HttpEntity<Object> requestEntity = new HttpEntity<>(getHeaders(jwt));
        ResponseEntity<T> o = restTemplate.exchange(url, HttpMethod.GET, requestEntity, responseClass);
        return o.getBody();
    }

    @Override
    public <T> T sendPostRequest(String url, Jwt jwt, Object body, Class<T> responseClass) {

        HttpEntity<Object> requestEntity = new HttpEntity<>(body, getHeaders(jwt));
        ResponseEntity<T> o = restTemplate.exchange(url, HttpMethod.POST, requestEntity, responseClass);
        return o.getBody();
    }

    @Override
    public <T> T sendPutRequest(String url, Jwt jwt, Object body, Class<T> responseClass) {

        HttpEntity<Object> requestEntity = new HttpEntity<>(body, getHeaders(jwt));
        ResponseEntity<T> o = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, responseClass);
        return o.getBody();
    }

    private HttpHeaders getHeaders(Jwt jwt) {
        return new HttpHeaders() {{
            String authHeader = "Bearer " + jwt.getTokenValue();
            set("Authorization", authHeader);
        }};
    }
}
