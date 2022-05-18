/**
 * 
 */
package org.seleniumpraveen.com.core.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.seleniumpraveen.com.core.driver.DriverSetup;
import org.seleniumpraveen.com.core.exception.FrameworkExceptions;
import org.seleniumpraveen.com.core.logger.Logs;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

/**
 * @author praveenkumar.tiwari
 *
 */
public class JSExecuter {

	private JSExecuter() {
	}

	public static JavascriptExecutor getJavaScriptExec() throws FrameworkExceptions {
		return (JavascriptExecutor) DriverSetup.getDriver();
	}

	public static void jsExecuter(String value) throws FrameworkExceptions {
		try {
			getJavaScriptExec().executeScript(value);
		} catch (WebDriverException we) {
			throw new FrameworkExceptions("Exception Occured " + we);
		}
	}

	// Java Script Executor
	public static void jsExecutor(String value, WebElement element) throws FrameworkExceptions {
		try {
			getJavaScriptExec().executeScript(value, element);
		} catch (WebDriverException we) {
			throw new FrameworkExceptions("Exception occurred" + we);
		}
	}

	// Click using Java Script Executor
	public static void jsClick(WebElement element) throws FrameworkExceptions {
		jsExecutor("arguments[0].click();", element);
		Waits.waitForPageLoadJS();
	}

	// Get Value using Java Script Executor
	public static String jsGetValue(WebElement element) throws FrameworkExceptions {
		return getJavaScriptExec().executeScript("return arguments[0].value;", element).toString();
	}

	public static void mouseHover(WebElement element) {
		try {
			String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover',true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
			((JavascriptExecutor) DriverSetup.getDriver()).executeScript(mouseOverScript, element);
		} catch (Exception e) {
			Logs.error("Failed in mouseHover method in JSExecutor class");
		}
	}
}
