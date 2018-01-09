package com.ldg.api.util;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Properties;

public class Remote131Properties {
	private static Properties properties = new Properties();
	private static String server;
	static {
		ClassPathResource cr = new ClassPathResource("remote131.properties");
		try {

			properties.load(cr.getInputStream());
			server=properties.getProperty("server");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getPropertiesVal(String key) {
		return properties.getProperty(key);
	}
	public static String getRequestPropertiesVal(String key) {
		return server+properties.getProperty(key);
	}
	public static void main(String[] args) {
		System.out.println(Remote131Properties.getPropertiesVal("getzhuyuanbingren"));
	}
}
