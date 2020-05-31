package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class ChangeAppConditionTests extends CoreTestCase {

    @Test
    public void testChangeScreenOrientationOnSearchResults(){
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.skipClick();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        String title_before_location = ArticlePageObject.getArticleSubtitle();
        this.rotateScreenLandscape();
        String title_after_rotation = ArticlePageObject.getArticleSubtitle();

        assertEquals(
                "Article title have been changed after screen rotation",
                title_before_location,
                title_after_rotation);

        this.rotateScreenPortrait();

        String title_after_second_rotation = ArticlePageObject.getArticleSubtitle();

        assertEquals(
                "Article title have been changed after second screen rotation",
                title_before_location,
                title_after_second_rotation);
    }
}
