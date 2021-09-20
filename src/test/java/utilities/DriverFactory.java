package utilities;

import com.microsoft.edge.seleniumtools.EdgeDriver;
import com.microsoft.edge.seleniumtools.EdgeOptions;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import reusableMethods.propertiesOperations;

/**
 * Launch Browser
 * Initialization of ThreadLocal driver
 * Launch Url based on Environment in Config.properties
 */

public class DriverFactory {


    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    public static Logger LOGGER;


    /**
     * Intialization of Webdriver and Launching browser
     * @param browserName
     * @return WebDriver
     */
    public WebDriver init_driver(String browserName) throws Exception{
        LOGGER = Logger.getLogger(String.valueOf(BaseClass.class));

        if (browserName.equalsIgnoreCase("edge")) {
            String edgeDriverPath = propertiesOperations.getPropertyValues("edge_driver_path");
            String path = System.getProperty("user.dir");

            System.setProperty("webdriver.edge.driver", path + edgeDriverPath);
            System.out.println("initialized path");
//            System.setProperty("webdriver.edge.UseChromium", "true");



            EdgeOptions options = new EdgeOptions();
//            options.addArguments("--headless");
//            options.addArguments("window-size=1200x600");
//            options.setHeadless(true);
//            options.addArguments("headless");
//            options.addArguments("disable-gpu");

            tlDriver.set(new EdgeDriver(options));
            LOGGER.info("Launched Edge Browser");


        } else {
            System.out.println("Please pass the correct browser name : " + browserName);
            LOGGER.info("Please pass the correct browser name : \" + browserName");
        }


        getDriver().manage().window().maximize();
        return getDriver();
    }


    public  static synchronized WebDriver getDriver()
    {
        return tlDriver.get();
    }

    /**
     * Launching URL
     */
    public void launchURL()  {
        try{
            String url;
                url = propertiesOperations.getPropertyValues("URL");
                getDriver().get(url);
                System.out.println("title: " + getDriver().getCurrentUrl());
                        LOGGER.info("Launched URL");

           // }

        }
        catch(Exception e){
            System.out.println("Please pass the correct url" );
            LOGGER.info("Please pass the correct url");
        }



    }

}
