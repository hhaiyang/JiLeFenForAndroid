package com.ugohb.android.pages;

import com.ugohb.android.HomeActivity;
import com.ugohb.android.PassportActivity;
import com.ugohb.android.R;
import com.ugohb.android.ui.AlertDialog;
import com.ugohb.android.ui.LoadingDialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class UserLoginPage extends BasePage{
	View root;
	UserLoginHolder uHolder;
	ImageView iv_back;
	String UserTel;
	String UserPwd;
	public UserLoginPage(Context ct) {
		super(ct);
	}

	@Override
	public View initView(LayoutInflater inflater) {
		uHolder=new UserLoginHolder();
		root=inflater.inflate(R.layout.page_user_login, null);
		initUI();
		initListen();
		return root;
	}
	public void initUI(){
		iv_back=(ImageView) root.findViewById(R.id.iv_back);
		uHolder.btn_go_register=(Button) root.findViewById(R.id.btn_go_register);
		uHolder.btn_login_submit=(Button) root.findViewById(R.id.btn_login_submit);
		uHolder.user_tel=(EditText) root.findViewById(R.id.user_tel);
		uHolder.user_pwd=(EditText) root.findViewById(R.id.user_pwd);
		uHolder.cb_memory=(CheckBox) root.findViewById(R.id.cb_memory);
		uHolder.tv_user_forget=(TextView) root.findViewById(R.id.tv_user_forget);
	}
	public void initListen(){
		iv_back.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				((PassportActivity)ct).finish();
			}
		});
		uHolder.btn_go_register.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				((PassportActivity)ct).SwitchRegist();
			}
		});
		uHolder.btn_login_submit.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				//1.跳出加载蒙层
				LoadingDialog login=new LoadingDialog(ct);
				login.show();
				//2.开始校验
				UserTel=uHolder.user_tel.getText().toString().trim();
				UserPwd=uHolder.user_pwd.getText().toString().trim();
				if (UserTel.equals("")||UserTel=="") {
					AlertDialog loginMsgAlert=new AlertDialog(ct);
					loginMsgAlert.builder();
					loginMsgAlert.setMsg("请输入账户");
					loginMsgAlert.setPositiveButton("确定", new OnClickListener() {
						@Override
						public void onClick(View v) {
							// 点击“确认”后的操作 
						}
					});
					
					login.dismiss();
					loginMsgAlert.show();
				}else if(UserPwd.equals("")||UserPwd==""){
					AlertDialog PwdMsgAlert=new AlertDialog(ct);
					PwdMsgAlert.builder();
					PwdMsgAlert.setMsg("请输入密码");
					PwdMsgAlert.setPositiveButton("确定", new OnClickListener() {
						@Override
						public void onClick(View v) {
							// 点击“确认”后的操作 
						}
					});					
					login.dismiss();
					PwdMsgAlert.show();
				}else{
					//校验，临时处理
					if (UserTel.equals(UserPwd)) {
						//登录成功操作
						UserPage.up.setLogined(UserTel);
						((PassportActivity)ct).finish();
					}else{
						//失败操作
						AlertDialog ErrorMsgAlert=new AlertDialog(ct);
						ErrorMsgAlert.builder();
						ErrorMsgAlert.setMsg("账户或密码不正确");
						ErrorMsgAlert.setPositiveButton("确定", new OnClickListener() {
							@Override
							public void onClick(View v) {
								// 点击“确认”后的操作 
							}
						});					
						login.dismiss();
						ErrorMsgAlert.show();
					}
				}
				
			}
		});
		
		
	}
	
	@Override
	public void initData() {}
	public class UserLoginHolder{
		public EditText user_tel;
		public EditText user_pwd;
		public CheckBox cb_memory;
		public TextView tv_user_forget;
		public Button btn_login_submit;
		public Button btn_go_register;
	}

	
}
