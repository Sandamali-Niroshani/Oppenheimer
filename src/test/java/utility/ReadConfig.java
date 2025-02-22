package utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

    //create object of prperties class
    Properties pro;

    public ReadConfig() {
        File src = new File("./Configuration/config.properties");

        try {
            FileInputStream fis = new FileInputStream(src);
            pro = new Properties();
            //load complete file
            pro.load(fis);
        } catch (Exception e) {
            System.out.println("Exception is " + e.getMessage());
        }
    }

    //Add different method for each variables in config.properties
    public String getClerkUserName() {
        String clerkUserName = pro.getProperty("clerkUserName");
        return clerkUserName;
    }

    public String getClerkPassword() {
        String clerkPassword = pro.getProperty("clerkPassword");
        return clerkPassword;
    }

    public String getBookKeeperUserName() {
        String bkUserName = pro.getProperty("bkUserName");
        return bkUserName;
    }

    public String getBookKeeperPassword() {
        String bkPassword = pro.getProperty("bkPassword");
        return bkPassword;
    }

    public String getApplicationURL() {
        String url = pro.getProperty("baseUrl");
        return url;
    }

    public String getBrowser() {
        String browser = pro.getProperty("browser");
        return browser;
    }

}
