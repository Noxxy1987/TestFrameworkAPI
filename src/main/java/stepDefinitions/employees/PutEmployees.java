package stepDefinitions.employees;

import apiCommon.ApiConstants;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import utils.Logger;
import utils.TestContext;

import static io.restassured.RestAssured.put;

public class PutEmployees {

    @When("^Put request on v1/update with ID : (.*)$")
    public void putRequest(int employeeId) {
        Response response = put(ApiConstants.UPDATE_ENDOINT);
        Logger.log("response: ", response.getBody().asString());
        TestContext.INSTANCE.add("response", response);
    }

}
