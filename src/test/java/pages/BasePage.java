package pages;

import driver.Driver;
import driver.WebDriverWaitConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class BasePage {


    protected WebDriverWaitConfig waitConfig;
    protected WebDriver driver;

    public BasePage() {
        driver = Driver.getDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        waitConfig = new WebDriverWaitConfig(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickOffset(WebElement element){
        Actions actions = new Actions(driver);
        actions.moveToElement(element, 10, 0) // X, Y Offset
                .click()
                .build()
                .perform();
    }
}