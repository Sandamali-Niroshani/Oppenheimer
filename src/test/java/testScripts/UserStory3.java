package testScripts;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utility.JsonUtils;
import web.pages.BookKeeperDashBoard;
import web.pages.ClerkDashBoard;
import web.pages.LoginPage;

import java.util.Map;

import static utility.Constants.*;

public class UserStory3 extends BaseTest {

    BookKeeperDashBoard bookKeeperDashBoard;
    LoginPage loginPage;
    Map<String, Object> testData;

    @BeforeMethod
    public void readTestFileAndSetup() {
        // Read test data from JSON file
         testData = JsonUtils.readJsonFile("src/test/java/resources/UserStory3.json");
         bookKeeperDashBoard = new BookKeeperDashBoard(driver);
         loginPage = new LoginPage(driver);
    }


    @Test
    public void generateTaxRelifFileAndVerify() {
        loginPage.loginToApplicationAsBookKeeper();
        bookKeeperDashBoard.downloadTaxReliefFile(FILEDOWNLOADPATH, testData.get("fileName").toString());
        bookKeeperDashBoard.readAndValidateFile(FILEDOWNLOADPATH, testData.get("fileName").toString());
    }

}



