package com.gorest.gorestinfo;

import com.gorest.steps.UserSteps;
import com.gorest.testbase.TestBase;
import com.gorest.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class UserCRUDTestWithSteps extends TestBase {

    static String name = "PrimUser" + TestUtils.getRandomValue();
    static String email = TestUtils.getRandomValue() + "xyz@gmail.com";
    static String gender = "male";
    static String status = "active";
    static int id;

    @Steps
    UserSteps userSteps;

    @Title("This will create new user")
    @Test
    public void test001() {
        ValidatableResponse response = userSteps.createUser(name, email, gender, status);
        response.log().all().statusCode(201);
    }

    @Title("Verify if the user was added to the application")
    @Test
    public void test002() {
        HashMap<String, Object> userMap = userSteps.getUserInfoByName(name);
        Assert.assertThat(userMap, hasValue(name));
        id = (int) userMap.get("id");
    }

    @Title("Update the user information and verify the udated information")
    @Test
    public void test003() {
        name = name + "_updated";
        List<String> courseList = new ArrayList<>();
        userSteps.updateUser(id, name, email, gender, status)
                .log().all().statusCode(200);
        // verify update
        HashMap<String, Object> userMap = userSteps.getUserInfoByName(name);
        Assert.assertThat(userMap, hasValue(name));

    }

    @Title("Delete the user and verify if the student is deleted!")
    @Test
    public void test004() {
        userSteps.deleteUser(id).statusCode(204);
        userSteps.getUserById(id).statusCode(404);

    }
}
