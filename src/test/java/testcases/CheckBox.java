/**
 * 
 */
package testcases;

import static org.seleniumpraveen.com.core.driver.DriverSetup.initializeDriver;

import java.util.Arrays;

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
public class CheckBox {
	@Test
	public void checkBox() throws FrameworkExceptions {
		initializeDriver();
		Assertions assertion = new Assertions();
		CoreUi coreUi = new CoreUi();
		coreUi.get();
		//coreUi.click(Locator.demoWebsite);
		coreUi.click(Locator.lightBoxCLose);
		coreUi.click(Locator.btnStartPracticing);
		coreUi.click(Locator.checkBoxFormDemp);
		coreUi.checkBoxBooleanSelection(Locator.isAgeSelectedCheckBox,
				ExcelUtils.getCellValue("TestRunner", "Single_Checkbox").equalsIgnoreCase("Y"));
		if (ExcelUtils.getCellValue("TestRunner", "Single_Checkbox").equalsIgnoreCase("Y")) {
			assertion.hardAssertTrue(coreUi.isChecked(Locator.isAgeSelectedCheckBox), "Element Checked");
			assertion.hardAssertEquals(coreUi.getText(Locator.displayTxtAgeMsg), "Success - Check box is checked",
					"Message Matched");
		} else {
			assertion.hardAssertFalse(coreUi.isChecked(Locator.isAgeSelectedCheckBox), "Element Un-Checked");
		}

		for (int i = 1; i <= coreUi.findElements(Locator.checkBoxAll).size(); i++) {
			coreUi.checkBoxBooleanSelection(String.format(Locator.multipleCheckBox, i),
					Arrays.asList(ExcelUtils.getCellValue("TestRunner", "Multiple_Checkbox").split(";"))
							.contains(String.valueOf(i)));
		}

		if (ExcelUtils.getCellValue("TestRunner", "Multiple_Checkbox").split(";").length > 2) {
			assertion.hardAssertEquals(coreUi.getText(Locator.checkBoxAllStatus), "Uncheck All", "Message Matched");
		} else {
			assertion.hardAssertEquals(coreUi.getText(Locator.checkBoxAllStatus), "Check All", "Message Matched");
		}

		coreUi.check(Locator.checkBoxAllStatus);
		coreUi.check(Locator.checkBoxAllStatus);
		coreUi.quit();
	}
}
