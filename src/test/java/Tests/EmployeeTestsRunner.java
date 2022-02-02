package Tests;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber", "json:target/jsonReports/EmployeeTests.json"},
        glue = "stepDefinitions",
        features = {
                "src/test/resources/features/createEmployee.feature",
                "src/test/resources/features/getEmployee.feature",
                "src/test/resources/features/getEmployees.feature",
                "src/test/resources/features/putEmployee.feature",
                "src/test/resources/features/deleteEmployee.feature"

        })
public class EmployeeTestsRunner {
}
