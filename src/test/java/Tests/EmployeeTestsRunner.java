package Tests;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber", "json:target/jsonReports/EmployeeTests.json"},
        glue = "stepDefinitions",
        features = {
                "src/test/resources/features/getEmployees.feature"

        })
public class EmployeeTestsRunner {
}
