package com.ugohb.android.utils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;

import com.ugohb.android.R;
import com.ugohb.android.ui.LoadingProgressView;

public class JqViewUtils {
	ViewGroup stage;
	View view;
	LoadingProgressView loading;
	Animation fade_out;
	Animation fade_in;
	Animation Slide_in;
	Animation Slide_out;
	AnimationListener animListener;
	AnimationListener SlideListener;
	public Context ct;	
	
	public JqViewUtils(Context ctx,ViewGroup container) {
		this.ct=ctx;
		this.stage=container;
		initUI();
	}
	public void setView(View view) {
		this.view = view;
	}
	public void ShowLoading(){
		if (loading==null) {
			loading=new LoadingProgressView(ct, "正在加载中", R.anim.loading_dialog_frame);
		}
		this.setView(loading.getRootView());
		this.SwitchPage();
	}
	public void SwitchPage(){
		if (stage.getChildAt(0)!=null) {
			stage.getChildAt(0).startAnimation(fade_out);			
		}else {
			ChangePage(stage, view,fade_in);
		}
	}

	public void SwitchView(){
		if (stage.getChildAt(0)!=null) {
			stage.getChildAt(0).startAnimation(Slide_out);
		}else {
			ChangePage(stage, view,Slide_in);
		}
	}
	/**
	 * 切换界面/fade
	 * @author JQ
	 * @param parent
	 * @param child
	 */
	protected void ChangePage(ViewGroup parent,View child,Animation amin){		
		if (child!=null) {
			parent.removeAllViews();
			parent.addView(child);
			child.startAnimation(amin);
		}
	}
	/**
	 * 初始化；
	 * @author JQ
	 */
	protected void initUI(){
		animListener=new AnimationListener() {			
			@Override
			public void onAnimationStart(Animation animation) {}			
			@Override
			public void onAnimationRepeat(Animation animation) {}			
			@Override
			public void onAnimationEnd(Animation animation) {
				ChangePage(stage, view,fade_in);
			}
		};
		SlideListener=new AnimationListener() {			
			@Override
			public void onAnimationStart(Animation animation) {}			
			@Override
			public void onAnimationRepeat(Animation animation) {}			
			@Override
			public void onAnimationEnd(Animation animation) {
				ChangePage(stage, view,Slide_in);
			}
		};
		fade_in=AnimationUtils.loadAnimation(ct, R.anim.fade_in);
		fade_out=AnimationUtils.loadAnimation(ct, R.anim.fade_out);
		fade_out.setAnimationListener(animListener);
		
		Slide_in=AnimationUtils.loadAnimation(ct, R.anim.slide_up);
		Slide_out=AnimationUtils.loadAnimation(ct, R.anim.slide_out);
		Slide_out.setAnimationListener(SlideListener);
	}

}
