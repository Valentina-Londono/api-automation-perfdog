package com.globant.tests;

import com.globant.config.TestRunner;
import com.globant.request.RequestBuilder;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LoginUserTests extends TestRunner {

    @Test(testName = "Validate successful login")
    public void validateSuccessfulLogin() {

        String username = "valtimore";
        String password = "12345";

        Response response = RequestBuilder.getRequest(
                getBaseUrl(),
                "/user/login?username=" + username + "&password=" + password,
                getApiKey()
        );


        assertEquals(response.getStatusCode(), 200, "The status code doesn't match");


        String rateLimit = response.getHeader("X-Rate-Limit");
        String expiresAfter = response.getHeader("X-Expires-After");

        assertNotNull(rateLimit, "Rate limit header should not be null");
        assertNotNull(expiresAfter, "Expires-After header should not be null");


        String message = response.getBody().asString();
        assertTrue(message.contains("logged in user session"), "The login message doesn't match");
    }


    @Test(testName = "Validate login with invalid credentials")
    public void validateInvalidLogin() {
        String username = "wrongUser";
        String password = "wrongPass";

        Response response = RequestBuilder.getRequest(
                getBaseUrl(),
                "/user/login?username=" + username + "&password=" + password,
                getApiKey()
        );

        assertEquals(response.getStatusCode(), 200, "The status code doesn't match");

        String message = response.getBody().asString();
        assertTrue(message.contains("logged in user session"), "Unexpected login message");
    }
}
