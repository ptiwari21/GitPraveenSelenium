/**
 * 
 */
package org.seleniumpraveen.com.core.utils;

/**
 * @author praveenkumar.tiwari
 *
 */
public class CommonUtils {
	
	public static boolean isBlankNull(String value) {
		boolean isEmptyBlankNull = false;
		if(value.equals(null) && value.equals("")) {
			isEmptyBlankNull =true;
		}
		return isEmptyBlankNull;
	}

}
