package com.swagger.pet.testCases;

import com.swagger.pet.apis.API;
import com.swagger.pet.data.ErrorMessages;
import com.swagger.pet.models.Order;
import com.swagger.pet.models.Pet;
import com.swagger.pet.models.ResponseMeesage;
import com.swagger.pet.models.User;
import com.swagger.pet.steps.PetSteps;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;

public class EdgeTestcases {
    @Test
    public void createPetWithlongName() {
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
    @Test
    public void deleteOrderTwice (){
        Order order = PetSteps.placeOrderStep();
        API.placeOrder(order);
        Response response =API.deleteOrderByID(order.getId());
        assertThat(response.statusCode(), equalTo(200));
        ResponseMeesage returnedResponse = response.body().as(ResponseMeesage.class);
        assertThat(returnedResponse.getMessage(),equalTo(String.valueOf(order.getId())));
        Response response1 =API.deleteOrderByID(order.getId());
        assertThat(response1.statusCode(), equalTo(404));
        ResponseMeesage returnedResponse1 = response1.body().as(ResponseMeesage.class);
        assertThat(returnedResponse1.getMessage(),equalTo(ErrorMessages.ORDER_NOT_FOUND1));
    }

    @Test
    public void placeOrderWithZeroQuantity() {
        Order order = PetSteps.placeOrderStep();
        order.setQuantity(0);

        Response response = API.placeOrder(order);

        assertThat(response.statusCode(), equalTo(200));
    }
    @Test
    public void placeOrderWithLargeQuantity() {
        Order order = PetSteps.placeOrderStep();
        order.setQuantity(Integer.MAX_VALUE);

        Response response = API.placeOrder(order);

        assertThat(response.statusCode(), equalTo(200));
    }
    @Test
    public void placeOrderWithFutureShipDate() {
        Order order = PetSteps.placeOrderStep();
        order.setShipDate("2035-01-01T10:00:00.000Z");

        Response response = API.placeOrder(order);

        assertThat(response.statusCode(), equalTo(200));
    }

}
