package com.swagger.pet.steps;

import com.github.javafaker.Faker;
import com.swagger.pet.models.Category;
import com.swagger.pet.models.Order;
import com.swagger.pet.models.Pet;
import com.swagger.pet.models.Tag;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;

public class PetSteps {
    static Faker faker = new Faker();

    // Predefined realistic pet tags
    static List<String> petTags = Arrays.asList(
            "vaccinated", "friendly", "aggressive", "trained", "puppy"
    );

    // Pick a random one
    static String randomTag = petTags.get(faker.random().nextInt(petTags.size()));
    public static void add_pet(){
    }
    public static Category generateCategory(){
        Category category = new Category();
        long categoryId=faker.number().numberBetween(1,9223372036854775807L);
        category.setId(categoryId);
        String categoryName=faker.animal().name();
        category.setName(categoryName);
        return category;

    }
    public static Tag generateTag(){
        Tag tag = new Tag();
        long tagId=faker.number().numberBetween(1,9223372036854775807L);
        tag.setId(tagId);
        tag.setName(randomTag);
        return tag;

    }
    public static Pet generatePet(){
        Pet pet = new Pet();
        long petId=faker.number().numberBetween(1,9223372036854775807L);
        pet.setId(petId);
        pet.setCategory(generateCategory());
        String petName=faker.dog().name();
        pet.setName(petName);
        pet.setPhotoUrls(List.of("string"));
        pet.setTags(List.of(generateTag()));
        pet.setStatus("available");
        return pet;

    }
    public static Pet updatePet(Pet pet,String status){
        Pet updatePet = new Pet();
        updatePet.setId(pet.getId());
        updatePet.setStatus(status);
        updatePet.setCategory(pet.getCategory());
        updatePet.setName(pet.getName());
        updatePet.setPhotoUrls(pet.getPhotoUrls());
        updatePet.setTags(pet.getTags());
        return updatePet;

    }
    public static Order placeOrderStep(){

        Order order = new Order();
        order.setId(8);
        order.setPetId(9);
        order.setQuantity(5153);
        order.setShipDate("2026-01-12T19:14:26.563Z");
        order.setStatus(Order.orderStatus.PLACED);
        order.setComplete(true);
        return order;
    }
    public static void assertAllPetsHaveStatus(Response response,String status) {
        JsonPath js = response.jsonPath();
        List<String> statuses = js.getList("status");

        for (int i = 0; i < statuses.size(); i++) {
            Assert.assertEquals(statuses.get(i), status);
        }
    }


}
