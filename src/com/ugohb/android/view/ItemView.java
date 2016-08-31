package com.ugohb.android.view;

import com.lidroid.xutils.BitmapUtils;
import com.ugohb.android.R;
import com.ugohb.android.domain.HomeJson.homeItem;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemView {
   private View view;
   public Context ct;
   public homeItem itemData;
   ItemHolder items;
   BitmapUtils but;
	public ItemView(Context ct,homeItem item) {
		this.ct = ct;
		this.itemData=item;
		this.items=new ItemHolder();
		this.but=new BitmapUtils(ct);
		LayoutInflater inflater = (LayoutInflater)ct.getSystemService(
			      Context.LAYOUT_INFLATER_SERVICE);
		view = initView(inflater);
	}
	public  View getRootView(){
    	return view;
    }
	public View initView(LayoutInflater inflater){
		
		View root=inflater.inflate(R.layout.page_list_item, null);
		items.tv_shop_name=(TextView) root.findViewById(R.id.tv_shop_name);
		items.tv_shop_integral=(TextView) root.findViewById(R.id.tv_shop_integral);
		items.iv_shop_thumb=(ImageView) root.findViewById(R.id.iv_shop_thumb);
		items.tv_shop_title=(TextView) root.findViewById(R.id.tv_shop_title);
		
		items.tv_shop_title.setText(Html.fromHtml(itemData.title));
		items.tv_shop_integral.setText(itemData.integral+"»ý·Ö");
		items.tv_shop_name.setText(itemData.name);
		but.display(items.iv_shop_thumb, itemData.thumb);
		return root;
	}
	public class ItemHolder{
		public TextView tv_shop_name;
		public TextView tv_shop_integral;
		public TextView tv_shop_title;
		public ImageView iv_shop_thumb;
	}
}
