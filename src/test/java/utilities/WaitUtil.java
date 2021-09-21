package utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
/**
 * Wait Utility for page and test classes
 */

import java.util.List;

public class WaitUtil extends BaseClass {
    private WebDriver driver;



    public WaitUtil(WebDriver driver) {
        this.driver = driver;


    }

}
