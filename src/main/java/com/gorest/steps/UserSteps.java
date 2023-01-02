package com.gorest.steps;

import com.gorest.constants.EndPoints;
import com.gorest.model.UserPojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class UserSteps {

    @Step("Creating student with name : {0}, email: {1}, gender: {2}, status: {3}")
    public ValidatableResponse createUser(String name, String email, String gender, String status) {

        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer token 6c8869b06b7b02768418648115d359444252b9ba6428f17dd635d20e4f53a072")
                .body(userPojo)
                .when()
                .post()
                .then();
    }

    @Step("Getting the user information with name: {0}")

    public HashMap<String, Object> getUserInfoByName(String name) {
        String p1 = "findAll{it.name == '";
        String p2 = "'}.get(0)";
        return SerenityRest.given().log().all()
                .when()
                .get(EndPoints.GET_ALL_USERS)
                .then()
                .statusCode(200)
                .extract()
                .path(p1 + name + p2);
    }

    @Step("Updating User information with userId: {0}, name: {1}, email: {2}, gender: {3}, and status: {4}")

    public ValidatableResponse updateUser(int id, String name, String email,
                                          String gender, String status) {
        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer token 6c8869b06b7b02768418648115d359444252b9ba6428f17dd635d20e4f53a072")
                .pathParam("id", id)
                .body(userPojo)
                .when()
                .put(EndPoints.UPDATE_USER_BY_ID)
                .then();
    }

    @Step("Deleting user information with id: {0}")

    public ValidatableResponse deleteUser(int id) {
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer token 6c8869b06b7b02768418648115d359444252b9ba6428f17dd635d20e4f53a072")
                .pathParam("id", id)
                .when()
                .delete(EndPoints.DELETE_USER_BY_ID)
                .then();
    }

    @Step("Getting user information with id: {0}")
    public ValidatableResponse getUserById(int id) {
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer token 6c8869b06b7b02768418648115d359444252b9ba6428f17dd635d20e4f53a072")
                .pathParam("id", id)
                .when()
                .get(EndPoints.GET_SINGLE_USER_BY_ID)
                .then();
    }

}
