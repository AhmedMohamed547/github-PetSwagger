package com.swagger.pet.apis;
import com.swagger.pet.base.Specs;
import com.swagger.pet.data.Route;
import com.swagger.pet.models.Order;
import com.swagger.pet.models.Pet;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;



public class API {
    public static Response addingApi(Pet p){
        return given().spec(Specs.requestSpec()).body(p).when().post(Route.ADDING_API)
                .then()
                .extract().response();
    }
    public static Response updatingApi(Pet p){
        return given().spec(Specs.requestSpec()).body(p).when().post(Route.ADDING_API)
                .then()
                .extract().response();
    }
    public static Response findByStatusAPI(String status){
        return given().spec(Specs.requestSpec()).queryParam("status", status)
                .log().all().when()
                .get(Route.FINDING_BY_STATUS_API)
                .then().log().all()
                .extract().response();
    }
    public static Response findByIdAPI(long id){
        return given().pathParams("id", id).
                spec(Specs.requestSpec())
                .when().get(Route.FINDING_BY_ID_API)
                .then().log().body()
                .extract().response();
    }
    public static Response findOrderByID(int id){
        return given().pathParams("id", id).
                spec(Specs.requestSpec())
                .when().get(Route.FIND_ORDER_BY_ID)
                .then().log().body()
                .extract().response();
    }
    public static Response deleteOrderByID(int id){
        return given().pathParams("id", id).
                spec(Specs.requestSpec())
                .when().delete(Route.DELETE_ORDER_BY_ID)
                .then().log().body()
                .extract().response();
    }

    public static Response getPetInv(){
        return given().spec(Specs.requestSpec())
                .when().get(Route.GET_PET_INVENTORIES)
                .then().log().all().extract().response();
    }
    public static Response placeOrder(Order o){
        return given().spec(Specs.requestSpec()).body(o).when()
                .post(Route.PLACE_AN_ORDER)
                .then().log().all()
                .extract().response();

    }
}
