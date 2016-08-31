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

public class HotItemView {
   private View view;
   public Context ct;
   public homeItem itemData;
   HotItemHolder items;
   BitmapUtils but;
	private static int[] Mark_ids = {
		R.drawable.mark_int_2,
		R.drawable.mark_int_3,
		R.drawable.mark_int_4,
		R.drawable.mark_int_5,	
		R.drawable.mark_int_6,
		R.drawable.mark_int_7,
		R.drawable.mark_int_8,
		R.drawable.mark_int_9,
		R.drawable.mark_int_10	
	};	
	public HotItemView(Context ct,homeItem item) {
		this.ct = ct;
		this.itemData=item;
		this.items=new HotItemHolder();
		this.but=new BitmapUtils(ct);
		LayoutInflater inflater = (LayoutInflater)ct.getSystemService(
			      Context.LAYOUT_INFLATER_SERVICE);
		view = initView(inflater);
	}
	public  View getRootView(){
    	return view;
    }
	public View initView(LayoutInflater inflater){
		
		View root=inflater.inflate(R.layout.banner_hot_item, null);
		items.iv_hot_thumb=(ImageView) root.findViewById(R.id.iv_hot_thumb);
		items.home_hot_int=(ImageView) root.findViewById(R.id.home_hot_int);
		items.tv_hot_name=(TextView) root.findViewById(R.id.tv_hot_name);
		items.tv_hot_title=(TextView) root.findViewById(R.id.tv_hot_title);
		
		items.home_hot_int.setImageResource(Mark_ids[Integer.parseInt(itemData.integral)-2]);
		items.tv_hot_name.setText(itemData.name);
		items.tv_hot_title.setText(Html.fromHtml(itemData.title));
		but.display(items.iv_hot_thumb, itemData.thumb);
		return root;
	}
	public class HotItemHolder{
		public TextView tv_hot_name;
		public TextView tv_hot_title;
		public ImageView home_hot_int;
		public ImageView iv_hot_thumb;
	}
}
