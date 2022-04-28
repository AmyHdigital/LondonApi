package com.amy.londonapi.controller;

import com.amy.londonapi.model.User;
import com.amy.londonapi.service.UsersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UsersControllerTests {

    private UsersService mockedUsersService;
    private UsersController usersController;

    @BeforeEach
    public void setup() {
        mockedUsersService = mock(UsersService.class);
        usersController = new UsersController(mockedUsersService);
    }

    @Test
    public void controllerReturns200WithEmptyListOfUsers(){
        when(mockedUsersService.getUsersInCityOrAroundLocation(
                anyString(),
                anyDouble(),
                anyDouble(),
                anyDouble()
                )).thenReturn(new ArrayList<>());

        ResponseEntity<List<User>> actualResponse = usersController.getUsersInOrAroundLondon();

        assertNotNull(actualResponse);
        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        assertEquals(0, actualResponse.getBody().size());
    }

    @Test
    public void controllerReturns200WithPopulatedListOfUsers() {
        // Create sample user
        User user1 = new User(
                1,
                "Maurise",
                "Shieldon",
                "mshieldon0@squidoo.com",
                "192.57.232.111",
                34.003135,
                -117.7228641);

        // Add sample user to list
        List<User> expectedUsersList = new ArrayList<>();
        expectedUsersList.add(user1);

        when(mockedUsersService.getUsersInCityOrAroundLocation(
                anyString(),
                anyDouble(),
                anyDouble(),
                anyDouble()
        )).thenReturn(expectedUsersList);

        ResponseEntity<List<User>> actualResponse = usersController.getUsersInOrAroundLondon();

        assertNotNull(actualResponse);
        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        assertEquals(1, actualResponse.getBody().size());
    }
}
