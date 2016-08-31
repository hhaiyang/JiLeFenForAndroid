package com.ugohb.android.pages;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.ugohb.android.R;
import com.ugohb.android.constant.Site;
import com.ugohb.android.domain.HomeJson;
import com.ugohb.android.ui.ErrorView;
import com.ugohb.android.ui.ErrorView.ViewReloadListener;
import com.ugohb.android.ui.LoadingProgressView;
import com.ugohb.android.utils.JqViewUtils;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.Toast;

public class IndexPage extends BasePage{
	
	public LinearLayout ll_container;
	ErrorView error;
	public View root;
	public ResultOfIndexPage resultPage;	
	private HomeJson home;
	public Handler mhandler;
	public int Duration=2000;
	JqViewUtils JVU;
	
	public IndexPage(Context ct) {
		super(ct);
	}

	@Override
	public View initView(LayoutInflater inflater) {
		root=inflater.inflate(R.layout.page_index, null);
		mhandler=new Handler();
		initUI();
		loadData();
		return root;
	}
	public void initUI(){		
		ll_container=(LinearLayout) root.findViewById(R.id.ll_container);
		JVU=new JqViewUtils(ct, ll_container);
		JVU.ShowLoading();
		
		error=new ErrorView(ct);
		error.setOnViewReloadListener(new ViewReloadListener() {			
			@Override
			public void onReload() {
				JVU.ShowLoading();
				loadData();
			}
		});		
	}
	private void loadData() {
		HttpUtils http=new HttpUtils();
		http.send(HttpMethod.GET, Site.URL_HOME, new RequestCallBack<String>() {
			@Override
			public void onFailure(HttpException arg0, String msg) {
				mhandler.postDelayed(new Runnable() {					
					@Override
					public void run() {
						JVU.setView(error.getRootView());
						JVU.SwitchPage();
					}
				}, Duration);
			}
			@Override
			public void onSuccess(ResponseInfo<String> res) {
				Toast.makeText(ct, "数据更新成功", Toast.LENGTH_SHORT).show();
				analyseData(res.result);
			}
		});		
	}

	private void analyseData(String result) {
		Gson gson=new Gson();
		home=gson.fromJson(result, HomeJson.class);
		resultPage=new ResultOfIndexPage(ct);
		resultPage.initFlash(home.flash);//初始化flash
		resultPage.initChannel(home.channel);
		resultPage.initTop(home.hot);
		mhandler.postDelayed(new Runnable() {			
			@Override
			public void run() {
				JVU.setView(resultPage.getRootView());
				JVU.SwitchPage();
			}
		}, Duration);
		
	}
	@Override
	public void initData() {
		
	}
}
