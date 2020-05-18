package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    private static final String
            SUBTITLE = "pagelib_edit_section_title_description",
            FOOTER_ELEMENT = "org.wikipedia:id/page_external_link";

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

}
