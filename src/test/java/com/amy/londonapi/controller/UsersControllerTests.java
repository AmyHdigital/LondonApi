package com.amy.londonapi.controller;

import com.amy.londonapi.model.User;
import com.amy.londonapi.service.UsersService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UsersControllerTests {


    @Test
    public void controllerReturns200WithEmptyListOfUsers(){
        UsersService mockedUsersService = mock(UsersService.class);
        UsersController usersController = new UsersController(mockedUsersService);

        when(mockedUsersService.getAllUsers()).thenReturn(new ArrayList<>());

        ResponseEntity<List<User>> actualResponse = usersController.getUsersInOrAroundLondon();

        assertNotNull(actualResponse);
        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        assertEquals(0, actualResponse.getBody().size());
    }


}
