package Tests.reqres_Tests;

import static io.restassured.RestAssured.*;

import utils.Logger;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;


import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


public class Tests_Post {
    @Test
    public void postRequest() {
        JSONObject request = new JSONObject();
        request.put("name", "John Doe");

        Response response = given()
                .body(request.toJSONString())
                .when()
                .post(Constants.API_URL + Constants.USERS_ENDPOINT);



        Assertions.assertEquals(201, response.statusCode());
        Logger.log("ID: "+ response.jsonPath().get("id"));
    }

    @Test
    public void postRequestWithHeader() {
        JSONObject request = new JSONObject();
        request.put("name", "John Doe");

        Response response = given()
                .header("ContentType","application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request.toJSONString())
                .when()
                .post(Constants.API_URL + Constants.USERS_ENDPOINT);



        Assertions.assertEquals(201, response.statusCode());
        Logger.log("ID: "+ response.jsonPath().get("id"));
    }

    @Test
    public void postRegisterNoEmailOrUser() {

        JSONObject request = new JSONObject();
            request.put("user", "morocz.hunor@pentalog.com");
            request.put("password","cookie");

        Response response = given()
                .body(request.toJSONString())
                .when()
                .post(Constants.API_URL + Constants.REGISTER_ENDPOINT);

        Assertions.assertEquals(400, response.statusCode());
        Logger.log("Response : "+ response.body().prettyPrint());
    }

    @Test
    public void postRegisterWithoutPwd() {

        JSONObject request = new JSONObject();
        request.put("email", "morocz.hunor@pentalog.com");

        Response response = given()
                .body(request.toJSONString())
                .when()
                .post(Constants.API_URL + Constants.REGISTER_ENDPOINT);

        Assertions.assertEquals(400, response.statusCode());
        Logger.log("Response : "+ response.statusCode());
    }

}
