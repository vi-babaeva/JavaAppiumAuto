package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUI extends MainPageObject{

    private static final String
            NAVIGATE_UP_BUTTON ="//android.widget.ImageButton[@content-desc=\"Navigate up\"]",
            NO_THANKS_BUTTON = "android:id/button2",
            VIEW_LIST_BUTTON = "org.wikipedia:id/snackbar_action",
            EXPLORE_BUTTON = "//android.widget.FrameLayout[@content-desc=\"Explore\"]/android.widget.ImageView";

    public NavigationUI(AppiumDriver driver) {
        super(driver);
    }

    public void goBackToAddNewArticle(String name_of_folder) {

        this.waitForElementAndClick(
                By.xpath(NAVIGATE_UP_BUTTON),
                "Cannot find 'Navigate up' button",
                5);

        this.waitForElementAndClick(
                By.id(NO_THANKS_BUTTON),
                "Cannot find 'NO THANKS' button",
                5);

        this.waitForElementAndClick(
                By.xpath(EXPLORE_BUTTON),
                "Cannot find 'Explore' button",
                5);
    }

    public void goToMyList() {
        this.waitForElementAndClick(
                By.id(VIEW_LIST_BUTTON),
                "Cannot find 'VIEW LIST' button",
                5);
    }

}
