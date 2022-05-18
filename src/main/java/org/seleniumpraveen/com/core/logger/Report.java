/**
 * 
 */
package org.seleniumpraveen.com.core.logger;

import org.seleniumpraveen.com.core.exception.FrameworkExceptions;
import org.seleniumpraveen.com.core.utils.Screenshot;
import org.testng.Reporter;

import com.google.common.base.Throwables;

/**
 * @author praveenkumar.tiwari
 *
 */
public class Report {

	public static void info(String stepName, String message) {
		Logs.info(message);
		Reporter.log(message, true);
		ExtentReport.info(stepName, message);
	}
	
	public static void info(String message) {
		Logs.info(message);
		Reporter.log(message, true);
		ExtentReport.info(message);
	}
	
	public static void pass(String stepName, String message) throws FrameworkExceptions  {
		Logs.info(message);
		Reporter.log(message, true);
		Screenshot.takeScreenshot();
		ExtentReport.pass(stepName, message);
	}
	
	public static void pass(String message) throws FrameworkExceptions  {
		Logs.info(message);
		Reporter.log(message, true);
		Screenshot.takeScreenshot();
		ExtentReport.pass(message);
	}
	
	public static void passWithoutScreenshot(String stepName, String message) {
		Logs.info(message);
		Reporter.log(message, true);
		ExtentReport.passWithoutScreenshot(stepName, message);
	}
	
	public static void passWithoutScreenshot(String message) {
		Logs.info(message);
		Reporter.log(message, true);
		ExtentReport.passWithoutScreenshot(message);
	}
	
	public static void skip(String stepName, String message) {
		Logs.info(message);
		Reporter.log(message, true);
		ExtentReport.skip(stepName, message);
	}
	
	public static void skip(String message) {
		Logs.info(message);
		Reporter.log(message, true);
		ExtentReport.skip(message);
	}
	
	public static void fail(Throwable throwable) throws FrameworkExceptions  {
		Logs.error(Throwables.getStackTraceAsString(throwable));
		Reporter.log(Throwables.getStackTraceAsString(throwable), true);
		Screenshot.takeScreenshot();
		ExtentReport.fail(throwable);
	}
	
	public static void fail(String message) throws FrameworkExceptions  {
		Logs.error(message);
		Reporter.log(message, true);
		Screenshot.takeScreenshot();
		ExtentReport.fail(message);
		
	}
	
	public static void fail(String stepName, String message) throws FrameworkExceptions  {
		Logs.error(message);
		Reporter.log(message, true);
		Screenshot.takeScreenshot();
		ExtentReport.fail(stepName, message);
		
	}
	
	public static void failWithoutScreenshot(Throwable throwable)  {
		Logs.error(Throwables.getStackTraceAsString(throwable));
		Reporter.log(Throwables.getStackTraceAsString(throwable), true);
		ExtentReport.failWithoutScreenshot(throwable);
	}
	
	public static void failWithoutScreenshot(String stepName, String message)  {
		Logs.error(message);
		Reporter.log(message, true);
		ExtentReport.failWithoutScreenshot(stepName, message);
		
	}
	
	public static void failWithoutScreenshot(String message)  {
		Logs.error(message);
		Reporter.log(message, true);
		ExtentReport.failWithoutScreenshot(message);
	}

}
