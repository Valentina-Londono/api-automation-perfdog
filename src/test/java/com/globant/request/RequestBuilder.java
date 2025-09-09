package com.globant.request;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpHeaders;
import org.apache.http.entity.ContentType;

public class RequestBuilder {

    public static Response getRequest(String baseUrl, String path, String apiKey){
        RequestSpecification requestSpecification = RestAssured.given()
                .baseUri(baseUrl)
                .header(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType())
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter());

        if(apiKey != null){
            requestSpecification.header("x-api-key", apiKey);
        }

        return  requestSpecification.get(path);
    }

    public static Response postRequest(String baseUrl, String path, Object body,String apiKey){
        RequestSpecification requestSpecification = RestAssured.given()
                .baseUri(baseUrl)
                .header(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType())
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .body(body);

        if(apiKey != null){
            requestSpecification.header("x-api-key", apiKey);
        }

        return  requestSpecification.post(path);
    }

}
