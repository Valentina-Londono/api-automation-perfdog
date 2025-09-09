package com.globant.tests;

import com.globant.config.TestRunner;
import com.globant.model.pet.Pet;
import com.globant.request.RequestBuilder;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

/**
 * Test class for GET /pet/findByStatus endpoint.
 * It gets all the pets with a status 'available'.
 */
public class GetAvailablePetsTests extends TestRunner {

    /**
     * Test: get a list of pets with available status.
     */
    @Test(testName = "Validate available pets list")
    public void validateAvailablePetsList() {
        Response response = RequestBuilder.getRequest(
                getBaseUrl(),
                "/pet/findByStatus?status=available",
                getApiKey()
        );

        assertEquals(response.getStatusCode(), 200, "The status code doesn't match");

        List<Pet> pets = response.jsonPath().getList("", Pet.class);

        assertFalse(pets.isEmpty(), "The pets list should not be empty.");
        assertEquals(pets.get(0).getStatus(), "available", "The first pet is not available.");
    }
}


