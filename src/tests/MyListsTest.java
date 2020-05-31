package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTest extends CoreTestCase {

    @Test
    public void testSaveTwoArticlesToTestListAndDeleteOneArticle(){
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

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

        assertEquals(
                "We see unexpected subtitle!",
                "High-level programming language",
                article_subtitle);
    }
}
