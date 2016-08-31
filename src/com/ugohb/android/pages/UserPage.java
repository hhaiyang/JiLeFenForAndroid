package com.ugohb.android.pages;

import com.ugohb.android.IntegralActivity;
import com.ugohb.android.PassportActivity;
import com.ugohb.android.R;
import com.ugohb.android.UserCenterActivity;
import com.ugohb.android.constant.Config;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class UserPage extends BasePage{
	View root;
	Button btn_user_login;
	UserHolder uHolder;
	String UserId;
	public static UserPage up;
	public UserPage(Context ct) {		
		super(ct);
	}

	@Override
	public View initView(LayoutInflater inflater) {
		up=this;
		root=inflater.inflate(R.layout.page_user, null);
		uHolder=new UserHolder();
		initUI();
		initListen();
		return root;
	}
	public void initUI(){
		btn_user_login=(Button) root.findViewById(R.id.btn_user_login);
		
		uHolder.rl_my_integral=(RelativeLayout) root.findViewById(R.id.rl_my_integral);
		uHolder.rl_my_notice=(RelativeLayout) root.findViewById(R.id.rl_my_notice);
		uHolder.rl_my_relate=(RelativeLayout) root.findViewById(R.id.rl_my_relate);
		uHolder.tv_user_contact=(TextView) root.findViewById(R.id.tv_user_contact);
		uHolder.tv_user_updPwd=(TextView) root.findViewById(R.id.tv_user_updPwd);
		uHolder.ll_logined=(LinearLayout) root.findViewById(R.id.ll_logined);
		uHolder.ll_unlogin=(LinearLayout) root.findViewById(R.id.ll_unlogin);
	}
	public void initListen(){
		btn_user_login.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				Intent login=new Intent(ct, PassportActivity.class);
				ct.startActivity(login);
			}
		});
		uHolder.rl_my_integral.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				//我的积分
				if (isLogin()) {
					Intent integral=new Intent(ct,IntegralActivity.class);
					ct.startActivity(integral);
				}
			}
		});
		uHolder.rl_my_notice.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				//我的关注
				if (isLogin()) {
					Intent UcNotice=new Intent();
					UcNotice.setClass(ct, UserCenterActivity.class);
					UcNotice.putExtra(Config.USER_CENTER, Config.USER_NOTICE);
					ct.startActivity(UcNotice);
				}
			}
		});
		uHolder.rl_my_relate.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				//关联账号
				if (isLogin()) {
					Intent UcRelate=new Intent();
					UcRelate.setClass(ct, UserCenterActivity.class);
					UcRelate.putExtra(Config.USER_CENTER, Config.USER_RELATE);
					ct.startActivity(UcRelate);
				}
			}
		});
		uHolder.tv_user_contact.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				//联系客服
			}
		});
		uHolder.tv_user_updPwd.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				//修改密码
				if (isLogin()) {
					Intent UcUpdPwd=new Intent();
					UcUpdPwd.setClass(ct, UserCenterActivity.class);
					UcUpdPwd.putExtra(Config.USER_CENTER, Config.USER_UPDPWD);
					ct.startActivity(UcUpdPwd);
				}
			}
		});		
	}
	public boolean isLogin(){
		if (UserId==null) {
			return false;
		}else{
			return true;
		}
	}
	public void setLogined(String uid){
		UserId=uid;
		if (View.INVISIBLE==uHolder.ll_logined.getVisibility()) {
			uHolder.ll_logined.setVisibility(View.VISIBLE);
		}
		uHolder.ll_unlogin.setVisibility(View.INVISIBLE);
	}
	
	@Override
	public void initData() {}
	public class UserHolder{
		public RelativeLayout rl_my_integral;
		public RelativeLayout rl_my_notice;
		public RelativeLayout rl_my_relate;
		public TextView tv_user_contact;
		public TextView tv_user_updPwd;
		public LinearLayout ll_logined;
		public LinearLayout ll_unlogin;
	}

	
}
