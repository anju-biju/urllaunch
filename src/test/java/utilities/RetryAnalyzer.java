package utilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/***
 * Utility class to implement retry whenever the test is failing
 * It will retry three times whenever a test is failed
 */

public class RetryAnalyzer implements IRetryAnalyzer {
    int counter = 0;
    int retryLimit =2;

    /***
     * Set retry limit to execute failed test cases
     * @param result
     * @return
     */
    public boolean retry(ITestResult result){
        if(counter < retryLimit){
            counter++;
            return true;
        }
        return false;
    }
}
