package com.swagger.pet.base;

import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class Specs {
    public static RequestSpecification requestSpec(){
        return  given().baseUri("https://petstore.swagger.io")
                .header("Content-Type", "application/json").log().all();
    }
}
