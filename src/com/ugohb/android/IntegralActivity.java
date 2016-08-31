package com.ugohb.android;

import java.util.ArrayList;

import com.ugohb.android.pages.AddJiFenPager;
import com.ugohb.android.pages.BasePage;
import com.ugohb.android.pages.DefaultPager;
import com.ugohb.android.pages.DetialJiFenPager;
import com.ugohb.android.view.NoScrollViewPager;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

public class IntegralActivity extends Activity implements OnClickListener {
	private ImageView iv_add_jifen,iv_detial_jifen;
	
	private NoScrollViewPager viewpager;
	private ArrayList<BasePage> basePagers;

	private DetialJiFenPager detialjifenpager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_int);
		initUI();
		initPager();
		initListener();
	}


	private void initUI() {
		iv_add_jifen=(ImageView) findViewById(R.id.iv_add_jifen);
		iv_detial_jifen=(ImageView) findViewById(R.id.iv_detial_jifen);
		viewpager=(NoScrollViewPager) findViewById(R.id.viewpager);
	}
	private void initListener() {
		iv_add_jifen.setOnClickListener(this);
		iv_detial_jifen.setOnClickListener(this);
	}


	private void initPager() {
		//初始化viewpager的界面
		basePagers = new ArrayList<BasePage>();
		basePagers.add(new DefaultPager(this));
		basePagers.add(new AddJiFenPager(this));
		detialjifenpager = new DetialJiFenPager(this);
		basePagers.add(detialjifenpager);
		
		//填充adapter
		viewpager.setAdapter(new JiFenPagerAdapter());
		
	}

	class JiFenPagerAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			return basePagers.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0==arg1;
		}
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			BasePage basePager = basePagers.get(position);
			container.addView(basePager.getRootView());
			return basePager.getRootView();
		}
		
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}
		
	}

	/**
	 * 点击事件的监听
	 */
	public void onClick(View v) {
		if(detialjifenpager.popupWindow!=null){
			detialjifenpager.popupWindow.dismiss();
		}
		switch (v.getId()) {
		case R.id.iv_add_jifen:
			setImageView(0);
			break;
		case R.id.iv_detial_jifen:
			setImageView(1);
			break;
		}
		
		
	}

	/**
	 * 设置imageview的显示
	 * @param position
	 */
	private void setImageView(int position) {

		if(position==0){
			iv_add_jifen.setImageResource(R.drawable.add_jifen_press);
			iv_add_jifen.setBackgroundColor(0xffe32643);
			iv_detial_jifen.setImageResource(R.drawable.detial_jifen_normal);
			iv_detial_jifen.setBackgroundColor(Color.TRANSPARENT);
			viewpager.setCurrentItem(position+1);
		}else if(position==1){
			iv_add_jifen.setImageResource(R.drawable.add_jifen_normal);
			iv_detial_jifen.setImageResource(R.drawable.detial_jifen_press);
			iv_detial_jifen.setBackgroundColor(0xffe32643);
			iv_add_jifen.setBackgroundColor(Color.TRANSPARENT);
			viewpager.setCurrentItem(position+1);
		}
	}

	/**
	 * 返回键的监听
	 */
	@Override
	public void onBackPressed() {
		if(detialjifenpager.isPopShowing()){
			detialjifenpager.popupWindow.dismiss();
		}else{
			super.onBackPressed();
		}
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		return super.onKeyDown(keyCode, event);
	}
	
	

}
