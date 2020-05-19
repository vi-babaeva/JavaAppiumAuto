package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {

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

        assertEquals(
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
}
