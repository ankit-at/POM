package rec.global.utils;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


/**
 * This class provides menthods for click, delete and reading the properties file
 * @author ankit
 *
 */
public class AbstractPageObject{
	
	public static String workDir = System.getProperty("user.dir");
		

	/**
	 * This method used to read properties file
	 * @return boolean
	 * @author ankit
	 * @return
	 * @since 12/04/2023
	 */
	public static String config(String key) {
		FileReader fileInput = null;
		try {
			fileInput = new FileReader(workDir + "/src/test/resources/TestData.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Properties prop = new Properties();

		// load properties file
		try {
			prop.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop.getProperty(key);
	}



}

