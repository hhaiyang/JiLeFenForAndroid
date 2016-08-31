package com.ugohb.android.ui;

import java.util.Timer;
import java.util.TimerTask;

import com.ugohb.android.R;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * @Description:�Զ���Ի���
 * @author http://blog.csdn.net/finddreams
 */
public class LoadingProgressView extends RelativeLayout {

	private AnimationDrawable mAnimation;
	private Context mContext;
	private ImageView mImageView;
	private String mLoadingTip;
	private TextView mLoadingTv;
	private int count = 0;
	private String oldLoadingTip;
	private int mResid;
	public View root;

	public LoadingProgressView(Context context, String content, int id) {
		super(context);
		this.mContext = context;
		this.mLoadingTip = content;
		this.mResid = id;
		initView();
	}


	private void initView() {
		root=View.inflate(mContext, R.layout.view_progress,null);
		mLoadingTv = (TextView) root.findViewById(R.id.loadingTv);
		mImageView = (ImageView) root.findViewById(R.id.loadingIv);
		initData();
	}
	public View getRootView(){
		return root;
	}
	private void initData() {

		mImageView.setBackgroundResource(mResid);
		// ͨ��ImageView�����õ�������ʾ��AnimationDrawable
		mAnimation = (AnimationDrawable) mImageView.getBackground();
		// Ϊ�˷�ֹ��onCreate������ֻ��ʾ��һ֡�Ľ������֮һ
		mImageView.post(new Runnable() {
			@Override
			public void run() {
				mAnimation.start();
			}
		});
		mLoadingTv.setText(mLoadingTip);

	}

	public void setContent(String str) {
		mLoadingTv.setText(str);
	}
}
