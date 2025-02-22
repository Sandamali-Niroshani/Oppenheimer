package web.pages;

import api.queryHelper.DBQueryHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

import static utility.CSVReader.getNatidsFromCSV;

public class ClerkDashBoard extends PageBase {

    private static List<String> natidList;

    private WebDriver ldriver;

    @FindBy(id = "dropdownMenuButton2")
    private WebElement ddlAddAHero;
    @FindBy(xpath = "//li//a[text()='Upload a csv file']")
    private WebElement txtUploadACSVFile;
    @FindBy(id = "upload-csv-file")
    private WebElement txtUploadFile;
    @FindBy(xpath = "//button[text()='Create']")
    private WebElement btnCreate;
    @FindBy(xpath = "//h3[text()='Created Successfully!']")
    private WebElement lblCreatedSuccessfully;
    @FindBy(xpath = "//h3[text()='Unable to create hero!']/following::p")
    private WebElement lblUnableToCreateHero;

    public ClerkDashBoard(WebDriver rdriver) {
        ldriver = rdriver;
        PageFactory.initElements(rdriver, this);
    }

    /**
     * Upload successful heroes via CSV file
     *
     * @param filePath path to the CSV file
     * @return void
     * @autor Niroshani
     * @completed 2025-02-06
     */

    public void uploadCorrectHeroesFile(String filePath, String successMessage) {
        uploadHeroesFile(filePath);
        waitForElementVisible(ldriver, lblCreatedSuccessfully);
        Assert.assertEquals(lblCreatedSuccessfully.getText(), successMessage);
    }

    /**
     * Upload erroneous heroes via CSV file
     *
     * @param filePath       path to the CSV file
     * @param failureMessage message to be displayed when the upload fails
     * @return void
     * @autor Niroshani
     * @completed 2025-02-06
     */

    public void uploadErroneousHeroesFile(String filePath, String failureMessage) {
        uploadHeroesFile(filePath);
        waitForElementVisible(ldriver, lblUnableToCreateHero);
        Assert.assertEquals(lblUnableToCreateHero.getText(), failureMessage);
    }

    /**
     * Upload multiple heroes via CSV file
     *
     * @param filePath path to the CSV file
     * @return void
     * @autor Niroshani
     * @completed 2025-02-06
     */
    private void uploadHeroesFile(String filePath) {
        ddlAddAHero.click();
        txtUploadACSVFile.click();

        // Ensure the element is visible before sending keys
        Assert.assertTrue(elementToBeClickable(ldriver, txtUploadFile), "Element is clickable");

        //Upload the file using sendKeys
        txtUploadFile.sendKeys(filePath);

        captureScreenshots(ldriver, "uploadHeroes");
        btnCreate.click();
    }

    /**
     * Verify heroes created via CSV file
     *
     * @return void
     * @autor Niroshani
     * @completed 2025-02-06
     */
    public void verifyHeroesCreatedViaCSV(String filePath) {

        natidList = getNatidsFromCSV(filePath);

        // Print natid values
        for (String natid : natidList) {
            System.out.println("Natid: " + natid);
            String dbNatId = DBQueryHelper.retrieveDBRecordFromNatid(natid);
            Assert.assertEquals(dbNatId, natid, "Record is found in DB");
        }

    }

    public void deleteWorkingClassHeroAndVerify() {
        for (String natid : natidList) {
            DBQueryHelper.deleteRecordFromWorkingClassHeroes(natid);
            DBQueryHelper.verifyRecordDeletedFromWorkingClassHeroes(natid);
        }
    }

}


