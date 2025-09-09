package com.globant.tests;

import com.globant.config.TestRunner;
import com.globant.model.user.ErrorResponse;
import com.globant.request.RequestBuilder;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Test class for GET /user/logout endpoint.
 * It logs out the user.
 */
public class LogoutTests extends TestRunner {

    /**
     * Test: log out a user.
     */
    @Test(testName = "Validate logout - success")
    public void validateLogoutSuccess() {
        Response response = RequestBuilder.getRequest(getBaseUrl(), "/user/logout", getApiKey());

        assertEquals(response.getStatusCode(), 200, "The status code doesn't match");

        ErrorResponse logoutResponse = response.as(ErrorResponse.class);

        assertEquals(logoutResponse.getCode(), 200, "The code doesn't match");
        assertEquals(logoutResponse.getMessage(), "ok", "The message doesn't match");
    }
}
