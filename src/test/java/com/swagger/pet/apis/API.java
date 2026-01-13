package com.swagger.pet.apis;
import com.swagger.pet.base.Specs;
import com.swagger.pet.data.Route;
import com.swagger.pet.models.Order;
import com.swagger.pet.models.Pet;
import com.swagger.pet.models.User;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;



public class API {
    public static Response addingApi(Pet p) {
        return given().spec(Specs.requestSpec()).body(p).when().post(Route.ADDING_API)
                .then()
                .extract().response();
    }

    public static Response updatingApi(Pet p) {
        return given().spec(Specs.requestSpec()).body(p).when().post(Route.ADDING_API)
                .then()
                .extract().response();
    }

    public static Response findByStatusAPI(String status) {
        return given().spec(Specs.requestSpec()).queryParam("status", status)
                .log().all().when()
                .get(Route.FINDING_BY_STATUS_API)
                .then().log().all()
                .extract().response();
    }

    public static Response findByIdAPI(long id) {
        return given().pathParams("id", id).
                spec(Specs.requestSpec())
                .when().get(Route.FINDING_BY_ID_API)
                .then().log().body()
                .extract().response();
    }

    public static Response findOrderByID(int id) {
        return given().pathParams("id", id).
                spec(Specs.requestSpec())
                .when().get(Route.FIND_ORDER_BY_ID)
                .then().log().body()
                .extract().response();
    }

    public static Response deleteOrderByID(int id) {
        return given().pathParams("id", id).
                spec(Specs.requestSpec())
                .when().delete(Route.DELETE_ORDER_BY_ID)
                .then().log().body()
                .extract().response();
    }

    public static Response getPetInv() {
        return given().spec(Specs.requestSpec())
                .when().get(Route.GET_PET_INVENTORIES)
                .then().log().all().extract().response();
    }

    public static Response placeOrder(Order o) {
        return given().spec(Specs.requestSpec()).body(o).when()
                .post(Route.PLACE_AN_ORDER)
                .then().log().all()
                .extract().response();

    }

    public static Response createUser(List<User> u) {
        return given().spec(Specs.requestSpec()).body(u).when()
                .post(Route.CREATE_USER)
                .then().log().all()
                .extract().response();
    }

    public static Response findByUsername(String userName) {
        return given().pathParams("userName", userName).spec(Specs.requestSpec()).when()
                .get(Route.FIND_BY_USERNAME)
                .then().log().all()
                .extract().response();
    }

    public static Response loginUser(String userName, String password) {
        return given().queryParam("username", userName).queryParam("password", password)
                .spec(Specs.requestSpec()).when()
                .get(Route.LOGIN_TO_SYSTEM)
                .then().log().all()
                .extract().response();
    }

    public static Response logoutUser() {
        return given()
                .spec(Specs.requestSpec()).when()
                .get(Route.LOGOUT_FROM_SYSTEM)
                .then().log().all()
                .extract().response();
    }
}
