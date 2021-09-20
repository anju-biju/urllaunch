package utilities;

import com.aventstack.extentreports.ExtentReports;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/****
 * Utility class to configure styles in Extent Report
 * Configure path for extent report
 */

public class ExtentManager{

    private static String reportBaseDirectory;
    private static ExtentReports extent;


    public static ExtentReports getInstance() {
        if (extent == null)
            createInstance();
        return extent;
    }

    /***
     *  Create an extent report instance
     */

    public static void createInstance() {
        ExtentManager.initDirectories();
        setReportBaseDirectory(StringConstants.REPORT_FILE_PATH);
        DateFormat dateFormat = new SimpleDateFormat("MMM_dd_yyyy_HH_mm_ss_SSS");
        Date date = new Date();
        String dateName = dateFormat.format(date);
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(StringConstants.REPORT_FILE_PATH+"Test-Automaton-Report.html"+"_"+dateName);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setDocumentTitle("Automation Test Results");
        htmlReporter.config().setReportName("Automation Test Results");
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("OS",  System.getProperty("os.name"));
        extent.setSystemInfo("Java",  System.getProperty("java.specification.version"));
        extent.setSystemInfo("User",  System.getProperty("user.name"));
    }

    public synchronized static String getReportBaseDirectory() {
        return reportBaseDirectory;
    }
    public synchronized static void setReportBaseDirectory(String reportBaseDirectory) {
        ExtentManager.reportBaseDirectory = reportBaseDirectory;
    }

    public static void initDirectories() {
        try {
            createFolder(StringConstants.REPORT_FILE_PATH + StringConstants.OUTPUT_FOLDER_SCREENSHOTS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void createFolder(String folderPath) {
        File file = new File(folderPath);
        if (!file.exists()) file.mkdirs();
    }


}

