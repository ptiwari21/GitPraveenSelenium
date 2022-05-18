/**
 * 
 */
package org.seleniumpraveen.com.core.ui;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.seleniumpraveen.com.core.driver.DriverSetup;
import org.seleniumpraveen.com.core.exception.FrameworkExceptions;
import org.seleniumpraveen.com.core.helper.Constants;
import org.seleniumpraveen.com.core.logger.Assertions;
import org.seleniumpraveen.com.core.logger.Logs;
import org.seleniumpraveen.com.core.logger.Report;
import org.seleniumpraveen.com.core.utils.Config;
import org.seleniumpraveen.com.core.utils.ExcelUtils;
import org.seleniumpraveen.com.core.utils.JSExecuter;
import org.seleniumpraveen.com.core.utils.Waits;

/**
 * @author praveenkumar.tiwari
 *
 */
public class CoreUi implements ICoreUi {

	protected static WebDriver driver;
	private Assertions assertions = new Assertions();
	private ElementFinder elementFinder = new ElementFinder();
	private static final int COMMAND_DEFAULT_TIMEOUT_SECONDS = 60;

	public CoreUi() {
		driver = DriverSetup.getDriver();
	}

	public void get(String url) throws FrameworkExceptions {
		driver.get(url);
		driver.manage().timeouts().pageLoadTimeout(COMMAND_DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
		Report.info(String.format("Navigated to url \"%s\" successfully.", url));
	}

	public void get() throws FrameworkExceptions {
		ExcelUtils.setWorkbookExcel(Config.getConfigProperty(Constants.EXCEL_PATH));
		String url = ExcelUtils.getCellValue("TestRunner", "Url");
		driver.get(url);
		driver.manage().timeouts().pageLoadTimeout(COMMAND_DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
		Report.info(String.format("Navigated to url \"%s\" successfully.", url));
	}

	public void quit() throws FrameworkExceptions {
		driver.quit();
		DriverSetup.setDriver(null);
		Report.info("Browser Instance closed successfully");
	}

	public void close() throws FrameworkExceptions {
		String title = driver.getTitle();
		driver.close();
		Report.info("Browser window '" + title + "' closed successfully");
	}

	public boolean isLoaded(WebElement element) throws FrameworkExceptions {
		try {
			boolean flag = false;
			if (Waits.WaitForVisibilityOf(element,
					Integer.parseInt(Config.getConfigProperty(Constants.ELEMENT_WAIT_TIME))) != null) {
				JSExecuter.jsExecutor("arguments[0].scrollIntoView(true);", element);
				flag = true;
			} else {
				assertions.hardAssertFalse(true,
						Constants.ELEMENT_LOG_MESSAGE + element.toString() + Constants.ISLOADED_LOG_MESSAGE_FAILURE);
			}
			return flag;
		} catch (Exception ex) {
			throw new FrameworkExceptions("Element: " + " not loaded.\n" + ex);
		}
	}

	public boolean isLoaded(String locatorValue) throws FrameworkExceptions {
		try {
			boolean flag = false;
			if (Waits.WaitForVisibilityOf(elementFinder.findElement(locatorValue),
					Integer.parseInt(Config.getConfigProperty(Constants.ELEMENT_WAIT_TIME))) != null) {
				JSExecuter.jsExecutor("arguments[0].scrollIntoView(true);", elementFinder.findElement(locatorValue));
				flag = true;
			} else {
				assertions.hardAssertFalse(true,
						Constants.ELEMENT_LOG_MESSAGE + locatorValue + Constants.ISLOADED_LOG_MESSAGE_FAILURE);
			}
			return flag;
		} catch (Exception ex) {
			throw new FrameworkExceptions("Element: " + locatorValue + " not loaded.\n" + ex);
		}
	}

	public void click(WebElement element) throws FrameworkExceptions {
		try {
			if (isLoaded(element)) {
				if (isDisplayed(element)) {
					element.click();
					Report.info("Clicked to " + Constants.ELEMENT_LOG_MESSAGE + "\"" + getElementLocator(element)
							+ "\" successfully.");
				} else {
					throw new FrameworkExceptions(Constants.FORMATTER + Constants.ELEMENT_LOG_MESSAGE
							+ Constants.ISLOADED_LOG_MESSAGE_FAILURE);
				}
			} else {
				throw new FrameworkExceptions(
						Constants.FORMATTER + Constants.ELEMENT_LOG_MESSAGE + Constants.ISLOADED_LOG_MESSAGE_FAILURE);
			}
		} catch (Exception fe) {
			throw new FrameworkExceptions(
					Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE + " click() of Element class.\n" + fe);
		}
	}

	public void click(String locatorValue) throws FrameworkExceptions {
		try {
			if (isLoaded(locatorValue)) {
				if (isDisplayed(locatorValue)) {
					findElement(locatorValue).click();
					Report.info(
							"Clicked to " + Constants.ELEMENT_LOG_MESSAGE + "\"" + locatorValue + "\" successfully.");
				} else {
					throw new FrameworkExceptions(Constants.FORMATTER + Constants.ELEMENT_LOG_MESSAGE
							+ Constants.ISLOADED_LOG_MESSAGE_FAILURE);
				}
			} else {
				throw new FrameworkExceptions(Constants.FORMATTER + Constants.ELEMENT_LOG_MESSAGE + locatorValue
						+ Constants.ISLOADED_LOG_MESSAGE_FAILURE);
			}
		} catch (Exception fe) {
			throw new FrameworkExceptions(
					Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE + " click() of Element class.\n" + fe);
		}
	}

	public String getAttribute(WebElement element, String value) throws FrameworkExceptions {
		String attributeValue = null;
		try {
			if (isLoaded(element)) {
				attributeValue = element.getAttribute(value);
			} else {
				throw new FrameworkExceptions(
						"Element Element: " + getElementLocator(element) + " not loaded in method getAttribute()");
			}
		} catch (Exception fe) {
			throw new FrameworkExceptions(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE
					+ " getAttribute() of Element class.\n" + fe);
		}
		return attributeValue;
	}

	public String getAttribute(String locatorValue, String value) throws FrameworkExceptions {
		String attributeValue = null;
		try {
			if (isLoaded(locatorValue)) {
				attributeValue = findElement(locatorValue).getAttribute(value);
			} else {
				throw new FrameworkExceptions(
						"Element Element: " + locatorValue + " not loaded in method getAttribute()");
			}
		} catch (Exception fe) {
			throw new FrameworkExceptions(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE
					+ " getAttribute() of Element class.\n" + fe);
		}
		return attributeValue;
	}

	public boolean isAttributePresent(WebElement element, String value) {
		boolean isAttributePresent = false;
		try {
			if (isLoaded(element)) {
				element.getAttribute(value);
				isAttributePresent = true;
			}
		} catch (Exception fe) {
			isAttributePresent = false;
		}
		return isAttributePresent;
	}

	public boolean isAttributePresent(String locatorValue, String value) {
		boolean isAttributePresent = false;
		try {
			if (isLoaded(locatorValue)) {
				findElement(locatorValue).getAttribute(value);
				isAttributePresent = true;
			}
		} catch (Exception fe) {
			isAttributePresent = false;
		}
		return isAttributePresent;
	}

	public boolean hasMandatoryMarker(String locatorValue) throws FrameworkExceptions {
		try {
			return (findElement(locatorValue).findElement(By.cssSelector("[mandatory]")).getAttribute("mandatory"))
					.contains("true");
		} catch (Exception e) {
			Logs.error("Failed in hasMandatoryMarker method" + e);
			return false;
		}
	}

	public boolean hasMandatoryMarker(WebElement element) throws FrameworkExceptions {
		try {
			return (element.findElement(By.cssSelector("[mandatory]")).getAttribute("mandatory")).contains("true");
		} catch (Exception e) {
			Logs.error("Failed in hasMandatoryMarker method" + e);
			return false;
		}
	}

	public String getText(WebElement element) throws FrameworkExceptions {
		try {
			if (isLoaded(element)) {
				return element.getText();
			} else {
				throw new FrameworkExceptions("Element Element: " + " not loaded in method getText()");
			}
		} catch (Exception fe) {
			throw new FrameworkExceptions(
					Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE + " getText() of Element class.\n" + fe);
		}
	}

	public String getText(String locatorValue) throws FrameworkExceptions {
		try {
			if (isLoaded(locatorValue)) {
				return findElement(locatorValue).getText();
			} else {
				throw new FrameworkExceptions("Element Element: " + locatorValue + " not loaded in method getText()");
			}
		} catch (Exception fe) {
			throw new FrameworkExceptions(
					Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE + " getText() of Element class.\n" + fe);
		}
	}

	public boolean isDisplayed(WebElement element) throws FrameworkExceptions {
		boolean flag = false;
		try {
			flag = element.isDisplayed();
		} catch (Exception fe) {
			Logs.error(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE + " isDisplayed() of Element class");
			return false;
		}
		return flag;
	}

	public boolean isDisplayed(String locatorValue) throws FrameworkExceptions {
		boolean flag = false;
		try {
			flag = findElement(locatorValue).isDisplayed();
		} catch (Exception fe) {
			Logs.error(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE + " isDisplayed() of Element class");
			return false;
		}
		return flag;
	}

	public boolean isReadOnly(WebElement element, String value) throws FrameworkExceptions {
		try {
			if (getAttribute(element, "readonly") != null) {
				return getAttribute(element, "readonly").equals(value);
			} else if (getAttribute(element, "disabled") != null) {
				return getAttribute(element, "disabled").equals(value);
			} else
				return false;
		} catch (Exception fe) {
			Logs.error(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE + " isReadOnly() of Element class");
			return false;
		}
	}

	public boolean isReadOnly(String locatorValue, String value) throws FrameworkExceptions {
		try {
			if (getAttribute(findElement(locatorValue), "readonly") != null) {
				return getAttribute(findElement(locatorValue), "readonly").equals(value);
			} else if (getAttribute(findElement(locatorValue), "disabled") != null) {
				return getAttribute(findElement(locatorValue), "disabled").equals(value);
			} else
				return false;
		} catch (Exception fe) {
			Logs.error(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE + " isReadOnly() of Element class");
			return false;
		}
	}

	public boolean jsClick(WebElement element) {
		try {
			if (isLoaded(element)) {
				JSExecuter.getJavaScriptExec().executeScript("arguments[0].click();", element);
				Report.info("Clicked on " + Constants.ELEMENT_LOG_MESSAGE + getElementLocator(element)
						+ " using JavaScriptExecuter successfully");
				return true;
			} else {
				Logs.error(
						Constants.FORMATTER + Constants.ELEMENT_LOG_MESSAGE + Constants.ISLOADED_LOG_MESSAGE_FAILURE);
				return false;
			}
		} catch (Exception fe) {
			Logs.error(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE + " getText() of Element class");
			return false;
		}
	}

	public boolean jsClick(String locatorValue) {
		try {
			if (isLoaded(locatorValue)) {
				JSExecuter.getJavaScriptExec().executeScript("arguments[0].click();", findElement(locatorValue));
				Report.info("Clicked on " + Constants.ELEMENT_LOG_MESSAGE + locatorValue
						+ " using JavaScriptExecuter successfully");
				return true;
			} else {
				Logs.error(Constants.FORMATTER + Constants.ELEMENT_LOG_MESSAGE + locatorValue
						+ Constants.ISLOADED_LOG_MESSAGE_FAILURE);
				return false;
			}
		} catch (Exception fe) {
			Logs.error(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE + " getText() of Element class");
			return false;
		}
	}

	public boolean isEditable(WebElement element) throws FrameworkExceptions {
		return element.isEnabled();
	}

	public boolean isEditable(String locatorValue) throws FrameworkExceptions {
		return findElement(locatorValue).isEnabled();
	}

	public String getTagName(WebElement element) throws FrameworkExceptions {
		try {
			if (isLoaded(element)) {
				String strTagName = element.getTagName();
				Report.info("Retrived tag name " + strTagName + " for " + Constants.ELEMENT_LOG_MESSAGE
						+ getElementLocator(element) + " successfully");
				return strTagName;
			} else {
				Logs.error(
						Constants.FORMATTER + Constants.ELEMENT_LOG_MESSAGE + Constants.ISLOADED_LOG_MESSAGE_FAILURE);
				return "";
			}
		} catch (Exception fe) {
			Logs.error(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE + " getText() of Element class");
			return "";
		}
	}

	public String getTagName(String locatorValue) throws FrameworkExceptions {
		try {
			if (isLoaded(locatorValue)) {
				String strTagName = findElement(locatorValue).getTagName();
				Report.info("Retrived tag name " + strTagName + " for " + Constants.ELEMENT_LOG_MESSAGE + locatorValue
						+ " successfully");
				return strTagName;
			} else {
				Logs.error(Constants.FORMATTER + Constants.ELEMENT_LOG_MESSAGE + locatorValue
						+ Constants.ISLOADED_LOG_MESSAGE_FAILURE);
				return "";
			}
		} catch (Exception fe) {
			Logs.error(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE + " getText() of Element class");
			return "";
		}
	}

	// Alert
	public void clickAlert() throws FrameworkExceptions {
		try {
			new WebDriverWait(driver, Integer.parseInt(Config.getConfigProperty(Constants.ELEMENT_WAIT_TIME)))
					.ignoring(NoAlertPresentException.class).until(ExpectedConditions.alertIsPresent());
			org.openqa.selenium.Alert alert = driver.switchTo().alert();
			alert.accept();
			Report.info("Accepted alert successfully");

		} catch (NoAlertPresentException nape) {
			throw new FrameworkExceptions(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE
					+ "clickAlert() of Alert class, alert not present." + nape);
		} catch (Exception fe) {
			throw new FrameworkExceptions(
					Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE + "clickAlert() of Alert class.\n" + fe);
		}

	}

	public String getAlertText() throws FrameworkExceptions {
		String strAlertText = "";
		try {
			if (isAlertPresent()) {
				strAlertText = driver.switchTo().alert().getText();
				Report.info("Retrived text from alert successfully");
			}
		} catch (Exception fe) {
			throw new FrameworkExceptions(
					Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE + "getAlertText() of Alert class.\n" + fe);
		}
		return strAlertText;
	}

	public void acceptAlert() throws FrameworkExceptions {
		try {
			if (isAlertPresent()) {
				driver.switchTo().alert().accept();
				Report.info("Clicked on the alert Pop-up");
			}
		} catch (NoAlertPresentException nape) {
			Report.info("Alert does not exist");
		} catch (Exception fe) {
			throw new FrameworkExceptions(
					Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE + "acceptAlert() of Alert class.\n" + fe);
		}

	}

	public void sendKeysAlert(String setValue) throws FrameworkExceptions {
		try {
			if (isAlertPresent()) {
				driver.switchTo().alert().sendKeys(setValue);
				;
				Report.info("set value '" + setValue + "' on the alert Pop-up");
			}
		} catch (NoAlertPresentException nape) {
			Report.info("Alert does not exist");
		} catch (Exception fe) {
			throw new FrameworkExceptions(
					Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE + "sendKeysAlert() of Alert class.\n" + fe);
		}

	}

	public boolean isAlertPresent() throws FrameworkExceptions {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException nape) {
			Report.info("Alert does not exist");
			return false;
		} catch (Exception fe) {
			Logs.error(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE + "isAlertPresent() of Alert class.");
			return false;
		}
	}

	public void waitForAlert() throws FrameworkExceptions {
		try {
			new WebDriverWait(driver, Integer.parseInt(Config.getConfigProperty(Constants.ELEMENT_WAIT_TIME)))
					.ignoring(NoAlertPresentException.class).until(ExpectedConditions.alertIsPresent());

		} catch (Exception fe) {
			throw new FrameworkExceptions(
					Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE + "waitForAlert() of Alert class.\n" + fe);
		}

	}

	// dismissAlert Method for alert dialog
	public void dismissAlert() throws FrameworkExceptions {
		try {
			if (isAlertPresent()) {
				driver.switchTo().alert().dismiss();
			}
		} catch (Exception fe) {
			throw new FrameworkExceptions(
					Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE + "dismissAlert() of Alert class.\n" + fe);
		}
	}

	// CheckBox
	public void check(WebElement element) throws FrameworkExceptions {
		try {
			if (isLoaded(element)) {
				if (!isChecked(element)) {
					click(element);
					Report.info("Checkbox element: " + " checked");

				} else {
					Report.info("Checkbox element: " + " already checked");
				}
			} else {
				throw new FrameworkExceptions("Checkbox Element: " + " not loaded");
			}
		} catch (Exception fe) {
			throw new FrameworkExceptions(
					Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE + " check() of Checkbox class.\n" + fe);
		}

	}

	public void check(String locatorValue) throws FrameworkExceptions {
		try {
			if (isLoaded(locatorValue)) {
				if (!isChecked(locatorValue)) {
					click(locatorValue);
					Report.info("Checkbox element: " + locatorValue + " checked");

				} else {
					Report.info("Checkbox element: " + locatorValue + " already checked");
				}
			} else {
				throw new FrameworkExceptions("Checkbox Element: " + locatorValue + " not loaded");
			}
		} catch (Exception fe) {
			throw new FrameworkExceptions(
					Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE + " check() of Checkbox class.\n" + fe);
		}

	}

	public void unCheck(WebElement element) throws FrameworkExceptions {
		try {
			if (isLoaded(element)) {
				if (isChecked(element)) {
					click(element);
					Report.info("Checkbox element: " + " un-checked");

				} else {
					Report.info("Checkbox element: " + " already un-checked");
				}
			} else {
				throw new FrameworkExceptions("Checkbox Element: " + " not loaded");
			}
		} catch (Exception fe) {
			throw new FrameworkExceptions(
					Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE + " check() of Checkbox class.\n" + fe);
		}

	}

	public void unCheck(String locatorValue) throws FrameworkExceptions {
		try {
			if (isLoaded(locatorValue)) {
				if (isChecked(locatorValue)) {
					click(locatorValue);
					Report.info("Checkbox element: " + locatorValue + " un-checked");

				} else {
					Report.info("Checkbox element: " + locatorValue + " already un-checked");
				}
			} else {
				throw new FrameworkExceptions("Checkbox Element: " + locatorValue + " not loaded");
			}
		} catch (Exception fe) {
			throw new FrameworkExceptions(
					Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE + " check() of Checkbox class.\n" + fe);
		}

	}

	public boolean isChecked(WebElement element) throws FrameworkExceptions {
		try {
			if (isLoaded(element)) {
				return element.isSelected();
			} else {
				Logs.error(Constants.FORMATTER + " Checkbox element: " + " checking failed");
				return false;
			}
		} catch (Exception fe) {
			Logs.error(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE + " isChecked() of Checkbox class" + fe);
			return false;
		}
	}

	public boolean isChecked(String locatorValue) throws FrameworkExceptions {
		try {
			if (isLoaded(locatorValue)) {
				return findElement(locatorValue).isSelected();
			} else {
				Logs.error(Constants.FORMATTER + " Checkbox element: " + locatorValue + " checking failed");
				return false;
			}
		} catch (Exception fe) {
			Logs.error(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE + " isChecked() of Checkbox class" + fe);
			return false;
		}
	}

	public void checkBoxBooleanSelection(String locatorValue, boolean toBeSelect) throws FrameworkExceptions {
		if (toBeSelect) {
			check(locatorValue);
		} else {
			unCheck(locatorValue);
		}
	}

	public void checkBoxBooleanSelection(WebElement element, boolean toBeSelect) throws FrameworkExceptions {
		if (toBeSelect) {
			check(element);
		} else {
			unCheck(element);
		}
	}

	// DropDown
	public void selectByValue(WebElement element, String valueToBeSelected) throws FrameworkExceptions {
		try {
			String strExceptionText = "DropDown Element: ";
			Select select = null;
			if (valueToBeSelected == null)
				return;
			if (isLoaded(element)) {
				select = new Select(element);
				select.selectByValue(valueToBeSelected);
				Report.info(strExceptionText + " => " + valueToBeSelected + Constants.SELECT_LOG_MESSAGE);
			} else {
				throw new FrameworkExceptions(strExceptionText + " not loaded in method selectByValue()");
			}
		} catch (Exception fe) {
			throw new FrameworkExceptions(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE
					+ " selectByValue() of DropDown class.\n" + fe);
		}
	}

	public void selectByValue(String locatorValue, String valueToBeSelected) throws FrameworkExceptions {
		try {
			String strExceptionText = "DropDown Element: ";
			Select select = null;
			if (valueToBeSelected == null)
				return;
			if (isLoaded(locatorValue)) {
				select = new Select(findElement(locatorValue));
				select.selectByValue(valueToBeSelected);
				Report.info(
						strExceptionText + locatorValue + " => " + valueToBeSelected + Constants.SELECT_LOG_MESSAGE);
			} else {
				throw new FrameworkExceptions(
						strExceptionText + locatorValue + " not loaded in method selectByValue()");
			}
		} catch (Exception fe) {
			throw new FrameworkExceptions(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE
					+ " selectByValue() of DropDown class.\n" + fe);
		}
	}

	public void selectByVisibleText(WebElement element, String valueToBeSelected) throws FrameworkExceptions {
		try {
			String strExceptionText = "DropDown Element: ";
			Select select = null;
			if (valueToBeSelected == null)
				return;
			if (isLoaded(element)) {
				select = new Select(element);
				select.selectByVisibleText(valueToBeSelected);
				Report.info(strExceptionText + " => " + valueToBeSelected + Constants.SELECT_LOG_MESSAGE);
			} else {
				throw new FrameworkExceptions(strExceptionText + " not loaded in method selectByVisibleText()");
			}
		} catch (Exception fe) {
			throw new FrameworkExceptions(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE
					+ " selectByVisibleText() of DropDown class.\n" + fe);
		}

	}

	public void selectByVisibleText(String locatorValue, String valueToBeSelected) throws FrameworkExceptions {
		try {
			String strExceptionText = "DropDown Element: ";
			Select select = null;
			if (valueToBeSelected == null)
				return;
			if (isLoaded(locatorValue)) {
				select = new Select(findElement(locatorValue));
				select.selectByVisibleText(valueToBeSelected);
				Report.info(
						strExceptionText + locatorValue + " => " + valueToBeSelected + Constants.SELECT_LOG_MESSAGE);
			} else {
				throw new FrameworkExceptions(
						strExceptionText + locatorValue + " not loaded in method selectByVisibleText()");
			}
		} catch (Exception fe) {
			throw new FrameworkExceptions(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE
					+ " selectByVisibleText() of DropDown class.\n" + fe);
		}

	}

	public void selectByIndex(WebElement element, int valueToBeSelected) throws FrameworkExceptions {
		try {
			String strExceptionText = "DropDown Element: ";
			Select select = null;
			if (isLoaded(element)) {
				select = new Select(element);
				select.selectByIndex(valueToBeSelected);
				Report.info(strExceptionText + " => " + valueToBeSelected + Constants.SELECT_LOG_MESSAGE);
			} else {
				throw new FrameworkExceptions(strExceptionText + " not loaded in method selectByIndex()");
			}
		} catch (Exception fe) {
			throw new FrameworkExceptions(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE
					+ " selectByIndex() of DropDown class.\n" + fe);
		}

	}

	public void selectByIndex(String locatorValue, int valueToBeSelected) throws FrameworkExceptions {
		try {
			String strExceptionText = "DropDown Element: ";
			Select select = null;
			if (isLoaded(locatorValue)) {
				select = new Select(findElement(locatorValue));
				select.selectByIndex(valueToBeSelected);
				Report.info(
						strExceptionText + locatorValue + " => " + valueToBeSelected + Constants.SELECT_LOG_MESSAGE);
			} else {
				throw new FrameworkExceptions(
						strExceptionText + locatorValue + " not loaded in method selectByIndex()");
			}
		} catch (Exception fe) {
			throw new FrameworkExceptions(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE
					+ " selectByIndex() of DropDown class.\n" + fe);
		}

	}

	public void selectByIgnoringSpecialCharacters(WebElement element, String text) throws FrameworkExceptions {
		if (text == null)
			return;
		try {
			String strExceptionText = "DropDown Element: ";
			Select select = null;
			if (isLoaded(element)) {
				select = new Select(element);
				List<WebElement> optionElements = select.getOptions();

				for (WebElement optionElement : optionElements) {
					String option = optionElement.getText().toLowerCase().replaceAll("[^a-zA-Z0-9]", "");
					String partValue = text.toLowerCase().replaceAll("[^a-zA-Z0-9]", "");
					if (option.contains(partValue)) {
						String optionIndex = optionElement.getAttribute("index");
						selectByIndex(element, Integer.parseInt(optionIndex));
						break;
					}
				}
				Report.info(strExceptionText + " => " + text + Constants.SELECT_LOG_MESSAGE);
			} else {
				throw new FrameworkExceptions(
						strExceptionText + " not loaded in method selectByIgnoringSpecialCharacters()");
			}
		} catch (Exception fe) {
			throw new FrameworkExceptions(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE
					+ " selectByIgnoringSpecialCharacters() of DropDown class.\n" + fe);
		}

	}

	public void selectByIgnoringSpecialCharacters(String locatorValue, String text) throws FrameworkExceptions {
		if (text == null)
			return;
		try {
			String strExceptionText = "DropDown Element: ";
			Select select = null;
			if (isLoaded(locatorValue)) {
				select = new Select(findElement(locatorValue));
				List<WebElement> optionElements = select.getOptions();

				for (WebElement optionElement : optionElements) {
					String option = optionElement.getText().toLowerCase().replaceAll("[^a-zA-Z0-9]", "");
					String partValue = text.toLowerCase().replaceAll("[^a-zA-Z0-9]", "");
					if (option.contains(partValue)) {
						String optionIndex = optionElement.getAttribute("index");
						selectByIndex(locatorValue, Integer.parseInt(optionIndex));
						break;
					}
				}
				Report.info(strExceptionText + locatorValue + " => " + text + Constants.SELECT_LOG_MESSAGE);
			} else {
				throw new FrameworkExceptions(
						strExceptionText + locatorValue + " not loaded in method selectByIgnoringSpecialCharacters()");
			}
		} catch (Exception fe) {
			throw new FrameworkExceptions(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE
					+ " selectByIgnoringSpecialCharacters() of DropDown class.\n" + fe);
		}

	}

	public void listBoxSelectValue(String locatorValue, String listlocatorValue, String valueToBeSelected)
			throws FrameworkExceptions {
		try {
			if (valueToBeSelected == null)
				return;
			click(locatorValue);
			int i = 1;
			for (WebElement element : findElements(listlocatorValue)) {
				if (getText(element).equals(valueToBeSelected)) {
					click(element);
					break;
				} else if (i == findElements(listlocatorValue).size()) {
					throw new FrameworkExceptions(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE
							+ " listBoxSelectByValue() of DropDown class value " + valueToBeSelected
							+ " not present in Element " + listlocatorValue + " .");
				}
				i++;
			}
		} catch (Exception fe) {
			throw new FrameworkExceptions(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE
					+ " listBoxSelectByValue() of DropDown class.\n" + fe);
		}

	}

	public void listBoxSelectMultipleValue(String locatorValue, String listlocatorValue, String[] listValueToBeSelected)
			throws FrameworkExceptions {
		try {
			if (listValueToBeSelected == null)
				return;
			for (String valueToBeSelected : listValueToBeSelected) {
				int i = 1;
				click(locatorValue);
				for (WebElement element : findElements(listlocatorValue)) {
					if (getText(element).equals(valueToBeSelected)) {
						click(element);
						break;
					} else if (i == findElements(listlocatorValue).size()) {
						throw new FrameworkExceptions(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE
								+ " listBoxSelectByValue() of DropDown class value " + valueToBeSelected
								+ " not present in Element " + listlocatorValue + " .");
					}
					i++;
				}
			}
		} catch (Exception fe) {
			throw new FrameworkExceptions(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE
					+ " listBoxSelectByValue() of DropDown class.\n" + fe);
		}

	}

	public void setText(WebElement element, String text) throws FrameworkExceptions {
		if (text == null)
			return;
		try {
			if (isLoaded(element)) {
				element.sendKeys(text);
				Report.info("Value \'" + text + "\' set in " + Constants.TEXTFIELD_LOG_MESSAGE + "\""
						+ getElementLocator(element) + "\" successfully.");
			} else {
				throw new FrameworkExceptions(Constants.TEXTFIELD_LOG_MESSAGE + " not loaded in method setText()");
			}
		} catch (Exception fe) {
			throw new FrameworkExceptions(
					Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE + " setText() of TextField class.\n" + fe);
		}

	}

	public void setText(String locatorValue, String text) throws FrameworkExceptions {
		if (text == null)
			return;
		try {
			if (isLoaded(locatorValue)) {
				findElement(locatorValue).sendKeys(text);
				Report.info("Value \'" + text + "\' set in " + Constants.TEXTFIELD_LOG_MESSAGE + "\"" + locatorValue
						+ "\" successfully.");
			} else {
				throw new FrameworkExceptions(
						Constants.TEXTFIELD_LOG_MESSAGE + locatorValue + " not loaded in method setText()");
			}
		} catch (Exception fe) {
			throw new FrameworkExceptions(
					Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE + " setText() of TextField class.\n" + fe);
		}

	}

	public void clearText(WebElement element) throws FrameworkExceptions {
		try {
			if (isLoaded(element)) {
				element.clear();
				Report.info(Constants.TEXTFIELD_CLEAR_TEXT_MESSAGE);
			} else {
				throw new FrameworkExceptions(Constants.TEXTFIELD_LOG_MESSAGE + " not loaded in method clearText()");
			}
		} catch (Exception fe) {
			throw new FrameworkExceptions(
					Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE + " clearText() of TextField class.\n" + fe);
		}
	}

	public void clearText(String locatorValue) throws FrameworkExceptions {
		try {
			if (isLoaded(locatorValue)) {
				findElement(locatorValue).clear();
				Report.info(Constants.TEXTFIELD_CLEAR_TEXT_MESSAGE);
			} else {
				throw new FrameworkExceptions(
						Constants.TEXTFIELD_LOG_MESSAGE + locatorValue + " not loaded in method clearText()");
			}
		} catch (Exception fe) {
			throw new FrameworkExceptions(
					Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE + " clearText() of TextField class.\n" + fe);
		}
	}

	public void setTextWithSendKey(WebElement element, String text, Keys key) throws FrameworkExceptions {
		if (text == null)
			return;
		try {
			if (isLoaded(element)) {
				element.sendKeys(text);
				Report.info("Value \'" + text + "\' set in " + Constants.TEXTFIELD_LOG_MESSAGE + "\""
						+ getElementLocator(element) + "\" successfully.");
				sendKey(element, key);
			} else {
				throw new FrameworkExceptions(
						Constants.TEXTFIELD_LOG_MESSAGE + " not loaded in method setTextWithSendKey()");
			}
		} catch (Exception fe) {
			throw new FrameworkExceptions(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE
					+ " setTextWithSendKey() of TextField class.\n" + fe);
		}

	}

	public void setTextWithSendKey(String locatorValue, String text, Keys key) throws FrameworkExceptions {
		if (text == null)
			return;
		try {
			if (isLoaded(locatorValue)) {
				findElement(locatorValue).sendKeys(text);
				Report.info("Value \'" + text + "\' set in " + Constants.TEXTFIELD_LOG_MESSAGE + "\"" + locatorValue
						+ "\" successfully.");
				sendKey(locatorValue, key);
			} else {
				throw new FrameworkExceptions(
						Constants.TEXTFIELD_LOG_MESSAGE + locatorValue + " not loaded in method setTextWithSendKey()");
			}
		} catch (Exception fe) {
			throw new FrameworkExceptions(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE
					+ " setTextWithSendKey() of TextField class.n" + fe);
		}

	}

	public void sendKey(WebElement element, Keys key) throws FrameworkExceptions {
		try {
			if (isLoaded(element)) {
				element.sendKeys(key);
				Report.info("Key \'" + key.name() + "\' set in " + Constants.TEXTFIELD_LOG_MESSAGE + "\""
						+ getElementLocator(element) + "\" successfully.");
			} else {
				throw new FrameworkExceptions(
						Constants.TEXTFIELD_LOG_MESSAGE + " not loaded in method setTextWithSendKey()");
			}
		} catch (Exception fe) {
			throw new FrameworkExceptions(
					Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE + " sendKey() of TextField class.\n" + fe);
		}

	}

	public void sendKey(String locatorValue, Keys key) throws FrameworkExceptions {
		try {
			if (isLoaded(locatorValue)) {
				findElement(locatorValue).sendKeys(key);
				Report.info("Key \'" + key.name() + "\' set in " + Constants.TEXTFIELD_LOG_MESSAGE + "\"" + locatorValue
						+ "\" successfully.");
			} else {
				throw new FrameworkExceptions(
						Constants.TEXTFIELD_LOG_MESSAGE + locatorValue + " not loaded in method setTextWithSendKey()");
			}
		} catch (Exception fe) {
			throw new FrameworkExceptions(
					Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE + " sendKey() of TextField class.\n" + fe);
		}

	}

	public void switchToWindowAfterNewWindowFocused() throws FrameworkExceptions {
		try {
			String winHandleBefore = getWindowHandle();
			for (String winHandle : driver.getWindowHandles()) {
				if (!winHandle.equals(winHandleBefore))
					switchToWindow(winHandle);
			}
		} catch (Exception fe) {
			throw new FrameworkExceptions(
					Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE + " sendKey() of TextField class.\n" + fe);
		}

	}

	public void switchToWindow(String windowName) throws FrameworkExceptions {
		try {
			driver.switchTo().window(windowName);
		} catch (Exception fe) {
			throw new FrameworkExceptions(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE
					+ " switchToWindow() of TextField class.\n" + fe);
		}

	}

	public String getWindowHandle() throws FrameworkExceptions {
		try {
			return driver.getWindowHandle();
		} catch (Exception fe) {
			throw new FrameworkExceptions(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE
					+ " getWindowHandle() of TextField class.\n" + fe);
		}
	}

	public void switchToDefault() throws FrameworkExceptions {
		try {
			driver.switchTo().defaultContent();
		} catch (Exception fe) {
			throw new FrameworkExceptions(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE
					+ " switchToDefault() of TextField class.\n" + fe);
		}

	}

	public String getWindowURL() throws FrameworkExceptions {
		try {
			return driver.getCurrentUrl();
		} catch (Exception fe) {
			throw new FrameworkExceptions(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE
					+ " getWindowURL() of TextField class.\n" + fe);
		}
	}

	public void switchToAvailableWindow(long waitTimeBeforeSwitch) throws FrameworkExceptions {
		try {
			Waits.staticWait(waitTimeBeforeSwitch);
			for (String winHandle : driver.getWindowHandles()) {
				switchToWindow(winHandle);
				break;
			}
		} catch (Exception fe) {
			throw new FrameworkExceptions(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE
					+ " switchToAvailableWindow() of TextField class.\n" + fe);
		}

	}

	public void switchOriginWindowAndCloseOther() throws FrameworkExceptions {
		try {
			String winHandleBefore = getWindowHandle();
			for (String winHandle : driver.getWindowHandles()) {
				if (!winHandle.equals(winHandleBefore)) {
					switchToWindow(winHandle);
					close();
				}
			}
			switchToWindow(winHandleBefore);
		} catch (Exception fe) {
			throw new FrameworkExceptions(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE
					+ " closeOtherWindow() of window class.\n" + fe);
		}

	}

	public static String getElementLocator(WebElement element) {
		return element.toString().split("->")[1].substring(0, element.toString().split("->")[1].length() - 1);
	}

	public WebElement findElement(WebElement element) throws FrameworkExceptions {
		try {
			if (isLoaded(element)) {
				return element;
			} else {
				throw new FrameworkExceptions(Constants.TEXTFIELD_LOG_MESSAGE + " not loaded in method findElement()");
			}
		} catch (Exception fe) {
			throw new FrameworkExceptions(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE + " findElement() "
					+ getElementLocator(element) + "\n" + fe);

		}
	}

	public WebElement findElement(String locatorValue) throws FrameworkExceptions {
		try {
			if (isLoaded(locatorValue)) {
				return elementFinder.findElement(locatorValue);
			} else {
				throw new FrameworkExceptions(Constants.TEXTFIELD_LOG_MESSAGE + " not loaded in method findElement()");
			}
		} catch (Exception fe) {
			throw new FrameworkExceptions(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE + " findElement() "
					+ locatorValue + "\n" + fe);
		}
	}

	public List<WebElement> findElements(String locatorValue) throws FrameworkExceptions {
		try {
			if (isLoaded(locatorValue)) {
				return elementFinder.findElements(locatorValue);
			} else {
				throw new FrameworkExceptions(Constants.TEXTFIELD_LOG_MESSAGE + " not loaded in method findElement()");
			}
		} catch (Exception fe) {
			throw new FrameworkExceptions(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE + " findElement() "
					+ locatorValue + "\n" + fe);

		}
	}

	public boolean isElementPresent(String locatorValue) {
		try {
			elementFinder.findElement(locatorValue);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isElementDisplayed(String locatorValue) {
		try {
			return elementFinder.findElement(locatorValue).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
}
