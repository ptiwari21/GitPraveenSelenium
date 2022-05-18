/**
 * 
 */
package testcases;

import static org.seleniumpraveen.com.core.driver.DriverSetup.initializeDriver;

import org.seleniumpraveen.com.core.exception.FrameworkExceptions;
import org.seleniumpraveen.com.core.helper.Locator;
import org.seleniumpraveen.com.core.logger.Assertions;
import org.seleniumpraveen.com.core.ui.CoreUi;
import org.seleniumpraveen.com.core.utils.Config;
import org.testng.annotations.Test;

/**
 * @author praveenkumar.tiwari
 *
 */
public class AlertBox {
	@Test
	public void alertBox() throws FrameworkExceptions {
		initializeDriver();
		Assertions assertion = new Assertions();
		CoreUi coreUi = new CoreUi();
		coreUi.get();
		//coreUi.click(Locator.demoWebsite);
		coreUi.click(Locator.lightBoxCLose);
		coreUi.click(Locator.btnStartPracticing);
		coreUi.click(Locator.alertBoxFormDemp);
		coreUi.click(Locator.alertBoxClick);
		assertion.hardAssertTrue(coreUi.isAlertPresent(), "Alert Presert", "Alert not present");
		coreUi.acceptAlert();
		assertion.hardAssertFalse(coreUi.isAlertPresent(), "Alert not Presert", "Alert present");
		coreUi.click(Locator.confirmBoxClick);
		assertion.hardAssertTrue(coreUi.isAlertPresent(), "Confirm Alert Presert", "Confirm Alert not present");
		coreUi.acceptAlert();
		assertion.hardAssertFalse(coreUi.isAlertPresent(), "COnfirm Alert not Presert", "Confirm Alert present");
		coreUi.click(Locator.confirmBoxClick);
		assertion.hardAssertTrue(coreUi.isAlertPresent(), "Confirm Alert Presert", "Confirm Alert not present");
		coreUi.dismissAlert();
		assertion.hardAssertFalse(coreUi.isAlertPresent(), "Confirm Alert not Presert", "Confirm Alert present");
		coreUi.click(Locator.promptBoxClick);
		assertion.hardAssertTrue(coreUi.isAlertPresent(), "Prompt Alert Presert", "Prompt Alert not present");
		coreUi.dismissAlert();
		assertion.hardAssertFalse(coreUi.isAlertPresent(), "Prompt Alert not Presert", "Prompt Alert present");
		coreUi.click(Locator.promptBoxClick);
		assertion.hardAssertTrue(coreUi.isAlertPresent(), "Prompt Alert Presert", "Prompt Alert not present");
		coreUi.sendKeysAlert(Config.testCaseId);
		coreUi.acceptAlert();
		assertion.hardAssertFalse(coreUi.isAlertPresent(), "Prompt Alert not Presert", "Prompt Alert present");
		coreUi.quit();

	}
}
