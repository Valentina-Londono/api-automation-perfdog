package com.globant.tests;

import com.globant.config.TestRunner;
import com.globant.model.user.ErrorResponse;
import com.globant.model.user.User;
import com.globant.request.RequestBuilder;
import com.globant.utils.TestUtils;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GetUserTests extends TestRunner {

    private String username = "valtimore";
    private String password = "12345";

    @BeforeMethod
    public void setupUser() {
        TestUtils.createTestUser(username, password, getBaseUrl(), getApiKey());
    }

    @Test(testName = "Validate found user - assertion 3")
    public void userFoundTestAssertion3() {
        Response response = RequestBuilder.getRequest(getBaseUrl(), "/user/" + username, getApiKey());
        User user = response.as(User.class);

        assertEquals(response.getStatusCode(), 200, "The status code doesn't match");
        assertEquals(user.getUsername(), username, "The username doesn't match");
    }

    @Test(testName = "Validate not found user")
    public void userNotFoundTestAssertion() {
        Response response = RequestBuilder.getRequest(getBaseUrl(), "/user/unknownUser", getApiKey());
        ErrorResponse error = response.as(ErrorResponse.class);

        assertEquals(response.getStatusCode(), 404, "The status code doesn't match");
        assertEquals(error.getCode(), 1, "Unexpected error code");
    }
}

