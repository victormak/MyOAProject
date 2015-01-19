package com.sml.myoa.view;

import com.sml.myoa.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FRA_Title extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
//		return super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.fra_titlebar, container,false);
		return view;
	}

}
