package com.ugohb.android.adapter;


import com.ugohb.android.R;

import android.content.Context;
import android.provider.Contacts;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressWarnings("deprecation")
public class MenuGridViewAdapter extends BaseAdapter {
	private static int[] listhome_ids;
	private static String [] names;
	private Context context;
	public MenuGridViewAdapter (Context context,int[] listhome_ids,String [] names){
		this.listhome_ids=listhome_ids;
		this.names=names;
		this.context=context;
		
	}
	public int getCount() {
		return names.length;
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view = View.inflate(context, R.layout.menu_grid_item, null);
		ImageView iv_item = (ImageView) view.findViewById(R.id.iv_item);
		TextView tv_item = (TextView) view.findViewById(R.id.tv_item);
		
		tv_item.setText(names[position]);
		iv_item.setImageResource(listhome_ids[position]);
		return view;
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

}
