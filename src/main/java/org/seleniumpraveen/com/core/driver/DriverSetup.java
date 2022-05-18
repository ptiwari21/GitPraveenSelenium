/**
 * 
 */
package org.seleniumpraveen.com.core.driver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.seleniumpraveen.com.core.exception.FrameworkExceptions;
import org.seleniumpraveen.com.core.helper.Constants;
import org.seleniumpraveen.com.core.logger.Logs;
import org.seleniumpraveen.com.core.utils.Config;
import org.seleniumpraveen.com.core.utils.ExcelUtils;
import org.seleniumpraveen.com.core.utils.FileUtility;

/**
 * @author praveenkumar.tiwari
 *
 */

public class DriverSetup {

	public static boolean browserInitialized = false;
	private static final int COMMAND_DEFAULT_TIMEOUT_SECONDS = 60;
	private static WebDriver driver;

	private DriverSetup() {
	}

	/**
	 * To initialize the Driver
	 * 
	 * @param browser - Browser (e.g. Chrome, Firefox IE etc)
	 * @return
	 * @throws FrameworkExceptions 
	 */
	public static void initializeDriver(String browser) throws FrameworkExceptions {
		if (("firefox").equalsIgnoreCase(browser)) {
			driver = initiateFireFoxDriver();
			Logs.info("Started Firefox Browser successfully.");
		} else if (("chrome").equalsIgnoreCase(browser)) {
			driver = initiateChromeDriver();
			Logs.info("Started Chrome Browser successfully.");
		} else if (("ie").equalsIgnoreCase(browser)) {
			driver = initiateIEDriver();
			Logs.info("Started IE Browser successfully.");
		} else {
			Logs.error("Browser is not support by the framework");
		}
		//driver = new EventFiringWebDriver(driver).register(new LogEventListener());
		browserInitialized = true;
	}
	
	public static void initializeDriver() throws FrameworkExceptions {
		String browser = ExcelUtils.getCellValue("TestRunner", "Browser_Type");
		if (("firefox").equalsIgnoreCase(browser)) {
			driver = initiateFireFoxDriver();
			Logs.info("Started Firefox Browser successfully.");
		} else if (("chrome").equalsIgnoreCase(browser)) {
			driver = initiateChromeDriver();
			Logs.info("Started Chrome Browser successfully.");
		} else if (("ie").equalsIgnoreCase(browser)) {
			driver = initiateIEDriver();
			Logs.info("Started IE Browser successfully.");
		} else {
			Logs.error("Browser is not support by the framework");
		}
		//driver = new EventFiringWebDriver(driver).register(new LogEventListener());
		browserInitialized = true;
	}

	/**
	 * To initialize the Chrome Driver
	 * 
	 * @param
	 * @return driver - chrome driver
	 * @throws FrameworkExceptions 
	 */
	private static WebDriver initiateChromeDriver() throws FrameworkExceptions {
		WebDriver driver = null;
		try {
			String chromePath = Config.getConfigProperty(Constants.CHROME_DRIVER_PATH);
			System.setProperty("webdriver.chrome.driver", chromePath);

			ChromeOptions options = new ChromeOptions();
			options.addArguments("--features-type");
			options.setExperimentalOption("useAutomationExtension", false);
			options.addArguments("--start-maximized");
			options.addArguments("chrome.switches", "--disable-extensions");
			driver = new ChromeDriver(options);
			Logs.info("Started Google Chrome Browser successfully");
			driver.manage().timeouts().implicitlyWait(COMMAND_DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
			
		} catch (Exception e) {
			throw new FrameworkExceptions("Failed to initiate Chrome Driver" + e);
		}
		return driver;
	}

	/**
	 * To initialize the FireFox Driver
	 * 
	 * @param
	 * @return driver - firefox driver
	 * @throws FrameworkExceptions 
	 */
	private static WebDriver initiateFireFoxDriver() throws FrameworkExceptions {
		WebDriver driver = null;
		try {
			String firefoxPath = FileUtility
					.getFileSeparatedPath(Config.getConfigProperty(Constants.FIREFOX_DRIVER_PATH));
			System.setProperty("webdriver.gecko.driver", firefoxPath);
			driver = new FirefoxDriver();
			Logs.info("Started FireFox Driver");
			driver.manage().timeouts().implicitlyWait(COMMAND_DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
		} catch (Exception e) {
			throw new FrameworkExceptions("Failed to initiate FireFox Driver" + e);
		}
		return driver;
	}

	/**
	 * To initialize the IE Driver
	 * 
	 * @param
	 * @return driver - IE driver
	 * @throws FrameworkExceptions 
	 */
	private static WebDriver initiateIEDriver() throws FrameworkExceptions {
		WebDriver driver = null;
		try {
			String iePath = FileUtility.getFileSeparatedPath(Config.getConfigProperty(Constants.IE_DRIVER_PATH));
			System.setProperty("webdriver.ie.driver", iePath);
			driver = new InternetExplorerDriver();
			Logs.info("Started Internet Explorer Driver");
			driver.manage().timeouts().implicitlyWait(COMMAND_DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
		} catch (Exception e) {
			throw new FrameworkExceptions("Failed to initiate IE Driver" + e);
		}
		return driver;
	}

    public static WebDriver getDriver() {
        return driver;
    }
    
    public static void setDriver(WebDriver driver) {
        DriverSetup.driver = driver;
    }
}
