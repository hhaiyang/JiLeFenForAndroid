package com.ugohb.android;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.ugohb.android.pages.UserLoginPage;
import com.ugohb.android.pages.UserRegistPage;
import com.ugohb.android.utils.JqViewUtils;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

public class PassportActivity extends Activity {
	@ViewInject(R.id.passport_login)
	LinearLayout passport_login;
	@ViewInject(R.id.passport_regist)
	LinearLayout passport_regist;
	Context ct;
	UserLoginPage uLogin;
	UserRegistPage uRegist;

	Animation Slide_in;
	Animation Slide_out;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_passport);
		ct=this;
		ViewUtils.inject(this);
		Slide_in=AnimationUtils.loadAnimation(ct, R.anim.slide_up);
		Slide_out=AnimationUtils.loadAnimation(ct, R.anim.slide_out);
		initUI();
	}
	public void initUI(){
		uLogin=new UserLoginPage(ct);
		uRegist=new UserRegistPage(ct);
		passport_login.removeAllViews();
		passport_regist.removeAllViews();
		passport_login.addView(uLogin.getRootView());
		passport_regist.addView(uRegist.getRootView());
	}
	public void SwitchRegist(){
		passport_regist.startAnimation(Slide_in);
		passport_regist.setVisibility(View.VISIBLE);
	}
	public void CloseRegist(){
		passport_regist.startAnimation(Slide_out);
		passport_regist.setVisibility(View.INVISIBLE);
	}
	@Override
	public void onBackPressed() {
		if (View.VISIBLE==passport_regist.getVisibility()) {
			CloseRegist();
		}else {
			finish();
		}
	}
}
