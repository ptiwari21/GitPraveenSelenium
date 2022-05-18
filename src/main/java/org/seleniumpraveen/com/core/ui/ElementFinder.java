/**
 * 
 */
package org.seleniumpraveen.com.core.ui;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.seleniumpraveen.com.core.driver.DriverSetup;

/**
 * @author praveenkumar.tiwari
 *
 */
public class ElementFinder implements IElementFinder {

	private WebDriver driver;
	
	public ElementFinder() {
		driver = DriverSetup.getDriver();
	}
	
	public WebElement findElement(String locatorValue) {
		return driver.findElement(findBy(locatorValue));
	}

	public WebElement findElement(By by) {
		return driver.findElement(by);
	}
	
	public List<WebElement> findElements(String locatorValue) {
		return driver.findElements(findBy(locatorValue));
	}

	public List<WebElement> findElements(By by) {
		return driver.findElements(by);
	}

	public By findBy(String locatorValue) {
		if(locatorValue.startsWith("id:")) {
			return By.id(locatorValue.replace("id:", ""));
		} else if(locatorValue.toLowerCase().startsWith("name:")) {
			return By.name(locatorValue.replace("name:", ""));
		} if(locatorValue.toLowerCase().startsWith("linktext:")) {
			return By.linkText(locatorValue.replace("linktext:", ""));
		} if(locatorValue.toLowerCase().startsWith("partiallinktext:")) {
			return By.partialLinkText(locatorValue.replace("partiallinktext:", ""));
		} if(locatorValue.toLowerCase().startsWith("cssselector:")) {
			return By.cssSelector(locatorValue.replace("cssselector:", ""));
		} if(locatorValue.toLowerCase().startsWith("tagname:")) {
			return By.tagName(locatorValue.replace("tagname:", ""));
		} if(locatorValue.toLowerCase().startsWith("classname:")) {
			return By.className(locatorValue.replace("classname:", ""));
		} if(locatorValue.toLowerCase().startsWith("xpath:")) {
			return By.xpath(locatorValue.replace("xpath:", ""));
		} else {
			return By.xpath(locatorValue);
		}
		
	}

}
