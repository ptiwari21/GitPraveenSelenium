/**
 * 
 */
package org.seleniumpraveen.com.core.utils;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnhandledAlertException;
import org.seleniumpraveen.com.core.driver.DriverSetup;
import org.seleniumpraveen.com.core.exception.FrameworkExceptions;
import org.seleniumpraveen.com.core.logger.Logs;

/**
 * @author praveenkumar.tiwari
 *
 */
public class Screenshot {

	public static void takeScreenshot(String imageName, String imagePath) throws FrameworkExceptions {
		try {
			File scrFile = ((TakesScreenshot) DriverSetup.getDriver()).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(imagePath + "\\" + imageName + ".png"));
		} catch (IOException e) {
			Logs.error("Io Exception occured", e);
		} catch (UnhandledAlertException alertException) {
			Logs.error("UnhandledAlert Exception occured");
		} catch (Exception e) {
			throw new FrameworkExceptions(e);
		}
	}

	public static void takeScreenshot(String imagePath) throws FrameworkExceptions {
		try {
			File scrFile = ((TakesScreenshot) DriverSetup.getDriver()).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(imagePath));
		} catch (IOException e) {
			Logs.error("Io Exception occured", e);
		} catch (UnhandledAlertException alertException) {
			Logs.error("UnhandledAlert Exception occured");
		} catch (Exception e) {
			throw new FrameworkExceptions(e);
		}
	}

	public static String takeScreenshot() throws FrameworkExceptions {
		DateFormat dateFormat = new SimpleDateFormat("dd_mm_yyyy_hh_mm_ssaa");
		Date date = new Date();
		String imagePath = System.getProperty("user.dir") + "\\reports\\praveen-selenium_"
				+ System.getProperty("current.date") + "\\screenshot\\" + Config.testCaseId + "\\" + Config.testCaseId
				+ "_" + dateFormat.format(date) + ".png";
		try {
			File scrFile = ((TakesScreenshot) DriverSetup.getDriver()).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(imagePath));
		} catch (IOException e) {
			Logs.error("Io Exception occured", e);
		} catch (UnhandledAlertException alertException) {
			Logs.error("UnhandledAlert Exception occured");
		} catch (Exception e) {
			throw new FrameworkExceptions(e);
		}
		return imagePath;
	}

	public static String getBase64Screenshot() throws FrameworkExceptions {
		try {
			return "data:image/png;base64,"
					+ ((TakesScreenshot) DriverSetup.getDriver()).getScreenshotAs(OutputType.BASE64);
		} catch (Exception e) {
			throw new FrameworkExceptions(e);
		}
	}
}
