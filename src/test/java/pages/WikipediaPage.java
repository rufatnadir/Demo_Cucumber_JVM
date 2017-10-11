package pages;

import core.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;


public class WikipediaPage extends BaseClass {

    public WikipediaPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id='searchInput']")
    public WebElement search_field;

    @FindBy(xpath = "//*[@type='submit']")
    public WebElement search_button;

    @FindBy(xpath = ".//*[@id='firstHeading']")
    public WebElement page_heading;

    @FindBy(xpath = ".//*[@id='toc']")
    public WebElement contents_panel;

    @FindBy(xpath = ".//a[@class='togglelink' and @role='button']")
    public WebElement content_togglelink;

    @FindBy(xpath = "//*[@class='suggestions-results']/a")
    public List<WebElement> autocomplete_drop_down_list;

}
