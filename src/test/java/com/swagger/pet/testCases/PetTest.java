package com.swagger.pet.testCases;

import com.swagger.pet.apis.API;
import com.swagger.pet.models.Error;
import com.swagger.pet.models.Order;
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
        String status = "pending";
        Response response = API.findByStatusAPI(status);
        PetSteps.assertAllPetsHaveStatus(response,status);
    }
    @Test
    public void findPetsByStatus1() {
        String status = "pending";
        Response response = API.findByStatusAPI(status);
        PetSteps.assertAllPetsHaveStatus(response,status);
    }
    @Test
    public void findPetsByStatus2() {
        String status = "available";
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
    public void findingByID ()
    {
        Order order = PetSteps.placeOrderStep();
        API.placeOrder(order);
        Response response =API.findOrderByID(order.getId());
        Order returnedResponse = response.body().as(Order.class);
        assertThat(returnedResponse.getId(),equalTo(order.getId()));
        assertThat(response.statusCode(), equalTo(200));
    }
    @Test
    public void getPetInventories (){
        Response response=API.getPetInv();
        assertThat(response.statusCode(),equalTo(200));
    }
    @Test
    public void placeOrder (){
        Order order = PetSteps.placeOrderStep();
        Response response = API.placeOrder(order);
        assertThat(response.statusCode(),equalTo(200));
    }
    @Test
    public void deleteOrder (){
        Order order = PetSteps.placeOrderStep();
        API.placeOrder(order);
        Response response =API.deleteOrderByID(order.getId());
        Error returnedResponse = response.body().as(Error.class);
        assertThat(returnedResponse.getMessage(),equalTo(String.valueOf(order.getId())));
        assertThat(response.statusCode(), equalTo(200));
    }




}
