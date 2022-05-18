/**
 * 
 */
package runner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.seleniumpraveen.com.core.exception.FrameworkExceptions;
import org.seleniumpraveen.com.core.helper.Constants;
import org.seleniumpraveen.com.core.utils.Config;
import org.seleniumpraveen.com.core.utils.ExcelUtils;
import org.testng.TestNG;
import org.testng.annotations.Test;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

/**
 * @author praveenkumar.tiwari
 *
 */
public class TestRunner {

	static {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss");
		System.setProperty("current.date", dateFormat.format(new Date()));
	}

	@Test
	public void testRunnerNG() throws FrameworkExceptions {
		ExcelUtils.setWorkbookExcel(Config.getConfigProperty(Constants.EXCEL_PATH));
		TestNG myTestNG = new TestNG();
		XmlSuite mySuite = new XmlSuite();
		mySuite.setName("PraveenSelenium Test Suite");
		mySuite.addListener("org.uncommons.reportng.HTMLReporter");
		mySuite.addListener("org.uncommons.reportng.JUnitXMLReporter");
		mySuite.addListener("org.seleniumpraveen.com.core.logger.ListenerClass");
		myTestNG.setOutputDirectory(System.getProperty("user.dir") + "\\reports\\praveen-selenium_"
				+ System.getProperty("current.date") + "\\test-output");
		List<XmlTest> myTests = new ArrayList<XmlTest>();
		for (int i = 2; i <= ExcelUtils.getRowNumber("TestRunner"); i++) {
			if (ExcelUtils.getCellValue("TestRunner", "Execute", i).equalsIgnoreCase("Y")) {
				XmlTest myTest = new XmlTest(mySuite);
				myTest.setName(ExcelUtils.getCellValue("TestRunner", "Test_Case_ID", i));
				List<XmlClass> myClasses = new ArrayList<XmlClass>();
				myClasses.add(new XmlClass("testcases." + ExcelUtils.getCellValue("TestRunner", "Script_Name", i)));
				myTest.setXmlClasses(myClasses);
				myTests.add(myTest);
			}
		}
		mySuite.setTests(myTests);
		List<XmlSuite> mySuites = new ArrayList<XmlSuite>();
		mySuites.add(mySuite);
		myTestNG.setXmlSuites(mySuites);
		myTestNG.setUseDefaultListeners(true);
		myTestNG.run();

	}
}
