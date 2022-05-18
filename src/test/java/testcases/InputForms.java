/**
 * 
 */
package testcases;

import static org.seleniumpraveen.com.core.driver.DriverSetup.initializeDriver;

import org.seleniumpraveen.com.core.exception.FrameworkExceptions;
import org.seleniumpraveen.com.core.helper.Locator;
import org.seleniumpraveen.com.core.logger.Assertions;
import org.seleniumpraveen.com.core.ui.CoreUi;
import org.seleniumpraveen.com.core.utils.ExcelUtils;
import org.testng.annotations.Test;

/**
 * @author praveenkumar.tiwari
 *
 */
public class InputForms {
	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	@Test
	public void inputForms() throws FrameworkExceptions {
		initializeDriver();
		Assertions assertion = new Assertions();
		CoreUi coreUi = new CoreUi();
		coreUi.get();
		//coreUi.click(Locator.demoWebsite);
		coreUi.click(Locator.lightBoxCLose);
		coreUi.click(Locator.btnStartPracticing);
		coreUi.click(Locator.simpleFormDemp);
		coreUi.setText(Locator.inputUserMsg, ExcelUtils.getCellValue("TestRunner", "User_Message"));
		coreUi.click(Locator.btnShowMsg);
		assertion.hardAssertEquals(coreUi.getText(Locator.displayUserMsg), ExcelUtils.getCellValue("TestRunner", "User_Message"), "Message Matched", "Message not matched");
		coreUi.setText(Locator.inputSum1, ExcelUtils.getCellValue("TestRunner", "Sum_One"));
		coreUi.setText(Locator.inputSum2, ExcelUtils.getCellValue("TestRunner", "Sum_Two"));
		coreUi.click(Locator.btnGetTotal);
		assertion.hardAssertEquals(coreUi.getText(Locator.displayValueMsg), String.valueOf(Integer.parseInt(ExcelUtils.getCellValue("TestRunner", "Sum_One")) + Integer.parseInt(ExcelUtils.getCellValue("TestRunner", "Sum_Two"))), "Message Matched", "Message not matched");
		coreUi.quit();
	}
}
