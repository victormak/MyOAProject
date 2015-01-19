package com.sml.myoa.view;

import com.sml.myoa.R;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FRA_Login_Content extends Fragment {

	private Button btnLogin;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fra_login_content, container, false);
		btnLogin = (Button)view.findViewById(R.id.btn_login);
		btnLogin.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent();
				i.setClass(getActivity(), ACT_Main.class);
				startActivity(i);
			}
		});
		return view;
	}

	
}
