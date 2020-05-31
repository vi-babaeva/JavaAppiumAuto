package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class AndroidArticlePageObject extends ArticlePageObject {
    static {
        SUBTITLE = "xpath://*[@resource-id='pcs-edit-section-title-description']";
        FOOTER_ELEMENT = "xpath://*[@text='View article in browser']";
        OPTIONS_BUTTON = "id:org.wikipedia:id/page_toolbar_button_show_overflow_menu";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "id:org.wikipedia:id/page_action_overflow_reading_lists";
        MORE_OPTIONS_BUTTON = "xpath://android.widget.TextView[@content-desc='More options']";
        CREATE_NEW_LIST_OVERLAY = "id:org.wikipedia:id/reading_lists_overflow_create_new_list";
        MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input";
        GOT_IT_BUTTON = "id:org.wikipedia:id/onboarding_button";
        MY_LIST_OK_BUTTON = "id:android:id/button1";
        SHOW_TAB_BUTTON = "id:org.wikipedia:id/tabsCountText";
        ADD_ARTICLE_TO_READ_LIST_BUTTON = "id:org.wikipedia:id/article_menu_bookmark";
        ARTICLE_TO_DELETE = "xpath://*[@text='Object-oriented programming language']";
        ARTICLE_WITH_TITLE_TO_STAY = "xpath://*[@text='JavaScript']";
    }

    public AndroidArticlePageObject(AppiumDriver driver){
        super(driver);
    }

}
