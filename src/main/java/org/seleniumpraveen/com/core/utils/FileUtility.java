/**
 * 
 */
package org.seleniumpraveen.com.core.utils;

import java.io.File;

/**
 * @author praveenkumar.tiwari
 *
 */
public class FileUtility {
	
	/** get project directory path.*/
	public static String getProjectRootPath() {		
		return System.getProperty("user.dir");
	}
	
	public static String getFileSeparatedPath(String slashedPath) {
		String fullPath = getProjectRootPath() + File.separator + slashedPath;
		return fullPath.replace("/",File.separator);								
	}
}
