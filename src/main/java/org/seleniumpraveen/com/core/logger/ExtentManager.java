/**
 * 
 */
package org.seleniumpraveen.com.core.logger;

import com.relevantcodes.extentreports.ExtentReports;

/**
 * @author praveenkumar.tiwari
 *
 */
public class ExtentManager {

    private static ExtentReports extent;

    public synchronized static ExtentReports getReporter(){
        if(extent == null){
            //Set HTML reporting file location
            extent = new ExtentReports(System.getProperty("user.dir") + "\\reports\\praveen-selenium_"
					+ System.getProperty("current.date")+"\\ExtentReportResults.html", true);
        }
        return extent;
    }
}