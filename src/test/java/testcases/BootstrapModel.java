/**
 * 
 */
package testcases;

import static org.seleniumpraveen.com.core.driver.DriverSetup.initializeDriver;

import org.seleniumpraveen.com.core.exception.FrameworkExceptions;
import org.seleniumpraveen.com.core.helper.Locator;
import org.seleniumpraveen.com.core.logger.Assertions;
import org.seleniumpraveen.com.core.ui.CoreUi;
import org.testng.annotations.Test;

/**
 * @author praveenkumar.tiwari
 *
 */
public class BootstrapModel {

	@Test
	public void bootstrapModel() throws FrameworkExceptions {
		initializeDriver();
		Assertions assertion = new Assertions();
		CoreUi coreUi = new CoreUi();
		coreUi.get();
		//coreUi.click(Locator.demoWebsite);
		coreUi.click(Locator.lightBoxCLose);
		coreUi.click(Locator.btnStartPracticing);
		coreUi.click(Locator.bootstraperModelFormDemp);
		coreUi.click(Locator.singleLaunchModel);
		assertion.hardAssertTrue(coreUi.getText(Locator.singleLaunchHeaderModel).contains("Modal Title"),
				"Message Matched", "Message not Matched");
		assertion.hardAssertTrue(
				coreUi.getText(Locator.singleLaunchBodyModel)
						.contains("This is the place where the content for the modal dialog displays"),
				"Message Matched", "Message not Matched");
		coreUi.click(Locator.singleLaunchSaveModel);

		coreUi.click(Locator.singleLaunchModel);
		coreUi.click(Locator.singleLaunchCloseModel);

		coreUi.click(Locator.singleLaunchModel);
		coreUi.click(Locator.singleLaunchCloseIconModel);

	}
}
