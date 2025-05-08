package cucumber.hooks;

import driver.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class DriverHooks {

    private static final WebDriver driver= Driver.getDriver();

    @After()
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            if (driver != null) {
                final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", scenario.getName());

            }
        }

        Driver.closeDriver();

    }


}
