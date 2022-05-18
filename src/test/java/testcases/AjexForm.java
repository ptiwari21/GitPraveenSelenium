/**
 * 
 */
package testcases;

import static org.seleniumpraveen.com.core.driver.DriverSetup.initializeDriver;

import org.seleniumpraveen.com.core.exception.FrameworkExceptions;
import org.seleniumpraveen.com.core.helper.Locator;
import org.seleniumpraveen.com.core.logger.Assertions;
import org.seleniumpraveen.com.core.ui.CoreUi;
import org.seleniumpraveen.com.core.ui.ElementFinder;
import org.seleniumpraveen.com.core.utils.ExcelUtils;
import org.seleniumpraveen.com.core.utils.Waits;
import org.testng.annotations.Test;

/**
 * @author praveenkumar.tiwari
 *
 */
public class AjexForm {
	@Test
	public void ajexForm() throws FrameworkExceptions {
		initializeDriver();
		CoreUi coreUi = new CoreUi();
		Assertions assertions = new Assertions();
		coreUi.get();
		//coreUi.click(Locator.demoWebsite);
		coreUi.click(Locator.lightBoxCLose);
		coreUi.click(Locator.btnStartPracticing);
		coreUi.click(Locator.btnProceedNextPracticing);
		coreUi.click(Locator.ajexFormFormDemp);
		coreUi.setText(Locator.name, ExcelUtils.getCellValue("TestRunner", "Name"));
		coreUi.setText(Locator.description, ExcelUtils.getCellValue("TestRunner", "Description"));
		coreUi.click(Locator.btnSubmit);
		assertions.hardAssertTrue(Waits.WaitForInvisibilityOfElementLocated(new ElementFinder().findBy(Locator.btnSubmit), 10), "Element not visible", "Element visible");
		
	}

}
