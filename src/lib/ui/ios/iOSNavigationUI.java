package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;

public class iOSNavigationUI extends NavigationUI {

    static {
        BACK_BUTTON = "id:Back";
        CANCEL_BUTTON = "id:Cancel";
        VIEW_LIST_BUTTON = "id:Saved";
        CLOSE_POPUP_ICON = "xpath://XCUIElementTypeButton[@name='Close']";
    }
    public iOSNavigationUI(AppiumDriver driver) {
        super(driver);
    }
}
