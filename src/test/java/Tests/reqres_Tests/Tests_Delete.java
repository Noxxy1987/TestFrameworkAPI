package Tests.reqres_Tests;

import io.restassured.response.Response;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class Tests_Delete {
    private static String API_URL = "https://reqres.in/api";
    private static String USERS_ENDPOINT = "/users";
    private static String USERS_ENDPOINT_UPDATE = "/users/2";
    @Test
    public void deleteRequest() {
            Response response =
                    when()
                     .delete(API_URL + USERS_ENDPOINT);



        Assertions.assertEquals(204, response.statusCode());
    }

    @Test
    public void deleteRequestAndCountUsers() {
        Response response =
                when()
                        .delete(API_URL + USERS_ENDPOINT);


        Assertions.assertEquals(204, response.statusCode());

        Response response2 = given().get(API_URL + USERS_ENDPOINT);

        Assertions.assertEquals(200, response2.statusCode());
        Assertions.assertEquals("12", response2.jsonPath().getString("total"));//normally user would actually get deleted
    }
}
