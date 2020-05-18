package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    private static final String
            SUBTITLE = "pagelib_edit_section_title_description",
            FOOTER_ELEMENT = "org.wikipedia:id/page_external_link",
            OPTIONS_BUTTON = "org.wikipedia:id/page_toolbar_button_show_overflow_menu",
            OPTIONS_ADD_TO_MY_LIST_BUTTON = "org.wikipedia:id/page_action_overflow_reading_lists",
            MORE_OPTIONS_BUTTON = "//android.widget.TextView[@content-desc=\"More options\"]",
            CREATE_NEW_LIST_OVERLAY = "org.wikipedia:id/reading_lists_overflow_create_new_list",
            MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input",
            GOT_IT_BUTTON = "org.wikipedia:id/onboarding_button",
            MY_LIST_OK_BUTTON = "android:id/button1",
            SHOW_TAB_BUTTON = "org.wikipedia:id/tabsCountText",
            ADD_ARTICLE_TO_READ_LIST_BUTTON = "org.wikipedia:id/article_menu_bookmark",
            ARTICLE_TO_DELETE = "//*[@text='Object-oriented programming language']",
            ARTICLE_WITH_TITLE_TO_STAY = "//*[@text='JavaScript']";


    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    //здесь изменила title на subtitle, т.к. в текущей версии приложения title не имеет уникальных локаторов, а у subtitle есть уникальный id
    public WebElement waitForSubtitleElement(){
        return this.waitForElementPresent(By.id(SUBTITLE),
                "Cannot find article title on page", 10);
    }

    public String getArticleSubtitle() {
        WebElement subtitle_element = waitForSubtitleElement();
        return subtitle_element.getAttribute("text");
    }

    public void swipeToFooter() {
        this.swipeUpToFindElement(By.id(FOOTER_ELEMENT),
                "Cannot find the end of article",
                20);
    }

    public void addFirstArticleToMyList(String name_of_folder){
        this.waitForElementAndClick(
                By.id(OPTIONS_BUTTON),
                "Cannot find 'More options' button",
                10);

        this.waitForElementAndClick(
                By.id(OPTIONS_ADD_TO_MY_LIST_BUTTON),
                "Cannot find 'My lists'",
                5);

        this.waitForElementAndClick(
                By.xpath(MORE_OPTIONS_BUTTON),
                "Cannot find 'More options' button on 'My lists' screen",
                5);

        this.waitForElementAndClick(
                By.id(CREATE_NEW_LIST_OVERLAY),
                "Cannot find 'Create new list'",
                5);

        this.waitForElementAndClear(
                By.id(MY_LIST_NAME_INPUT),
                "Cannot clear title 'My reading list'",
                5);

        this.waitForElementAndSendKeys(
                By.id(MY_LIST_NAME_INPUT),
                "Test list",
                "Cannot input title list",
                5);

        this.waitForElementAndClick(
                By.id(MY_LIST_OK_BUTTON),
                "Cannot find 'OK' button",
                5);

        this.waitForElementAndClick(
                By.id(SHOW_TAB_BUTTON),
                "Cannot find 'Show tabs' button",
                5);

        this.waitForElementAndClick(
                By.id(ADD_ARTICLE_TO_READ_LIST_BUTTON),
                "Cannot find 'Add this article to a reading list' button",
                5);

        this.waitForElementAndClick(
                By.id(GOT_IT_BUTTON),
                "Cannot find 'GOT IT' button",
                5);
    }

   public void addSecondArticleToMyList(String name_of_folder) {

        this.waitForElementAndClick(
                By.id(ADD_ARTICLE_TO_READ_LIST_BUTTON),
                "Cannot find 'Add this article to a reading list' button",
                5);
    }

    public void swipeToDeleteOneArticleAndCheckIt(){
        this.swipeElementToLeft(
                By.xpath(ARTICLE_TO_DELETE),
                "Cannot find 'Object-oriented programming language' topic in my folder");

        this.waitForElementNotPresent(
                By.xpath(ARTICLE_TO_DELETE),
                "Cannot delete saved article",
                25);

        this.waitForElementPresent(
                By.xpath(ARTICLE_WITH_TITLE_TO_STAY),
                "Cannot find 'JavaScript' topic in my folder",
                25);

        this.waitForElementAndClick(
                By.xpath(ARTICLE_WITH_TITLE_TO_STAY),
                "Cannot click 'High-level programming language' topic in my folder",
                5);
    }
}