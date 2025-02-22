package web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class BookKeeperDashBoard extends PageBase {

    private WebDriver ldriver;

    @FindBy(id = "tax_relief_btn")
    private WebElement btnTaxRelief;

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

    public BookKeeperDashBoard(WebDriver rdriver) {
        ldriver = rdriver;
        PageFactory.initElements(rdriver, this);
    }

    /**
     * Download tax relief file
     *
     * @param filePath
     */

    public void downloadTaxReliefFile(String filePath, String fileName) {
        jsClick(ldriver, btnTaxRelief);
        captureScreenshots(ldriver, "downloadTaxReliefFile");
        // Wait for file to be downloaded
        File downloadedFile = new File(filePath + "/" + fileName);

        int waitTime = 10; // Max wait time in seconds
        while (waitTime > 0 && !downloadedFile.exists()) {
            // Wait for file to be downloaded
            ldriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
            waitTime--;
        }

        // Verify the file is downloaded
        if (downloadedFile.exists()) {
            System.out.println("File downloaded successfully: " + downloadedFile.getAbsolutePath());
        } else {
            System.err.println(" File not found! Download failed.");
        }
    }

    public void readAndValidateFile(String filePath, String fileName) {

        filePath = filePath + "/" + fileName;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            List<String> bodyRecords = new ArrayList<>();
            int expectedFooter = 0;
            int lineCount = 0;

            while ((line = br.readLine()) != null) {
                lineCount++;

                // Check if it's a footer (last line)
                if (line.matches("\\d+")) {
                    expectedFooter = Integer.parseInt(line);
                } else {
                    // Validate line format <natid>, <tax relief amount>
                    String[] parts = line.split(",");
                    if (parts.length == 2) {
                        bodyRecords.add(line);
                    } else {
                        System.err.println("Invalid line format: " + line);
                    }
                }
            }



            // Validate footer count
            if (bodyRecords.size() == expectedFooter) {
                System.out.println("File validation passed. Total records: " + expectedFooter);
            } else {
                System.err.println("Footer count mismatch. Expected: " + expectedFooter + ", Found: " + bodyRecords.size());

            }

        } catch (IOException e) {
            //log error

            // create validation error


            e.printStackTrace();
        }
    }


}


