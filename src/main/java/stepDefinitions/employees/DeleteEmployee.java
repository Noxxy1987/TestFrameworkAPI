package stepDefinitions.employees;

import apiCommon.ApiConstants;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import utils.Logger;
import utils.TestContext;

import static io.restassured.RestAssured.delete;

public class DeleteEmployee {

    @When("^Create delete request: (.*)$")
    public void deleteSingleEmployee(String id) {
        if (id.equals("[blank])")) {
            id = "";
        }
        Response response = delete( ApiConstants.DELETE_ENDPOINT+"2");
        Logger.log("response: "+ response.getBody().asString());
        TestContext.INSTANCE.add("response", response);
    }
}
