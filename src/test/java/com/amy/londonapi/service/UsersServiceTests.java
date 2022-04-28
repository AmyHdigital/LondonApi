package com.amy.londonapi.service;

import com.amy.londonapi.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UsersServiceTests {

    /**
     * Constant for the latitude of London.
     */
    final private double LONDON_LATITUDE = 51.507222;

    /**
     * Constant for the longitude of London.
     */
    final private double LONDON_LONGITUDE = -0.1275;

    private RestTemplate mockedRestTemplate;

    @BeforeEach
    public void setup() {
        mockedRestTemplate = mock(RestTemplate.class);
    }

    @Test
    public void testUsersApiReturnsListOfUsers() {
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
        ResponseEntity<User[]> allUsersResponse = new ResponseEntity<>(expectedUsersArray, HttpStatus.OK);

        String url = "http://bpdts-test-app.herokuapp.com/";

        // Mock out the rest template call
        when(mockedRestTemplate.getForEntity(url + "users", User[].class)).thenReturn(allUsersResponse);

        UsersService usersService = new UsersService(mockedRestTemplate, url);

        List<User> actualResponse = usersService.getAllUsers();

        assertNotNull(actualResponse);
        assertNotEquals(0, actualResponse.size());
    }

    @Test
    public void testUsersFromCityApiReturnsListOfUsers() {
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
        ResponseEntity<User[]> allUsersResponse = new ResponseEntity<>(expectedUsersArray, HttpStatus.OK);

        String url = "http://bpdts-test-app.herokuapp.com/";

        // Mock out the rest template call
        when(mockedRestTemplate.getForEntity(url + "city/London/users", User[].class)).thenReturn(allUsersResponse);

        UsersService usersService = new UsersService(mockedRestTemplate, url);

        List<User> actualResponse = usersService.getAllUsersFromCity("London");

        assertNotNull(actualResponse);
        assertNotEquals(0, actualResponse.size());
    }

    @Test
    public void whenNoUsersThenGetUsersWithinXMilesOfLocationReturnsEmptyList() {
        // Convert list to array for easier processing
        User[] usersArray = new User[0];

        // Create mocked response from the Users API call
        ResponseEntity<User[]> allUsersResponse = new ResponseEntity<>(usersArray, HttpStatus.OK);

        String url = "http://bpdts-test-app.herokuapp.com/";

        // Mock out the rest template call
        when(mockedRestTemplate.getForEntity(url + "users", User[].class)).thenReturn(allUsersResponse);

        UsersService usersService = new UsersService(mockedRestTemplate, url);

        List<User> actualResponse = usersService.getUsersWithinXMilesOfLocation(
                50,
                LONDON_LATITUDE,
                LONDON_LONGITUDE);

        assertNotNull(actualResponse);
        assertEquals(0, actualResponse.size());
    }

    @Test
    public void whenOneUserWithin50MilesOfLocationThenGetUsersWithin50MilesOfLondonReturnsListWithOneUser() {
        // Create sample user
        User user1 = new User(
                1,
                "Maurise",
                "Shieldon",
                "mshieldon0@squidoo.com",
                "192.57.232.111",
                51.6553959,
                0.0572553);

        // Add sample user to list
        List<User> usersList = new ArrayList<>();
        usersList.add(user1);

        // Convert list to array for easier processing
        User[] usersArray = new User[usersList.size()];
        usersList.toArray(usersArray);

        // Create mocked response from the Users API call
        ResponseEntity<User[]> allUsersResponse = new ResponseEntity<>(usersArray, HttpStatus.OK);

        String url = "http://bpdts-test-app.herokuapp.com/";

        // Mock out the rest template call
        when(mockedRestTemplate.getForEntity(url + "users", User[].class)).thenReturn(allUsersResponse);

        UsersService usersService = new UsersService(mockedRestTemplate, url);

        List<User> actualResponse = usersService.getUsersWithinXMilesOfLocation(
                50,
                LONDON_LATITUDE,
                LONDON_LONGITUDE);

        assertNotNull(actualResponse);
        assertEquals(1, actualResponse.size());

    }

    @Test
    public void whenOneUserOutside50MilesOfLocationThenGetUsersWithin50MilesOfLondonReturnsEmptyList() {
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
        List<User> usersList = new ArrayList<>();
        usersList.add(user1);

        // Convert list to array for easier processing
        User[] usersArray = new User[usersList.size()];
        usersList.toArray(usersArray);

        // Create mocked response from the Users API call
        ResponseEntity<User[]> allUsersResponse = new ResponseEntity<>(usersArray, HttpStatus.OK);

        String url = "http://bpdts-test-app.herokuapp.com/";

        // Mock out the rest template call
        when(mockedRestTemplate.getForEntity(url + "users", User[].class)).thenReturn(allUsersResponse);

        UsersService usersService = new UsersService(mockedRestTemplate, url);

        List<User> actualResponse = usersService.getUsersWithinXMilesOfLocation(
                50,
                LONDON_LATITUDE,
                LONDON_LONGITUDE);

        assertNotNull(actualResponse);
        assertEquals(0, actualResponse.size());


    }


    @Test
    public void whenTwoUsersWithin50MilesOfLocationThenGetUsersWithin50MilesOfLondonReturnsListWithTwoUsers() {
        // Create sample user
        User user1 = new User(
                1,
                "Maurise",
                "Shieldon",
                "mshieldon0@squidoo.com",
                "192.57.232.111",
                51.6553959,
                0.0572553);

        User user2 = new User(
                1,
                "Maurise",
                "Shieldon",
                "mshieldon0@squidoo.com",
                "192.57.232.111",
                51.507222,
                -0.1275);

        // Add sample user to list
        List<User> usersList = new ArrayList<>();
        usersList.add(user1);
        usersList.add(user2);

        // Convert list to array for easier processing
        User[] usersArray = new User[usersList.size()];
        usersList.toArray(usersArray);

        // Create mocked response from the Users API call
        ResponseEntity<User[]> allUsersResponse = new ResponseEntity<>(usersArray, HttpStatus.OK);

        String url = "http://bpdts-test-app.herokuapp.com/";

        // Mock out the rest template call
        when(mockedRestTemplate.getForEntity(url + "users", User[].class)).thenReturn(allUsersResponse);

        UsersService usersService = new UsersService(mockedRestTemplate, url);

        List<User> actualResponse = usersService.getUsersWithinXMilesOfLocation(
                50,
                LONDON_LATITUDE,
                LONDON_LONGITUDE);

        assertNotNull(actualResponse);
        assertEquals(2, actualResponse.size());


    }

    @Test
    public void whenOneUsersWithin50MilesOfLocationAndOneOutsideThenGetUsersWithin50MilesOfLondonReturnsListWithOneUser() {
        // Create sample user
        User user1 = new User(
                1,
                "Maurise",
                "Shieldon",
                "mshieldon0@squidoo.com",
                "192.57.232.111",
                51.6553959,
                0.0572553);

        User user2 = new User(
                1,
                "Maurise",
                "Shieldon",
                "mshieldon0@squidoo.com",
                "192.57.232.111",
                34.003135,
                -117.7228641);

        // Add sample user to list
        List<User> usersList = new ArrayList<>();
        usersList.add(user1);
        usersList.add(user2);

        // Convert list to array for easier processing
        User[] usersArray = new User[usersList.size()];
        usersList.toArray(usersArray);

        // Create mocked response from the Users API call
        ResponseEntity<User[]> allUsersResponse = new ResponseEntity<>(usersArray, HttpStatus.OK);

        String url = "http://bpdts-test-app.herokuapp.com/";

        // Mock out the rest template call
        when(mockedRestTemplate.getForEntity(url + "users", User[].class)).thenReturn(allUsersResponse);

        UsersService usersService = new UsersService(mockedRestTemplate, url);

        List<User> actualResponse = usersService.getUsersWithinXMilesOfLocation(
                50,
                LONDON_LATITUDE,
                LONDON_LONGITUDE);

        assertNotNull(actualResponse);
        assertEquals(1, actualResponse.size());
    }

    @Test
    public void whenOneUserInLondonButLocationMoreThan50MilesFromLondonThenOneUserReturned() {
        // Create sample user
        User londonUser = new User(
                1,
                "Maurise",
                "Shieldon",
                "mshieldon0@squidoo.com",
                "192.57.232.111",
                34.003135,
                -117.7228641);

        // Add sample user to list
        List<User> londonUserList = new ArrayList<>();
        londonUserList.add(londonUser);

        // Convert list to array for easier processing
        User[] londonUsersArray = new User[londonUserList.size()];
        londonUserList.toArray(londonUsersArray);

        // Add sample user to list of users within location
        List<User> withinLocationUserList = new ArrayList<>();
        withinLocationUserList.add(londonUser);

        // Convert list to array for easier processing
        User[] withinLocationUserArray = new User[withinLocationUserList.size()];
        withinLocationUserList.toArray(withinLocationUserArray);

        // Create mocked response from the Users API call
        ResponseEntity<User[]> allUsersResponse = new ResponseEntity<>(withinLocationUserArray, HttpStatus.OK);
        ResponseEntity<User[]> usersInCityResponse = new ResponseEntity<>(londonUsersArray, HttpStatus.OK);

        String url = "http://bpdts-test-app.herokuapp.com/";

        // Mock out the rest template call
        when(mockedRestTemplate.getForEntity(url + "users", User[].class)).thenReturn(allUsersResponse);
        when(mockedRestTemplate.getForEntity(url + "city/London/users", User[].class)).thenReturn(usersInCityResponse);

        UsersService usersService = new UsersService(mockedRestTemplate, url);

        List<User> actualResponse = usersService.getUsersInCityOrAroundLocation(
                "London",
                50,
                LONDON_LATITUDE,
                LONDON_LONGITUDE);

        assertNotNull(actualResponse);
        assertEquals(1, actualResponse.size());

    }

    @Test
    public void whenOneUserInLondonButLocationWithin50MilesFromLondonThenOneUserReturned() {
        // Create sample user
        User londonUser = new User(
                1,
                "Maurise",
                "Shieldon",
                "mshieldon0@squidoo.com",
                "192.57.232.111",
                51.6553959,
                0.0572553);

        // Add sample user to list of London users
        List<User> londonUserList = new ArrayList<>();
        londonUserList.add(londonUser);

        // Convert list to array for easier processing
        User[] londonUsersArray = new User[londonUserList.size()];
        londonUserList.toArray(londonUsersArray);

        // Add sample user to list of users within location
        List<User> withinLocationUserList = new ArrayList<>();
        withinLocationUserList.add(londonUser);

        // Convert list to array for easier processing
        User[] withinLocationUserArray = new User[withinLocationUserList.size()];
        withinLocationUserList.toArray(withinLocationUserArray);

        // Create mocked response from the Users API call
        ResponseEntity<User[]> allUsersResponse = new ResponseEntity<>(withinLocationUserArray, HttpStatus.OK);
        ResponseEntity<User[]> usersInCityResponse = new ResponseEntity<>(londonUsersArray, HttpStatus.OK);

        String url = "http://bpdts-test-app.herokuapp.com/";

        // Mock out the rest template call
        when(mockedRestTemplate.getForEntity(url + "users", User[].class)).thenReturn(allUsersResponse);
        when(mockedRestTemplate.getForEntity(url + "city/London/users", User[].class)).thenReturn(usersInCityResponse);

        UsersService usersService = new UsersService(mockedRestTemplate, url);

        List<User> actualResponse = usersService.getUsersInCityOrAroundLocation(
                "London",
                50,
                LONDON_LATITUDE,
                LONDON_LONGITUDE);

        assertNotNull(actualResponse);
        assertEquals(1, actualResponse.size());
    }

    @Test
    public void whenOneUserInLondonAndAnotherUserWithin50MilesOfLondonThenTwoUserReturned() {
        // Create sample user
        User londonUser = new User(
                1,
                "Maurise",
                "Shieldon",
                "mshieldon0@squidoo.com",
                "192.57.232.111",
                34.003135,
                -117.7228641);

        User userWithin50Miles = new User(
                1,
                "Bob",
                "Bella",
                "bb0@doggo.com",
                "192.57.232.111",
                51.6553959,
                0.0572553);

        // Add sample user to list of London users
        List<User> londonUserList = new ArrayList<>();
        londonUserList.add(londonUser);

        // Convert list to array for easier processing
        User[] londonUsersArray = new User[londonUserList.size()];
        londonUserList.toArray(londonUsersArray);

        // Add sample user to list of users within location
        List<User> withinLocationUserList = new ArrayList<>();
        withinLocationUserList.add(userWithin50Miles);

        // Convert list to array for easier processing
        User[] withinLocationUserArray = new User[withinLocationUserList.size()];
        withinLocationUserList.toArray(withinLocationUserArray);

        // Create mocked response from the Users API call
        ResponseEntity<User[]> allUsersResponse = new ResponseEntity<>(withinLocationUserArray, HttpStatus.OK);
        ResponseEntity<User[]> usersInCityResponse = new ResponseEntity<>(londonUsersArray, HttpStatus.OK);

        String url = "http://bpdts-test-app.herokuapp.com/";

        // Mock out the rest template call
        when(mockedRestTemplate.getForEntity(url + "users", User[].class)).thenReturn(allUsersResponse);
        when(mockedRestTemplate.getForEntity(url + "city/London/users", User[].class)).thenReturn(usersInCityResponse);

        UsersService usersService = new UsersService(mockedRestTemplate, url);

        List<User> actualResponse = usersService.getUsersInCityOrAroundLocation(
                "London",
                50,
                LONDON_LATITUDE,
                LONDON_LONGITUDE);

        assertNotNull(actualResponse);
        assertEquals(2, actualResponse.size());
    }


}
