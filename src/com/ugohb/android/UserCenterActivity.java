package com.ugohb.android;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.ugohb.android.constant.Config;
import com.ugohb.android.pages.BasePage;
import com.ugohb.android.pages.UserNoticePage;
import com.ugohb.android.pages.UserRelatedPage;
import com.ugohb.android.pages.UserUpdPwdPage;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.LinearLayout;

public class UserCenterActivity extends Activity {
	@ViewInject(R.id.user_center_stage)
	LinearLayout user_center_stage;	
	public Context ct;
	public BasePage MPage;
	String UIType;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_center);
		ct=this;
		ViewUtils.inject(this);
		initUI();
	}

	private void initUI() {
		UIType=getIntent().getStringExtra(Config.USER_CENTER);
		if (UIType.equals(Config.USER_NOTICE)) {
			MPage=new UserNoticePage(ct);
		}else if (UIType.equals(Config.USER_RELATE)) {
			MPage=new UserRelatedPage(ct);
		}else if (UIType.equals(Config.USER_UPDPWD)) {
			MPage=new UserUpdPwdPage(ct);
		}else {
			MPage=new UserNoticePage(ct);
		}
		user_center_stage.removeAllViews();
		user_center_stage.addView(MPage.getRootView());
	}
}
