package com.sml.myoa.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import android.content.Context;

public class ClientUtil {
	public  Properties configurationHelp(Context context){
//		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("/assets/oaclient.properties");
		
		Properties properties = new Properties();
		try {
			properties.load(context.getAssets().open("oaclient.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}
}
