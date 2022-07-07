package com.hackerthon.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Properties;

public class ReadProperty {
	
	public static final String FILE_PATH = "../Hackerthon2015Input/src/com/hackerthon/config/writeprop.properties";

	public static final String THAWA_USERNAME = "username";
	public static final String DRIVER_NAME = "driverName";
	
	public static final String USERNAME = "username";
	
	public static final String NAME = "Java";
	public static final String AGE = "20";
	public static final String MESSAGE = "message";
	
	public static void main(String[] args) {
		
		
		try {
			
			// Read property file
			FileInputStream fileInputStream = new FileInputStream("../Hackerthon2015Input/src/com/hackerthon/config/config.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);
			//String value = properties.getProperty("portnumber");
			System.out.println(properties.getProperty(THAWA_USERNAME));
			System.out.println(properties.getProperty(DRIVER_NAME));
		
			
			// Write to property file
			FileOutputStream fileOutputStream = new FileOutputStream(FILE_PATH);
			Properties propertyObject = new Properties();
			propertyObject.setProperty(USERNAME, "malak");
			propertyObject.store(fileOutputStream, null);
			
			// Pass parameters
			String test =  MessageFormat.format((String)properties.getProperty(MESSAGE), NAME, AGE);
			System.out.println(MessageFormat.format((String)properties.getProperty(MESSAGE), NAME, AGE));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
