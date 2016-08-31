package com.ugohb.android.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

public class ParallaxScrollView extends ScrollView {
	public ParallaxScrollView(Context context) {
		super(context);
	}

	public ParallaxScrollView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public ParallaxScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	private ImageView mImage;
	private int mOriginalHeight;
	private int drawableHeight;
	/**
	 * ����ImageViewͼƬ, �õ�����
	 * @param mImage
	 */
	public void setParallaxImage(ImageView mImage) {
		this.mImage = mImage;
		mOriginalHeight = mImage.getHeight(); // ԭʼĬ�ϸ߶�
		drawableHeight = mImage.getDrawable().getIntrinsicHeight(); //ͼƬʵ�ʸ߶�
	}

	private LoacationListener loacationListener;
	public void setLoacationListener(LoacationListener loacationListener) {
		this.loacationListener = loacationListener;
	}
	public interface LoacationListener{
		public void currentLocation(int scrollX,int scrollY);
	}
	@SuppressLint("NewApi")
	@Override
	protected boolean overScrollBy(int deltaX, int deltaY, int scrollX,
			int scrollY, int scrollRangeX, int scrollRangeY,
			int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
		if(isTouchEvent && deltaY<0){
			
			if(mImage.getHeight()<=drawableHeight){
				int newHeight = (int) (mImage.getHeight() + Math.abs(deltaY / 3.0f));				
				// �߶Ȳ�����ͼƬ���߶�ʱ,��������Ч
				mImage.getLayoutParams().height = newHeight;
				mImage.requestLayout();
			}
		}		
		if (loacationListener!=null) {
			loacationListener.currentLocation(scrollX,scrollY);
		}
		return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX,
				scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
	}
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		switch(ev.getAction()){
		case MotionEvent.ACTION_UP:
				// ִ�лص�����
				// �ӵ�ǰ�߶�mImage.getHeight(), ִ�ж�����ԭʼ�߶�mOriginalHeight
				final int startHeight = mImage.getHeight();
				final int endHeight = mOriginalHeight;

				// ִ�лص�����, �Զ���Animation
				ResetAnimation animation=new ResetAnimation(mImage, startHeight, endHeight);
				startAnimation(animation);
			
			break;
	}
		return super.onTouchEvent(ev);
	}
	

}
