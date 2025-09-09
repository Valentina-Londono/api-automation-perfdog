package com.globant.tests;

import com.globant.config.TestRunner;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.apache.http.HttpHeaders;
import org.apache.http.entity.ContentType;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class GetUser extends TestRunner {
    @Test(testName = "validate found user")
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
}
