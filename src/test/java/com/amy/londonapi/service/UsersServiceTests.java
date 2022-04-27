package com.amy.londonapi.service;

import com.amy.londonapi.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UsersServiceTests {

    @Test
    public void testUsersApiReturnsListOfUsers(){
        UsersService usersService = new UsersService();

        ResponseEntity<List<User>> actualResponse = usersService.getAllUsers();

        assertNotNull(actualResponse);
        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        assertNotEquals(0, actualResponse.getBody().size());

    }
}
