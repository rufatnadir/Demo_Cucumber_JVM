package pages;

import core.BaseClass;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

/**
 * Created by rnadir on 12/14/17.
 */
public class LinkedinPage extends BaseClass {
    public LinkedinPage(WebDriver driver){
        super(driver);
    }

    @FindBy(id = "login-email")
    public WebElement login_email_field;

    @FindBy(id = "login-password")
    public WebElement login_password_field;

    @FindBy(id = "login-submit")
    public WebElement login_submit_button;

    @FindBy(className = "feed-identity-module__member-photo")
    public WebElement member_photo;

    @FindBy(xpath = ".//input[@type='text' and @role='combobox']")
    public WebElement search_field;

    @FindBy(xpath = ".//*[@id='nav-search-controls-wormhole']/button")
    public WebElement search_button;

    @FindBys(@FindBy(className = "search-result__title"))
    public List<WebElement> search_result_title;

    @FindBy(className = "org-top-card-module__name")
    public WebElement company_module_name;

    @FindBy(className = "blended-srp-results-js")
    public WebElement search_results_block;

    @FindBy(className = "search-results__total")
    public WebElement search_results_total;

}
