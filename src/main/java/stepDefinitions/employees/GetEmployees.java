package stepDefinitions.employees;

import apiCommon.ApiConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Then;
import io.restassured.http.Header;
import model.responses.Employees;
import utils.Logger;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import utils.TestContext;

import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class GetEmployees {

    @When("^Get request on v1/employees$")
    public void getRequest() {
        Response response = get(ApiConstants.EMPLOYEES_ENDPOINT);
        Logger.log("response: ", response.getBody().asString());
        TestContext.INSTANCE.add("response", response);
    }

    @Then("^Response code is (.*)$")
    public void assertResponseCode(Integer code) {
        Response response = (Response) TestContext.INSTANCE.get("response");
        assertThat(String.format("Invalid response code! Expected: %s, Actual: %s", code, response.getStatusCode()), response.getStatusCode() == code, is(true));
    }

    @Then("^Response status is (.*)$")
    public void assertResponseStatus(String status) {
        Response response = (Response) TestContext.INSTANCE.get("response");
        assertThat(String.format("Invalid response code! Expected: %s, Actual: %s", status, response.getStatusLine()), response.getStatusLine().contains(status), is(true));
    }

    @Then("^Response schema corresponds with baseline: (.*)$")
    public void assertResponseSchema(String baseline) {
        Response response = (Response) TestContext.INSTANCE.get("response");

        response.then().assertThat()
                .body(matchesJsonSchemaInClasspath(baseline));
    }

    @Then("^Response body contains expected number of employees: (.*)")
    public void assertResponseBody(int number) throws JsonProcessingException {
        Response response = (Response) TestContext.INSTANCE.get("response");
        String body = response.getBody().asString();
        ObjectMapper mapper = new ObjectMapper();
        try {
            Employees employees = mapper.readValue(body, Employees.class);

            assertThat("Number of employees is returned is different from expected", employees.getData().size() == number, is(true));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
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
