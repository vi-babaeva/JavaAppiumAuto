package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTest extends CoreTestCase {

    private static final String name_of_folder = "Learning programming";
    @Test
    public void testSaveTwoArticlesToTestListAndDeleteOneArticle(){
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.skipClick();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForSubtitleElement();

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addFirstArticleToMyList(name_of_folder);
            MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
            MyListsPageObject.openFolderByName(name_of_folder);
            NavigationUI NavigationUI = NavigationUIFactory.get(driver);
            NavigationUI.goBackToAddNewArticle(name_of_folder);

            SearchPageObject.initSearchInput();
            SearchPageObject.typeSearchLine("Java");
            SearchPageObject.clickByArticleWithSubstring("High-level programming language");
            ArticlePageObject.addSecondArticleToMyList(name_of_folder);
            MyListsPageObject.openFolderByName(name_of_folder);
            NavigationUI.goToMyList();
        } else {
            ArticlePageObject.addArticlesToMySaved();
            NavigationUI NavigationUI = NavigationUIFactory.get(driver);
            NavigationUI.backToSearchList();
            SearchPageObject.clickByArticleWithSubstring("High-level programming language");
            ArticlePageObject.addArticlesToMySaved();
            NavigationUI.backToSearchList();
            NavigationUI.clickOnCancelButton();
            NavigationUI.goToMyList();
            NavigationUI.closeSyncSavedArticlesPopUp();
        }
        ArticlePageObject.swipeToDeleteOneArticle();
        ArticlePageObject.checkThatOneArticleWasDeleted();
        String article_subtitle = ArticlePageObject.getArticleSubtitle();

        assertEquals(
                "We see unexpected subtitle!",
                "High-level programming language",
                article_subtitle);
    }
}
