/**
 * 
 */
package org.seleniumpraveen.com.core.helper;

/**
 * @author praveenkumar.tiwari
 *
 */
public class Locator {

	// Demo WebForm
	public static final String demoWebsite = "linktext:Demo Website!";
	public static final String lightBoxCLose = "id:at-cv-lightbox-close";
	public static final String btnStartPracticing = "id:btn_basic_example";
	public static final String btnProceedNextPracticing = "id:btn_inter_example";

	// Input Form
	public static final String simpleFormDemp = "//*[text()='Simple Form Demo' and @class='list-group-item']";
	public static final String inputUserMsg = "//input[@id='user-message']";
	public static final String btnShowMsg = "//button[text()='Show Message']";
	public static final String displayUserMsg = "//div[@id='user-message']/span";
	public static final String inputSum1 = "//input[@id='sum1']";
	public static final String inputSum2 = "//input[@id='sum2']";
	public static final String btnGetTotal = "//button[text()='Get Total']";
	public static final String displayValueMsg = "//*[@id='displayvalue']";

	// CheckBox
	public static final String checkBoxFormDemp = "//*[text()='Check Box Demo' and @class='list-group-item']";
	public static final String isAgeSelectedCheckBox = "id:isAgeSelected";
	public static final String displayTxtAgeMsg = "id:txtAge";
	public static final String checkBoxAll = "classname:cb1-element";
	public static final String multipleCheckBox = "//*[text()='Option %s']/input";
	public static final String checkBoxAllStatus = "id:check1";

	// RadioButton
	public static final String radioButtonFormDemp = "//*[text()='Radio Buttons Demo' and @class='list-group-item']";
	public static final String optRadio = "//*[@value='%s' and @name='optradio']";
	public static final String radioButtonCheck = "id:buttoncheck";
	public static final String radioButtonMsg = "//*[@class='radiobutton']";
	public static final String genderRadio = "//*[@value='%s' and @name='gender']";
	public static final String ageGroupRadio = "//*[@value='%s' and @name='ageGroup']";
	public static final String getValuesButtonCheck = "//*[text()='Get values']";
	public static final String radioButtonGroupMsg = "//*[@class='groupradiobutton']";

	// SelectDropdownList
	public static final String selectDropdownListFormDemp = "//*[text()='Select Dropdown List' and @class='list-group-item']";
	public static final String selectDayDemoDropdown = "id:select-demo";
	public static final String selectDayDemoDropdownMsg = "//select[@id='select-demo']/following-sibling::p";

	// GoogleSearch
	public static final String searchTextField = "name:q";
	public static final String helloWorldVerifyMsg = "//*[text()='\"Hello, World!\" program - Wikipedia']";

	// AlertBox
	public static final String alertBoxFormDemp = "//*[text()='Javascript Alerts' and @class='list-group-item']";
	public static final String alertBoxClick = "//*[text()='Click the button to display an alert box:']/following-sibling::button";
	public static final String confirmBoxClick = "//*[text()='Click the button to display an confirm box:']/following-sibling::button";
	public static final String promptBoxClick = "//*[text()='Click below button for prompt box.']/following-sibling::button";

	// windowPopup
	public static final String windowPopupFormDemp = "//*[text()='Window Popup Modal' and @class='list-group-item']";
	public static final String twitterWindowBtn = "partiallinktext:Follow On Twitter";
	public static final String facebookWindowBtn = "partiallinktext:Like us On Facebook";
	public static final String facebookTwitterWindowBtn = "partiallinktext:Follow Twitter & Facebook";
	public static final String followAllWindowBtn = "partiallinktext:Follow All";

	// BootstrapAlert
	public static final String bootstraperAlertFormDemp = "//*[text()='Bootstrap Alerts' and @class='list-group-item']";
	public static final String bootstraperAlertBtn = "//*[@id='autoclosable-btn-success']/parent::div/button";
	public static final String bootstraperAlertMsg = "//*[@id='autoclosable-btn-success']/parent::div/following-sibling::div/div";

	// BootstrapAlert
	public static final String bootstraperModelFormDemp = "//*[text()='Bootstrap Modals' and @class='list-group-item']";
	public static final String singleLaunchModel = "//*[text()='Single Modal Example']/following-sibling::div/a";
	public static final String singleLaunchContentModel = "//*[@id='myModal0']//*[@class='modal-content']";
	public static final String singleLaunchHeaderModel = singleLaunchContentModel + "//*[@class='modal-header']";
	public static final String singleLaunchTitleModel = singleLaunchHeaderModel + "//*[@class='modal-title']";
	public static final String singleLaunchCloseIconModel = singleLaunchHeaderModel + "//*[@class='close']";
	public static final String singleLaunchBodyModel = singleLaunchContentModel + "//*[@class='modal-body']";
	public static final String singleLaunchFooterModel = singleLaunchContentModel + "//*[@class='modal-footer']";
	public static final String singleLaunchCloseModel = singleLaunchFooterModel + "//*[text()='Close']";
	public static final String singleLaunchSaveModel = singleLaunchFooterModel + "//*[text()='Save changes']";
	public static final String multipleLaunchModel = "//*[text()='Multiple Modal Example']/following-sibling::div/a";
	public static final String multipleLaunchContentModel = "//*[@id='myModal']//*[@class='modal-content']";
	public static final String multipleLaunchHeaderModel = multipleLaunchContentModel + "//*[@class='modal-header']";
	public static final String multipleLaunchTitleModel = multipleLaunchHeaderModel + "//*[@class='modal-title']";
	public static final String multipleLaunchCloseIconModel = multipleLaunchHeaderModel + "//*[@class='close']";
	public static final String multipleLaunchBodyModel = multipleLaunchContentModel + "//*[@class='modal-body']";
	public static final String multipleLaunchFooterModel = multipleLaunchContentModel + "//*[@class='modal-footer']";
	public static final String multipleLaunchCloseModel = multipleLaunchFooterModel + "//*[text()='Close']";
	public static final String multipleLaunchSaveModel = multipleLaunchFooterModel + "//*[text()='Save changes']";
	public static final String singleLaunchContentModel2 = "//*[@id='myModal2']//*[@class='modal-content']";
	public static final String singleLaunchHeaderModel2 = singleLaunchContentModel2 + "//*[@class='modal-header']";
	public static final String singleLaunchTitleModel2 = singleLaunchHeaderModel2 + "//*[@class='modal-title']";
	public static final String singleLaunchCloseIconModel2 = singleLaunchHeaderModel2 + "//*[@class='close']";
	public static final String singleLaunchBodyModel2 = singleLaunchContentModel2 + "//*[@class='modal-body']";
	public static final String singleLaunchFooterModel2 = singleLaunchContentModel2 + "//*[@class='modal-footer']";
	public static final String singleLaunchCloseModel2 = singleLaunchFooterModel2 + "//*[text()='Close']";
	public static final String singleLaunchSaveModel2 = singleLaunchFooterModel2 + "//*[text()='Save changes']";

	// InputFormsWithValidation
	public static final String inputFormsWithValidationFormDemp = "//*[text()='Input Form with Validations' and @class='list-group-item']";
	public static final String firstName = "//*[@name='first_name']";
	public static final String lastName = "//*[@name='last_name']";
	public static final String email = "//*[@name='email']";
	public static final String phone = "//*[@name='phone']";
	public static final String address = "//*[@name='address']";
	public static final String city = "//*[@name='city']";
	public static final String state = "//*[@name='state']";
	public static final String zip = "//*[@name='zip']";
	public static final String website = "//*[@name='website']";
	public static final String hosting = "//*[@name='hosting' and @value='%s']";
	public static final String comment = "//*[@name='comment']";
	public static final String send = "//*[@type='submit' and text()='Send ']";

	// AjexForm
	public static final String ajexFormFormDemp = "//*[text()='Ajax Form Submit' and @class='list-group-item']";
	public static final String name = "//*[@name='title']";
	public static final String description = "//*[@id='description']";
	public static final String btnSubmit = "//*[@name='btn-submit']";

	// AjexForm
	public static final String selectListDropdownFormDemp = "//*[text()='JQuery Select dropdown' and @class='list-group-item']";

}
