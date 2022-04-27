package com.amy.londonapi.controller;

import com.amy.londonapi.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ControllerTests {


    @Test
    public void controllerReturns200WithEmptyListOfUsers(){
        UsersController usersController = new UsersController();

        ResponseEntity<List<User>> actualResponse = usersController.getUsersInOrAroundLondon();

        assertNotNull(actualResponse);
        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        assertEquals(0, actualResponse.getBody().size());
    }


}
