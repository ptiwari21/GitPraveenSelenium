/**
 * 
 */
package org.seleniumpraveen.com.core.logger;

import org.seleniumpraveen.com.core.exception.FrameworkExceptions;
import org.seleniumpraveen.com.core.utils.Screenshot;
import com.relevantcodes.extentreports.LogStatus;

/**
 * @author praveenkumar.tiwari
 *
 */
public class ExtentReport {

	public static void failWithoutScreenshot(Throwable throwable) {
		ExtentTestManager.getTest().log(LogStatus.FAIL, throwable.getMessage());
	}

	public static void failWithoutScreenshot(String details) {
		ExtentTestManager.getTest().log(LogStatus.FAIL, details);
	}

	public static void failWithoutScreenshot(String stepName, String details) {
		ExtentTestManager.getTest().log(LogStatus.FAIL, stepName, details);
	}

	public static void failWithoutScreenshot(String stepName, Throwable throwable) {
		ExtentTestManager.getTest().log(LogStatus.FAIL, stepName, throwable);
	}

	public static void fail(Throwable throwable) throws FrameworkExceptions {
		ExtentTestManager.getTest().log(LogStatus.FAIL,
				throwable + "\n" + ExtentTestManager.getTest().addBase64ScreenShot(Screenshot.getBase64Screenshot()));
	}

	public static void fail(String details) throws FrameworkExceptions {
		ExtentTestManager.getTest().log(LogStatus.FAIL,
				details + "\n" + ExtentTestManager.getTest().addBase64ScreenShot(Screenshot.getBase64Screenshot()));
	}

	public static void fail(String stepName, String details) throws FrameworkExceptions {
		ExtentTestManager.getTest().log(LogStatus.FAIL, stepName,
				details + "\n" + ExtentTestManager.getTest().addBase64ScreenShot(Screenshot.getBase64Screenshot()));
	}

	public static void fail(String stepName, Throwable throwable) throws FrameworkExceptions {
		ExtentTestManager.getTest().log(LogStatus.FAIL, stepName,
				throwable + "\n" + ExtentTestManager.getTest().addBase64ScreenShot(Screenshot.getBase64Screenshot()));
	}

	public static void pass(String details) throws FrameworkExceptions {
		ExtentTestManager.getTest().log(LogStatus.PASS,
				details + "\n" + ExtentTestManager.getTest().addBase64ScreenShot(Screenshot.getBase64Screenshot()));
	}

	public static void pass(String stepName, String details) throws FrameworkExceptions {
		ExtentTestManager.getTest().log(LogStatus.PASS, stepName,
				details + "\n" + ExtentTestManager.getTest().addBase64ScreenShot(Screenshot.getBase64Screenshot()));
	}

	public static void passWithoutScreenshot(String details) {
		ExtentTestManager.getTest().log(LogStatus.PASS, details);
	}

	public static void passWithoutScreenshot(String stepName, String details) {
		ExtentTestManager.getTest().log(LogStatus.PASS, stepName, details);
	}

	public static void skip(String details) {
		ExtentTestManager.getTest().log(LogStatus.SKIP, details);
	}

	public static void skip(String stepName, String details) {
		ExtentTestManager.getTest().log(LogStatus.SKIP, stepName, details);
	}

	public static void info(String details) {
		ExtentTestManager.getTest().log(LogStatus.INFO, details);
	}

	public static void info(String stepName, String details) {
		ExtentTestManager.getTest().log(LogStatus.INFO, stepName, details);
	}
}
