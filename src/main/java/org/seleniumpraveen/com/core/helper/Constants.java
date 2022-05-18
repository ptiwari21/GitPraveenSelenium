/**
 * 
 */
package org.seleniumpraveen.com.core.helper;

import org.seleniumpraveen.com.core.utils.FileUtility;

/**
 * @author praveenkumar.tiwari
 *
 */
public class Constants {
	
	public static final String CONFIG_FILE_PATH = FileUtility.getFileSeparatedPath("Config.xml");
	
	/** The Constant BrowserType. */
    public static final String BROWSER_TYPE="BrowserType";

    /** The Constant Application URL. */
    public static final String APP_URL="AppURL";

    /** Main User Credentials */
    public static final String USER_NAME="UserName";
    public static final String USER_PASSWORD ="Password";

    /**The Driver path**/
    public static final String CHROME_DRIVER_PATH= "ChromeDriverPath";
    public static final String IE_DRIVER_PATH="IEDriverPath";
    public static final String FIREFOX_DRIVER_PATH = "FireFoxDriverPath";
    
    /** Element Wait Time**/
    public static final String ELEMENT_WAIT_TIME ="ElementWaitTime";
    
    /** Excel Path**/
    public static final String EXCEL_PATH ="ExcelPath";
    public static final String TEST_CASE_COLUMN_NAME ="Test_Case_ID";

    /**Logger**/
    public static final String ELEMENT_LOG_MESSAGE = "Element ";
    public static final String ISLOADED_LOG_MESSAGE_FAILURE = " loading failed";
    public static final String ISDISPLAYED_LOG_MESSAGE_FAILURE = " displaying failed";
    public static final String SELECT_LOG_MESSAGE = " is Selected";
    public static final String SELECT_LOG_MESSAGE_SUCCESSS = " selected successfully";
    public static final String SELECT_LOG_MESSAGE_FAILURE = " selection failed";
    public static final String TEXTFIELD_LOG_MESSAGE="TextField element ";
    public static final String SET_TEXT_LOG_MESSAGE = " set text successfully";
    public static final String TEXTFIELD_CLEAR_TEXT_MESSAGE = "Text field text is erased";
    public static final String FORMATTER = "#### ";
    public static final String FAILURE_METHOD_MESSAGE = "Failure at the method: ";


    private Constants(){}
	

}
