package com.ugohb.android;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.ugohb.android.constant.Site;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class WebActivity extends Activity implements OnClickListener  {
	@ViewInject(R.id.webview)
	private WebView webview;
	@ViewInject(R.id.Progress_Bar)
	private ProgressBar Progress_Bar;
	/**
	 * title组件
	 */
	@ViewInject(R.id.web_title)
	private TextView web_title;
	@ViewInject(R.id.go_back)
	private ImageView go_back;
	@ViewInject(R.id.iShare)
	private ImageView iShare;

	private String URL;
	private String iShareUrl;
	protected Context ct;
	protected String atitle;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web);
		ViewUtils.inject(this);
		ct=WebActivity.this;
		atitle="优购淮北";
		initView();
		initData();
	}
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.go_back:
			finish();
			break;
		case R.id.iShare:
			break;
		default:
			break;
		}
	}
	void initView(){
		go_back.setOnClickListener(this);
		iShare.setOnClickListener(this);
	}
	@SuppressLint("NewApi")
	void initData(){
		try {
			URL=getIntent().getStringExtra(Site.URL);
			iShareUrl=URL;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(URL.isEmpty() && URL==""){
			URL="http://www.baidu.com";
		}
		loadHtml(webview, URL);
	}
	@SuppressLint("SetJavaScriptEnabled")
	void loadHtml(final WebView view, final String url){
		view.getSettings().setJavaScriptEnabled(true);
		view.setWebChromeClient(new WebChromeClient(){

			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				if(newProgress==100){
					Progress_Bar.setVisibility(View.GONE);
				}else{
					if(View.GONE==Progress_Bar.getVisibility()){
						Progress_Bar.setVisibility(View.VISIBLE);
					}
					Progress_Bar.setProgress(newProgress);
				}
				super.onProgressChanged(view, newProgress);
			}

			@Override
			public void onReceivedTitle(WebView view, String title) {
				// TODO Auto-generated method stub
				super.onReceivedTitle(view, title);
				atitle=title;
				web_title.setText(title);
			}
			
			
		});	
		view.loadUrl(url);
		//覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
		view.setWebViewClient(new WebViewClient(){
			 public boolean shouldOverrideUrlLoading(WebView view, String url) {
				 // TODO Auto-generated method stub
			   //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
			     view.loadUrl(url);
			    return true;
			 }
		   @Override
	        public void onReceivedError(WebView view, int errorCode,
	        		String description, String failingUrl) {
	        	super.onReceivedError(view, errorCode, description, failingUrl);
//erro
	        }
		});
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK && webview.canGoBack()) {
			
			webview.goBack();// 返回前一个页面
			return true;
		}else{
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}
}
