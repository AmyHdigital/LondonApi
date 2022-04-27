package com.amy.londonapi.controller;

import com.amy.londonapi.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsersController {

  public ResponseEntity<List<User>> getUsersInOrAroundLondon(){

      ResponseEntity<List<User>> users = new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);

      return users;
  }
}
