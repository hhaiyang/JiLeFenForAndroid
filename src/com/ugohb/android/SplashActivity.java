package com.ugohb.android;

import com.ugohb.android.constant.Config;
import com.ugohb.android.utils.JqCommonUtils;
import com.ugohb.android.utils.SPUtils;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

public class SplashActivity extends Activity {
	ImageView splash_main;
	int DURATION=2000;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		  //全屏            
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,                
                                       WindowManager.LayoutParams. FLAG_FULLSCREEN);  
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		init();
	}
	
	void init(){
		splash_main=(ImageView) findViewById(R.id.splash_main);
		AlphaAnimation anim=new AlphaAnimation(0.1f,1.0f);
		anim.setDuration(DURATION);		
		splash_main.startAnimation(anim);
		anim.setAnimationListener(an);
	}	
	
	/**
	 * 动画监听
	 */
	private AnimationListener an=new AnimationListener() {	
		@Override
		public void onAnimationStart(Animation animation) {}
		
		@Override
		public void onAnimationRepeat(Animation animation) {}
		
		@Override
		public void onAnimationEnd(Animation animation) {
			JqCommonUtils.delay(DURATION);//延迟1.5s
			NextActivity();
		}
	};

	/**
	 * 下一个界面
	 */
	private void NextActivity() {
		boolean userGuide=SPUtils.getSPboolen(this,Config.isGuide, false);
		if(!userGuide){
			// 跳转到新手引导页
			startActivity(new Intent(SplashActivity.this, GuideActivity.class));
		} else {
			//跳转主页
			startActivity(new Intent(SplashActivity.this, HomeActivity.class));
		}
		finish();
	}
}
