package com.sml.myoa.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 网络工具类
 * @author Victor
 *
 */
public class NetUtils {

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
