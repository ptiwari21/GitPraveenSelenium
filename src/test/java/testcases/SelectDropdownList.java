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
public class SelectDropdownList {
	
	@Test
	public void selectDropdownList() throws FrameworkExceptions {
		initializeDriver();
		Assertions assertion = new Assertions();
		CoreUi coreUi = new CoreUi();
		coreUi.get();
		//coreUi.click(Locator.demoWebsite);
		coreUi.click(Locator.lightBoxCLose);
		coreUi.click(Locator.btnStartPracticing);
		coreUi.click(Locator.selectDropdownListFormDemp);
		coreUi.selectByValue(Locator.selectDayDemoDropdown, ExcelUtils.getCellValue("TestRunner", "Select_Day"));
		assertion.hardAssertEquals(coreUi.getText(Locator.selectDayDemoDropdownMsg), "Day selected :- "+ExcelUtils.getCellValue("TestRunner", "Select_Day"), "Message Matched", "Message not matched");
		coreUi.quit();
	}

}
