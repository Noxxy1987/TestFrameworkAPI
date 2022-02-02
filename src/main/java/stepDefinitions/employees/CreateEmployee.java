package stepDefinitions.employees;

import apiCommon.ApiConstants;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import utils.Logger;
import utils.TestContext;

import java.util.Map;

import static io.restassured.RestAssured.given;


public class CreateEmployee {

    @Given("^Create employee request:")
    public void createRequest(Map<String, String> employeeData) {
        JSONObject request = new JSONObject();
        for (String key : employeeData.keySet()) {
            request.put(key, employeeData.get(key));
        }
        TestContext.INSTANCE.add("request", request);
    }

    @Given("^Create empty request:")
    public void createEmptyRequest() {
        JSONObject request = new JSONObject();
        TestContext.INSTANCE.add("request", request);

    }

    @When("^Send post request on /create$")
    public void sendPostRequest() {
        JSONObject request = (JSONObject) TestContext.INSTANCE.get("request");

        Response response = given()
                .contentType("application/json")
                .body(request.toJSONString())
                .when()
                .post(ApiConstants.CREATE_ENDPOINT);


        Logger.log("response: " + response.getBody().asString());
        TestContext.INSTANCE.add("response", response);
    }

}
