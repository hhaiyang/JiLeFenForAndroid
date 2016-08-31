package com.ugohb.android.adapter;


import java.util.ArrayList;

import com.ugohb.android.R;
import com.ugohb.android.domain.HomeJson.homeItem;
import com.ugohb.android.view.HotItemView;

import android.content.Context;
import android.provider.Contacts;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressWarnings("deprecation")
public class BannerGridViewAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<homeItem> list;
	public BannerGridViewAdapter (Context context,ArrayList<homeItem> list){
		this.context=context;
		this.list=list;
	}
	public int getCount() {
		return list.size();
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		HotItemView view=new HotItemView(context, list.get(position));
		convertView=view.getRootView();
		return convertView;
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
