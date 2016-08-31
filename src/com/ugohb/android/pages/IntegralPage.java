package com.ugohb.android.pages;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.ugohb.android.R;
import com.ugohb.android.constant.Site;
import com.ugohb.android.domain.IntegralJson;
import com.ugohb.android.ui.ErrorView;
import com.ugohb.android.ui.ErrorView.ViewReloadListener;
import com.ugohb.android.utils.JqViewUtils;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class IntegralPage extends BasePage{
	
	public IntegralJson integral;
	ResultOfIntegralPage resultPage;
	
	public LinearLayout ll_page_container;
	ErrorView error;
	public Handler mhandler;
	public int Duration=2000;
	JqViewUtils JVU;

	public IntegralPage(Context ct) {
		super(ct);
	}

	@Override
	public View initView(LayoutInflater inflater) {
		View root=inflater.inflate(R.layout.page_integral, null);
		ll_page_container=(LinearLayout) root.findViewById(R.id.ll_page_container);
	
		initUI();
		
		loadData();
		return root;
	}
	public void initUI(){
		mhandler=new Handler();
		JVU=new JqViewUtils(ct, ll_page_container);
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
		http.send(HttpMethod.GET, Site.URL_INTEGRAL, new RequestCallBack<String>() {
			@Override
			public void onFailure(HttpException arg0, String msg) {
				Toast.makeText(ct, "数据更新失败", Toast.LENGTH_SHORT).show();
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
				analyseData(res.result);
			}
		});
	}

	protected void analyseData(String result) {
		Gson gson=new Gson();
		integral=gson.fromJson(result, IntegralJson.class);
		resultPage=new ResultOfIntegralPage(ct);
		resultPage.initList(integral.shops);
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
