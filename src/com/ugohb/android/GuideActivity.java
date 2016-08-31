package com.ugohb.android;

import java.util.ArrayList;

import com.ugohb.android.constant.Config;
import com.ugohb.android.utils.SPUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class GuideActivity extends Activity {
	private static final int[] mImageIds = new int[] { R.drawable.slide_01,
		R.drawable.slide_02, R.drawable.slide_01 };
	private ViewPager vpGuide;
	private ArrayList<ImageView> mImageViewList;
	private Button btnStart;// 开始体验
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide);
		initGuide();
	}
	
	void initGuide(){
		//初始化引导页
		vpGuide = (ViewPager) findViewById(R.id.vp_guide);
		btnStart = (Button) findViewById(R.id.btn_start);
		btnStart.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				jumpHome();
			}
		});
		
		mImageViewList = new ArrayList<ImageView>();
		// 初始化引导页的3个页面
		for (int i = 0; i < mImageIds.length; i++) {
			ImageView image = new ImageView(this);
			image.setBackgroundResource(mImageIds[i]);// 设置引导页背景
			mImageViewList.add(image);
		}
		vpGuide.setAdapter(new GuideAdapter());
		vpGuide.setOnPageChangeListener(new GuidePageListener());
	}
	void jumpHome(){
		// 更新sp, 表示已经展示了新手引导
		SPUtils.setSPboolean(GuideActivity.this,Config.isGuide, true);
		// 跳转主页面
		startActivity(new Intent(GuideActivity.this, HomeActivity.class));
		finish();
	}
	/**
	 * ViewPager数据适配器 
	 */
	class GuideAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return mImageIds.length;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(mImageViewList.get(position));
			return mImageViewList.get(position);
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}
	}
	/**
	 * viewpager的滑动监听
	 */
	class GuidePageListener implements OnPageChangeListener{
		// 滑动状态发生变化
		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
			
		}
		// 滑动事件
		@Override
		public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
			
		}
		// 某个页面被选中
		@Override
		public void onPageSelected(int position) {
			if(position==mImageIds.length -1){
				btnStart.setVisibility(View.VISIBLE);
			}else {
				btnStart.setVisibility(View.INVISIBLE);
			}
		}
		
	}

	
}
