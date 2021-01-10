package com.businesslogic.server.service;

import com.businesslogic.server.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthenticationServerProxy {

    @Autowired
    private RestTemplate rest;

    @Value("${auth.server.base.url}")
    private String baseUrl;

    public void sendAuth(String username, String password) {
        final String url = baseUrl + "/user/auth";

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        final HttpEntity<User> request = new HttpEntity<>(user);
        rest.postForEntity(url, request, Void.class);
    }

    public boolean sendOTP(String username,
                           String code) {
        String url = baseUrl + "/otp/check";

        User user = new User();
        user.setUsername(username);
        user.setCode(code);

        HttpEntity<Object> request = new HttpEntity<>(user);
        ResponseEntity<Void> response = rest.postForEntity(url, request, Void.class);

        return response
                .getStatusCode()
                .equals(HttpStatus.OK);
    }
}
