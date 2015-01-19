package com.sml.myoa.view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Fragment基类，所有的Fragment都继承它
 * @author Victor
 *
 */

public abstract class FRA_Base extends Fragment {

	private View mView;
	
	private int mLayoutId;
	
	protected LayoutInflater mInflater;
	
	protected FRA_Base(){
		this.mLayoutId = getLayoutId();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		this.mInflater = inflater;
		
		if(null == mView){
			mView = inflater.inflate(mLayoutId, container, false);
			initViews(mView);
			bindEvents();
			initDatas();
		}
		else {
			ViewGroup parent = (ViewGroup) mView.getParent();       
			if (parent != null) {            
				parent.removeView(mView);         
			}
		}
		return mView;
	}

	protected abstract int getLayoutId();
	
	protected abstract void initViews(View view);
	
	protected abstract void bindEvents();
	
	protected abstract void initDatas();
	
}
