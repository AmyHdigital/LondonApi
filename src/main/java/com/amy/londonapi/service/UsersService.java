package com.amy.londonapi.service;

import com.amy.londonapi.model.User;
import com.amy.londonapi.utilities.Geolocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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


    public List<User> getUsersWithinXMilesOfLocation (double miles, double startLatitude, double startLongitude){

        List<User> listOfUsers = getAllUsers();

        return listOfUsers.stream()
                .filter(user -> Geolocation.isUserWithinXMilesOfCoordinates(
                        user.getLatitude(),
                        user.getLongitude(),
                        miles,
                        startLatitude,
                        startLongitude))
                .collect(Collectors.toList());
    }

    public List<User> getUsersInCityOrAroundLocation(String city,double miles, double startLatitude, double startLongitude){
        List<User> listOfUsersInCity = getAllUsersFromCity(city);
        List<User> listOfUsersAroundLocation = getUsersWithinXMilesOfLocation(miles,startLatitude,startLongitude);

        return Stream.of(listOfUsersInCity,listOfUsersAroundLocation)
                .flatMap(Collection::stream)
                .distinct()
                .collect(Collectors.toList());
    }
}
