package stepDefinitions.employees;

import utils.Logger;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import utils.TestContext;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


public class GetEmployees {
    public static final String EMPLOYEE_URL = "http://dummy.restapiexample.com/api/v1/employees";

    @When("^Get request on v1/employees$")
     public void getRequest() {
        Response response = get( EMPLOYEE_URL);
        Logger.log(response.getBody().asString());
        TestContext.INSTANCE.add("response", response);
//        Assertions.assertEquals(200, response.getStatusCode());
    }

    @When("^Response is (.*)$")
    public void assertGetRequest(String code) {
        Response response = (Response) TestContext.INSTANCE.get("response");
//        assertThat();
    }


}
