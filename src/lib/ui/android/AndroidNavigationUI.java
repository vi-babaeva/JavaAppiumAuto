package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;

public class AndroidNavigationUI extends NavigationUI {
    static {
        NAVIGATE_UP_BUTTON ="xpath://android.widget.ImageButton[@content-desc='Navigate up']";
        NO_THANKS_BUTTON = "id:android:id/button2";
        VIEW_LIST_BUTTON = "id:org.wikipedia:id/snackbar_action";
        EXPLORE_BUTTON = "xpath://android.widget.FrameLayout[@content-desc='Explore']/android.widget.ImageView";
    }

    public AndroidNavigationUI(AppiumDriver driver){
        super(driver);
    }
}
