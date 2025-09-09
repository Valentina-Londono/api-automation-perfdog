package com.globant.tests;

import com.globant.config.TestRunner;
import com.globant.model.store.CreateOrderRequest;
import com.globant.model.store.CreateOrderResponse;
import com.globant.request.RequestBuilder;
import com.globant.model.user.ErrorResponse;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.time.ZonedDateTime;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Test class for POST /store/order endpoint.
 * It checks order creation for success and server error cases.
 */
public class PostOrderTests extends TestRunner {

    /**
     * Test: create an order successfully should return 200 and valid order data.
     * Test: if server error happens, it should return 500 with error info.
     */
    @Test(testName = "Validate order creation - success")
    public void validateOrderCreationSuccess() {
        CreateOrderRequest orderRequest = CreateOrderRequest.builder()
                .id(1)
                .petId(10)
                .quantity(2)
                .shipDate(ZonedDateTime.now().toString())
                .status("placed")
                .complete(true)
                .build();

        Response response = RequestBuilder.postRequest(getBaseUrl(), "/store/order", orderRequest, getApiKey());

        if (response.getStatusCode() == 200) {
            CreateOrderResponse orderResponse = response.as(CreateOrderResponse.class);

            assertEquals(response.getStatusCode(), 200, "The status code doesn't match");
            assertEquals(orderResponse.getPetId(), 10, "The petId doesn't match");
            assertEquals(orderResponse.getQuantity(), 2, "The quantity doesn't match");
            assertEquals(orderResponse.getStatus(), "placed", "The status doesn't match");
            assertTrue(orderResponse.isComplete(), "The order should be complete");
        } else if (response.getStatusCode() == 500) {
            ErrorResponse error = response.as(ErrorResponse.class);

            assertEquals(error.getCode(), 500, "The error code doesn't match");
            assertEquals(error.getType(), "unknown", "The error type doesn't match");
        } else {
            throw new AssertionError("Unexpected status code: " + response.getStatusCode());
        }
    }
}


