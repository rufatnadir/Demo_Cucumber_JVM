package core;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Assert;

import helpers.PropertyReader;

import java.util.concurrent.TimeUnit;



public class Hooks {

    public static WebDriver driver;
    private static PropertyReader props = new PropertyReader();
    private static final String FILE_PATH_SEPARATOR = System.getProperty("file.separator");
    private static final String SYSTEM_USER_DIR = System.getProperty("user.dir");
    private static final String PATH_TO_DRIVERS = SYSTEM_USER_DIR + FILE_PATH_SEPARATOR + "drivers" + FILE_PATH_SEPARATOR;
    private static Logger logger = LoggerFactory.getLogger(Hooks.class);
    private static String browser = props.readProperty("browser");


    public static WebDriver getDriver() {

        if (browser.equalsIgnoreCase("chrome")) {
            if (System.getProperty("os.name").contains("Mac")) {
                System.setProperty("webdriver.chrome.driver", PATH_TO_DRIVERS + "chromedriver_81");
            } else if (System.getProperty("os.name").contains("Windows")) {
                System.setProperty("webdriver.chrome.driver", PATH_TO_DRIVERS + "chromedriver.exe");
            } else {
                logger.info("Couldn't detect System OS");
                Assert.fail();
            }

            ChromeOptions options = new ChromeOptions();
            options.addArguments("disable-infobars");
            driver = new ChromeDriver(options);


            // FireFox
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        }

        driver.manage().deleteAllCookies();
//        logger.info("Deleting cookies");
        driver.manage().window().maximize();
        logger.info("Window size is: " + driver.manage().window().getSize());
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        return driver;
    }

    @Before

    public static void startScenario(Scenario scenario) {

        logger.info("\n############################################################################################\n" +
                "------- SCENARIO '" + scenario.getName() + "' STARTED! -------\n"
                + "###########################################################################################\n");
    }


    @After
    /*
      Embed a screenshot in test report if test failed
     */
    public static void embedScreenshot(Scenario scenario) {

        if(scenario.isFailed()) {
            if(driver != null) {
                try {
                    scenario.write("Current Page URL is " + driver.getCurrentUrl());
                    byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                    scenario.embed(screenshot, "image/png");
                } catch(WebDriverException somePlatformsDontSupportScreenshots) {
                    logger.error(somePlatformsDontSupportScreenshots.getMessage());
                        }
                    }

        logger.info("\n############################################################################################\n" +
                    "------- SCENARIO '" + scenario.getName() + "' FAILED! -------\n "
                    + "###########################################################################################\n");
        } else {
            logger.info("\n############################################################################################\n" +
                    "------- SCENARIO '" + scenario.getName() + "' PASSED! -------\n "
                    + "###########################################################################################\n");
        }
        if(driver != null) {
            driver.quit();
        }
    }

}
