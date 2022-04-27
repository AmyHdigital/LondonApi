package com.amy.londonapi.service;

import com.amy.londonapi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UsersService {

    private final RestTemplate restTemplate;

    @Autowired
    public UsersService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<User> getAllUsers(){

        List<User> users = new ArrayList<>();

        ResponseEntity<User[]> allUsers = restTemplate.getForEntity("",User[].class);
        users = Arrays.asList(allUsers.getBody());

        return users;
    }
}
