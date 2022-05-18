package testcases;

import static org.seleniumpraveen.com.core.driver.DriverSetup.initializeDriver;

import org.openqa.selenium.Keys;
import org.seleniumpraveen.com.core.exception.FrameworkExceptions;
import org.seleniumpraveen.com.core.helper.Locator;
import org.seleniumpraveen.com.core.logger.Assertions;
import org.seleniumpraveen.com.core.ui.CoreUi;
import org.seleniumpraveen.com.core.utils.ExcelUtils;
import org.testng.annotations.Test;

public class GoogleSearch {
	
	@Test
	public void googleSearch() throws FrameworkExceptions {
		
		initializeDriver();
		Assertions assertion = new Assertions();
		CoreUi coreUi = new CoreUi();
		coreUi.get();
		coreUi.setTextWithSendKey(Locator.searchTextField,ExcelUtils.getCellValue("TestRunner", "Google_Search"), Keys.ENTER);
		assertion.hardAssertTrue(coreUi.isDisplayed(Locator.helloWorldVerifyMsg), "Message Displayed", "Message not Displayed");
		
	}

}
