package com.ugohb.android;

import java.util.ArrayList;
import java.util.List;

import com.ugohb.android.pages.BasePage;
import com.ugohb.android.pages.IndexPage;
import com.ugohb.android.pages.IntegralPage;
import com.ugohb.android.pages.MorePage;
import com.ugohb.android.pages.UserPage;
import com.ugohb.android.ui.AlertDialog;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class HomeActivity extends Activity implements OnClickListener{
	private ViewPager vp_home;
	List<BasePage> list = new ArrayList<BasePage>();
	private HomePageAdapter adapter;
	int currentMenuID=0;
	
	private ImageView[] bt_menu = new ImageView[4];
	// 界面底部的菜单按钮id
	private int[] bt_menu_id = { R.id.iv_menu_0, R.id.iv_menu_1, R.id.iv_menu_2, R.id.iv_menu_3 };
	// 界面底部的选中菜单按钮资源
	private int[] select_on = { R.drawable.menu_home_hover, R.drawable.menu_user_hover,
			R.drawable.menu_ptp_hover, R.drawable.menu_more_hover };
	// 界面底部的未选中菜单按钮资源
	private int[] select_off = { R.drawable.menu_home, R.drawable.menu_user,
			R.drawable.menu_ptp, R.drawable.menu_more };
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		initVpData();
		initMenuView();
		initView();
	}

	//初始化page
	void initView(){
		vp_home=(ViewPager) findViewById(R.id.vp_home);			
		vp_home.setAdapter(adapter);
		vp_home.setOnPageChangeListener(new OnPageChangeListener() {
			
			public void onPageSelected(int position) {				
				BasePage page = list.get(position);
				page.initData();
				currentMenuID=position;
				aMenu(currentMenuID);// 设置按钮的选中和未选中资源
			}
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {}
			public void onPageScrollStateChanged(int state) {}
		});
	}
	void initVpData(){
		list.add(new IndexPage(this));
		list.add(new UserPage(this));
		list.add(new IntegralPage(this));
		list.add(new MorePage(this));	
		adapter = new HomePageAdapter(this, list);
	}
	// 初始化组件
	private void initMenuView() {
		// 找到底部菜单的按钮并设置监听
		for (int i = 0; i < bt_menu.length; i++) {
			bt_menu[i] = (ImageView) findViewById(bt_menu_id[i]);
			bt_menu[i].setOnClickListener(this);
		}
		// 设置默认首页为点击时的图片
		aMenu(0);
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
			case R.id.iv_menu_0:
				currentMenuID=0;
				break;
			case R.id.iv_menu_1:
				currentMenuID=1;
				break;
			case R.id.iv_menu_2:
				currentMenuID=2;
				break;
			case R.id.iv_menu_3:
				currentMenuID=3;
				break;
		}
		// 设置按钮的选中和未选中资源
		SwitchMenu(currentMenuID);
	}
	/**
	 * 菜单切换
	 */
	public void SwitchMenu(int pos){
		vp_home.setCurrentItem(pos, false);
		aMenu(pos);
	}
	/**
	 * 底部菜单
	 * @param pos
	 */
	void aMenu(int pos){
		for (int i = 0; i < bt_menu.length; i++) {
			bt_menu[i].setImageResource(select_off[i]);
			if (pos == i) {
				bt_menu[i].setImageResource(select_on[i]);
			}
		}
	}
	
	class HomePageAdapter extends PagerAdapter {
		private Context ct;
		private List<BasePage> list;

		public HomePageAdapter(Context ct, List<BasePage> list) {
			this.ct = ct;
			this.list = list;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			((ViewPager) container).removeView(list.get(position)
					.getRootView());

		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			((ViewPager) container).addView(list.get(position)
					.getRootView(), 0);
			return list.get(position).getRootView();
		}

	}
	
	public void showExitDialog(){
		final AlertDialog exit=new AlertDialog(HomeActivity.this);
		exit.builder();
		exit.setMsg("你要退出程序吗？");
		exit.setPositiveButton("退出", new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 点击“确认”后的操作 
              HomeActivity.this.finish(); 
			}
		});
		exit.setNegativeButton("取消", new OnClickListener() {
			@Override
			public void onClick(View v) {
//				exit.setCancelable(true);
			}
		});
		exit.show();
		
	}
	public void onBackPressed() { 
		if (currentMenuID!=0) {
			SwitchMenu(0);
		}else {
			showExitDialog();
		} 
    }
	
	
}
