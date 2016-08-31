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
	// ����ײ��Ĳ˵���ťid
	private int[] bt_menu_id = { R.id.iv_menu_0, R.id.iv_menu_1, R.id.iv_menu_2, R.id.iv_menu_3 };
	// ����ײ���ѡ�в˵���ť��Դ
	private int[] select_on = { R.drawable.menu_home_hover, R.drawable.menu_user_hover,
			R.drawable.menu_ptp_hover, R.drawable.menu_more_hover };
	// ����ײ���δѡ�в˵���ť��Դ
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

	//��ʼ��page
	void initView(){
		vp_home=(ViewPager) findViewById(R.id.vp_home);			
		vp_home.setAdapter(adapter);
		vp_home.setOnPageChangeListener(new OnPageChangeListener() {
			
			public void onPageSelected(int position) {				
				BasePage page = list.get(position);
				page.initData();
				currentMenuID=position;
				aMenu(currentMenuID);// ���ð�ť��ѡ�к�δѡ����Դ
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
	// ��ʼ�����
	private void initMenuView() {
		// �ҵ��ײ��˵��İ�ť�����ü���
		for (int i = 0; i < bt_menu.length; i++) {
			bt_menu[i] = (ImageView) findViewById(bt_menu_id[i]);
			bt_menu[i].setOnClickListener(this);
		}
		// ����Ĭ����ҳΪ���ʱ��ͼƬ
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
		// ���ð�ť��ѡ�к�δѡ����Դ
		SwitchMenu(currentMenuID);
	}
	/**
	 * �˵��л�
	 */
	public void SwitchMenu(int pos){
		vp_home.setCurrentItem(pos, false);
		aMenu(pos);
	}
	/**
	 * �ײ��˵�
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
		exit.setMsg("��Ҫ�˳�������");
		exit.setPositiveButton("�˳�", new OnClickListener() {
			@Override
			public void onClick(View v) {
				// �����ȷ�ϡ���Ĳ��� 
              HomeActivity.this.finish(); 
			}
		});
		exit.setNegativeButton("ȡ��", new OnClickListener() {
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
