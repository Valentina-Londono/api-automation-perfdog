package com.globant.utils;

import com.globant.model.user.CreateUserRequest;
import com.globant.request.RequestBuilder;
import io.restassured.response.Response;

import static org.testng.Assert.assertEquals;

public class TestUtils {

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
