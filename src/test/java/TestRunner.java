
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
       plugin={"pretty","io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm",
                        "json:target/cucumber.json",
                        "html:test-output"},
        features = "src/test/resources/resources/test.feature",
        glue = {"StepDefinitions"},
        tags = "@TEST"
)

public class TestRunner {
    @BeforeClass
    public static void before() {
        RestAssured.filters(new AllureRestAssured());
    }
}






