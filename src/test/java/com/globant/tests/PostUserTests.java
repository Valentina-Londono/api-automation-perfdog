package com.globant.tests;

import com.globant.config.TestRunner;
import com.globant.model.user.CreateUserRequest;
import com.globant.model.user.CreateUserResponse;
import com.globant.request.RequestBuilder;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Test class for POST /user endpoint.
 * It checks user creation success.
 */
public class PostUserTests extends TestRunner {

    /**
     * Test: create a new user should return 200 and valid user info.
     */
    @Test(testName = "Validate user creation")
    public void validateUserCreation() {
        CreateUserRequest createUserRequest = CreateUserRequest.builder()
                .username("valtimore")
                .email("valti@gmail.com")
                .password("12345")
                .build();

        Response response = RequestBuilder.postRequest(getBaseUrl(), "/user", createUserRequest, getApiKey());
        CreateUserResponse createUserResponse = response.as(CreateUserResponse.class);

        assertEquals(response.getStatusCode(), 200, "The status code doesn't match.");
        assertEquals(createUserResponse.getCode(), 200, "The code doesn't match.");
        assertEquals(createUserResponse.getType(), "unknown", "The type doesn't match");

        long createdUserId = Long.parseLong(createUserResponse.getMessage());
        assertTrue(createdUserId > 0, "The id of the user should be greater than 0.");
    }
}

