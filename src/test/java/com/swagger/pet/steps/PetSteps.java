package com.swagger.pet.steps;

import com.github.javafaker.Faker;
import com.swagger.pet.models.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;

public class PetSteps {
    static Faker faker = new Faker();

    static List<String> petTags = Arrays.asList(
            "vaccinated", "friendly", "aggressive", "trained", "puppy"
    );
    static String randomTag = petTags.get(faker.random().nextInt(petTags.size()));
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
    public static Pet generatePet() {
        Pet pet = new Pet();
        pet.setId(faker.number().numberBetween(1, 9223372036854775807L));
        pet.setCategory(generateCategory());
        pet.setName(faker.dog().name());
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
            order.setId(faker.number().numberBetween(1,50));
            order.setPetId(faker.number().numberBetween(1,50));
            order.setQuantity(faker.number().numberBetween(1,50));
            order.setShipDate("2026-01-12T19:14:26.563Z");
            order.setStatus(Order.orderStatus.PLACED);
            order.setComplete(true);
            return order;
        }
    public static Order placeOrderManually(){

        Order order = new Order();
        order.setId(-6546);
        order.setPetId(-70);
        order.setQuantity(-10);
        order.setShipDate("qhatkh");
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
    public static List<User> createListOfUsers(){
        User user = new User();
        user.setUserId(faker.number().numberBetween(1,54556456));
        user.setUserName(faker.name().username());
        user.setFirstName(faker.name().firstName());
        user.setLastName(faker.name().lastName());
        user.setEmail(faker.internet().emailAddress());
        user.setPassword(faker.internet().password());
        user.setPhone(faker.phoneNumber().cellPhone());
        user.setUserStatus(1);
        return List.of(user);
    }
    public static List<User> createListOfUserss(){
        User user = new User();
        user.setUserId(-544);
        user.setUserName("1542");
        user.setFirstName(faker.name().firstName());
        user.setLastName(faker.name().lastName());
        user.setEmail(faker.internet().emailAddress());
        user.setPassword(faker.internet().password());
        user.setPhone(faker.phoneNumber().cellPhone());
        user.setUserStatus(1);
        return List.of(user);
    }
    public static User loginToSystem(){
        User user = (User) createListOfUsers();
        user.setUserName(user.getUserName());
        user.setPassword(user.getPassword());
        return user;
    }


}
