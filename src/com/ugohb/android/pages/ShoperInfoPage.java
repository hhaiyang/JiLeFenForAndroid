package com.ugohb.android.pages;

import com.ugohb.android.MapActivity;
import com.ugohb.android.R;
import com.ugohb.android.domain.DetailJson.Shang;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ShoperInfoPage extends BasePage{
	
	public View root;
	private Shang shop;	
	ShopHolder shopHolder;
	public RelativeLayout rl_shop_stage;
	public ShopActivityPage shop_hot_info;
	public ShoperInfoPage(Context ct) {
		super(ct);
	}

	@Override
	public View initView(LayoutInflater inflater) {
		root=inflater.inflate(R.layout.view_shop_info, null);
		shopHolder=new ShopHolder();
		shop_hot_info=new ShopActivityPage(ct);
		initUI();
		initListener();
		
		rl_shop_stage.removeAllViews();
		rl_shop_stage.addView(shop_hot_info.getRootView());
		
		return root;
	}
	public void initUI(){	
		rl_shop_stage=(RelativeLayout) root.findViewById(R.id.rl_shop_stage);
		shopHolder.tv_tel_munber=(TextView) root.findViewById(R.id.tv_tel_munber);
		shopHolder.tv_details_call=(TextView) root.findViewById(R.id.tv_details_call);
		shopHolder.tv_address=(TextView) root.findViewById(R.id.tv_address);
		shopHolder.tv_map_address=(TextView) root.findViewById(R.id.tv_map_address);
		shopHolder.tv_shop_brief=(TextView) root.findViewById(R.id.tv_shop_brief);
		shopHolder.tv_shop_int_period_time=(TextView) root.findViewById(R.id.tv_shop_int_period_time);
		shopHolder.tv_shop_pay_attention=(TextView) root.findViewById(R.id.tv_shop_pay_attention);
		shopHolder.tv_shop_int_status_on=(TextView) root.findViewById(R.id.tv_shop_int_status_on);
	}
	public void setShang(Shang shoper){
		this.shop=shoper;
	}
	public void notifyChage(){
		if (shop!=null) {
			shopHolder.tv_tel_munber.setText("µÁª∞£∫"+shop.tel);
			shopHolder.tv_address.setText("µÿ÷∑£∫"+shop.address);
			shopHolder.tv_shop_brief.setText("ºÚΩÈ£∫"+shop.brief);
			shopHolder.tv_shop_int_period_time.setText(shop.int_decline);
			shopHolder.tv_shop_int_status_on.setText(shop.int_status);
		}
	}
	public void initListener(){
		shopHolder.tv_map_address.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				ct.startActivity(new Intent(ct, MapActivity.class));
			}
		});
		shopHolder.tv_details_call.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				if (shop!=null) {
					Intent dial= new Intent();
					dial.setAction("android.intent.action.CALL");
					dial.setData(Uri.parse("tel:"+shop.tel));
					ct.startActivity(dial);
				}
			}
		});
	}
	@Override
	public void initData() {}
	public class ShopHolder{
		public TextView tv_tel_munber;
		public TextView tv_details_call;
		public TextView tv_address;
		public TextView tv_map_address;
		public TextView tv_shop_brief;
		
		public TextView tv_shop_int_period_time;
		public TextView tv_shop_pay_attention;
		public TextView tv_shop_int_status_on;
	}
}
