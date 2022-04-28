package com.amy.londonapi.controller;

import com.amy.londonapi.model.User;
import com.amy.londonapi.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsersController {

    private UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }


    @GetMapping(value = "/v1/london-users", produces = {"application/json"})
    public ResponseEntity<List<User>> getUsersInOrAroundLondon() {

        List<User> users = usersService.getAllUsers();
        ResponseEntity<List<User>> response = new ResponseEntity<>(users, HttpStatus.OK);

        return response;
    }
}
