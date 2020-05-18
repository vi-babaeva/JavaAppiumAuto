import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MainPageObject;
import lib.ui.SearchPageObject;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import java.util.List;

public class FirstTest extends CoreTestCase {

    private MainPageObject MainPageObject;

    protected void setUp() throws Exception {
        super.setUp();

        MainPageObject = new MainPageObject(driver);
    }

    //Ex3
    @Test
    public void testHomeworkCancelSearch(){
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.skipClick();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Android");

        Assert.assertTrue( "Less than 3 articles found",
                driver.findElementsById("org.wikipedia:id/page_list_item_title").size()>2);

        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitEmptySearchResultList();
    }


    @Test
    public void testSearch(){
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.skipClick();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    public void testCancelSearch(){
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.skipClick();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

     @Test
     public void testCompareArticleTitle(){
         SearchPageObject SearchPageObject = new SearchPageObject(driver);

         SearchPageObject.skipClick();
         SearchPageObject.initSearchInput();
         SearchPageObject.typeSearchLine("Java");
         SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

         ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
         ArticlePageObject.waitForSubtitleElement();
         String article_subtitle = ArticlePageObject.getArticleSubtitle();

         Assert.assertEquals(
                 "We see unexpected subtitle!",
                 "Object-oriented programming language",
                 article_subtitle);
     }

    @Test
    public void testSwipeArticle(){
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.skipClick();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Neirone");
        SearchPageObject.clickByArticleWithSubstring("Comune in Liguria, Italy");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForSubtitleElement();
        ArticlePageObject.swipeToFooter();
    }

    //Ex5
    @Test
    public void testSaveTwoArticlesToTestListAndDeleteOneArticle(){
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'SKIP')]"),
                "Cannot find 'Skip' button",
                1);

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc=\"Search Wikipedia\"]"),
                "Cannot find element_to_init_search",
                5);

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                "Cannot find search input",
                5);

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@index='2']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by 'Java'",
                20);

        MainPageObject.waitForElementPresent(
                By.xpath("//*[contains(@text, 'Java (programming language)')]"),
                "Cannot find 'Java (programming language)' title",
                20);

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/page_toolbar_button_show_overflow_menu"),
                "Cannot find 'More options' button",
                10);

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/page_action_overflow_reading_lists"),
                "Cannot find 'My lists'",
                5);

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.TextView[@content-desc=\"More options\"]"),
                "Cannot find 'More options' button on 'My lists' screen",
                5);

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/reading_lists_overflow_create_new_list"),
                "Cannot find 'Create new list'",
                5);

        MainPageObject.waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Cannot clear title 'My reading list'",
                5);

        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                "Test list",
                "Cannot input title list",
                5);

        MainPageObject.waitForElementAndClick(
                By.id("android:id/button1"),
                "Cannot find 'OK' button",
                5);

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/tabsCountText"),
                "Cannot find 'Show tabs' button",
                5);

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/article_menu_bookmark"),
                "Cannot find 'Add this article to a reading list' button",
                5);

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find 'GOT IT' button",
                5);

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Test list']"),
                "Cannot find 'Test list'",
                5);

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]"),
                "Cannot find 'Navigate up' button",
                5);

        MainPageObject.waitForElementAndClick(
                By.id("android:id/button2"),
                "Cannot find 'NO THANKS' button",
                5);

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc=\"Explore\"]/android.widget.ImageView"),
                "Cannot find 'Explore' button",
                5);

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc=\"Search Wikipedia\"]"),
                "Cannot find element_to_init_search",
                5);

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/recent_searches_list"),
                "Cannot find 'java' in list",
        5);

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@index='1']//*[@text='High-level programming language']"),
                "Cannot find 'High-level programming language' topic searching by 'Java'",
                15);

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/article_menu_bookmark"),
                "Cannot find 'Add this article to a reading list' button",
                5);

        // сохранение 2ой статьи в папку Test list
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Test list']"),
                "Cannot find 'Test list'",
                5);

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/snackbar_action"),
                "Cannot find 'VIEW LIST' button",
                5);

        MainPageObject.swipeElementToLeft(
                By.xpath("//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic in Test list");

        MainPageObject.waitForElementNotPresent(
                By.xpath("//*[@text='Object-oriented programming language']"),
                "Cannot delete saved article",
                5);

        MainPageObject.waitForElementPresent(
                By.xpath("//*[@text='High-level programming language']"),
                "Cannot find 'High-level programming language' topic in Test list",
                5);

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='High-level programming language']"),
                "Cannot click 'High-level programming language' topic in Test list",
                5);

        WebElement title_element = MainPageObject.waitForElementPresent(
                By.xpath("//*[contains(@text, 'JavaScript')]"),
                "Cannot find 'JavaScript' title",
                15);

        String article_title = title_element.getAttribute("text");

        Assert.assertEquals(
                "We see unexpected title!",
                "JavaScript",
                article_title);

    }

    @Test
    public void testAmountOfNotEmptySearch(){
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'SKIP')]"),
                "Cannot find 'Skip' button",
                1);

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc=\"Search Wikipedia\"]"),
                "Cannot find element_to_init_search",
                5);

        String search_line = "Linkin Park Discography";

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                search_line,
                "Cannot find search input",
                5);

        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@resource-id='org.wikipedia:id/page_list_item_title']";
        MainPageObject.waitForElementPresent(
                By.xpath(search_result_locator),
                "Cannot find anything by the request " +  search_line,
                15);

        int amount_of_search_results = MainPageObject.getAmountOfElements(
                By.xpath(search_result_locator));

        Assert.assertTrue(
                "We found too few results!",
                amount_of_search_results > 0 );
    }

    @Test
    public void testAmountOfEmptySearch(){
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'SKIP')]"),
                "Cannot find 'Skip' button",
                1);

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc=\"Search Wikipedia\"]"),
                "Cannot find element_to_init_search",
                5);

        String search_line = "hshdhdenneje";

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                search_line,
                "Cannot find search input",
                5);

        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@resource-id='org.wikipedia:id/page_list_item_title']";
        String empty_result_label = "//*[@text='No results found']";

        MainPageObject.waitForElementPresent(
                By.xpath(empty_result_label),
                "Cannot find empty result label by the request " +  search_line,
                15);

        MainPageObject.assertElementNotPresent(
                By.xpath(search_result_locator),
                "We've found some results by request " + search_line);
    }

    //Ex6
    @Test
    public void testAssertTitle(){
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'SKIP')]"),
                "Cannot find 'Skip' button",
                1);

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc=\"Search Wikipedia\"]"),
                "Cannot find element_to_init_search",
                5);

        String search_line = "Java";

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                search_line,
                "Cannot find search input",
                5);

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by 'Java'",
                15);

        String search_result_locator = "//*[@text='Java (programming language)']";

        MainPageObject.assertElementPresent(
                By.xpath(search_result_locator),
                "as title article by topic " + search_line);

    }

    @Test
    public void testChangeScreenOrientationOnSearchResults(){
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'SKIP')]"),
                "Cannot find 'Skip' button",
                1);

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find element_to_init_search",
                5);

        String search_line = "Java";
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                search_line,
                "Cannot find search input",
                5);

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by " + search_line,
                15);

        String title_before_location = MainPageObject.waitForElementAndGetAttribute(
                By.xpath("//*[@text='Object-oriented programming language']"),
                "text",
                "Cannot find title of article",
                15);

        driver.rotate(ScreenOrientation.LANDSCAPE);

        String title_after_rotation = MainPageObject.waitForElementAndGetAttribute(
                By.xpath("//*[@text='Object-oriented programming language']"),
                "text",
                "Cannot find title of article",
                15);

        Assert.assertEquals(
                "Article title have been changed after screen rotation",
                title_before_location,
                title_after_rotation);

        driver.rotate(ScreenOrientation.PORTRAIT);

        String title_after_second_rotation = MainPageObject.waitForElementAndGetAttribute(
                By.xpath("//*[@text='Object-oriented programming language']"),
                "text",
                "Cannot find title of article",
                15);

        Assert.assertEquals(
                "Article title have been changed after second screen rotation",
                title_before_location,
                title_after_second_rotation);
    }

    @Test
    public void testEx4(){
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip' button",
                1);

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' field",
                5);

        String search_value = "Java";
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                search_value,
                "Cannot find search input",
                5);

        List<WebElement> elements = driver.findElementsById("org.wikipedia:id/page_list_item_title");
        for (WebElement e : elements)
        {
            String search_value_lower_case = search_value.toLowerCase();
            Assert.assertTrue("'" + search_value + "' word is not found in search result",
                    e.getText().toLowerCase().contains(search_value_lower_case));
        }
    }
}