package com.ugohb.android.pages;

import com.ugohb.android.R;
import com.ugohb.android.UserCenterActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class UserNoticePage extends BasePage{
	View root;
	ImageView iv_back;
	public UserNoticePage(Context ct) {
		super(ct);
	}

	@Override
	public View initView(LayoutInflater inflater) {
		root=inflater.inflate(R.layout.page_user_notice, null);
		initUI();
		initListen();
		return root;
	}
	public void initUI(){
		iv_back=(ImageView) root.findViewById(R.id.iv_back);
	}
	public void initListen(){
		iv_back.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				((UserCenterActivity)ct).finish();
			}
		});		
	}
	
	@Override
	public void initData() {}

	
}
