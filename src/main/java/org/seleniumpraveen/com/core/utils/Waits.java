/**
 * 
 */
package org.seleniumpraveen.com.core.utils;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.regex.Pattern;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.seleniumpraveen.com.core.driver.DriverSetup;
import org.seleniumpraveen.com.core.exception.FrameworkExceptions;
import org.seleniumpraveen.com.core.helper.Constants;
import org.seleniumpraveen.com.core.logger.Logs;
import org.seleniumpraveen.com.core.ui.CoreUi;
import org.seleniumpraveen.com.core.ui.ElementFinder;

/**
 * @author praveenkumar.tiwari
 *
 */
public class Waits {

	private static int waitTime = 0;
	public static long defaultTimeout = 2000;
	private static WebElement element;
	private static WebDriver driver = DriverSetup.getDriver();
	private static ElementFinder elementFinder = new ElementFinder();
	private static CoreUi coreUi = new CoreUi();

	// Get Wait Time
	public static int getWaitTime() throws FrameworkExceptions {
		if (waitTime == 0)
			waitTime = Integer.parseInt(Config.getConfigProperty(Constants.ELEMENT_WAIT_TIME));
		return waitTime;
	}

	// Static wait with Wait Time as Input parameter
	public static void staticWait(long waitTimeInSeconds) {
		try {
			Thread.sleep(waitTimeInSeconds * 1000);
		} catch (Exception e) {
			Logs.error("Failed in staticWait method" + e);
		}
	}

	public static boolean waitForPageLoadJS() {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};
		try {
			Thread.sleep(1000);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(expectation);
			return true;
		} catch (Exception e) {
			Logs.error("Page did not load");
			return false;
		}

	}

	// Fluent Wait
	@SuppressWarnings("deprecation")
	public static WebElement fluentWait(final WebElement element, long duration) throws FrameworkExceptions {
		try {
			return new FluentWait<WebDriver>(driver).withTimeout(duration, TimeUnit.SECONDS)
					.ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
					.pollingEvery(1, TimeUnit.SECONDS).until(new Function<WebDriver, WebElement>() {
						public WebElement apply(WebDriver input) {
							boolean isPresent = element.isDisplayed() && element.isEnabled();
							if (isPresent) {
								return element;
							} else {
								return null;
							}

						}
					});
		} catch (Exception e) {
			throw new FrameworkExceptions("Element: " + element + " not found" + e);
		}
	}

	public static boolean waitForElementExist(String locatorValue, long duration) throws FrameworkExceptions {
		for (int i = 1; i <= duration; i++) {
			if (coreUi.isElementPresent(locatorValue)) {
				return true;
			} else {
				staticWait(1);
			}
		}
		return false;
	}

	public static boolean waitForElementVisible(String locatorValue, long duration) throws FrameworkExceptions {
		for (int i = 1; i <= duration; i++) {
			if (coreUi.isElementDisplayed(locatorValue)) {
				return true;
			} else {
				staticWait(1);
			}
		}
		return false;
	}

	public static boolean waitForElementInvisible(String locatorValue, long duration) {
		for (int i = 1; i <= duration; i++) {
			if (!coreUi.isElementDisplayed(locatorValue)) {
				return true;
			} else {
				staticWait(1);
			}
		}
		return false;
	}

	public static Boolean WaitForInvisibilityOfElementWithText(final By locator, final String text, long duration)
			throws FrameworkExceptions {
		try {
			return new WebDriverWait(driver, duration)
					.until(ExpectedConditions.invisibilityOfElementWithText(locator, text));
		} catch (Exception e) {
			throw new FrameworkExceptions(String.format("element containing '%s' visible: %s", text, locator), e);
		}

	}

	public static WebDriver WaitForFrameToBeAvailableAndSwitchToIt(final String frameLocator, long duration)
			throws FrameworkExceptions {
		try {
			return new WebDriverWait(driver, duration)
					.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
		} catch (Exception e) {
			throw new FrameworkExceptions("frame failed to be available: " + frameLocator, e);
		}

	}

	public static WebDriver WaitForFrameToBeAvailableAndSwitchToIt(final WebElement frameLocator, long duration)
			throws FrameworkExceptions {
		try {
			return new WebDriverWait(driver, duration)
					.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
		} catch (Exception e) {
			throw new FrameworkExceptions("frame failed to be available: " + frameLocator, e);
		}

	}

	public static WebDriver WaitForFrameToBeAvailableAndSwitchToIt(final int frameLocator, long duration)
			throws FrameworkExceptions {
		try {
			return new WebDriverWait(driver, duration)
					.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
		} catch (Exception e) {
			throw new FrameworkExceptions("frame failed to be available: " + frameLocator, e);
		}

	}

	public static WebDriver WaitForFrameToBeAvailableAndSwitchToIt(final By locator, long duration)
			throws FrameworkExceptions {
		try {
			return new WebDriverWait(driver, duration)
					.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
		} catch (Exception e) {
			throw new FrameworkExceptions("frame failed to be available: " + locator, e);
		}
	}

	public static boolean WaitForInvisibilityOfElementLocated(final By locator, long duration)
			throws FrameworkExceptions {
		try {
			return new WebDriverWait(driver, duration).until(ExpectedConditions.invisibilityOfElementLocated(locator));
		} catch (Exception e) {
			throw new FrameworkExceptions("element visible: " + locator, e);
		}

	}

	public static WebElement WaitForElementToBeClickable(final By locator, long duration) throws FrameworkExceptions {
		try {
			return new WebDriverWait(driver, duration).until(ExpectedConditions.elementToBeClickable(locator));
		} catch (Exception e) {
			throw new FrameworkExceptions("element failed to be clickable: " + locator, e);
		}

	}

	public static WebElement WaitForElementToBeClickable(final WebElement element, long duration)
			throws FrameworkExceptions {
		try {
			return new WebDriverWait(driver, duration).until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception e) {
			throw new FrameworkExceptions("element failed to be clickable: " + element, e);
		}

	}

	public static boolean WaitForElementToBeSelected(final By locator, long duration) throws FrameworkExceptions {
		try {
			return new WebDriverWait(driver, duration).until(ExpectedConditions.elementToBeSelected(locator));
		} catch (Exception e) {
			throw new FrameworkExceptions(String.format("element (%s) failed to be selected", element), e);
		}

	}

	public static boolean WaitForElementToBeSelected(final WebElement element, long duration)
			throws FrameworkExceptions {
		try {
			return new WebDriverWait(driver, duration).until(ExpectedConditions.elementToBeSelected(element));
		} catch (Exception e) {
			throw new FrameworkExceptions("Element: " + element + " not found" + e);
		}

	}

	public static List<WebElement> waitForNumberOfElementsToBeMoreThan(final By locator, final Integer number,
			long duration) throws FrameworkExceptions {
		try {
			return new WebDriverWait(driver, duration)
					.until(ExpectedConditions.numberOfElementsToBeMoreThan(locator, number));
		} catch (Exception e) {
			throw new FrameworkExceptions(
					String.format("number of elements found by %s failed to be more than \"%s\". ", locator, number),
					e);
		}

	}

	public static boolean WaitForNumberOfWindowsToBe(final int expectedNumberOfWindows, long duration)
			throws FrameworkExceptions {
		try {
			return new WebDriverWait(driver, duration)
					.until(ExpectedConditions.numberOfWindowsToBe(expectedNumberOfWindows));
		} catch (Exception e) {
			throw new FrameworkExceptions("number of open windows failed to be " + expectedNumberOfWindows, e);
		}

	}

	public static boolean WaitForElementSelectionStateToBe(final WebElement element, final boolean selected,
			long duration) throws FrameworkExceptions {
		try {
			return new WebDriverWait(driver, duration)
					.until(ExpectedConditions.elementSelectionStateToBe(element, selected));
		} catch (Exception e) {
			throw new FrameworkExceptions(
					String.format("element (%s) failed to %sbe selected", element, (selected ? "" : "not ")), e);
		}

	}

	public static boolean WaitForElementSelectionStateToBe(final By locator, final boolean selected, long duration)
			throws FrameworkExceptions {
		try {
			return new WebDriverWait(driver, duration)
					.until(ExpectedConditions.elementSelectionStateToBe(locator, selected));
		} catch (Exception e) {
			throw new FrameworkExceptions(
					String.format("element failed to found by %s to %sbe selected", locator, (selected ? "" : "not ")),
					e);
		}

	}

	public static List<WebElement> WaitForNumberOfElementsToBe(final By locator, final Integer number, long duration)
			throws FrameworkExceptions {
		try {
			return new WebDriverWait(driver, duration).until(ExpectedConditions.numberOfElementsToBe(locator, number));
		} catch (Exception e) {
			throw new FrameworkExceptions(
					String.format("number of elements failed to be found by %s to be \"%s\".", locator, number), e);
		}

	}

	public static List<WebElement> waitForNumberOfElementsToBeLessThan(final By locator, final Integer number,
			long duration) throws FrameworkExceptions {
		try {
			return new WebDriverWait(driver, duration).until(ExpectedConditions.numberOfElementsToBe(locator, number));
		} catch (Exception e) {
			throw new FrameworkExceptions(
					String.format("number of elements failed to be found by %s to be \"%s\".", locator, number), e);
		}

	}

	public static WebElement WaitForVisibilityOf(final WebElement element, long duration) throws FrameworkExceptions {
		try {
			return new WebDriverWait(driver, duration).until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			throw new FrameworkExceptions("failed visibility of " + element, e);
		}

	}

	public static WebElement WaitForPresenceOfElementLocated(final By locator, long duration)
			throws FrameworkExceptions {
		try {
			return new WebDriverWait(driver, duration).until(ExpectedConditions.presenceOfElementLocated(locator));
		} catch (Exception e) {
			throw new FrameworkExceptions("presence of element failed to be located by: " + locator, e);
		}

	}

	public static WebElement WaitForVisibilityOfElementLocated(final By locator, long duration)
			throws FrameworkExceptions {
		try {
			return new WebDriverWait(driver, duration).until(ExpectedConditions.visibilityOfElementLocated(locator));
		} catch (Exception e) {
			throw new FrameworkExceptions("visibility of element failed to be located by " + locator, e);
		}

	}

	public static List<WebElement> waitForPresenceOfAllElementsLocatedBy(final By locator, long duration)
			throws FrameworkExceptions {
		try {
			return new WebDriverWait(driver, duration)
					.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
		} catch (Exception e) {
			throw new FrameworkExceptions("presence of any elements failed to be located by " + locator, e);
		}

	}

	public static boolean WaitForTextToBePresentInElement(final WebElement element, final String text, long duration)
			throws FrameworkExceptions {
		try {
			return new WebDriverWait(driver, duration)
					.until(ExpectedConditions.textToBePresentInElement(element, text));
		} catch (Exception e) {
			throw new FrameworkExceptions(
					String.format("text ('%s') failed to be present in element %s", text, element), e);
		}

	}

	public static boolean WaitForTextToBePresentInElementLocated(final By locator, final String text, long duration)
			throws FrameworkExceptions {
		try {
			return new WebDriverWait(driver, duration)
					.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
		} catch (Exception e) {
			throw new FrameworkExceptions(
					String.format("text ('%s') failed to be present in element found by %s", text, locator), e);
		}

	}

	public static boolean WaitForTextToBePresentInElementValue(final WebElement element, final String text,
			long duration) throws FrameworkExceptions {
		try {
			return new WebDriverWait(driver, duration)
					.until(ExpectedConditions.textToBePresentInElementValue(element, text));
		} catch (Exception e) {
			throw new FrameworkExceptions(
					String.format("text ('%s') failed to be the value of element %s", text, element), e);
		}

	}

	public static boolean WaitForTextToBePresentInElementValue(final By locator, final String text, long duration)
			throws FrameworkExceptions {
		try {
			return new WebDriverWait(driver, duration)
					.until(ExpectedConditions.textToBePresentInElementValue(locator, text));
		} catch (Exception e) {
			throw new FrameworkExceptions(
					String.format("text ('%s') failed to be the value of element located by %s", text, locator), e);
		}

	}

	public static List<WebElement> waitForVisibilityOfAllElementsLocatedBy(final By locator, long duration)
			throws FrameworkExceptions {
		try {
			return new WebDriverWait(driver, duration)
					.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		} catch (Exception e) {
			throw new FrameworkExceptions("failed alert to be present", e);
		}

	}

	public static Alert waitForAlertIsPresent(long duration) throws FrameworkExceptions {
		try {
			return new WebDriverWait(driver, duration).until(ExpectedConditions.alertIsPresent());
		} catch (Exception e) {
			throw new FrameworkExceptions("Element: " + element + " not found" + e);
		}

	}

	public static boolean WaitForUrlMatches(final String regex, long duration) throws FrameworkExceptions {
		try {
			return new WebDriverWait(driver, duration).until(ExpectedConditions.urlMatches(regex));
		} catch (Exception e) {
			throw new FrameworkExceptions(String.format("url to match the regex \"%s\".", regex), e);
		}

	}

	public static boolean WaitForUrlContains(final String fraction, long duration) throws FrameworkExceptions {
		try {
			return new WebDriverWait(driver, duration).until(ExpectedConditions.urlContains(fraction));
		} catch (Exception e) {
			throw new FrameworkExceptions(String.format("url failed to contain \"%s\".", fraction), e);
		}

	}

	public static boolean WaitForTitleIs(final String title, long duration) throws FrameworkExceptions {
		try {
			return new WebDriverWait(driver, duration).until(ExpectedConditions.titleIs(title));
		} catch (Exception e) {
			throw new FrameworkExceptions(String.format("title failed to be \"%s\".", title), e);
		}

	}

	public static boolean WaitForUrlToBe(final String url, long duration) throws FrameworkExceptions {
		try {
			return new WebDriverWait(driver, duration).until(ExpectedConditions.urlToBe(url));
		} catch (Exception e) {
			throw new FrameworkExceptions(String.format("url failed to be \"%s\".", url));
		}

	}

	public static boolean WaitForTitleContains(final String title, long duration) throws FrameworkExceptions {
		try {
			return new WebDriverWait(driver, duration).until(ExpectedConditions.titleContains(title));
		} catch (Exception e) {
			throw new FrameworkExceptions(String.format("title failed to contain \"%s\".", title), e);
		}

	}

	public static boolean WaitForTextToBe(final By locator, final String value, long duration)
			throws FrameworkExceptions {
		try {
			return new WebDriverWait(driver, duration).until(ExpectedConditions.textToBe(locator, value));
		} catch (Exception e) {
			throw new FrameworkExceptions(
					String.format("element failed to be found by %s to have text \"%s\".", locator, value), e);
		}

	}

	public static boolean WaitForInvisibilityOf(final WebElement element, long duration) throws FrameworkExceptions {
		try {
			return new WebDriverWait(driver, duration).until(ExpectedConditions.invisibilityOf(element));
		} catch (Exception e) {
			throw new FrameworkExceptions("Failed invisibility of " + element, e);
		}

	}

	public static boolean WaitForAttributeToBe(final WebElement element, final String attribute, final String value,
			long duration) throws FrameworkExceptions {
		try {
			return new WebDriverWait(driver, duration)
					.until(ExpectedConditions.attributeToBe(element, attribute, value));
		} catch (Exception e) {
			throw new FrameworkExceptions(String.format("Failed to " + attribute + " to be \"%s\".", value), e);
		}

	}

	public static boolean WaitForAttributeToBe(final By locator, final String attribute, final String value,
			long duration) throws FrameworkExceptions {
		try {
			return new WebDriverWait(driver, duration)
					.until(ExpectedConditions.attributeToBe(locator, attribute, value));
		} catch (Exception e) {
			throw new FrameworkExceptions(
					String.format("element failed to be found by %s to have value \"%s\".", locator, value), e);
		}

	}

	public static boolean WaitForAttributeContains(final By locator, final String attribute, final String value,
			long duration) throws FrameworkExceptions {
		try {
			return new WebDriverWait(driver, duration)
					.until(ExpectedConditions.attributeContains(locator, attribute, value));
		} catch (Exception e) {
			throw new FrameworkExceptions(
					String.format("value failed to be found by %s to contain \"%s\".", locator, value), e);
		}

	}

	public static boolean WaitForAttributeContains(final WebElement element, final String attribute, final String value,
			long duration) throws FrameworkExceptions {
		try {
			return new WebDriverWait(driver, duration)
					.until(ExpectedConditions.attributeContains(element, attribute, value));
		} catch (Exception e) {
			throw new FrameworkExceptions(String.format("failed value to contain \"%s\". ", value), e);
		}

	}

	public static boolean WaitForTextMatches(final By locator, final Pattern pattern, long duration)
			throws FrameworkExceptions {
		try {
			return new WebDriverWait(driver, duration).until(ExpectedConditions.textMatches(locator, pattern));
		} catch (Exception e) {
			throw new FrameworkExceptions(
					String.format("text found by %s failed to be matched pattern \"%s\".", locator, pattern.pattern()),
					e);
		}

	}

	public static boolean WaitForAttributeToBeNotEmpty(final WebElement element, final String attribute, long duration)
			throws FrameworkExceptions {
		try {
			return new WebDriverWait(driver, duration)
					.until(ExpectedConditions.attributeToBeNotEmpty(element, attribute));
		} catch (Exception e) {
			throw new FrameworkExceptions(String.format("attribute ('%s') empty for element %s", attribute, element),
					e);
		}

	}

	public static WebElement WaitForPresenceOfNestedElementLocatedBy(final WebElement element, final By childLocator,
			long duration) throws FrameworkExceptions {
		try {
			return new WebDriverWait(driver, duration)
					.until(ExpectedConditions.presenceOfNestedElementLocatedBy(element, childLocator));
		} catch (Exception e) {
			throw new FrameworkExceptions(
					String.format("visibility of failed to be element located by %s", childLocator), e);
		}

	}

	public static WebElement WaitForPresenceOfNestedElementLocatedBy(final By locator, final By childLocator,
			long duration) throws FrameworkExceptions {
		try {
			return new WebDriverWait(driver, duration)
					.until(ExpectedConditions.presenceOfNestedElementLocatedBy(locator, childLocator));
		} catch (Exception e) {
			throw new FrameworkExceptions(
					String.format("visibility of element failed to be located by %s -> %s", locator, childLocator), e);
		}

	}

	public static List<WebElement> WaitForPresenceOfNestedElementsLocatedBy(final By locator, final By childLocator,
			long duration) throws FrameworkExceptions {
		try {
			return new WebDriverWait(driver, duration)
					.until(ExpectedConditions.presenceOfNestedElementsLocatedBy(locator, childLocator));
		} catch (Exception e) {
			throw new FrameworkExceptions(
					String.format("visibility of element failed to located by %s -> %s", locator, childLocator), e);
		}

	}

	public static List<WebElement> WaitForVisibilityOfNestedElementsLocatedBy(final By locator, final By childLocator,
			long duration) throws FrameworkExceptions {
		try {
			return new WebDriverWait(driver, duration)
					.until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(locator, childLocator));
		} catch (Exception e) {
			throw new FrameworkExceptions(
					String.format("visibility of elements failed to located by %s -> %s", locator, childLocator), e);
		}

	}

	public static List<WebElement> WaitForVisibilityOfNestedElementsLocatedBy(final WebElement element,
			final By childLocator, long duration) throws FrameworkExceptions {
		try {
			return new WebDriverWait(driver, duration)
					.until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(element, childLocator));
		} catch (Exception e) {
			throw new FrameworkExceptions(
					String.format("visibility of element failed to belocated by %s -> %s\n", element, childLocator), e);
		}

	}

}
