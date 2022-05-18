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
public class RadioButton {

	String expectedOptRdioButtonMsg = "Radio button '%s' is checked";
	String expectedGroupRdioButtonMsg = "Sex : %s Age group: %s";
	
	/**
	 * @param args
	 */
	@Test
	public void radioButton() throws FrameworkExceptions {
		initializeDriver();
		Assertions assertion = new Assertions();
		CoreUi coreUi = new CoreUi();
		coreUi.get();
		//coreUi.click(Locator.demoWebsite);
		coreUi.click(Locator.lightBoxCLose);
		coreUi.click(Locator.btnStartPracticing);
		coreUi.click(Locator.radioButtonFormDemp);
		coreUi.click(String.format(Locator.optRadio, ExcelUtils.getCellValue("TestRunner", "Gender_Option")));
		coreUi.click(Locator.radioButtonCheck);
		assertion.hardAssertEquals(coreUi.getText(Locator.radioButtonMsg),
				String.format(expectedOptRdioButtonMsg, ExcelUtils.getCellValue("TestRunner", "Gender_Option")), "Message Matched", "Message not matched");
		coreUi.click(String.format(Locator.genderRadio, ExcelUtils.getCellValue("TestRunner", "Gender_Option")));
		coreUi.click(String.format(Locator.ageGroupRadio, ExcelUtils.getCellValue("TestRunner", "Age_Group_Option")));
		coreUi.click(Locator.getValuesButtonCheck);
		assertion.hardAssertEquals(coreUi.getText(Locator.radioButtonGroupMsg).replace("\n", " "),
				String.format(expectedGroupRdioButtonMsg, ExcelUtils.getCellValue("TestRunner", "Gender_Option"), ExcelUtils.getCellValue("TestRunner", "Age_Group_Option")), "Message Matched",
				"Message not matched");
		coreUi.quit();
		
	}
}
