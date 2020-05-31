package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchTests extends CoreTestCase {

    @Test
    public void testHomeworkCancelSearch(){
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.skipClick();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Android");
        SearchPageObject.waitForSearchResult("Wikipedia list");

        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();
        assertTrue(
                "Less than 3 articles found",
                amount_of_search_results > 2 );

        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitEmptySearchResultList();
    }

    @Test
    public void testSearch(){
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.skipClick();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    public void testCancelSearch(){
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.skipClick();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    public void testAmountOfNotEmptySearch(){
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.skipClick();
        SearchPageObject.initSearchInput();
        String search_line = "Linkin Park Discography";
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();

        assertTrue(
                "We found too few results!",
                amount_of_search_results > 0 );
    }

    @Test
    public void testAmountOfEmptySearch(){
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.skipClick();
        SearchPageObject.initSearchInput();
        String search_line = "hshdhdenneje";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }

    @Test
    public void testEx4(){
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.skipClick();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");

        String search_value = "Java";

        List<WebElement> elements = driver.findElementsById("org.wikipedia:id/page_list_item_title");
        for (WebElement e : elements)
        {
            String search_value_lower_case = search_value.toLowerCase();
            assertTrue("'" + search_value + "' word is not found in search result",
                    e.getText().toLowerCase().contains(search_value_lower_case));
        }
    }
}
