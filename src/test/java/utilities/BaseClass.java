package utilities;


import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import reusableMethods.propertiesOperations;

/***
 * Baseclass for opening and closing browser
 */
public class BaseClass {

    public static WebDriver driver;
    public static Logger EventLogger;
    public Helper helper;
    public DriverFactory df;
    public JavaScriptUtil jsutil;


    @BeforeClass
    public void launchBrowser() throws Exception {
        EventLogger = Logger.getLogger(String.valueOf(BaseClass.class));
        df = new DriverFactory();
        driver = df.init_driver(propertiesOperations.getPropertyValues("browser"));
        helper = new Helper(driver);
        jsutil = new JavaScriptUtil(driver);
    }

    @AfterClass
    public void tearDownDriver() {
        if (driver != null) {
            try {
                driver.close();
            } catch (WebDriverException e) {
                System.out.println("***** CAUGHT EXCEPTION IN DRIVER TEARDOWN *****");
                System.out.println(e);
            }

        }
    }
}