package com.frameboter.service;

import org.springframework.security.oauth2.jwt.Jwt;

public interface HttpService {

    <T> T sendGetRequest(String url, Jwt jwt, Class<T> responseClass);

    <T> T sendPostRequest(String url, Jwt jwt, Object body, Class<T> responseClass);

    <T> T sendPutRequest(String url, Jwt jwt, Object body, Class<T> responseClass);
}
