package driver;

import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebDriverWaitConfig {
    private final WebDriver driver;
    private final int TIME_OUT = 500;

    public WebDriverWaitConfig(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriverWait webdriverWait() {
        return new WebDriverWait(driver, Duration.ofMillis(this.TIME_OUT));
    }

    public Wait<WebDriver> fluentWait(int timeout) {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofMillis(timeout))
                .pollingEvery(Duration.ofMillis(1))
                .ignoring(NotFoundException.class, StaleElementReferenceException.class)
                .withMessage("The element wasn't found");
    }

    public Wait<WebDriver> fluentWait() {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofMillis(TIME_OUT))
                .pollingEvery(Duration.ofMillis(1))
                .ignoring(NotFoundException.class, StaleElementReferenceException.class)
                .withMessage("The element wasn't found");
    }
}

