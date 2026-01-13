package com.swagger.pet.testCases;

import com.swagger.pet.apis.API;
import com.swagger.pet.models.Error;
import com.swagger.pet.models.Order;
import com.swagger.pet.models.User;
import com.swagger.pet.steps.PetSteps;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class NegativeTestcases {
    @Test
    public void findPetByWrongId() {
        Response response= API.findByIdAPI(82282);
        Error returnedResponse = response.as(com.swagger.pet.models.Error.class);
        assertThat(returnedResponse.getMessage(),equalTo("Pet not found"));
        assertThat(response.statusCode(),equalTo(404));
    }
    @Test
    public void findOrderByWrongId() {
        Response response= API.findOrderByID(24892);
        Error returnedResponse = response.as(com.swagger.pet.models.Error.class);
        assertThat(returnedResponse.getMessage(),equalTo("Order not found"));
        assertThat(response.statusCode(),equalTo(404));
    }
    @Test
    public void placeOrderWithWrongShipDate (){
        Order order = PetSteps.placeOrderManually();
        Response response = API.placeOrder(order);
        Error returnedResponse = response.as(Error.class);
        assertThat(returnedResponse.getMessage(),equalTo("something bad happened"));
        assertThat(response.statusCode(),equalTo(500));
    }
    @Test
    public void deleteOrderByWrongId (){
        Response response =API.deleteOrderByID(88927);
        Error returnedResponse = response.body().as(Error.class);
        assertThat(returnedResponse.getMessage(),equalTo("Order Not Found"));
        assertThat(response.statusCode(), equalTo(404));
    }
    @Test
    public void findingByWrongUsername ()
    {
        Response response=API.findByUsername("ahmed.mousa");
        Error returnedResponse=response.as(Error.class);
        assertThat(returnedResponse.getMessage(),equalTo("User not found"));
        assertThat(response.statusCode(),equalTo(404));

    }
}
