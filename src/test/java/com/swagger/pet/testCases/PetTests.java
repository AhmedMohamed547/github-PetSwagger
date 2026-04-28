package com.swagger.pet.testCases;

import com.swagger.pet.apis.API;
import com.swagger.pet.data.ErrorMessages;
import com.swagger.pet.models.Order;
import com.swagger.pet.models.Pet;
import com.swagger.pet.models.ResponseMeesage;
import com.swagger.pet.steps.PetSteps;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PetTests {
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
        String status = "sold";
        Response response = API.findByStatusAPI(status);
        PetSteps.assertAllPetsHaveStatus(response,status);
    }
    @Test
    public void findPetById() {
        Pet pet = PetSteps.generatePet();
        API.addingApi(pet);
        Response response=API.findByIdAPI(pet.getId());
        Pet returnedResponse = response.body().as(Pet.class);
        assertThat(returnedResponse.getId(), equalTo(pet.getId()));
        assertThat(response.statusCode(), equalTo(200));
    }

    @Test
    public void findPetByWrongId() {
        Response response= API.findByIdAPI(82282);
        ResponseMeesage returnedResponse = response.as(ResponseMeesage.class);
        assertThat(returnedResponse.getMessage(),equalTo(ErrorMessages.PET_NOT_FOUND));
        assertThat(response.statusCode(),equalTo(404));
    }
    @Test
    public void createPetWithLongName() {
        Pet pet = PetSteps.generatePet();
        pet.setName("A".repeat(255));

        Response response = API.addingApi(pet);

        assertThat(response.statusCode(), equalTo(200));
    }
    @Test
    public void createSamePetTwice() {
        Pet pet = PetSteps.generatePet();

        API.addingApi(pet);
        Response response = API.addingApi(pet);

        assertThat(response.statusCode(),(equalTo(200)));
    }
}
