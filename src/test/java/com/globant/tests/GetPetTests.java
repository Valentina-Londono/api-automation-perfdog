package com.globant.tests;

import com.globant.config.TestRunner;
import com.globant.model.pet.Pet;
import com.globant.model.user.ErrorResponse;
import com.globant.request.RequestBuilder;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * Test class for GET /pet endpoint.
 * It checks pet found and pet not found cases.
 */
public class GetPetTests extends TestRunner {

    /**
     * Test: get an existing pet should return 200 and valid pet data.
     */
    @Test(testName = "Validate found pet")
    public void validateFoundPet() {
        Response response = RequestBuilder.getRequest(getBaseUrl(), "/pet/10", getApiKey());
        Pet pet = response.as(Pet.class);

        assertEquals(response.getStatusCode(), 200, "The status code doesn't match");
        assertNotNull(pet.getId(), "The pet id should not be null");
        assertNotNull(pet.getName(), "The pet name should not be null");
    }

    /**
     * Test: get an unknown pet should return 404.
     */
    @Test(testName = "Validate not found pet")
    public void validateNotFoundPet() {
        Response response = RequestBuilder.getRequest(getBaseUrl(), "/pet/9111119999", getApiKey());
        ErrorResponse error = response.as(ErrorResponse.class);

        assertEquals(response.getStatusCode(), 404, "The status code doesn't match");
    }
}

