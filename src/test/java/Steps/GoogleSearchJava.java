package Steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import pages.GoogleSearchPage;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


public class GoogleSearchJava {


    private WebDriver driver;
    private GoogleSearchPage googleSearchPage;
    private final StringBuilder sb = new StringBuilder();

    @Given("^I navigate to Google search page$")
    public void iNavigateToGoogleSearchPage() {

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        prefs.put("profile.default_content_settings.popups", 0);
        prefs.put("download.prompt_for_download", false);
        prefs.put("download.directory_upgrade", true);
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);

        ChromeOptions options = new ChromeOptions();
        options.addArguments(
                "--ignore-certificate-errors",
                "--disable-popup-blocking",
                "--incognito",
                "--disable-blink-features=AutomationControlled",
                "--disable-infobars",
                "--no-sandbox",
                "--disable-search-engine-choice-screen"
        );
        options.setExperimentalOption("prefs", prefs);
        //options.setExperimentalOption("excludeSwitches", List.of("enable-automation"));

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.google.com/");
    }

    @When("^I input \"([^\"]*)\" to the search textfield$")
    public void iInputToTheSearchTextfield(String arg0) {
        googleSearchPage = new GoogleSearchPage(driver);
        googleSearchPage.confirmCookiesBtn.click();
        googleSearchPage.searchTextField.sendKeys(arg0);
    }
    @And("^I click on search button$")
    public void iClickOnSearchButton() {
        googleSearchPage.searchTextField.sendKeys(Keys.ENTER);
    }

    @And("^I collected search results for finding \"([^\"]*)\"$")
    public void iCheckSearchResults(String arg0) {
        googleSearchPage.getResultsOnThePage(sb);// Smoke test, in case find it at the first page we can finish the test
        if (!sb.toString().contains(arg0)) {
            try {
                while (googleSearchPage.searchResultsPageLinkNext.isDisplayed()) {
                    //googleSearchPage.searchResultsPageLinkNext.click();
                    Actions actions = new Actions(driver);
                    actions.moveToElement(googleSearchPage.searchResultsPageLinkNext, 10, 0) // X, Y Offset
                            .click()
                            .build()
                            .perform();
                    googleSearchPage.getResultsOnThePage(sb);
                }
            } catch (NoSuchElementException ex) {
                System.out.println("it was the last page");
            }
        }
        driver.quit();
    }

    @Then("^I find \"([^\"]*)\"$")
    public void iFind(String arg0) {
         Assert.assertTrue(sb.toString().contains(arg0));
    }

    @Then("^I didn't find \"([^\"]*)\"$")
    public void iDidNotFind(String unExpectedValue) {
        String actualResult = sb.toString();
        assertThat(actualResult).as(String.format("Incorrect unExpectedValue value received %s",
                        actualResult ))
                .doesNotContainIgnoringCase(unExpectedValue);
    }
}
