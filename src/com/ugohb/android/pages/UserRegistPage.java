package com.ugohb.android.pages;

import com.ugohb.android.PassportActivity;
import com.ugohb.android.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class UserRegistPage extends BasePage{
	View root;
	UserRegistHolder uHolder;
	ImageView iv_back;
	public UserRegistPage(Context ct) {
		super(ct);
	}

	@Override
	public View initView(LayoutInflater inflater) {
		root=inflater.inflate(R.layout.page_user_regist, null);
		uHolder=new UserRegistHolder();
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
				((PassportActivity)ct).CloseRegist();
			}
		});
		
	}
	
	@Override
	public void initData() {}
	public class UserRegistHolder{
		public EditText regist_user_tel;
		public EditText regist_user_idcode;
		public EditText regist_user_pwd;
		public EditText regist_user_pwd_true;
		public Button btn_regist_submit;
		public TextView tv_idcode_get;
	}

	
}
