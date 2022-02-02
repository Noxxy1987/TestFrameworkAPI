package Tests.reqres_Tests;

import utils.Logger;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Map;


public class Tests_Get {
    //"https://demoqa.com/utilities/weather/city";
    //"https://reqres.in/api/users"

    @Test
    public void getRequest() {
        Response response = RestAssured.request(Method.GET, "https://demoqa.com/utilities/weather/city/Cluj-Napoca");
        Logger.log(response.getBody().asString());

        Assertions.assertEquals(200, response.getStatusCode());
    }

    @Test
    public void getWeatherRequest() {
        RestAssured.baseURI = "https://demoqa.com/utilities/weather/city";
        Response responseSimple = RestAssured.get("/Cluj-Napoca");

        RequestSpecification httpRequest = RestAssured.given();

        Response response = httpRequest.request(Method.GET, "/Cluj-Napoca");
        Logger.log("Response is: " + response.getBody().asString());

        Assertions.assertEquals(200, response.getStatusCode());
        Assertions.assertEquals("Cluj-Napoca", response.jsonPath().getString("City"));
    }

    @Test
    public void getRequestAndAssert() {

        given().header("ContentType", "application/json")
                .when()
                .get(Constants.API_URL+Constants.USERS_ENDPOINT)
                .then()
                .assertThat()
                .body("total", equalTo(12))
                .log().all();
    }

    @Test
    public void getAssertBody() {

        get(Constants.API_URL + Constants.USERS_ENDPOINT)
                .then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("baseline.json"));
    }

    @Test
    public void getUsersListAssert() {

        List<Map<String, Object>> list = get(Constants.API_URL + Constants.USERS_ENDPOINT)
                .jsonPath()
                .get("data");

        assertThat(String.format("Response incorrect! Expected: %s Actual: %s","1", list.get(0).get("id"))
                , list.get(0).get("id").equals(1)
                , is(true));
        assertThat(String.format("Response incorrect! Expected: %s Actual: %s","george.bluth@reqres.in", list.get(0).get("email"))
                , list.get(0).get("email").equals("george.bluth@reqres.in")
                , is(true));
        assertThat(String.format("Response incorrect! Expected: %s Actual: %s","George", list.get(0).get("first_name"))
                , list.get(0).get("first_name").equals("George")
                , is(true));
        assertThat(String.format("Response incorrect! Expected: %s Actual: %s","Bluth", list.get(0).get("last_name"))
                , list.get(0).get("last_name").equals("Bluth")
                , is(true));
        assertThat(String.format("Response incorrect! Expected: %s Actual: %s","https://reqres.in/img/faces/1-image.jpg", list.get(0).get("avatar"))
                , list.get(0).get("avatar").equals("https://reqres.in/img/faces/1-image.jpg")
                , is(true));
    }

    @Test
    public void getUsersList2() {

        RequestSpecification request = RestAssured.given();

        Response response =
                request
                        .queryParam("page","2")
                        .get(Constants.API_URL + Constants.USERS_ENDPOINT);

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals(2, (Integer)response.jsonPath().get("page"));
        Logger.log("We are on page :  " + response.jsonPath().get("page"));
    }

    @Test
    public void getSingleUser() {

        Map<String, Object> userData =
                given()
                        .get(Constants.API_URL + Constants.USERS_ENDPOINT + "/3")
                        .jsonPath()
                        .get("data");

        assertThat(String.format("Response Incorrect ! Expected: %s Actual: %s","Emma", userData.get("first_name"))
                , userData.get("first_name").equals("Emma")
                , is(true));
    }

    @Test
    public void getSingleUserNotFound() {

        Response response =
                given()
                        .get(Constants.API_URL + Constants.USERS_ENDPOINT + "/23");

        Assertions.assertEquals(404, response.statusCode());
        Logger.log("Re :  " + response.jsonPath().get("page"));
    }
}
