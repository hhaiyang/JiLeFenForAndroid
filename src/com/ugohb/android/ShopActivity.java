package com.ugohb.android;

import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.ugohb.android.constant.ImagesResource;
import com.ugohb.android.constant.Site;
import com.ugohb.android.domain.DetailJson;
import com.ugohb.android.pages.ShoperInfoPage;
import com.ugohb.android.ui.ErrorView;
import com.ugohb.android.ui.ErrorView.ViewReloadListener;
import com.ugohb.android.ui.LoadingProgressDialog;
import com.ugohb.android.ui.ParallaxScrollView;
import com.ugohb.android.ui.ParallaxScrollView.LoacationListener;
import com.ugohb.android.utils.JqViewUtils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ShopActivity extends Activity {
	
	@ViewInject(R.id.ParallaxScroll)
	ParallaxScrollView ParallaxScroll;
	
	@ViewInject(R.id.fr_top_view)
	FrameLayout fr_top_view;
	@ViewInject(R.id.iv_details_main)
	ImageView iv_details_main;
	@ViewInject(R.id.iv_mark_int)
	ImageView iv_mark_int;
	
	@ViewInject(R.id.tv_shop_name)
	TextView tv_shop_name;
	@ViewInject(R.id.ll_details_titlebar_bg)
	LinearLayout ll_details_titlebar_bg;
	
	@ViewInject(R.id.tv_details_name)
	TextView tv_details_name;
	@ViewInject(R.id.iv_detail_back)
	ImageView iv_detail_back;	
	@ViewInject(R.id.iv_details_share)
	ImageView iv_details_share;
	ShoperInfoPage shoper;

	@ViewInject(R.id.ll_stage)
	LinearLayout ll_stage;
	JqViewUtils JVU;
	Context ct;
	DetailJson dj;
	private Handler mHandle;
	private BitmapUtils but;
	ErrorView error;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shop);
		ViewUtils.inject(this);
		ct=this;
		JVU=new JqViewUtils(ct, ll_stage);
		JVU.ShowLoading();
		initUI();
		initKit();
		getData();
	}
	public void getData(){
		HttpUtils http=new HttpUtils();
		http.send(HttpMethod.GET, Site.URL_DETAIL, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				JVU.setView(error.getRootView());
				JVU.SwitchPage();				
			}

			@Override
			public void onSuccess(ResponseInfo<String> res) {
				analyseData(res.result);
				
			}
		});
	}
	
	protected void analyseData(String result) {
		Gson gson=new Gson();
		dj=gson.fromJson(result, DetailJson.class);

		shoper.setShang(dj.shop);
		shoper.notifyChage();
		but.display(iv_details_main, dj.shop.img);
		
		iv_mark_int.setImageResource(
				ImagesResource.Shop_Int_ids[Integer.parseInt(dj.shop.ints)-1]);
		tv_shop_name.setText(dj.shop.name);
		
		mHandle.postDelayed(new Runnable() {			
			@Override
			public void run() {
				JVU.setView(shoper.getRootView());
				JVU.SwitchPage();
			}
		}, 2000);
	}
	public void initUI(){
		shoper=new ShoperInfoPage(ct);
		but=new BitmapUtils(ct);
		but.configDefaultLoadingImage(R.drawable.splash_hb);
		mHandle=new Handler();
		error=new ErrorView(ct);
		error.setOnViewReloadListener(new ViewReloadListener() {			
			@Override
			public void onReload() {
				getData();				
			}
		});
		
		
		iv_details_main.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {			
			@SuppressLint("NewApi")
			@Override
			public void onGlobalLayout() {
				ParallaxScroll.setParallaxImage(iv_details_main);
				iv_details_main.getViewTreeObserver().removeOnGlobalLayoutListener(this);				
			}
		});
		ParallaxScroll.setLoacationListener(new LoacationListener() {			
			@SuppressLint("NewApi")
			@Override
			public void currentLocation(int scrollX, int scrollY) {
				if (scrollY<iv_details_main.getHeight()) {
					float alpha=((float)scrollY)/((float)iv_details_main.getHeight());
					ll_details_titlebar_bg.setAlpha(alpha);
				}
				if (scrollY>iv_details_main.getHeight()) {
					if (dj!=null) {
						tv_details_name.setText(dj.shop.name);
					}
				}else{
					tv_details_name.setText("");
				}
			}
		});
	}
	public void initKit(){
		iv_detail_back.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		iv_details_share.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				Toast.makeText(ct, "·ÖÏí", Toast.LENGTH_SHORT).show();
			}
		});
	}
}
