package Steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.GoogleSearchPage;

import java.util.concurrent.TimeUnit;


public class GoogleSearchJava {


    private WebDriver driver;
    private GoogleSearchPage googleSearchPage;
    private StringBuilder sb = new StringBuilder();

    @Given("^I navigate to Google search page$")
    public void iNavigateToGoogleSearchPage() {
        System.setProperty("webdriver.chrome.driver", "src\\test\\java\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.google.com/");
    }

    @When("^I input \"([^\"]*)\" to the search textfield$")
    public void iInputToTheSearchTextfield(String arg0) {
        googleSearchPage = new GoogleSearchPage(driver);
        googleSearchPage.searchTextField.sendKeys(arg0);
    }
    @And("^I click on search button$")
    public void iClickOnSearchButton() {
        googleSearchPage.searchTextField.sendKeys(Keys.ENTER);
    }

    @And("^I collected search results for finding \"([^\"]*)\"$")
    public void iCheckSearchResults(String arg0) {
        googleSearchPage.checkResultsOnThePage(sb);// Smoke test, in case find it at the first page we can finish the test
        if (!sb.toString().contains(arg0)) {
            try {
                while (googleSearchPage.searchResultsPageLinkNext.isDisplayed()) {
                    googleSearchPage.searchResultsPageLinkNext.click();
                    googleSearchPage.checkResultsOnThePage(sb);
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
}
