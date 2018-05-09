package step_definitions;

import core.Hooks;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import helpers.PropertyReader;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.LinkedinPage;

import static core.BaseClass.*;


/**
 * Created by rnadir on 12/14/17.
 */
public class LinkedinPageSteps {

    private static PropertyReader props = new PropertyReader();
    private WebDriver driver = Hooks.getDriver();
    private static Logger logger = LoggerFactory.getLogger(LinkedinPageSteps.class);
    private LinkedinPage linkedinPage = PageFactory.initElements(driver, LinkedinPage.class);

    public LinkedinPageSteps() throws Exception {}

    @Then("^Login to \"([^\"]*)\" as \"([^\"]*)\"$")
    public void loginToAs(String page, String user) throws InterruptedException {
        checkTitleEquals("LinkedIn: Log In or Sign Up");
        if (user.equalsIgnoreCase("Rufat Nadir")) {
            String userEmail = props.readProperty("linkedin_user");
            String userPassword = props.readProperty("linkedin_password");
            insertText(linkedinPage.login_email_field, userEmail);
            insertText(linkedinPage.login_password_field, userPassword);
            clickElementWhenClickable(linkedinPage.login_submit_button);
            Assert.assertTrue(linkedinPage.member_photo.isDisplayed());
            logger.info("Logged in as '"+user+"'\n");
            Thread.sleep(3000);
        }
    }

    @Given("^Navigate to Linkedin login page$")
    public void navigateToLinkedinLoginPage() throws InterruptedException {
        String url = props.readProperty("linkedin");

        driver.navigate().to(url);
        logger.info("Navigate to "+url+"\n");
        Thread.sleep(3000);

    }

    @And("^Perform search for \"([^\"]*)\" in search field$")
    public void performSearchForInSearchField(String searchText) throws InterruptedException {
        insertText(linkedinPage.search_field, searchText);
        clickElementWhenClickable(linkedinPage.search_button);
        waitForElementToBeVisible(linkedinPage.search_results_block, 3000);
        logger.info("Searching for '"+searchText+"'\n");
        Thread.sleep(3000);

    }


    @Then("^Click on \"([^\"]*)\" button$")
    public void clickOnButton(String buttonName) throws InterruptedException {
        WebElement button = driver.findElement(By.xpath(".//button[@data-vertical='"+buttonName+"']"));
        clickElementWhenClickable(button);
        waitForAttributeEquals(button, "class", "active");
        logger.info("Click on '"+buttonName+"' button\n");
        Thread.sleep(3000);

    }

    @And("^Click on \"([^\"]*)\" search result$")
    public void clickOnSearchResult(String name) throws InterruptedException {
        waitForElementToBeVisible(linkedinPage.search_results_total, 5000);
        for (WebElement result : linkedinPage.search_result_title){
            System.out.println("Text on element: "+result.getText());
//            logger.info("Text on element: "+result.getText());
            if (result.getText().contains(name)){
                logger.info("Select '"+name+"' from search result list\n");
                clickElementWhenClickable(result);
                break;
            }
        }
        Thread.sleep(3000);

    }

    @And("^\"([^\"]*)\" company page will open$")
    public void companyPageWillOpen(String name) throws InterruptedException {
        Assert.assertTrue(linkedinPage.company_module_name.getText().equalsIgnoreCase(name));
        logger.info("Company page opened");
        Thread.sleep(3000);


    }
}
