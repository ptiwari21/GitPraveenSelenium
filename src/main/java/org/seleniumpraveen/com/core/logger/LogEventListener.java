/**
 * 
 */
package org.seleniumpraveen.com.core.logger;

import org.openqa.selenium.*;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.testng.TestListenerAdapter;
/**
 * @author praveenkumar.tiwari
 *
 */



public class LogEventListener extends TestListenerAdapter implements WebDriverEventListener {

    private By lastFindBy;

    
    public void beforeNavigateTo(String url, WebDriver webDriver) {
        Logs.info("WebDriver navigating to:'"+url+"'");
    }

    
    public void beforeFindBy(By by, WebElement webElement, WebDriver webDriver) {
        lastFindBy = by;
    }

    
    public void afterClickOn(WebElement element, WebDriver webDriver) {
        
    }

    
    public void beforeChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {
        // TODO Auto-generated method stub
    }

    
    public void afterChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {
        // TODO Auto-generated method stub
    }

    
    public void onException(Throwable error, WebDriver webDriver) {
        if (error.getClass().equals(NoSuchElementException.class)){
            Logs.error("WebDriver error: Element not found "+lastFindBy);
        } else if(error.getClass().equals(StaleElementReferenceException.class)){
            Logs.error("Stale element exception:");
        } else if(error.getClass().equals(UnhandledAlertException.class)) {
            Logs.error("Alert exception: ");
        } else {
            Logs.error("WebDriver error:" + error);
        }
    }

    
    public void afterFindBy(By arg0, WebElement arg1, WebDriver arg2) {
        // TODO Auto-generated method stub
    }

    
    public void afterNavigateBack(WebDriver arg0) {
        // TODO Auto-generated method stub
    }

    
    public void afterNavigateForward(WebDriver arg0) {
        // TODO Auto-generated method stub
    }

    
    public void beforeNavigateRefresh(WebDriver webDriver) {
        // TODO Auto-generated method stub
    }

    
    public void afterNavigateRefresh(WebDriver webDriver) {
        // TODO Auto-generated method stub
    }

    
    public void afterNavigateTo(String arg0, WebDriver arg1) {
        // TODO Auto-generated method stub
    }

    
    public void afterScript(String arg0, WebDriver arg1) {
        // TODO Auto-generated method stub
    }

    
    public void beforeClickOn(WebElement arg0, WebDriver arg1) {
        // TODO Auto-generated method stub

    }

    
    public void beforeNavigateBack(WebDriver arg0) {
        // TODO Auto-generated method stub
    }

    
    public void beforeNavigateForward(WebDriver arg0) {
        // TODO Auto-generated method stub
    }

    
    public void beforeScript(String arg0, WebDriver arg1) {
        // TODO Auto-generated method stub
    }

	public void beforeAlertAccept(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void afterAlertAccept(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void afterAlertDismiss(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void beforeAlertDismiss(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void beforeSwitchToWindow(String windowName, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void afterSwitchToWindow(String windowName, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public <X> void beforeGetScreenshotAs(OutputType<X> target) {
		// TODO Auto-generated method stub
		
	}

	public <X> void afterGetScreenshotAs(OutputType<X> target, X screenshot) {
		// TODO Auto-generated method stub
		
	}

	public void beforeGetText(WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void afterGetText(WebElement element, WebDriver driver, String text) {
		// TODO Auto-generated method stub
		
	}
}

