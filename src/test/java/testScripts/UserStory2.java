package testScripts;

import api.queryHelper.DBQueryHelper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import web.pages.ClerkDashBoard;
import web.pages.LoginPage;

import static utility.Constants.*;

public class UserStory2 extends BaseTest {

    ClerkDashBoard clerkDashBoard;
    LoginPage loginPage;

    @BeforeMethod
    public void readTestFileAndSetup() {
        clerkDashBoard = new ClerkDashBoard(driver);
        loginPage = new LoginPage(driver);
    }

    @Test(priority = 1)
    public void createMultipleHeroesViaCSV() {
        loginPage.loginToApplicationAsClerk();
        clerkDashBoard.uploadCorrectHeroesFile(CORRECTHEROESCSVFILEPATH, FILEUPLOADSUCCESSFULLYMSG);
        clerkDashBoard.verifyHeroesCreatedViaCSV(CORRECTHEROESCSVFILEPATH);
    }

    @Test(dependsOnMethods = {"createMultipleHeroesViaCSV"},priority = 2)
    public void createMultipleHeroesWithErrorCSV() {
        loginPage.loginToApplicationAsClerk();
        clerkDashBoard.uploadErroneousHeroesFile(ERRONEOUSHEROESCSVFILEPATH, FAILEDFILEUPLOADMSG);
    }

    @Test(dependsOnMethods = {"createMultipleHeroesViaCSV"},priority = 3)
    public void deleteWorkingClassHeroes() {
        clerkDashBoard.deleteWorkingClassHeroAndVerify();
    }
}



