package com.ugohb.android.pages;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.ugohb.android.CateActivity;
import com.ugohb.android.ShopActivity;
import com.ugohb.android.HomeActivity;
import com.ugohb.android.ListActivity;
import com.ugohb.android.R;
import com.ugohb.android.SearchActivity;
import com.ugohb.android.WebActivity;
import com.ugohb.android.adapter.BannerGridViewAdapter;
import com.ugohb.android.adapter.MenuGridViewAdapter;
import com.ugohb.android.constant.Config;
import com.ugohb.android.constant.ImagesResource;
import com.ugohb.android.constant.Site;
import com.ugohb.android.domain.HomeJson;
import com.ugohb.android.domain.HomeJson.channelItem;
import com.ugohb.android.domain.HomeJson.fl;
import com.ugohb.android.domain.HomeJson.homeItem;
import com.ugohb.android.ui.ErrorView;
import com.ugohb.android.ui.LoadingProgressView;
import com.ugohb.android.utils.JqCommonUtils;
import com.ugohb.android.view.HotItemView;
import com.ugohb.android.view.ItemView;
import com.ugohb.android.view.RollViewPager;
import com.ugohb.android.view.RollViewPager.OnPagerClickCallback;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ResultOfIndexPage extends BasePage{
	public TextView tv_search_btn;
	/**
	 * flash
	 */
	private View top_flash; 
	private ArrayList<View> dotList;
	private LinearLayout dotLl;
	private TextView topTitle;
	private LinearLayout mViewPagerLay;	
	private RollViewPager mViewPager;
	private OnPagerClickCallback flash_listener;
	
	private ListView home_list;	
	public View root;

	/**
	 * menu_linner
	 */
	public View MENU_LINNER;
	MenuHolder menuHolder;
	
	/**
	 * banner_hot
	 */
	private View banner_hot; 
	HotItemHolder hotItemHolder;
	public OnItemClickListener MenuListen;
	public ResultOfIndexPage(Context ct) {
		super(ct);
	}

	@Override
	public View initView(LayoutInflater inflater) {
		root=inflater.inflate(R.layout.page_index_result, null);
		tv_search_btn=(TextView) root.findViewById(R.id.tv_search_btn);
		tv_search_btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent search=new Intent(ct,SearchActivity.class);
				ct.startActivity(search);
			}
		});

		home_list=(ListView) root.findViewById(R.id.home_list);
		
		MENU_LINNER=inflater.inflate(R.layout.menu_linner, null);
		initMenuLinner(MENU_LINNER);
		
		/*banner_hot */
		banner_hot=inflater.inflate(R.layout.banner_hot,null);
		hotItemHolder=new HotItemHolder();
		hotItemHolder.banner_hot_1=(LinearLayout) banner_hot.findViewById(R.id.banner_hot_1);
		hotItemHolder.banner_hot_2=(LinearLayout) banner_hot.findViewById(R.id.banner_hot_2);
		hotItemHolder.banner_hot_3=(LinearLayout) banner_hot.findViewById(R.id.banner_hot_3);
		hotItemHolder.banner_hot_4=(LinearLayout) banner_hot.findViewById(R.id.banner_hot_4);
		
		
		/*flash组件*/
		top_flash=inflater.inflate(R.layout.slider, null);
		dotLl=(LinearLayout) top_flash.findViewById(R.id.dots_ll);
		mViewPagerLay=(LinearLayout) top_flash.findViewById(R.id.top_viewpager);
		topTitle=(TextView) top_flash.findViewById(R.id.top_title);
		home_list.addHeaderView(top_flash);
		home_list.addHeaderView(MENU_LINNER);
		home_list.addHeaderView(banner_hot);
		return root;
	}

	@Override
	public void initData() {
		
	}

	/**
	 * 初始化频道
	 */
	void initChannel(ArrayList<channelItem> channel){
		home_list.setAdapter(new ListAdapter(channel, ct));
	}
	/**
	 * 初始化热门
	 */
	void initTop(final ArrayList<homeItem> hots){
		HotItemView hot0=new HotItemView(ct,hots.get(0));
		hotItemHolder.banner_hot_1.addView(hot0.getRootView());

		HotItemView hot1=new HotItemView(ct,hots.get(1));
		hotItemHolder.banner_hot_2.addView(hot1.getRootView());

		HotItemView hot2=new HotItemView(ct,hots.get(2));
		hotItemHolder.banner_hot_3.addView(hot2.getRootView());

		HotItemView hot3=new HotItemView(ct,hots.get(3));
		hotItemHolder.banner_hot_4.addView(hot3.getRootView());
		
		hotItemHolder.banner_hot_1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent hot=new Intent();
				hot.setClass(ct, ShopActivity.class);
				hot.putExtra(Config.DETAIL_FLAG, hots.get(0).id);
				ct.startActivity(hot);
			}
		});
		hotItemHolder.banner_hot_2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent hot=new Intent();
				hot.setClass(ct, ShopActivity.class);
				hot.putExtra(Config.DETAIL_FLAG, hots.get(1).id);
				ct.startActivity(hot);
			}
		});
		hotItemHolder.banner_hot_3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent hot=new Intent();
				hot.setClass(ct, ShopActivity.class);
				hot.putExtra(Config.DETAIL_FLAG, hots.get(2).id);
				ct.startActivity(hot);
			}
		});
		hotItemHolder.banner_hot_4.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent hot=new Intent();
				hot.setClass(ct, ShopActivity.class);
				hot.putExtra(Config.DETAIL_FLAG, hots.get(3).id);
				ct.startActivity(hot);
			}
		});
	}
	
	public class HotItemHolder{		
		public LinearLayout banner_hot_1;
		public LinearLayout banner_hot_2;
		public LinearLayout banner_hot_3;
		public LinearLayout banner_hot_4;
	}
		
	public void initMenuLinner(View menu){
		menuHolder=new MenuHolder();
		menuHolder.menu_ll_integral=(LinearLayout) menu.findViewById(R.id.menu_ll_integral);
		menuHolder.menu_ll_discount=(LinearLayout) menu.findViewById(R.id.menu_ll_discount);
		menuHolder.menu_ll_market=(LinearLayout) menu.findViewById(R.id.menu_ll_market);
		menuHolder.menu_ll_cate=(LinearLayout) menu.findViewById(R.id.menu_ll_cate);
		
		menuHolder.menu_ll_integral.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				//积分处理
				((HomeActivity)ct).SwitchMenu(2);
			}
		});
		menuHolder.menu_ll_discount.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				//打折
				Intent li= new Intent();
				li.setClass(ct, ListActivity.class);
				li.putExtra(Config.LI_FLAG, "1");
				ct.startActivity(li);
			}
		});
		menuHolder.menu_ll_market.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				//商超
				Intent market= new Intent();
				market.setClass(ct, ListActivity.class);
				market.putExtra(Config.LI_FLAG, "1");
				ct.startActivity(market);
			}
		});
		menuHolder.menu_ll_cate.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				//总分类
				Intent cate= new Intent();
				cate.setClass(ct, CateActivity.class);
				ct.startActivity(cate);
			}
		});
		
		
	}
	
	public class MenuHolder{		
		public LinearLayout menu_ll_integral;
		public LinearLayout menu_ll_discount;
		public LinearLayout menu_ll_market;
		public LinearLayout menu_ll_cate;
	}
	
	/**
	 * 幻灯片初始化
	 */
	void initFlash(final ArrayList<fl> flash){
		initDot(flash.size());
		flash_listener=new OnPagerClickCallback() {
			
			@Override
			public void onPagerClick(int position) {
			
				Intent fl=new Intent();
				fl.setClass(ct, WebActivity.class);
				fl.putExtra(Site.URL, flash.get(position).url);
				ct.startActivity(fl);
			}
		};
		mViewPager = new RollViewPager(ct, dotList,
				R.drawable.dot_focus, R.drawable.dot_normal,flash_listener);		
		
		mViewPager.setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT));
		ArrayList<String> imgSrcs=new ArrayList<String>();
		for (int i = 0; i < flash.size(); i++) {
			String imgSrc=flash.get(i).images;
			imgSrcs.add(imgSrc);
		}
		ArrayList<String> titles=new ArrayList<String>();
		for (int i = 0; i < flash.size(); i++) {
			titles.add(flash.get(i).title);
		}
		mViewPager.setTitle(topTitle, titles);
		mViewPager.setUriList(imgSrcs);
		mViewPager.startRoll();
		mViewPagerLay.removeAllViews();
		mViewPagerLay.addView(mViewPager);
	}
	private void initDot(int size) {
		dotList = new ArrayList<View>();
		dotLl.removeAllViews();
		for (int i = 0; i < size; i++) {
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					JqCommonUtils.dip2px(ct, 5), JqCommonUtils.dip2px(ct, 5));
			params.setMargins(15, 0, 15, 0);
			View m = new View(ct);
			if (i == 0) {
				m.setBackgroundResource(R.drawable.dot_focus);
			} else {
				m.setBackgroundResource(R.drawable.dot_normal);
			}
			m.setLayoutParams(params);
			dotLl.addView(m);
			dotList.add(m);
		}
	}
	class ListAdapter extends BaseAdapter{
		ArrayList<channelItem> channels;
		Context ctx;
		BitmapUtils but;
		public ListAdapter(ArrayList<channelItem> channels,Context ctx) {
			super();
			this.channels = channels;
			this.ctx=ctx;
			but=new BitmapUtils(ctx);
		}

		@Override
		public int getCount() {
			return channels.size();
		}

		
		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ChannelHolder holder=null;
			if(convertView==null){
				holder=new ChannelHolder();
				convertView=View.inflate(ctx, R.layout.home_banner, null);
				holder.tv_cname=(TextView) convertView.findViewById(R.id.tv_cname);
				holder.tv_get_more=(TextView) convertView.findViewById(R.id.tv_get_more);
				holder.home_lsit_first=(ImageView) convertView.findViewById(R.id.home_list_first);
				holder.home_list_second=(LinearLayout) convertView.findViewById(R.id.home_list_second);
				holder.home_list_third=(LinearLayout) convertView.findViewById(R.id.home_list_third);
				holder.home_lsit_first_int=(ImageView) convertView.findViewById(R.id.home_list_first_int);
				convertView.setTag(holder);
			}else{
				holder=(ChannelHolder) convertView.getTag();
			}
			final channelItem channel=channels.get(position);
			
			final ArrayList<homeItem> list=channel.list;
			
			final homeItem first=list.get(0);
			but.display(holder.home_lsit_first, first.thumb);
			holder.home_lsit_first_int.setImageResource(
					ImagesResource.Mark_ids[strToInt(first.integral)-2]);
			holder.home_lsit_first.setOnClickListener(new OnClickListener() {				
				@Override
				public void onClick(View v) {
					Intent dFirst=new Intent();
					dFirst.setClass(ctx, ShopActivity.class);
					dFirst.putExtra(Config.DETAIL_FLAG, first.id);
					ctx.startActivity(dFirst);
				}
			});
			
			ItemView second=new ItemView(ctx,list.get(1));
			holder.home_list_second.addView(second.getRootView());
			holder.home_list_second.setOnClickListener(new OnClickListener() {				
				@Override
				public void onClick(View v) {
					Intent dSecond=new Intent();
					dSecond.setClass(ctx, ShopActivity.class);
					dSecond.putExtra(Config.DETAIL_FLAG, list.get(1).id);
					ctx.startActivity(dSecond);
				}
			});
			ItemView third=new ItemView(ctx,list.get(2));
			holder.home_list_third.addView(third.getRootView());
			holder.home_list_third.setOnClickListener(new OnClickListener() {				
				@Override
				public void onClick(View v) {
					Intent Dthird=new Intent();
					Dthird.setClass(ctx, ShopActivity.class);
					Dthird.putExtra(Config.DETAIL_FLAG, list.get(2).id);
					ctx.startActivity(Dthird);
				}
			});
			
			
			
			holder.tv_cname.setText(channel.banner);
			holder.tv_get_more.setOnClickListener(new OnClickListener() {				
				@Override
				public void onClick(View v) {
					Intent channels=new Intent();
					channels.setClass(ctx,ListActivity.class);
					channels.putExtra(Config.LI_FLAG, channel.cid);
					ctx.startActivity(channels);
				}
			});
			
			
			return convertView;
		}
		
	}
	public class ChannelHolder{
		public TextView tv_cname;
		public TextView tv_get_more;
		
		public ImageView home_lsit_first;
		public ImageView home_lsit_first_int;
		public LinearLayout home_list_second;
		public LinearLayout home_list_third;
	}
	public int strToInt(String str){
		return Integer.parseInt(str);
	}
	
}
