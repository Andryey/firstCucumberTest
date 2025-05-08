package cucumber.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;
import pages.GoogleSearchPage;

import static org.assertj.core.api.Assertions.assertThat;


public class GoogleSearchJava {


    private final StringBuilder sb = new StringBuilder();
    private GoogleSearchPage googleSearchPage = new GoogleSearchPage();

    @Given("^I navigate to Google search page$")
    public void iNavigateToGoogleSearchPage() {
        googleSearchPage.navigateToGoogleSearchPage();
    }

    @When("^I input \"([^\"]*)\" to the search textfield$")
    public void iInputToTheSearchTextfield(String arg0) {

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
                    googleSearchPage.clickOffset();
                    googleSearchPage.getResultsOnThePage(sb);
                }
            } catch (NoSuchElementException ex) {
                System.out.println("it was the last page");
            }
        }
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
