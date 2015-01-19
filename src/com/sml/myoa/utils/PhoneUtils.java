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
	
	//判断是否有网络连接
	public static boolean isNetWorkConnected(Context context){
		if(context!=null){
			ConnectivityManager mConnectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mynetWorkInfo = mConnectivityManager.getActiveNetworkInfo();
			if(mynetWorkInfo!=null){
				return mynetWorkInfo.isAvailable();
			}
		}
		return false ;
	}
	
	//获取当前网络状态
	public static int getConnectedType(Context context) { 
		if (context != null) { 
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE); 
			NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo(); 
			if (mNetworkInfo != null && mNetworkInfo.isAvailable()) { 
				return mNetworkInfo.getType(); 
			} 
		} 
		return -1; 
	}
}
