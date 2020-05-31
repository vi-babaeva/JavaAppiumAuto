package lib.ui;

import io.appium.java_client.AppiumDriver;

abstract public class SearchPageObject extends MainPageObject {

    protected static String
            SKIP_BUTTON,
            SEARCH_INIT_ELEMENT,
            SEARCH_INPUT,
            SEARCH_RESULT_BY_SUBSTRING_TPL,
            SEARCH_CANCEL_BUTTON,
            SEARCH_RESULT_LIST,
            SEARCH_RESULT_ELEMENT,
            SEARCH_EMPTY_RESULT_ELEMENT,
            SEARCH_RESULT_TITLE_LOCATOR_TPL;

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getSearchResultTitleLocatorTpl(String title) {
        return SEARCH_RESULT_TITLE_LOCATOR_TPL.replace("{TITLE}", title);
    }
    /* TEMPLATES METHODS */

    public void skipClick(){
        this.waitForElementAndClick(SKIP_BUTTON,
                "Cannot find 'Skip' button", 5);
    }

    public void initSearchInput() {
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT,
                "Cannot find and click search init element", 5);
        this.waitForElementPresent(SEARCH_INIT_ELEMENT,
                "Cannot find search input after clicking search init element");
    }

    public void typeSearchLine(String search_line){
        this.waitForElementAndSendKeys(SEARCH_INPUT, search_line,
                "Cannot find and type into search input",  15);
    }

    public void waitForSearchResult(String substring){
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(search_result_xpath,
                "Cannot find search result with substring " + substring);
    }

    public void waitForCancelButtonToAppear(){
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON,
                "Cannot find search cancel button!", 5);
    }

    public void clickCancelSearch(){
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON,
                "Cannot find and click search cancel button", 5);
    }

    public void waitForCancelButtonToDisappear(){
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON,
                "Search cancel button still present", 5);
    }

    public void clickByArticleWithSubstring(String substring){
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath,
                "Cannot find and click search result with substring " + substring, 10);
    }

    public void waitEmptySearchResultList(){
        this.waitForElementNotPresent(SEARCH_RESULT_LIST,
                "Search results is still present on page", 5);
    }

    public int getAmountOfFoundArticles(){
        this.waitForElementPresent(SEARCH_RESULT_ELEMENT,
                "Cannot find anything by the request",
                15);
        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }

    public void waitForEmptyResultsLabel(){
        this.waitForElementPresent(SEARCH_EMPTY_RESULT_ELEMENT,
                "Cannot find empty result element", 15);
    }

    public void assertThereIsNoResultOfSearch(){
        this.assertElementNotPresent(SEARCH_RESULT_ELEMENT,
                "We supposed not to find any results");
    }

    public void assertTitle(String title){
        String search_result_locator = getSearchResultTitleLocatorTpl(title);

        //проверила, что тест проходит, когда дождался появляения title
        this.waitForElementPresent(search_result_locator, "Error", 15);

        this.assertElementPresent(search_result_locator, "as title article " + title);
    }
}