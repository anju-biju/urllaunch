package reusableMethods;

import org.openqa.selenium.WebDriver;
import utilities.JavaScriptUtil;
import utilities.WaitUtil;

/**
 * Service Now reusable  class for getting  all the locators
 */

public class Helper  {

    private WebDriver driver;
    Helper commonHelper;
    WaitUtil waits;
    JavaScriptUtil jsutil;
    public Helper(WebDriver driver) {

        this.driver = driver;
        commonHelper = new Helper(driver);
        waits = new WaitUtil(driver);
        jsutil= new JavaScriptUtil(driver);

    }






}

