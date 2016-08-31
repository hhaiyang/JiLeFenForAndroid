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
	private Button btnStart;// ��ʼ����
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide);
		initGuide();
	}
	
	void initGuide(){
		//��ʼ������ҳ
		vpGuide = (ViewPager) findViewById(R.id.vp_guide);
		btnStart = (Button) findViewById(R.id.btn_start);
		btnStart.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				jumpHome();
			}
		});
		
		mImageViewList = new ArrayList<ImageView>();
		// ��ʼ������ҳ��3��ҳ��
		for (int i = 0; i < mImageIds.length; i++) {
			ImageView image = new ImageView(this);
			image.setBackgroundResource(mImageIds[i]);// ��������ҳ����
			mImageViewList.add(image);
		}
		vpGuide.setAdapter(new GuideAdapter());
		vpGuide.setOnPageChangeListener(new GuidePageListener());
	}
	void jumpHome(){
		// ����sp, ��ʾ�Ѿ�չʾ����������
		SPUtils.setSPboolean(GuideActivity.this,Config.isGuide, true);
		// ��ת��ҳ��
		startActivity(new Intent(GuideActivity.this, HomeActivity.class));
		finish();
	}
	/**
	 * ViewPager���������� 
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
	 * viewpager�Ļ�������
	 */
	class GuidePageListener implements OnPageChangeListener{
		// ����״̬�����仯
		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
			
		}
		// �����¼�
		@Override
		public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
			
		}
		// ĳ��ҳ�汻ѡ��
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
