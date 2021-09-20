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

    public List<WebElement> visibilityOfAllElements(By locator, int timeOut){
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public WebElement waitForElementToBeLocated(By locator, int timeOut){
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public WebElement waitForElementToBeVisible(By locator, int timeOut){
        WebElement element = driver.findElement(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public String waitForPageTitlePresent(String titleValue, int timeOut){
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        wait.until(ExpectedConditions.titleContains(titleValue));
        return driver.getTitle();
    }

    public Alert waitForAlertToBePresent(int timeOut){
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        return wait.until(ExpectedConditions.alertIsPresent());
    }

    public boolean waitForUrl(String url, int timeOut){
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        return wait.until(ExpectedConditions.urlContains(url));
    }

    public void clickWhenReady(By locator, int timeOut){
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }
    public void waitForFrame(By locator, int timeOut){
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
         wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));

    }


}
