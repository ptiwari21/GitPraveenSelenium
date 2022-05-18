/**
 * 
 */
package org.seleniumpraveen.com.core.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * @author praveenkumar.tiwari
 *
 */
public interface IElementFinder {
	WebElement findElement(String locatorValue);
	WebElement findElement(By by);
	By findBy(String locatorValue);

}
