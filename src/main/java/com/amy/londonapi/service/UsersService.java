package com.amy.londonapi.service;

import com.amy.londonapi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UsersService {

    private final RestTemplate restTemplate;
    private final String backendUrl;

    @Autowired
    public UsersService(RestTemplate restTemplate, @Value("${backend_service_url}") final String backendUrl) {
        this.restTemplate = restTemplate;
        this.backendUrl = backendUrl;
    }

    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();

        String url = backendUrl + "users";
        ResponseEntity<User[]> allUsers = restTemplate.getForEntity(url, User[].class);
        users = Arrays.asList(allUsers.getBody());

        return users;
    }


    public List<User> getAllUsersFromCity(String city) {

        List<User> users = new ArrayList<>();

        String url = backendUrl + "city/" + city + "/users";
        ResponseEntity<User[]> allCityUsers = restTemplate.getForEntity(url, User[].class);
        users = Arrays.asList(allCityUsers.getBody());

        return users;
    }
}
