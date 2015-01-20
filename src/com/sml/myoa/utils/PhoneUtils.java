package com.sml.myoa.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

/**
 * 获取手机信息工具类
 * @author Victor
 *
 */
public class PhoneUtils {

	//获取手机IMEI号
	public static String getIMEI(Activity act){
		TelephonyManager  tm = (TelephonyManager)act.getSystemService(Context.TELEPHONY_SERVICE);
		String myIMEI = tm.getDeviceId();
		String myIMSI = tm.getSubscriberId();
		String myType = android.os.Build.MODEL;//手机型号
		String myBrand = android.os.Build.BRAND; //手机品牌
	    StringBuffer sb = new StringBuffer();
	    sb.append(myIMEI+",");
	    sb.append(myIMSI+",");
	    sb.append(myType+",");
	    sb.append(myBrand);
		return sb.toString();
	}
	
}
