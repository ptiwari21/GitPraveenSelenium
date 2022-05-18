/**
 * 
 */
package org.seleniumpraveen.com.core.logger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.seleniumpraveen.com.core.driver.DriverSetup;
import org.seleniumpraveen.com.core.exception.FrameworkExceptions;
import org.seleniumpraveen.com.core.utils.Config;
import org.seleniumpraveen.com.core.utils.ExcelUtils;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

/**
 * @author praveenkumar.tiwari
 *
 */
public class ListenerClass extends TestListenerAdapter {

	WebDriver driver = null;
	DateFormat dateFormat = new SimpleDateFormat("dd_mm_yyyy_hh_mm_ssaa");
	Date date = new Date();

	@Override
	public void onTestFailure(ITestResult result) {
		try {
			Report.fail(result.getThrowable());
		} catch (FrameworkExceptions e) {
			Report.failWithoutScreenshot(result.getThrowable());
		}
		if (DriverSetup.getDriver() != null) {
			DriverSetup.getDriver().quit();
			DriverSetup.setDriver(null);
			Report.info("Browser Instance closed successfully on Test Failure.");
		}
	}

	@Override
	public void onFinish(ITestContext arg0) {
		if (DriverSetup.getDriver() != null) {
			DriverSetup.getDriver().quit();
			DriverSetup.setDriver(null);
			Report.info("Browser Instance closed successfully on Test Finish./n/n");
		}
		Report.info("Execution finished for Test " + arg0.getName() +"\n\n");
		ExtentTestManager.endTest();
		ExtentManager.getReporter().flush();
	}

	@Override
	public void onStart(ITestContext arg0) {
		Config.testCaseId = arg0.getName();
		ExtentTestManager.startTest(Config.testCaseId, ExcelUtils.getCellValue("TestRunner", "Test_Description"));
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		Report.info("Test execution ended for test case " + arg0.getName());
	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		Report.skip("Test execution ended: Test Skipped " + arg0.getName());
		if (DriverSetup.getDriver() != null) {
			DriverSetup.getDriver().quit();
			DriverSetup.setDriver(null);
			Report.skip("Browser Instance closed successfully on Test Skip.");
		}
	}

	@Override
	public void onTestStart(ITestResult arg0) {
		Report.info("Test execution started for test case '"+Config.testCaseId+"'.");
	}

	@Override
	public void onTestSuccess(ITestResult arg0) {
		if (DriverSetup.getDriver() != null) {
			DriverSetup.getDriver().quit();
			DriverSetup.setDriver(null);
			Report.info("Browser Instance closed successfully on Test Success.");
		}
		Report.passWithoutScreenshot("All test steps executed successfully.");
	}

}
