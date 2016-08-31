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
		  //ȫ��            
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
	 * ��������
	 */
	private AnimationListener an=new AnimationListener() {	
		@Override
		public void onAnimationStart(Animation animation) {}
		
		@Override
		public void onAnimationRepeat(Animation animation) {}
		
		@Override
		public void onAnimationEnd(Animation animation) {
			JqCommonUtils.delay(DURATION);//�ӳ�1.5s
			NextActivity();
		}
	};

	/**
	 * ��һ������
	 */
	private void NextActivity() {
		boolean userGuide=SPUtils.getSPboolen(this,Config.isGuide, false);
		if(!userGuide){
			// ��ת����������ҳ
			startActivity(new Intent(SplashActivity.this, GuideActivity.class));
		} else {
			//��ת��ҳ
			startActivity(new Intent(SplashActivity.this, HomeActivity.class));
		}
		finish();
	}
}
