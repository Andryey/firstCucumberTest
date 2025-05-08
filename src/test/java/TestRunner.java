import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/feature",
        //tags = "not @wip and not @WIP",
        tags = "@test",
        glue={"cucumber"},
        plugin =  {"pretty", "html:target/report/cucumber.html", "json:target/report/cucumber.json"}
)
public class TestRunner {
}
