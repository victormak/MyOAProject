package com.sml.myoa.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import com.sml.myoa.R;
import com.sml.myoa.utils.ClientUtil;
import com.sml.myoa.utils.PhoneUtils;

import android.app.Activity;
import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FRA_Login_Content extends FRA_Base {

	private HttpClient httpClient = null;
	private HttpPost postMethod = null;
	private BroadcastReceiver networkStateReceiver = null;
	private SharedPreferences sharedPreferences = null;

	private Button btnLogin;
	private EditText etId;
	private EditText etPassword;

	private String serverIP;
	private String serverPort;

	// @Override
	// public View onCreateView(LayoutInflater inflater, ViewGroup container,
	// Bundle savedInstanceState) {
	// View view = inflater.inflate(R.layout.fra_login_content, container,
	// false);
	// btnLogin = (Button)view.findViewById(R.id.btn_login);
	// btnLogin.setOnClickListener(new View.OnClickListener() {
	//
	// @Override
	// public void onClick(View v) {
	// Intent i = new Intent();
	// i.setClass(getActivity(), ACT_Main.class);
	// startActivity(i);
	// }
	// });
	//
	// return view;
	// }

	@Override
	protected int getLayoutId() {
		return R.layout.fra_login_content;
	}

	@Override
	protected void initViews(View view) {
		btnLogin = (Button) view.findViewById(R.id.btn_login);
		etId = (EditText) view.findViewById(R.id.et_login_id);
		etPassword = (EditText) view.findViewById(R.id.et_login_password);
	}

	@Override
	protected void bindEvents() {

		networkStateReceiver = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
				ConnectivityManager connectMager = (ConnectivityManager) getActivity().getSystemService(getActivity().CONNECTIVITY_SERVICE);// ������ӷ��������
				NetworkInfo wifiNetInfo = connectMager
						.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
				NetworkInfo mobileNetInfo = connectMager
						.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
				if (!wifiNetInfo.isConnected() && !mobileNetInfo.isConnected()) {
					Toast.makeText(
							getActivity(),
							getResources().getString(
									R.string.no_network_connection),
							Toast.LENGTH_LONG).show();
				} else {
					Toast.makeText(
							getActivity(),
							getResources().getString(
									R.string.has_network_connection),
							Toast.LENGTH_LONG).show();
				}

			}
		};

		IntentFilter intentFilter = new IntentFilter(); 
		intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION); 
		getActivity().registerReceiver(networkStateReceiver, intentFilter);
		
		btnLogin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				String strId = etId.getText().toString();
				String strPassword = etPassword.getText().toString();
				if (strId != null && strId != "" && strPassword != null
						&& strPassword != "") {
					sendInfo(PhoneUtils.getIMEI(getActivity()).toString(),
							serverIP, serverPort, strId, strPassword);
				} else {
					Toast.makeText(getActivity(),
							getResources().getString(R.string.not_null),
							Toast.LENGTH_LONG).show();
				}

			}
		});
	}

	protected void sendInfo(String string, String serverIP, String serverPort,
			String strId, String strPassword) {
		httpClient = new DefaultHttpClient();
		String posturl = "http://" + serverIP + ":" + serverPort
				+ "/oa/sigin.action";
		System.out.println("call sendinfo" + serverIP + serverPort + posturl
				+ "   " + strId + strPassword);
		postMethod = new HttpPost(posturl);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("user.user_email", strId));
		params.add(new BasicNameValuePair("user.user_pass", strPassword));
		try {
			postMethod.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			HttpResponse httpResponse = httpClient.execute(postMethod);
			System.out.println("httpResponse" + httpResponse);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				// ��¼�ɹ�
				// ��¼�û���������
				SharedPreferences.Editor editor = sharedPreferences.edit();
				editor.putString("username", strId);
				editor.putString("password", strPassword);
				editor.putString("islogin", "true");
				// �ύ���������
				editor.commit();
				// ��ת
				Intent intent = new Intent(getActivity(), ACT_Main.class);
				this.startActivity(intent);
			} else {
				System.out.println("false");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void initDatas() {
		// �������� ��Ҫ������жϣ���ϸ��Ϣ��Ҫ�����˽�
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}

		sharedPreferences = getActivity().getSharedPreferences("userInfo",
				Activity.MODE_PRIVATE);

		// ��������ļ���Ϣ����
		ClientUtil cutil = new ClientUtil(); // �ͻ��˹�����
		Properties cp = cutil.configurationHelp(getActivity()); // ��������ļ�������
		serverIP = cp.getProperty("oaclient.server.ip");
		serverPort = cp.getProperty("oaclient.server.port");

	}

}
