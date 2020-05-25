package tests.iOS;

import lib.ui.WelcomePageObject;
import org.junit.Test;

public class GetStartedTest extends iOSTestCase {

    @Test
    public void testPassThroughWelcome() {
        WelcomePageObject WelcomePage = new WelcomePageObject(driver);

        WelcomePage.waitForMainTitle();
        WelcomePage.waitNextButton();
        WelcomePage.clickNextButton();

        WelcomePage.waitForNewWaysToExploreText();
        WelcomePage.clickNextButton();

        WelcomePage.waitForSubtitleAboutLanguages();
        WelcomePage.clickNextButton();

        WelcomePage.waitForHelpMakeTheAppBetterText();
        WelcomePage.clickGetStartedButton();
    }
}
