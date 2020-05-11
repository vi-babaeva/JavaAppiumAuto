import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
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

    @Test
    public void testSwipeArticle(){
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
                By.xpath("//*[@index='1']//*[@text='High-level programming language']"),
                "Cannot find 'High-level programming language' topic searching by 'Java'",
                5);

        waitForElementPresent(
                By.xpath("//*[contains(@text, 'JavaScript')]"),
                "Cannot find 'JavaScript' title",
                15);

        swipeUpToFindElement(
                By.id("org.wikipedia:id/page_external_link"),
                "Cannot find 'JS' article in search",
                20);
    }

    @Test
    public void saveTwoArticlesToTestListAndDeleteOneArticle(){
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
                20);

        waitForElementPresent(
                By.xpath("//*[contains(@text, 'Java (programming language)')]"),
                "Cannot find 'Java (programming language)' title",
                20);

        waitForElementAndClick(
                By.id("org.wikipedia:id/page_toolbar_button_show_overflow_menu"),
                "Cannot find 'More options' button",
                10);

        waitForElementAndClick(
                By.id("org.wikipedia:id/page_action_overflow_reading_lists"),
                "Cannot find 'My lists'",
                5);

        waitForElementAndClick(
                By.xpath("//android.widget.TextView[@content-desc=\"More options\"]"),
                "Cannot find 'More options' button on 'My lists' screen",
                5);

        waitForElementAndClick(
                By.id("org.wikipedia:id/reading_lists_overflow_create_new_list"),
                "Cannot find 'Create new list'",
                5);

        waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Cannot clear title 'My reading list'",
                5);

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                "Test list",
                "Cannot input title list",
                5);

        waitForElementAndClick(
                By.id("android:id/button1"),
                "Cannot find 'OK' button",
                5);

        waitForElementAndClick(
                By.id("org.wikipedia:id/tabsCountText"),
                "Cannot find 'Show tabs' button",
                5);

        waitForElementAndClick(
                By.id("org.wikipedia:id/article_menu_bookmark"),
                "Cannot find 'Add this article to a reading list' button",
                5);

        waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find 'GOT IT' button",
                5);

        waitForElementAndClick(
                By.xpath("//*[@text='Test list']"),
                "Cannot find 'Test list'",
                5);

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]"),
                "Cannot find 'Navigate up' button",
                5);

        waitForElementAndClick(
                By.id("android:id/button2"),
                "Cannot find 'NO THANKS' button",
                5);

        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc=\"Explore\"]/android.widget.ImageView"),
                "Cannot find 'Explore' button",
                5);

        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc=\"Search Wikipedia\"]"),
                "Cannot find element_to_init_search",
                5);

        waitForElementAndClick(
                By.id("org.wikipedia:id/recent_searches_list"),
                "Cannot find 'java' in list",
        5);

        waitForElementAndClick(
                By.xpath("//*[@index='1']//*[@text='High-level programming language']"),
                "Cannot find 'High-level programming language' topic searching by 'Java'",
                15);

        waitForElementAndClick(
                By.id("org.wikipedia:id/article_menu_bookmark"),
                "Cannot find 'Add this article to a reading list' button",
                5);

        // сохранение 2ой статьи в папку Test list
        waitForElementAndClick(
                By.xpath("//*[@text='Test list']"),
                "Cannot find 'Test list'",
                5);

        waitForElementAndClick(
                By.id("org.wikipedia:id/snackbar_action"),
                "Cannot find 'VIEW LIST' button",
                5);

        swipeElementToLeft(
                By.xpath("//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic in Test list");

        waitForElementNotPresent(
                By.xpath("//*[@text='Object-oriented programming language']"),
                "Cannot delete saved article",
                5);

        waitForElementPresent(
                By.xpath("//*[@text='High-level programming language']"),
                "Cannot find 'High-level programming language' topic in Test list",
                5);

        waitForElementAndClick(
                By.xpath("//*[@text='High-level programming language']"),
                "Cannot click 'High-level programming language' topic in Test list",
                5);

        WebElement title_element = waitForElementPresent(
                By.xpath("//*[contains(@text, 'JavaScript')]"),
                "Cannot find 'JavaScript' title",
                15);

        String article_title = title_element.getAttribute("text");

        Assert.assertEquals(
                "We see unexpected title!",
                "JavaScript",
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

    protected void swipeUp(int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);

        action
                .press(x, start_y)
                .waitAction(timeOfSwipe)
                .moveTo(x, end_y)
                .release()
                .perform();
    }

    protected void swipeUpQuick(){
        swipeUp(200);
    }

    protected void swipeUpToFindElement(By by, String error_message, int max_swipes){
        int already_swiped = 0;
        while (driver.findElements(by).size() == 0) {

            if (already_swiped > max_swipes){
                waitForElementPresent(by,
                        "Cannot find element by swiping up. \n" + error_message,
                        0);
                return;
            }

            swipeUpQuick();
            ++already_swiped;
        }
    }

    protected void swipeElementToLeft(By by, String error_message){
        WebElement element = waitForElementPresent(by, error_message, 10);

        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(600)
                .moveTo(left_x, middle_y)
                .release()
                .perform();
    }
}