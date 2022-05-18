/**
 * 
 */
package testcases;

import static org.seleniumpraveen.com.core.driver.DriverSetup.initializeDriver;

import org.seleniumpraveen.com.core.exception.FrameworkExceptions;
import org.seleniumpraveen.com.core.helper.Locator;
import org.seleniumpraveen.com.core.ui.CoreUi;
import org.seleniumpraveen.com.core.utils.ExcelUtils;
import org.testng.annotations.Test;

/**
 * @author praveenkumar.tiwari
 *
 */
public class InputFormsWithValidation {
	
	@Test
	public void inputFormsWithValidation() throws FrameworkExceptions {
		initializeDriver();
		CoreUi coreUi = new CoreUi();
		coreUi.get();
		//coreUi.click(Locator.demoWebsite);
		coreUi.click(Locator.lightBoxCLose);
		coreUi.click(Locator.btnStartPracticing);
		coreUi.click(Locator.btnProceedNextPracticing);
		coreUi.click(Locator.inputFormsWithValidationFormDemp);
		coreUi.setText(Locator.firstName, ExcelUtils.getCellValue("TestRunner", "First_Name"));
		coreUi.setText(Locator.lastName, ExcelUtils.getCellValue("TestRunner", "Last_Name"));
		coreUi.setText(Locator.email, ExcelUtils.getCellValue("TestRunner", "Email"));
		coreUi.setText(Locator.phone, ExcelUtils.getCellValue("TestRunner", "Phone"));
		coreUi.setText(Locator.address, ExcelUtils.getCellValue("TestRunner", "Address"));
		coreUi.setText(Locator.city, ExcelUtils.getCellValue("TestRunner", "City"));
		coreUi.selectByVisibleText(Locator.state, ExcelUtils.getCellValue("TestRunner", "State"));
		coreUi.setText(Locator.zip, ExcelUtils.getCellValue("TestRunner", "Zip_Code"));
		coreUi.setText(Locator.website, ExcelUtils.getCellValue("TestRunner", "WebsiteName"));
		coreUi.click(String.format(Locator.hosting, ExcelUtils.getCellValue("TestRunner", "Is_Hosting")));
		coreUi.setText(Locator.comment, ExcelUtils.getCellValue("TestRunner", "Project_Description"));
		coreUi.click(Locator.send);
	}
}
