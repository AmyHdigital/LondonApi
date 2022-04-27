package com.amy.londonapi.service;

import com.amy.londonapi.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UsersServiceTests {

    private RestTemplate mockedRestTemplate;

    @Test
    public void testUsersApiReturnsListOfUsers(){
        mockedRestTemplate = mock(RestTemplate.class);

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

        // Convert list to array for easier processing
        User[] expectedUsersArray = new User[expectedUsersList.size()];
        expectedUsersList.toArray(expectedUsersArray);

        // Create mocked response from the Users API call
        ResponseEntity<User[]> allUsersResponse = new ResponseEntity<>(expectedUsersArray,HttpStatus.OK);

        String url = "http://bpdts-test-app.herokuapp.com/";

        // Mock out the rest template call
        when(mockedRestTemplate.getForEntity(url + "users",User[].class)).thenReturn(allUsersResponse);

        UsersService usersService = new UsersService(mockedRestTemplate, url);

        List<User> actualResponse = usersService.getAllUsers();

        assertNotNull(actualResponse);
        assertNotEquals(0, actualResponse.size());

    }
}
