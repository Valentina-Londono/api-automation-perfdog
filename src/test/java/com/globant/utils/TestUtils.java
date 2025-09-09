package com.globant.utils;

import com.globant.model.user.CreateUserRequest;
import com.globant.request.RequestBuilder;
import io.restassured.response.Response;

import static org.testng.Assert.assertEquals;

/**
 * Utility class for common test methods.
 */
public class TestUtils {

    /**
     * Method: create a test user.
     * It sends a POST request to /user and checks status 200.
     * @return the username of the created user
     */
    public static String createTestUser(String username, String password, String baseUrl, String apiKey) {
        CreateUserRequest userRequest = CreateUserRequest.builder()
                .username(username)
                .email(username + "@test.com")
                .password(password)
                .build();

        Response response = RequestBuilder.postRequest(
                baseUrl,
                "/user",
                userRequest,
                apiKey
        );

        assertEquals(response.getStatusCode(), 200, "The user was not created successfully");
        return username;
    }
}

