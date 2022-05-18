/**
 * 
 */
package testcases;

import static org.seleniumpraveen.com.core.driver.DriverSetup.initializeDriver;

import org.seleniumpraveen.com.core.exception.FrameworkExceptions;
import org.seleniumpraveen.com.core.helper.Locator;
import org.seleniumpraveen.com.core.logger.Report;
import org.seleniumpraveen.com.core.ui.CoreUi;
import org.testng.annotations.Test;

/**
 * @author praveenkumar.tiwari
 *
 */
public class ListBoxDropDown {

	@Test
	public void listBoxDropDown() throws FrameworkExceptions {
		initializeDriver();
		CoreUi coreUi = new CoreUi();
		coreUi.get();
		//coreUi.click(Locator.demoWebsite);
		coreUi.click(Locator.lightBoxCLose);
		coreUi.click(Locator.btnStartPracticing);
		coreUi.click(Locator.btnProceedNextPracticing);
		coreUi.click(Locator.selectListDropdownFormDemp);
		coreUi.listBoxSelectValue("//*[@id='select2-country-container']/following-sibling::span",
				"//*[@id='select2-country-results']/li", "Japan");
		coreUi.listBoxSelectMultipleValue(
				"//*[text()='Multi Select - Search and Select multiple states']/parent::div//*[@class='select2-selection__rendered']/parent::span",
				"//*[@class='select2-results__options']/li", "Iowa;Maine".split(";"));
		Report.pass("Screenshot Captured");
	}
}
