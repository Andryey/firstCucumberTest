package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class GoogleSearchPage {

    public GoogleSearchPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.CSS, using = "#lst-ib")
    public WebElement searchTextField;

    @FindAll(@FindBy(how = How.CSS, using = "#rso > div > div > div > div > div > div > div > span"))
    private List<WebElement> pageResultElements;

    @FindBy(how = How.CSS, using = "#pnnext")
    public WebElement searchResultsPageLinkNext;

    public void checkResultsOnThePage(StringBuilder sb) {
        for (WebElement element : pageResultElements) {
            sb.append(element.getText());
        }
    }
}
