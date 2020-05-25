package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class WelcomePageObject extends MainPageObject {

    private static final String
            STEP_THE_FREE_ENCYCLOPEDIA_TEXT = "///XCUIElementTypeStaticText[@name=\"The free encyclopedia\"]",
            STEP_NEW_WAYS_TO_EXPLORE_TEXT = "//XCUIElementTypeStaticText[@name=\"New ways to explore\"]",
            STEP_SEARCH_300_LANGUAGES_TEXT = "//XCUIElementTypeStaticText[@name=\"Search in nearly 300 languages\"]",
            STEP_HELP_MAKE_TEXT = "//XCUIElementTypeStaticText[@name=\"Help make the app better\"]",
            NEXT_LINK = "//XCUIElementTypeStaticText[@name=\"Next\"]" ,
            GET_STARTED_BUTTON = "//XCUIElementTypeStaticText[@name=\"Get started\"]";

    public WelcomePageObject(AppiumDriver driver) {
        super(driver);
    }

    public void waitForMainTitle() {
        this.waitForElementPresent(By.xpath(STEP_THE_FREE_ENCYCLOPEDIA_TEXT),
                "Cannot find 'The free encyclopedia'", 10);
    }

    public void waitNextButton(){
        this.waitForElementPresent(By.xpath(NEXT_LINK),
                "Cannot find 'Next' link", 10);
    }

    public void clickNextButton(){
        this.waitForElementAndClick(By.xpath(NEXT_LINK),
                "Cannot find and click 'Next' link", 5);
    }

    public void waitForNewWaysToExploreText() {
        this.waitForElementPresent(By.xpath(STEP_NEW_WAYS_TO_EXPLORE_TEXT),
                "Cannot find 'New ways to explore'", 10);
    }

    public void waitForSubtitleAboutLanguages() {
        this.waitForElementPresent(By.xpath(STEP_SEARCH_300_LANGUAGES_TEXT),
                "Cannot find 'Search in nearly 300 languages'", 10);
    }

    public void waitForHelpMakeTheAppBetterText() {
        this.waitForElementPresent(By.xpath(STEP_HELP_MAKE_TEXT),
                "Cannot find 'Help make the app better'", 10);
    }

    public void clickGetStartedButton(){
        this.waitForElementAndClick(By.xpath(GET_STARTED_BUTTON),
                "Cannot find and click 'Get started' button", 5);
    }
}
