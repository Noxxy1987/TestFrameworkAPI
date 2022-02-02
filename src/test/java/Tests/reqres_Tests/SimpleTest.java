package Tests.reqres_Tests;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;


public class SimpleTest {
    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://reqres.in/api";
    }

    @Test
    public void getRequest() {

        Response response = RestAssured.get("https://reqres.in/api/users");

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("12", response.jsonPath().getString("total"));
    }

}
