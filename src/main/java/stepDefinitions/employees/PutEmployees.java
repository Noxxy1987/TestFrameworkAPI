package stepDefinitions.employees;

import apiCommon.ApiConstants;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import utils.Logger;
import utils.TestContext;

import static io.restassured.RestAssured.put;

public class PutEmployees {

    @When("^Put request on v1/update with id : (.*)$")
    public void putRequestId(int employeeId) {
        Response response = put(ApiConstants.UPDATE_ENDOINT+employeeId);
        Logger.log("response: ", response.getBody().asString());
        TestContext.INSTANCE.add("response", response);
    }

    @When("^Put request on v1/update for the created resource$")
    public void putRequestManipulatedId() {
        String employeeId = "23";
        //endpoint only has mock data, employee is not created - using available data
        //String id = ((Map<String, Object>) response.jsonPath().get("data")).get("id");

        Response response = put(ApiConstants.UPDATE_ENDOINT+employeeId);
        Logger.log("response: ", response.getBody().asString());
        TestContext.INSTANCE.add("response", response);
    }

}
