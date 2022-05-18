/**
 * 
 */
package org.seleniumpraveen.com.core.logger;

import org.seleniumpraveen.com.core.exception.FrameworkExceptions;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

public class Assertions {

    private Assertion hardAssert = new Assertion();
    private SoftAssert softAssert;

    private SoftAssert getSoftAssert() {
        return softAssert;
    }
    public void setSoftAssert(SoftAssert softAssert) {
        this.softAssert = softAssert;
    }


    public void hardAssertTrue(boolean pageAssert, String stepDescription) throws FrameworkExceptions {
        String strSuccessMessage = "Verified: " + stepDescription;
        String strErrorMessage = "FAILED: " + stepDescription ;
        stepDescription = pageAssert? strSuccessMessage: strErrorMessage;
        hardAssert.assertTrue(pageAssert, strErrorMessage);
        Report.pass(addStepDescription(stepDescription));
    }

    public void hardAssertEquals(String actualValue, String expectedValue, String strSuccessMessage, String strErrorMessage) throws FrameworkExceptions {
        boolean result = actualValue.equals(expectedValue);
        String stepDescription = result? strSuccessMessage: strErrorMessage;
        hardAssertEquals(actualValue, expectedValue, stepDescription);
    }

    //@Step("{2}")
    private void hardAssertNotEquals(String actualValue, String expectedValue, String stepDescription) throws FrameworkExceptions {
    	
    	hardAssert.assertNotEquals(actualValue, expectedValue, stepDescription);
    	Report.pass(stepDescription);
    }

    public void hardAssertNotEquals(String actualValue, String expectedValue, String strSuccessMessage, String strErrorMessage) throws FrameworkExceptions {
        boolean result = !actualValue.equals(expectedValue);
        String stepDescription = result? strSuccessMessage: strErrorMessage;
        hardAssertNotEquals(actualValue, expectedValue, stepDescription);
    }

    public void hardAssertFalse(boolean pageAssert, String stepDescription) throws FrameworkExceptions {
        String strSuccessMessage = "Verified: " + stepDescription;
        String strErrorMessage = "FAILED: " + stepDescription;
        stepDescription = !pageAssert? strSuccessMessage: strErrorMessage;
        hardAssert.assertFalse(pageAssert, strErrorMessage);
        Report.pass(addStepDescription(stepDescription));
    }

    public void hardAssertEquals(String actualValue, String expectedValue, String stepDescription) throws FrameworkExceptions {
        String strSuccessMessage = "Verified: " + stepDescription + ". Expected: " + expectedValue + ", Actual: " + actualValue;
        String strErrorMessage = "FAILED: " + stepDescription + ". Expected: " + expectedValue + ", Actual: " + actualValue;
        stepDescription = actualValue.equals(expectedValue)? strSuccessMessage: strErrorMessage;
        hardAssert.assertEquals(actualValue,expectedValue, stepDescription);
        Report.pass(addStepDescription(stepDescription));
    }

    public void softAssertTrue(boolean pageAssert, String stepDescription){
        String strSuccessMessage = "Verified: " + stepDescription;
        String strErrorMessage = "FAILED: " + stepDescription;
        stepDescription = pageAssert? strSuccessMessage: strErrorMessage;
        addStepDescription(stepDescription);
        getSoftAssert().assertTrue(pageAssert);
    }

    public void softAssertTrue(boolean pageAssert, String strSuccessMessage, String strErrorMessage){
        String stepDescription = pageAssert? strSuccessMessage:strErrorMessage;
        softAssertTrue(pageAssert, stepDescription);
    }

    public void hardAssertTrue(boolean pageAssert, String strSuccessMessage, String strErrorMessage) throws FrameworkExceptions {
        String stepDescription = pageAssert? strSuccessMessage:strErrorMessage;
        hardAssertTrue(pageAssert, stepDescription);
    }
    
    public void hardAssertFalse(boolean pageAssert, String strSuccessMessage, String strErrorMessage) throws FrameworkExceptions {
        String stepDescription = !pageAssert? strSuccessMessage:strErrorMessage;
        hardAssertFalse(pageAssert, stepDescription);
    }

    public void softAssertFalse(boolean pageAssert, String stepDescription){
        String strSuccessMessage = "Verified: " + stepDescription;
        String strErrorMessage = "FAILED: " + stepDescription;
        stepDescription = !pageAssert? strSuccessMessage: strErrorMessage;
        addStepDescription(stepDescription);
        getSoftAssert().assertFalse(pageAssert);

    }

    public void softAssertFalse(boolean pageAssert, String strSuccessMessage, String strErrorMessage){
        String stepDescription = !pageAssert? strSuccessMessage: strErrorMessage;
        softAssertFalse( pageAssert, stepDescription);
    }

    /*public void softAssertEquals(String actualValue, String expectedValue, String stepDescription){
        String strSuccessMessage = "Verified: " + stepDescription + " = " + actualValue;
        String strErrorMessage = "FAILED: " + stepDescription + ". Expected: " + expectedValue + ", Actual: " + actualValue;
        stepDescription = "Expected value '"+expectedValue+"', Retrieved value '"+actualValue+"' -- "+stepDescription;
        logAssert(actualValue.equals(expectedValue),stepDescription);
        getSoftAssert().assertEquals(actualValue,expectedValue);
    }*/
   	
   public  String addStepDescription(String stepDescription){
    	return "*** " + stepDescription + " ***";
    }

    public void softAssertEquals(String actualValue, String expectedValue, String stepDescription){
        String strSuccessMessage = "Verified: " + stepDescription + " = " + actualValue;
        String strErrorMessage = "FAILED: " + stepDescription + ". Expected: " + expectedValue + ", Actual: " + actualValue;
        stepDescription = actualValue.equals(expectedValue)? strSuccessMessage: strErrorMessage;
        addStepDescription(stepDescription);
        getSoftAssert().assertEquals(actualValue,expectedValue);
    }


    public void softAssertEquals(String actualValue, String expectedValue, String strSuccessMessage, String strErrorMessage){
        strSuccessMessage += actualValue;
        strErrorMessage += ". Expected value '"+expectedValue+"', Retrieved value '"+actualValue;
        String stepDescription = actualValue.equals(expectedValue)? strSuccessMessage: strErrorMessage;
        softAssertEquals(actualValue, expectedValue, stepDescription);
    }

    public void assertAll(){
        getSoftAssert().assertAll();
    }

    public void softAssertEquals(int actualValue, int expectedValue, String stepDescription){
        String strSuccessMessage = "Verified: " + stepDescription + " = " + actualValue;
        String strErrorMessage = "FAILED: " + stepDescription + ". Expected: " + expectedValue + ", Actual: " + actualValue;
        stepDescription = actualValue == expectedValue? strSuccessMessage: strErrorMessage;
        addStepDescription(stepDescription);
        softAssertEquals(String.valueOf(actualValue), String.valueOf(expectedValue), stepDescription);
    }
}
