package com.swagger.pet.testCases;

import com.swagger.pet.apis.API;
import com.swagger.pet.steps.PetSteps;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import com.swagger.pet.models.Pet;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.equalTo;

public class PetTest {

    @Test
    public void addingPet() {
        Pet p = PetSteps.generatePet();
        Response response = API.addingApi(p);
        Pet returnedresponse = response.body().as(Pet.class);
        assertThat(response.statusCode(), equalTo(200));
        assertThat(returnedresponse.getStatus(), equalTo("available"));
    }
    @Test
    public void updatingPet() {
        Pet pet = PetSteps.generatePet();
        Response addResponse = API.addingApi(pet);
        Pet soldPet = PetSteps.updatePet(pet,"sold");
        Response updateResponse = API.updatingApi(soldPet);
        Pet returnedresponse = updateResponse.body().as(Pet.class);
        assertThat(updateResponse.statusCode(), equalTo(200));
        assertThat(returnedresponse.getStatus(), equalTo("sold"));
    }

    @Test
    public void findPetsByStatus() {
        Response response = API.findByStatusAPI();
        PetSteps.assertAllPetsHaveStatus(response, "sold");
    }

    @Test
    public void findPetmById() {
        Pet pet = PetSteps.generatePet();
        API.addingApi(pet);
        Response response=API.findByIdAPI(pet.getId());
        Pet returnedresponse = response.body().as(Pet.class);
        assertThat(returnedresponse.getId(), equalTo(pet.getId()));
        assertThat(response.statusCode(), equalTo(200));
    }

}
