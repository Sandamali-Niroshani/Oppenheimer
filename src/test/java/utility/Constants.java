package utility;

public class Constants {

    public static final String BASE_URL = "http://localhost:9997";

    public static final String CORRECTHEROESCSVFILEPATH = System.getProperty("user.dir") + "\\src\\test\\java\\resources\\files\\heros.csv";
    public static final String ERRONEOUSHEROESCSVFILEPATH = System.getProperty("user.dir") + "\\src\\test\\java\\resources\\files\\errorHeros.csv";
    public static final String FILEDOWNLOADPATH = System.getProperty("user.dir") + "\\src\\test\\java\\resources\\download";


    public static final String FILEUPLOADSUCCESSFULLYMSG = "Created Successfully!";
    public static final String FAILEDFILEUPLOADMSG = "There are 2 records which were not persisted! Please contact tech support for help!";
   public static final String  VOUCHERNAMECANNOTBEBLANK = "Voucher Name cannot be blank";

    public static final String OWESTATUS = "OWE";
    public static final String NILSTATUS = "NIL";
}
