package com.ugohb.android.pages;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import com.ugohb.android.R;

public class ShopActivityPage extends BasePage{
	public View root;
	
	View list_F;
	
	public ShopActivityPage(Context ct) {
		super(ct);
	}

	@Override
	public View initView(LayoutInflater inflater) {
		root=inflater.inflate(R.layout.view_activity_shop, null);
		list_F =inflater.inflate(R.layout.view_list, null);
		initUI();
		return root;
	}
	void initUI(){
		
		
	}

	@Override
	public void initData() {
		
	}
	

}
