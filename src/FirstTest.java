import lib.CoreTestCase;
import lib.ui.*;
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
     public void testCompareArticleSubtitle(){
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
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.skipClick();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForSubtitleElement();
        String name_of_folder = "List";
        ArticlePageObject.addFirstArticleToMyList(name_of_folder);

        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        MyListsPageObject.openFolderByName(name_of_folder);

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.goBackToAddNewArticle(name_of_folder);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("High-level programming language");
        ArticlePageObject.addSecondArticleToMyList(name_of_folder);
        MyListsPageObject.openFolderByName(name_of_folder);
        NavigationUI.goToMyList();
        ArticlePageObject.swipeToDeleteOneArticleAndCheckIt();

        String article_subtitle = ArticlePageObject.getArticleSubtitle();

        Assert.assertEquals(
                "We see unexpected subtitle!",
                "High-level programming language",
                article_subtitle);

    }

    @Test
    public void testAmountOfNotEmptySearch(){
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.skipClick();
        SearchPageObject.initSearchInput();
        String search_line = "Linkin Park Discography";
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();

        Assert.assertTrue(
                "We found too few results!",
                amount_of_search_results > 0 );
    }

    @Test
    public void testAmountOfEmptySearch(){
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.skipClick();
        SearchPageObject.initSearchInput();
        String search_line = "hshdhdenneje";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }

    //Ex6
    @Test
    public void testAssertTitle(){
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.skipClick();
        SearchPageObject.initSearchInput();
        String search_line = "Java";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        String title = "Java (programming language)";
        SearchPageObject.assertTitle(title);
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
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.skipClick();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");

        String search_value = "Java";

        List<WebElement> elements = driver.findElementsById("org.wikipedia:id/page_list_item_title");
        for (WebElement e : elements)
        {
            String search_value_lower_case = search_value.toLowerCase();
            Assert.assertTrue("'" + search_value + "' word is not found in search result",
                    e.getText().toLowerCase().contains(search_value_lower_case));
        }
    }
}