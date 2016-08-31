package com.ugohb.android;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SearchActivity extends Activity implements OnClickListener{
	@ViewInject(R.id.iv_back)
	public ImageView iv_back;
	@ViewInject(R.id.ed_search)
	public EditText ed_search;
	@ViewInject(R.id.tv_start_search)
	public TextView tv_start_search;
	
	@ViewInject(R.id.ll_hot_pannel)
	public LinearLayout ll_hot_pannel;
	@ViewInject(R.id.gv_hot_key)
	public GridView gv_hot_key;
	@ViewInject(R.id.search_list)
	public ListView search_list;
	public Context ct;
	
	public static String hots[]={
		"Ê×Îõ×ãµä",
		"Ê³ÉÐÒ»äÌÒ»¿¾",
		"Ä§Ê¯ÅÝÅÝÓã",
		"ÒÁ°ÙÅµ",
		"ºìÅÜ³µ",
		"Ð¡·ÊÑò",
		"ÞÈ³½¡¤Ö®ÀÖ",
		"Ð¡±´¿ÇÅûÈø",
		"¿µÎõ´øÓã"
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		ViewUtils.inject(this);
		ct=this;
		initListen();
		initView();
	}
	public void initView() {
		gv_hot_key.setAdapter(new hotsAdapter());
	}
	public void initListen(){
		iv_back.setOnClickListener(this);
		tv_start_search.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_back:
			finish();
			break;
		case R.id.tv_start_search:
			startSearch();
			break;
		default:
			break;
		}
		
	}
	private void startSearch() {
		String str=ed_search.getText().toString().trim();
		Toast.makeText(getBaseContext(), "ÕýÔÚÎªÄúÅ¬Á¦ËÑË÷:"+str, Toast.LENGTH_LONG).show();
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK ) {
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}
	
	public class hotsAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return hots.length;
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
			TextView tv=null;
			if(convertView==null){
				convertView=View.inflate(ct, R.layout.search_hots_item, null);
			}
			tv=(TextView) convertView.findViewById(R.id.hots_key);
			tv.setText(hots[position]);
			return convertView;
		}
		
	}
}
