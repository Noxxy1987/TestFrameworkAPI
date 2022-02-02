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

public class Tests_Put {

    @Test
    public void putRequestWithHeader() {
        JSONObject request = new JSONObject();
        request.put("name", "John Doe");

        Response response = given()
                .header("ContentType","application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request.toJSONString())
                .when()
                .put(Constants.API_URL + Constants.USERS_ENDPOINT + "2");



        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("John Doe", response.jsonPath().get("name"));
        Logger.log("ID: "+ response.jsonPath().get("name"));
    }
}
