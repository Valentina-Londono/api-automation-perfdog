package com.globant.tests;

import com.globant.config.TestRunner;
import com.globant.model.user.ErrorResponse;
import com.globant.model.user.User;
import com.globant.request.RequestBuilder;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpHeaders;
import org.apache.http.entity.ContentType;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

public class GetUserTests extends TestRunner {
    @Test(testName = "validate found user - assertion 1")
    public void userFoundTestAssertion() {
        RestAssured.given()
                .baseUri(getBaseUrl())
                .header(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType())
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when()
                .get("/user/username")
                .then()
                .body("username", equalTo("username"))
                .body("firstName", equalTo("firstName"))
                .body("lastName", equalTo("lastName"));
    }

    @Test(testName = "Validate found user - assertion 2")
    public void userFoundTestAssertion2() {
        Response response = RequestBuilder.getRequest(getBaseUrl(), "/user/username", getApiKey());
        JsonPath jsonPath = response.jsonPath();

        String username = jsonPath.getString("username");
        String firstName = jsonPath.getString("firstName");
        String lastName = jsonPath.getString("lastName");

        assertEquals(response.getStatusCode(), 200, "The status code doesn't match");
        assertEquals(username, "username", "The username doesn't match");
        assertEquals(firstName, "firstName",  "The firstName doesn't match");
        assertEquals(lastName, "lastName",  "The lastName doesn't match");
    }

    @Test(testName = "Validate found user - assertion 3")
    public void userFoundTestAssertion3() {
        Response response = RequestBuilder.getRequest(getBaseUrl(), "/user/username", getApiKey());
        User user = response.as(User.class);

        assertEquals(response.getStatusCode(), 200, "The status code doesn't match");
        assertEquals(user.getUsername(), "username", "The username doesn't match");
        assertEquals(user.getFirstName(), "firstName", "The firstName doesn't match");
        assertEquals(user.getLastName(), "lastName", "The lastName doesn't match");

    }

    @Test(testName = "Validate not found user - assertion 1")
    public void userNotFoundTestAssertion() {
        Response response = RequestBuilder.getRequest(getBaseUrl(), "/user/username2", getApiKey());
        ErrorResponse error = response.as(ErrorResponse.class);

        assertEquals(response.getStatusCode(), 404, "The status code doesn't match");
    }
}
