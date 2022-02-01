package stepDefinitions.employees;

import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Then;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import utils.Logger;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import utils.TestContext;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


public class GetEmployees {
    public static final String EMPLOYEE_URL = "http://dummy.restapiexample.com/api/v1/employees";

    @When("^Get request on v1/employees$")
    public void getRequest() {
        Response response = get(EMPLOYEE_URL);
        Logger.log("response: ", response.getBody().asString());
        TestContext.INSTANCE.add("response", response);
//        Assertions.assertEquals(200, response.getStatusCode());
    }

    @Then("^Response code is (.*)$")
    public void assertResponseCode(Integer code) {
        Response response = (Response) TestContext.INSTANCE.get("response");
        assertThat(String.format("Invalid response code! Expected: %s, Actual: %s", code, response.getStatusCode()), response.getStatusCode() == code, is(true));
    }

    @Then("^Response schema corresponds with baseline: (.*)$")
    public void assertResponseSchema(String baseline) {
        Response response = (Response) TestContext.INSTANCE.get("response");

        response.then().assertThat()
                .body(matchesJsonSchemaInClasspath(baseline));
    }

    @Then("^Response body corresponds with baseline: (.*)$")
    public void assertResponseBody(String baseline) {
        Response response = (Response) TestContext.INSTANCE.get("response");


    }

    @When("^Response headers are returned:")
    public void assertResponseHeaders(Map<String, String> headers) {
        Response response = (Response) TestContext.INSTANCE.get("response");
        StringBuilder errormessage = new StringBuilder();

        Map<String, String> actualheaders = response.getHeaders().asList().stream().sorted(Comparator.comparing(Header::getName)).collect(Collectors.toMap(Header::getName, Header::getValue, (v1, v2) -> v2));
//        Boolean identical = headers.entrySet().stream().allMatch(e -> e.getValue().equals(actualheaders.get(e.getKey())));

        assertThat(String.format("Expected headers size: %s, Actual: %s", headers.size(), actualheaders.entrySet().size()), headers.size() == actualheaders.entrySet().size(), is(true));
        for (String key : headers.keySet()) {

            if (!headers.get(key).equals(actualheaders.get(key))&&(!("ExpiresDate").contains(key))) {
                errormessage.append(String.format("invalid header value, Expected: %s, Actual: %s", headers.get(key), actualheaders.get(key)));
            }

        }
        assertThat(String.format("Failed: %s", errormessage.toString()), errormessage.toString().equals(""), is(true));
    }

    @DataTableType(replaceWithEmptyString = "[blank]")
    public String stringType(String cell) {
        return cell;
    }
}
