package com.hackerthon.common;

import java.util.logging.Level;

import java.util.logging.Logger;
import java.io.IOException;
import java.util.Properties;


public class CommonUtil {

	public static final Properties PROPERTIES = new Properties();

	static {
		try {
			PROPERTIES.load(QueryUtil.class.getResourceAsStream("../config/config.properties"));
		} catch (Exception e) {
			
		}
	}
}
