package com.swagger.pet.testCases;

import com.swagger.pet.apis.API;
import com.swagger.pet.data.ErrorMessages;
import com.swagger.pet.models.ResponseMeesage;
import com.swagger.pet.models.User;
import com.swagger.pet.steps.PetSteps;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class UserTests {
    @Test
    public void createUserList()
    {
        List<User> user = PetSteps.createListOfUsers();
        Response response= API.createUser(user);
        ResponseMeesage returnedResponse=response.body().as(ResponseMeesage.class);
        assertThat(returnedResponse.getMessage(),equalTo("ok"));
        assertThat(returnedResponse.getCode(),equalTo(200));
    }
    @Test
    public void findingByUsername ()
    {
        List<User> user = PetSteps.createListOfUsers();
        API.createUser(user);
        User u = user.get(0);
        Response response =API.findByUsername(u.getUserName());
        User returnedResponse=response.as(User.class);
        assertThat(returnedResponse.getUserName(),equalTo(u.getUserName()));
        assertThat(returnedResponse.getEmail(),equalTo(u.getEmail()));

    }
    @Test
    public void loginToSystem ()
    {
        List<User> user = PetSteps.createListOfUsers();
        API.createUser(user);
        User u = user.get(0);
        Response response =API.loginUser(u.getUserName(),u.getPassword());
        ResponseMeesage returnedResponse=response.as(ResponseMeesage.class);
        assertThat(returnedResponse.getCode(),equalTo(200));
    }
    @Test
    public void logoutFromSystem ()
    {
        Response response =API.logoutUser();
        ResponseMeesage returnedResponse = response.as(ResponseMeesage.class);
        assertThat(returnedResponse.getMessage(),equalTo("ok"));
        assertThat(returnedResponse.getCode(),equalTo(200));
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
