/**
 * 
 */
package testcases;

import static org.seleniumpraveen.com.core.driver.DriverSetup.initializeDriver;

import org.seleniumpraveen.com.core.exception.FrameworkExceptions;
import org.seleniumpraveen.com.core.helper.Locator;
import org.seleniumpraveen.com.core.ui.CoreUi;
import org.testng.annotations.Test;

/**
 * @author praveenkumar.tiwari
 *
 */
@Test
public class WindowPopup {

	public void windowPopup() throws FrameworkExceptions {
		initializeDriver();
		CoreUi coreUi = new CoreUi();
		coreUi.get();
		//coreUi.click(Locator.demoWebsite);
		coreUi.click(Locator.lightBoxCLose);
		coreUi.click(Locator.btnStartPracticing);
		coreUi.click(Locator.windowPopupFormDemp);
		
		coreUi.click(Locator.twitterWindowBtn);
		coreUi.switchOriginWindowAndCloseOther();
		
		coreUi.click(Locator.facebookWindowBtn);
		coreUi.switchOriginWindowAndCloseOther();
		
		coreUi.click(Locator.facebookTwitterWindowBtn);
		coreUi.switchOriginWindowAndCloseOther();
		
		coreUi.click(Locator.followAllWindowBtn);
		coreUi.switchOriginWindowAndCloseOther();
	}
}
