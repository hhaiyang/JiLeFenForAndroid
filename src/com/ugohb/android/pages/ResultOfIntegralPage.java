package com.ugohb.android.pages;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.ugohb.android.DuiActivity;
import com.ugohb.android.IntActivity;
import com.ugohb.android.R;
import com.ugohb.android.domain.IntegralJson;
import com.ugohb.android.domain.IntegralJson.shopItem;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ResultOfIntegralPage extends BasePage{
	public ListView integral_list;
	
	public View top_banner;
	public View view_all_shop;
	public IntegralJson integral;
	public HolderView topHolder;
	public ResultOfIntegralPage(Context ct) {
		super(ct);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View initView(LayoutInflater inflater) {
		View root=inflater.inflate(R.layout.page_integral_result, null);
		integral_list=(ListView) root.findViewById(R.id.integral_list);
		
		top_banner=inflater.inflate(R.layout.view_daily, null);
		view_all_shop=inflater.inflate(R.layout.view_all_shop, null);
		initTopBanner();
		
		integral_list.addHeaderView(top_banner);
		integral_list.addHeaderView(view_all_shop);		
		return root;
	}
	private void initTopBanner() {
		topHolder=new HolderView();
		topHolder.top1=(RelativeLayout) top_banner.findViewById(R.id.rl_int_top1);
		topHolder.top2=(RelativeLayout) top_banner.findViewById(R.id.rl_int_top2);
		topHolder.top3=(RelativeLayout) top_banner.findViewById(R.id.rl_int_top3);

		topHolder.top1.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				Intent ints=new Intent();
				ints.setClass(ct, IntActivity.class);
				ct.startActivity(ints);
			}
		});
		topHolder.top2.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				
			}
		});
		topHolder.top3.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				Intent dui=new Intent();
				dui.setClass(ct, DuiActivity.class);
				ct.startActivity(dui);
			}
		});
	}
	public class HolderView{
		public RelativeLayout top1;
		public RelativeLayout top2;
		public RelativeLayout top3;
	}


	protected void analyseData(String result) {
		Gson gson=new Gson();
		integral=gson.fromJson(result, IntegralJson.class);
		initList(integral.shops);
	}

	public void initList(ArrayList<shopItem> shops) {		
		integral_list.setAdapter(new listAdapter(shops, ct));
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		
	}
	class listAdapter extends BaseAdapter{
		ArrayList<shopItem> shops;
		Context ctx;
		BitmapUtils but;

		public listAdapter(ArrayList<shopItem> shops, Context ct) {
			super();
			this.shops = shops;
			this.ctx = ct;
			but=new BitmapUtils(ctx);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return shops.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			integralHolder holder=null;
			if(convertView==null){
				holder=new integralHolder();
				convertView=View.inflate(ctx, R.layout.integral_list_item, null);
				holder.iv_integral_thumb=(ImageView) convertView.findViewById(R.id.iv_integral_thumb);
				holder.tv_integral_title=(TextView) convertView.findViewById(R.id.tv_integral_title);
				holder.tv_integral_brief=(TextView) convertView.findViewById(R.id.tv_integral_brief);
				holder.tv_integral_price=(TextView) convertView.findViewById(R.id.tv_integral_price);
				holder.tv_integral_cost=(TextView) convertView.findViewById(R.id.tv_integral_cost);
				holder.tv_integral_get=(TextView) convertView.findViewById(R.id.tv_integral_get);
				convertView.setTag(holder);
			}else{
				holder=(integralHolder) convertView.getTag();
			}
			shopItem shop=shops.get(position);
			but.display(holder.iv_integral_thumb, shop.thumb);
			holder.tv_integral_title.setText(shop.title);
			holder.tv_integral_brief.setText(shop.brief);
			holder.tv_integral_price.setText(Html.fromHtml(shop.price));
			holder.tv_integral_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
			holder.tv_integral_cost.setText(shop.cost);
			
			return convertView;
		}
		
	}
	public class integralHolder{
		public ImageView iv_integral_thumb;
		public TextView tv_integral_title;
		public TextView tv_integral_brief;
		public TextView tv_integral_price;
		public TextView tv_integral_cost;
		public TextView tv_integral_get;
	}

	
}
