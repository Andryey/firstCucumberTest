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

    @FindBy(how = How.CSS, using = "#L2AGLb")
    public WebElement confirmCookiesBtn;

    @FindBy(how = How.CSS, using = "textarea[name=q]")
    public WebElement searchTextField;

    @FindAll(@FindBy(how = How.CSS, using = "#rso > div > div > div > div:nth-child(2) > div > span"))
    public List<WebElement> pageResultElements;

    @FindBy(how = How.CSS, using = "#pnnext > span:nth-child(2)")
    public WebElement searchResultsPageLinkNext;

    public void getResultsOnThePage(StringBuilder sb) {
        for (WebElement element : pageResultElements) {
            sb.append(element.getText());
        }
    }
}
