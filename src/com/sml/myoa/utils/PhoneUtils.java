package com.sml.myoa.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

/**
 * ��ȡ�ֻ���Ϣ������
 * @author Victor
 *
 */
public class PhoneUtils {

	//��ȡ�ֻ�IMEI��
	public static String getIMEI(Activity act){
		TelephonyManager  tm = (TelephonyManager)act.getSystemService(Context.TELEPHONY_SERVICE);
		String myIMEI = tm.getDeviceId();
		String myIMSI = tm.getSubscriberId();
		String myType = android.os.Build.MODEL;//�ֻ��ͺ�
		String myBrand = android.os.Build.BRAND; //�ֻ�Ʒ��
	    StringBuffer sb = new StringBuffer();
	    sb.append(myIMEI+",");
	    sb.append(myIMSI+",");
	    sb.append(myType+",");
	    sb.append(myBrand);
		return sb.toString();
	}
	
	//�ж��Ƿ�����������
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
	
	//��ȡ��ǰ����״̬
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
