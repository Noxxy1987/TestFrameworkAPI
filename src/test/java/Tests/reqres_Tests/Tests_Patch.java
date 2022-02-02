package Tests.reqres_Tests;

import utils.Logger;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static io.restassured.RestAssured.given;

public class Tests_Patch {

    @Test
    public void patchRequestWithHeader() {
        JSONObject request = new JSONObject();
        request.put("name", "Albert Einstein");

        Response response = given()
                .header("ContentType","application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request.toJSONString())
                .when()
                .patch(Constants.API_URL + Constants.USERS_ENDPOINT + "/2");



        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("Albert Einstein", response.jsonPath().get("name"));
        Logger.log("ID: "+ response.jsonPath().get("name"));
    }
}
