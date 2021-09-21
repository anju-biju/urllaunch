package LaunchBrowserURL;

import org.testng.annotations.Test;
import utilities.BaseClass;

/**
 * Test class for Launching URL
 */

public class LaunchBrowser extends BaseClass {
    @Test()
    public void LaunchBrowserUrl()
    {
        df.launchURL();
            }


}
