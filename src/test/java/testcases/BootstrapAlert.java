/**
 * 
 */
package testcases;

import static org.seleniumpraveen.com.core.driver.DriverSetup.initializeDriver;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.seleniumpraveen.com.core.exception.FrameworkExceptions;
import org.seleniumpraveen.com.core.helper.Locator;
import org.seleniumpraveen.com.core.logger.Assertions;
import org.seleniumpraveen.com.core.ui.CoreUi;
import org.seleniumpraveen.com.core.utils.Waits;
import org.testng.annotations.Test;

/**
 * @author praveenkumar.tiwari
 *
 */
public class BootstrapAlert {

	@Test
	public void bootstrapAlert() throws FrameworkExceptions {
		initializeDriver();
		CoreUi coreUi = new CoreUi();
		Assertions assertions = new Assertions();
		coreUi.get();
		//coreUi.click(Locator.demoWebsite);
		coreUi.click(Locator.lightBoxCLose);
		coreUi.click(Locator.btnStartPracticing);
		coreUi.click(Locator.bootstraperAlertFormDemp);
		List<WebElement> bootstraperAlertBtn = coreUi.findElements(Locator.bootstraperAlertBtn);
		for (int i = 0; i < bootstraperAlertBtn.size(); i++) {
			if (coreUi.getText(Locator.bootstraperAlertBtn + "[" + (i + 1) + "]").contains("Autocloseable")) {
				coreUi.click(Locator.bootstraperAlertBtn + "[" + (i + 1) + "]");
				assertions.hardAssertTrue(coreUi.isDisplayed(Locator.bootstraperAlertMsg + "[" + (i + 1) + "]"),
						"Alert Displayed", "Alert not Displayed");
				assertions.hardAssertTrue(
						Waits.WaitForInvisibilityOf(
								coreUi.findElement(Locator.bootstraperAlertMsg + "[" + (i + 1) + "]"),
								Integer.parseInt(
										coreUi.getText(Locator.bootstraperAlertMsg + "[" + (i + 1) + "]").substring(
												coreUi.getText(Locator.bootstraperAlertMsg + "[" + (i + 1) + "]")
														.indexOf(" seconds") - 1,
												coreUi.getText(Locator.bootstraperAlertMsg + "[" + (i + 1) + "]")
														.indexOf(" seconds")))),
						"Alert not Displayed", "Alert Displayed");
			} else {
				coreUi.click(Locator.bootstraperAlertBtn + "[" + (i + 1) + "]");
				assertions.hardAssertTrue(coreUi.isDisplayed(Locator.bootstraperAlertMsg + "[" + (i + 1) + "]"),
						"Alert Displayed", "Alert not Displayed");
				coreUi.click(Locator.bootstraperAlertMsg + "[" + (i + 1) + "]/button");
			}
		}
	}
}
