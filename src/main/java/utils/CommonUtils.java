package utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class CommonUtils {

//	protected static String projectLocation;
	private static Properties bundle;

	public static void loadProperties() {		
		try {
			String basePath = getProjectBasePath();
			System.out.println("basepath: "+basePath);
//			projectLocation = basePath;
			File file = new File(basePath+"\\SeleniumAppiumDemo\\config\\test.properties");
			FileInputStream fis = new FileInputStream(file);
			bundle = new Properties();
			bundle.load(fis);
			Report.info("test.properties loaded successfully...");
		}catch(Exception e) {
			Report.fail("test.properties file is not found. Hence could not load properties."+e);
		}				
	}	
	
	
	public static String getProperty(String key) {

		return bundle.getProperty(key);
	}
	
	public static String getProjectBasePath() {
		String basePath = null;
		File directory = new File("..");
		try {
			basePath = directory.getCanonicalPath();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return basePath;
	}


	
}
