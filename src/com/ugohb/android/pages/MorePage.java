package com.ugohb.android.pages;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.ugohb.android.R;
import com.ugohb.android.WebActivity;
import com.ugohb.android.constant.Site;
import com.ugohb.android.ui.AlertDialog;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MorePage extends BasePage implements OnClickListener{

	public TextView tv_play_integral;
	public TextView tv_follow_wechat;
	public TextView tv_ishare;
	public TextView tv_suggest_to;
	public LinearLayout ll_soft_update;
	public boolean isChecking=false;
	public softInfo newsoft;
	
	public MorePage(Context ct) {
		super(ct);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View initView(LayoutInflater inflater) {
		View root=inflater.inflate(R.layout.page_more, null);
		
		tv_play_integral=(TextView) root.findViewById(R.id.tv_play_integral);
		tv_follow_wechat=(TextView) root.findViewById(R.id.tv_follow_wechat);
		tv_ishare=(TextView) root.findViewById(R.id.tv_ishare);
		tv_suggest_to=(TextView) root.findViewById(R.id.tv_suggest_to);
		ll_soft_update=(LinearLayout) root.findViewById(R.id.ll_soft_update);
		
		
		Listen();
		
		
		return root;
	}

	@Override
	public void initData() {
		
	}
	
	
	/**
	 * 监听处理
	 */
	public void Listen(){
		tv_play_integral.setOnClickListener(this);
		tv_follow_wechat.setOnClickListener(this);
		tv_ishare.setOnClickListener(this);
		tv_suggest_to.setOnClickListener(this);
		ll_soft_update.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_play_integral:			
			//玩转积分
			Intent play=new Intent();
			play.setClass(ct, WebActivity.class);
			play.putExtra(Site.URL, Site.URL_TEST);
			ct.startActivity(play);
			break;
		case R.id.tv_follow_wechat:			
			//关注微信
			Intent follow=new Intent();
			follow.setClass(ct, WebActivity.class);
			follow.putExtra(Site.URL, Site.URL_TEST);
			ct.startActivity(follow);
			break;	
		case R.id.tv_ishare:
			//推荐给朋友
			Toast.makeText(ct, "分享给朋友", Toast.LENGTH_SHORT).show();
			break;	
		case R.id.tv_suggest_to:
			//意见反馈
			Intent suggest=new Intent();
			suggest.setClass(ct, WebActivity.class);
			suggest.putExtra(Site.URL, Site.URL_TEST);
			ct.startActivity(suggest);
			break;
		case R.id.ll_soft_update:
			if (!isChecking) {
				//软件更新
				updateSoft();
			}else{
				Toast.makeText(ct, "正在为您努力查询中……", Toast.LENGTH_SHORT).show();
			}
			break;
		default:
			break;
		}		
	}
	/**
	 * 软件更新检查
	 */
	public void updateSoft(){
		isChecking=true;
		HttpUtils http=new HttpUtils();
		http.send(HttpMethod.GET, Site.URL_SOFT, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String msg) {
				isChecking=false;
				
				AlertDialog soft=new AlertDialog(ct);
				soft.builder();
				String ms="呜呜呜~貌似网络出错了";
				soft.setMsg(ms);
				soft.setPositiveButton("确认", new OnClickListener() {
					@Override
					public void onClick(View v) {
						
					}
				 });
				soft.show();
			}
			@Override
			public void onSuccess(ResponseInfo<String> res) {
				isChecking=false;
				
				Gson gson=new Gson();
				newsoft=gson.fromJson(res.result, softInfo.class);
				softDialog(newsoft);
			}
		});	
	}
	public class softInfo{
		public String status;
		public String softVersion;
		public String softMsg;
		public String softUrl;
	}
	
	public void softDialog(final softInfo msg){
		AlertDialog soft=new AlertDialog(ct);
		soft.builder();
		soft.setTitle("软件更新");
		String ms=msg.softMsg;
		if (msg.softVersion!=null&&msg.status.equals("1")) {
			ms=msg.softMsg+msg.softVersion;
		}
		soft.setMsg(ms);
		soft.setNegativeButton("取消", new OnClickListener() {			
			@Override
			public void onClick(View v) {
				
			}
		});
		if (msg.status.equals("1")) {
			 soft.setPositiveButton("确认", new OnClickListener() {
				@Override
				public void onClick(View v) {
					goToUpdate(msg.softUrl);	
				}
			 });
		 }
		soft.show();
	}

	

	protected void goToUpdate(String url) {
		Toast.makeText(ct, "开始为您更新软件", Toast.LENGTH_SHORT).show();
	}


}
