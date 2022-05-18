/**
 * 
 */
package org.seleniumpraveen.com.core.ui;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.seleniumpraveen.com.core.exception.FrameworkExceptions;

/**
 * @author praveenkumar.tiwari
 *
 */
public interface ICoreUi {
	   	boolean isLoaded(WebElement element) throws FrameworkExceptions;
	    boolean isLoaded(String locatorValue) throws FrameworkExceptions;
	    
	    String getAttribute(WebElement element, String value) throws FrameworkExceptions;
	    String getAttribute(String locatorValue, String value) throws FrameworkExceptions;
	    
	    void click(WebElement element) throws FrameworkExceptions;
	    void click(String locatorValue) throws FrameworkExceptions;
	    
	    boolean isDisplayed(WebElement element) throws FrameworkExceptions;
	    boolean isDisplayed(String locatorValue) throws FrameworkExceptions;
	    
	    boolean isReadOnly(WebElement element, String value) throws FrameworkExceptions;
	    boolean isReadOnly(String locatorValue, String value) throws FrameworkExceptions;
	    
	    boolean jsClick(WebElement element) throws FrameworkExceptions;
	    boolean jsClick(String locatorValue) throws FrameworkExceptions;
	    
	    boolean isEditable(WebElement element) throws FrameworkExceptions;
	    boolean isEditable(String locatorValue) throws FrameworkExceptions;
	    
	    String getTagName(WebElement element) throws FrameworkExceptions;
	    String getTagName(String locatorValue) throws FrameworkExceptions;
	    
	    //Alert
	    void clickAlert() throws FrameworkExceptions;
	    String getAlertText() throws FrameworkExceptions;
	    void acceptAlert() throws FrameworkExceptions;
	    boolean isAlertPresent() throws FrameworkExceptions;
	    void waitForAlert() throws FrameworkExceptions;
	    
	    //CheckBox   
	    void check(WebElement element) throws FrameworkExceptions;
	    void check(String locatorValue) throws FrameworkExceptions;
	    boolean isChecked(WebElement element) throws FrameworkExceptions;
	    boolean isChecked(String locatorValue) throws FrameworkExceptions;
	    
	    //DropDown
	    void selectByValue(WebElement element, String text) throws FrameworkExceptions;
	    void selectByValue(String locatorValue, String text) throws FrameworkExceptions;
	    void selectByVisibleText(WebElement element, String text) throws FrameworkExceptions;
	    void selectByVisibleText(String locatorValue, String text) throws FrameworkExceptions;
	    void selectByIndex(WebElement element, int index) throws FrameworkExceptions;
	    void selectByIndex(String locatorValue, int index) throws FrameworkExceptions;
	    void selectByIgnoringSpecialCharacters(WebElement element, String text) throws FrameworkExceptions;
	    void selectByIgnoringSpecialCharacters(String locatorValue, String text) throws FrameworkExceptions;
	    
	    //TextField
	    void setText(WebElement element,String text) throws FrameworkExceptions;
	    void setText(String locatorValue,String text) throws FrameworkExceptions;
	    void clearText(WebElement element) throws FrameworkExceptions;
	    void clearText(String locatorValue) throws FrameworkExceptions;
	    void setTextWithSendKey(WebElement element,String text, Keys key) throws FrameworkExceptions;
	    void setTextWithSendKey(String locatorValue,String text, Keys key) throws FrameworkExceptions;
	    void sendKey(WebElement element,Keys key) throws FrameworkExceptions;
	    void sendKey(String locatorValue,Keys key) throws FrameworkExceptions;
	    
	    //Label
	    boolean hasMandatoryMarker(WebElement element) throws FrameworkExceptions;
	    boolean hasMandatoryMarker(String locatorValue) throws FrameworkExceptions;
	    
	    //Window
	    void switchToWindowAfterNewWindowFocused() throws FrameworkExceptions;
	    void switchToWindow(String windowName) throws  FrameworkExceptions;
	    String getWindowHandle() throws FrameworkExceptions;
	    void switchToDefault() throws FrameworkExceptions;
	    String getWindowURL() throws FrameworkExceptions;
	    void switchToAvailableWindow(long waitTimeBeforeSwitch) throws FrameworkExceptions;
	    
	    
	    
	    
}
