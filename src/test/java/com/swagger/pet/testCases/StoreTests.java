package com.swagger.pet.testCases;

import com.swagger.pet.apis.API;
import com.swagger.pet.data.ErrorMessages;
import com.swagger.pet.models.Order;
import com.swagger.pet.models.ResponseMeesage;
import com.swagger.pet.steps.PetSteps;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class StoreTests {
    @Test
    public void getPetInventories (){
        Response response= API.getPetInv();
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
        assertThat(response.statusCode(), equalTo(200));
        ResponseMeesage returnedResponse = response.body().as(ResponseMeesage.class);
        assertThat(returnedResponse.getMessage(),equalTo(String.valueOf(order.getId())));
    }
    @Test
    public void findOrderByID (){
        Order order = PetSteps.placeOrderStep();
        API.placeOrder(order);
        Response response =API.findOrderByID(order.getId());
        Order returnedResponse = response.body().as(Order.class);
        assertThat(returnedResponse.getId(),equalTo(order.getId()));
        assertThat(response.statusCode(), equalTo(200));
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
