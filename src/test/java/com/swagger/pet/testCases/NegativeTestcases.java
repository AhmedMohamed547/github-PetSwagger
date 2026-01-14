package com.swagger.pet.testCases;

import com.swagger.pet.apis.API;
import com.swagger.pet.data.ErrorMessages;
import com.swagger.pet.models.ResponseMeesage;
import com.swagger.pet.models.Order;
import com.swagger.pet.steps.PetSteps;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class NegativeTestcases {
    @Test
    public void findPetByWrongId() {
        Response response= API.findByIdAPI(82282);
        ResponseMeesage returnedResponse = response.as(ResponseMeesage.class);
        assertThat(returnedResponse.getMessage(),equalTo(ErrorMessages.PET_NOT_FOUND));
        assertThat(response.statusCode(),equalTo(404));
    }
    @Test
    public void findOrderByWrongId() {
        Response response= API.findOrderByID(24892);
        ResponseMeesage returnedResponse = response.as(ResponseMeesage.class);
        assertThat(returnedResponse.getMessage(),equalTo(ErrorMessages.ORDER_NOT_FOUND));
        assertThat(response.statusCode(),equalTo(404));
    }
    @Test
    public void placeOrderWithWrongShipDate (){
        Order order = PetSteps.placeOrderManually();
        Response response = API.placeOrder(order);
        ResponseMeesage returnedResponse = response.as(ResponseMeesage.class);
        assertThat(returnedResponse.getMessage(),equalTo(ErrorMessages.SOMETHING_BAD_HAPPENED));
        assertThat(response.statusCode(),equalTo(500));
    }
    @Test
    public void deleteOrderByWrongId (){
        Response response =API.deleteOrderByID(88927);
        ResponseMeesage returnedResponse = response.body().as(ResponseMeesage.class);
        assertThat(returnedResponse.getMessage(),equalTo(ErrorMessages.ORDER_NOT_FOUND1));
        assertThat(response.statusCode(), equalTo(404));
    }
    @Test
    public void findingByWrongUsername ()
    {
        Response response=API.findByUsername("ahmed.mousa");
        ResponseMeesage returnedResponse=response.as(ResponseMeesage.class);
        assertThat(returnedResponse.getMessage(),equalTo(ErrorMessages.USER_NOT_FOUND));
        assertThat(response.statusCode(),equalTo(404));

    }
}
