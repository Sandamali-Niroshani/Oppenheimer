package web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utility.ReadConfig;

import java.util.List;

public class LoginPage extends PageBase {
    ReadConfig readConfig = new ReadConfig();
    private WebDriver ldriver;

    public LoginPage(WebDriver rdriver) {
        ldriver = rdriver;
        PageFactory.initElements(rdriver, this);
    }

    @FindBy(id = "username-in")
    private WebElement txtUserName;
    @FindBy(id = "password-in")
    private WebElement txtPassword;
    @FindBy(xpath = "//input[@type='submit']")
    private WebElement btnLogin;
    @FindBy(xpath = "//h1[text()='Clerk Dashboard']")
    private WebElement lblClerkDashboard;
    @FindBy(xpath = "//h1[text()='Book Keeper Dashboard']")
    private WebElement lblBookKeeperDashboard;
    @FindBy(xpath = "//a[text()='Active']")
    private WebElement lnkActive;
    @FindBy(xpath = "//a[text()='Completed']")
    private WebElement lnkCompleted;
    @FindBy(xpath = "//a[text()='All']")
    private WebElement lnkAll;
    @FindBy(xpath = "//button[@class='clear-completed']")
    private WebElement btnClearCompleted;
    @FindBy(xpath = "//div[@class='view']//input[@type='checkbox']")
    private WebElement rdItem;
    @FindBy(xpath = "//div[@class='view']//input[@type='checkbox']")
    private List<WebElement> rdItems;
    @FindBy(xpath = "//button[@class='destroy']")
    private WebElement btnClose;
    @FindBy(xpath = "//button[@class='destroy']")
    private List<WebElement> btnCloseIcons;
    @FindBy(xpath = "//span[@class='todo-count']")
    private WebElement txtToDoActiveCount;
    @FindBy(id = "toggle-all")
    private WebElement chkToggleAll;



    /**
     * This method is used to login to the application
     *
     * @return void
     * @autor Niroshani
     * @completed 2025-02-06
     */
    public void loginToApplicationAsClerk() {

        String userName = readConfig.getClerkUserName();
        String password = readConfig.getClerkPassword();
        loginToApplication(userName, password);
        waitForElementVisible(ldriver, lblClerkDashboard);// Ensure element is visible
        captureScreenshots(ldriver, "loginToApplication");
    }

    public void loginToApplicationAsBookKeeper() {

        String userName = readConfig.getBookKeeperUserName();
        String password = readConfig.getBookKeeperPassword();
        loginToApplication(userName, password);
        waitForElementVisible(ldriver, lblBookKeeperDashboard);// Ensure element is visible
        captureScreenshots(ldriver, "loginToApplication");
    }

    private void loginToApplication(String userName, String password) {

        txtUserName.sendKeys(userName);
        txtPassword.sendKeys(password);
        btnLogin.click();
    }

}
