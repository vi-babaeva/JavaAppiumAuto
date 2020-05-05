import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class FirstTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","10");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","/Users/qa/Desktop/JavaAppiumAuto/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testHomeworkCancelSearch(){
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip' button",
                5);

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find element_to_init_search",
                5);

        // ищет какое-то слово
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Android",
                "Cannot find search input",
                5);

        // убеждается, что найдено несколько статей
        Assert.assertTrue( "Less than 3 articles found",
                driver.findElementsById("org.wikipedia:id/page_list_item_title").size()>2);

        // отменяет поиск
        waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search input",
                5);

        // убеждается, что результат поиска пропал
        waitForElementNotPresent(
                By.id("org.wikipedia:id/search_results_list"),
                "Search results is still present on page",
                5);
    }


    @Test
    public void firstTest(){
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'SKIP')]"),
                "Cannot find 'Skip' button",
                1);

        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc=\"Search Wikipedia\"]"),
                "Cannot find element_to_init_search",
                5);

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                "Cannot find search input",
                5);

        waitForElementPresent(
                By.xpath("//*[@index='2']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by 'Java'");
    }

    @Test
    public void testCancelSearch(){

        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip' button",
                5);

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find element_to_init_search",
                5);

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot find search input",
                5);

        waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search input",
                5);

        // в последней версии приложения, после ощищения поля, кнопка X пропадает
        /* waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X to cancel search",
                10); */

        waitForElementNotPresent(
                By.id("org.wikipedia:id/search_results_list"),
                "Search results is still present on page",
                5);
    }

     @Test
     public void testCompareArticleTitle(){
         waitForElementAndClick(
                 By.xpath("//*[contains(@text, 'SKIP')]"),
                 "Cannot find 'Skip' button",
                 1);

         waitForElementAndClick(
                 By.xpath("//android.widget.ImageView[@content-desc=\"Search Wikipedia\"]"),
                 "Cannot find element_to_init_search",
                 5);

         waitForElementAndSendKeys(
                 By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                 "Java",
                 "Cannot find search input",
                 5);

         waitForElementAndClick(
                 By.xpath("//*[@index='2']//*[@text='Object-oriented programming language']"),
                 "Cannot find 'Object-oriented programming language' topic searching by 'Java'",
                 5);

         WebElement title_element = waitForElementPresent(
                 By.xpath("//*[contains(@text, 'Java (programming language)')]"),
                 "Cannot find 'Java (programming language)' title",
                 15);

         String article_title = title_element.getAttribute("text");

         Assert.assertEquals(
                 "We see unexpected title!",
                 "Java (programming language)",
                 article_title);

     }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitForElementPresent(By by, String error_message) {
        return waitForElementPresent(by, error_message, 5);
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds){
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds){
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by));
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }
}