package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class AndroidSearchPageObject extends SearchPageObject {
    static {
        SKIP_BUTTON = "xpath://*[contains(@text, 'SKIP')]";
        SEARCH_INIT_ELEMENT = "xpath://*[contains(@text, 'Search Wikipedia')]";
        SEARCH_INPUT = "xpath://*[contains(@text, 'Search Wikipedia')]";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@text='{SUBSTRING}']";
        SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
        SEARCH_RESULT_LIST = "id:org.wikipedia:id/search_results_list";
        SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']//*[@resource-id='org.wikipedia:id/page_list_item_title']";
        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://*[@text='No results found']";
        SEARCH_RESULT_TITLE_LOCATOR_TPL = "xpath://*[@text='{TITLE}']";
    }

    public AndroidSearchPageObject(AppiumDriver driver) {
        super(driver);
    }
}
