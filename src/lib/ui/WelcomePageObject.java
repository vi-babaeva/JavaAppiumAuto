package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class WelcomePageObject extends MainPageObject {

    private static final String
            STEP_LEARN_MORE_LINK = "id:Learn more about Wikipedia",
            STEP_NEW_WAYS_TO_EXPLORE_TEXT = "id:New ways to explore",
            STEP_SEARCH_300_LANGUAGES_TEXT = "id:Search in nearly 300 languages",
            STEP_HELP_MAKE_TEXT = "id:Help make the app better",
            NEXT_LINK = "id:Next",
            GET_STARTED_BUTTON = "id:Get started";

    public WelcomePageObject(AppiumDriver driver) {
        super(driver);
    }

    public void waitForMainTitle() {
        this.waitForElementPresent(STEP_LEARN_MORE_LINK,
                "Cannot find 'Learn more about Wikipedia'", 10);
    }

    public void clickNextButton(){
        this.waitForElementAndClick(NEXT_LINK,
                "Cannot find and click 'Next' link", 5);
    }

    public void waitForNewWaysToExploreText() {
        this.waitForElementPresent(STEP_NEW_WAYS_TO_EXPLORE_TEXT,
                "Cannot find 'New ways to explore'", 10);
    }

    public void waitForSubtitleAboutLanguages() {
        this.waitForElementPresent(STEP_SEARCH_300_LANGUAGES_TEXT,
                "Cannot find 'Search in nearly 300 languages'", 10);
    }

    public void waitForHelpMakeTheAppBetterText() {
        this.waitForElementPresent(STEP_HELP_MAKE_TEXT,
                "Cannot find 'Help make the app better'", 10);
    }

    public void clickGetStartedButton(){
        this.waitForElementAndClick(GET_STARTED_BUTTON,
                "Cannot find and click 'Get started' button", 5);
    }
}
