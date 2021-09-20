package utilities;


import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

/**
 * Helper utility class or implementing all the general scenarios applicable to Service Now and VRA
 */
public class Helper {

    private WebDriver driver;
    WaitUtil waits;


    public Helper(WebDriver driver) {
        this.driver = driver;
        waits = new WaitUtil(driver);
    }


    public void selectDropDownValue(By locator, String type, String value) {

        Select select = new Select(getElement(locator));

        switch (type) {
            case "index":
                select.selectByIndex(Integer.parseInt(value));
                break;
            case "value":
                select.selectByValue(value);
                break;
            case "visibleText":
                select.selectByVisibleText(value);
                break;
            default:
                System.out.println("Please send the correct dropdown selection.......");
                break;
        }
    }

    public void inputValues(By locator, String value) {

        WebElement inputValue = getElement(locator);
        inputValue.sendKeys(value);

    }

    public void windowHandling(By locator, By childLocator) {
        String mainHandle = driver.getWindowHandle();
        (getElement(locator)).click();
        Set<String> allHandles = driver.getWindowHandles();
        System.out.println("Windows open after click " + allHandles.size());
        for (String windowHandle : allHandles) {
            if (mainHandle.equals(windowHandle)) {
                System.out.println("\t Parent Window ID is: \t" + windowHandle + "\n\t URL:\t\t" + driver.getCurrentUrl() + "\n\t Title:\t\t" + driver.getTitle());
            } else {
                driver.switchTo().window(windowHandle);
                System.out.println("\t Child Window ID is: \t" + windowHandle + "\n\t URL:\t\t" + driver.getCurrentUrl() + "\n\t Title:\t\t" + driver.getTitle());
                waits.clickWhenReady(childLocator, 30);


            }
        }

        driver.switchTo().window(mainHandle);
        driver.switchTo().frame(0);
    }


    public void clickButton(By locator) {

        getElement(locator).click();
    }

    public void clearField(By locator) {
        getElement(locator).clear();
    }

    public void switchFrame(By locator) {

        driver.switchTo().frame(getElement(locator));
    }


    public String screenShot(String filename) throws IOException {
        String path = System.getProperty("user.dir") + StringConstants.OUTPUT_FOLDER_SCREENSHOTS_AsSHOT + filename + getCurrentDate()+".png";
        File dest = new File(path);
        Screenshot fpScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
        ImageIO.write(fpScreenshot.getImage(), "PNG", dest);
        return path;

    }

    public String getAttribute(By locator) {
        String value = getElement(locator).getAttribute("value");
        return value;

    }

    public WebElement getElement(By locator) {

        return driver.findElement(locator);
    }


    public static String decodeString(String password) {
        byte[] decodedString = Base64.decodeBase64(password);
        return (new String(decodedString));

    }

    public String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yy.HH.mm.ss");
        Date date = new Date();
        String date1 = dateFormat.format(date);
        return date1;
    }
    public static String getCurrentDateWithoutTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yy");
        Date date = new Date();
        String date1 = dateFormat.format(date);
        return date1;
    }
    public void keyPressAndRelease() throws AWTException {
        Robot r = new Robot();
        r.keyPress(KeyEvent.VK_ENTER);
        r.keyRelease(KeyEvent.VK_ENTER);
    }

    public String  SwitchToAlertAndGetTextValue()
    {

        /***
         *  Switching to Alert
         */

        Alert alert = driver.switchTo().alert();

        /***
         * Capturing alert message
         */

        String alertMessage=  driver.switchTo().alert().getText();

        alert.accept();

        return alertMessage;

    }
    public void staticWait(int timeout) {
        try
        {
            Thread.sleep(timeout);
        }
        catch(InterruptedException  e)
        {
        }
    }

    public void refreshPageAndAcceptReload(){
        driver.navigate().refresh();
        staticWait(10000);
        driver.switchTo().alert().accept();
    }


}



