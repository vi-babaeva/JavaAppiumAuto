package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    private static final String
            SUBTITLE = "xpath://*[@resource-id='pcs-edit-section-title-description']",
            FOOTER_ELEMENT = "xpath://*[@text='View article in browser']",
            OPTIONS_BUTTON = "id:org.wikipedia:id/page_toolbar_button_show_overflow_menu",
            OPTIONS_ADD_TO_MY_LIST_BUTTON = "id:org.wikipedia:id/page_action_overflow_reading_lists",
            MORE_OPTIONS_BUTTON = "xpath://android.widget.TextView[@content-desc=\"More options\"]",
            CREATE_NEW_LIST_OVERLAY = "id:org.wikipedia:id/reading_lists_overflow_create_new_list",
            MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input",
            GOT_IT_BUTTON = "id:org.wikipedia:id/onboarding_button",
            MY_LIST_OK_BUTTON = "id:android:id/button1",
            SHOW_TAB_BUTTON = "id:org.wikipedia:id/tabsCountText",
            ADD_ARTICLE_TO_READ_LIST_BUTTON = "id:org.wikipedia:id/article_menu_bookmark",
            ARTICLE_TO_DELETE = "xpath://*[@text='Object-oriented programming language']",
            ARTICLE_WITH_TITLE_TO_STAY = "xpath://*[@text='JavaScript']";


    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public WebElement waitForSubtitleElement(){
        return this.waitForElementPresent(SUBTITLE,
                "Cannot find article subtitle on page", 10);
    }

    public String getArticleSubtitle() {
        WebElement subtitle_element = waitForSubtitleElement();
        return subtitle_element.getAttribute("text");
    }

    public void swipeToFooter() {
        this.swipeUpToFindElement(FOOTER_ELEMENT,
                "Cannot find the end of article",
                20);
    }

    public void addFirstArticleToMyList(String name_of_folder){
        this.waitForElementAndClick(OPTIONS_BUTTON,
                "Cannot find 'More options' button",
                10);

        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find 'My lists'",
                5);

        this.waitForElementAndClick(MORE_OPTIONS_BUTTON,
                "Cannot find 'More options' button on 'My lists' screen",
                5);

        this.waitForElementAndClick(CREATE_NEW_LIST_OVERLAY,
                "Cannot find 'Create new list'",
                5);

        this.waitForElementAndClear(MY_LIST_NAME_INPUT,
                "Cannot clear title 'My reading list'",
                5);

        this.waitForElementAndSendKeys(MY_LIST_NAME_INPUT,
                name_of_folder,
                "Cannot input title list",
                5);

        this.waitForElementAndClick(MY_LIST_OK_BUTTON,
                "Cannot find 'OK' button",
                5);

        this.waitForElementAndClick(SHOW_TAB_BUTTON,
                "Cannot find 'Show tabs' button",
                5);

        this.waitForElementAndClick(ADD_ARTICLE_TO_READ_LIST_BUTTON,
                "Cannot find 'Add this article to a reading list' button",
                5);

        this.waitForElementAndClick(GOT_IT_BUTTON,
                "Cannot find 'GOT IT' button",
                5);
    }

   public void addSecondArticleToMyList(String name_of_folder) {

        this.waitForElementAndClick(ADD_ARTICLE_TO_READ_LIST_BUTTON,
                "Cannot find 'Add this article to a reading list' button",
                5);
    }

    public void swipeToDeleteOneArticleAndCheckIt(){
        this.swipeElementToLeft(ARTICLE_TO_DELETE,
                "Cannot find 'Object-oriented programming language' topic in my folder");

        this.waitForElementNotPresent(ARTICLE_TO_DELETE,
                "Cannot delete saved article",
                25);

        this.waitForElementPresent(ARTICLE_WITH_TITLE_TO_STAY,
                "Cannot find 'JavaScript' topic in my folder",
                25);

        this.waitForElementAndClick(ARTICLE_WITH_TITLE_TO_STAY,
                "Cannot click 'High-level programming language' topic in my folder",
                5);
    }
}