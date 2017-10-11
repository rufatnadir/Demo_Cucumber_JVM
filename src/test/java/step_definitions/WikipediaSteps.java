package step_definitions;

import core.Hooks;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import helpers.PropertyReader;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.WikipediaPage;

import static core.BaseClass.*;

public class WikipediaSteps {

    private static PropertyReader props = new PropertyReader();
    private WebDriver driver = Hooks.getDriver();
    private static Logger logger = LoggerFactory.getLogger(WikipediaSteps.class);

    public WikipediaSteps() throws Exception {
    }

    @Given("^Navigate to \"([^\"]*)\"$")
    public void navigateTo(String testPage) {
        String url = props.readProperty(testPage);

        driver.navigate().to(url);
        logger.info("Navigate to "+url);
    }

    @Then("^Type \"([^\"]*)\" into the Search Bar$")
    public void typeIntoTheSearchBar(String textToSearch) {
        WikipediaPage wikiPage = PageFactory.initElements(driver, WikipediaPage.class);

        insertText(wikiPage.search_field, textToSearch);
    }

    @And("^Click on the Search Button$")
    public void clickOnTheSearchButton() {
        WikipediaPage wikiPage = PageFactory.initElements(driver, WikipediaPage.class);

        clickElementWhenClickable(wikiPage.search_button);
    }

    @Then("^Verify that \"([^\"]*)\" page opened$")
    public void verifyThatPageOpened(String pageName) {
        String expectedTitle = "";
            if (pageName.equalsIgnoreCase("Puppies")) {
                expectedTitle = "Puppy - Wikipedia";
            } else if (pageName.equalsIgnoreCase("France")) {
                expectedTitle = "France - Wikipedia";
            }

        Assert.assertTrue("Cannot verify page title", checkTitleEquals(expectedTitle));
        logger.info("Page '"+driver.getTitle()+"' opened");
    }

    @Given("^Find \"([^\"]*)\" panel on the page$")
    public void findContentsPanelOnThePage(String panelName) {
        WikipediaPage wikiPage = PageFactory.initElements(driver, WikipediaPage.class);
        WebElement element = null;

        if (panelName.equalsIgnoreCase("Contents")) {
            element = wikiPage.contents_panel;
        }
        Assert.assertTrue("Element not displayed", element.isDisplayed());
        logger.info("Element found on page");
    }

    @Then("^Hide \"([^\"]*)\" panel$")
    public void hidePanel(String panel) {
        WikipediaPage wikiPage = PageFactory.initElements(driver, WikipediaPage.class);

        WebElement element = null;

        if (panel.equalsIgnoreCase("Contents")) {
            element = wikiPage.content_togglelink;
        }

        if (element.getText().equalsIgnoreCase("show")) {
            logger.info("Panel is hidden");
        } else {
            element.click();
            logger.info("Hiding panel");
        }
    }

    @Then("^Close browser$")
    public void closeBrowser() {
        logger.info("Close browser");
        driver.quit();
    }

    @And("^Select \"([^\"]*)\" in autocomplete dropdown$")
    public void selectInAutocompleteDropdown(String item) {
        WikipediaPage wikiPage = PageFactory.initElements(driver, WikipediaPage.class);

        int result = 0;
        for (int i = 0; i < wikiPage.autocomplete_drop_down_list.size(); i++) {
            if (wikiPage.autocomplete_drop_down_list.get(i).getText().contentEquals(item)) {
                wikiPage.autocomplete_drop_down_list.get(i).click();
                result++;
                break;
            }
        }
        if (result == 0) {
            logger.warn("Couldn't find '" + item + "' in autocomplete drop down list");
        }
    }
}
