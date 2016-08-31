package com.ugohb.android;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.ugohb.android.constant.Categorys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class CateActivity extends Activity {
	@ViewInject(R.id.iv_cate_back)
	public ImageView iv_cate_back;
	@ViewInject(R.id.tv_cate_search)
	public TextView tv_cate_search;
	
	@ViewInject(R.id.lv_cate_main)
	public ListView lv_cate_main;
	@ViewInject(R.id.lv_cate_tree)
	public ListView lv_cate_tree;
	
	public RightAdapter treeAdapter;
	
	public Context ct;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_category);
		ViewUtils.inject(this);
		ct=this;
		treeAdapter=new RightAdapter(Categorys.Tree_0, ct);
		initView();
		initList();
		
	}
	private void initList() {
		lv_cate_main.setAdapter(new mainTree(ct, Categorys.cate, Categorys.cate_img));
		lv_cate_main.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int pos,
					long id) {
				String titles[]=null;
				switch (pos) {
				case 0:
					titles=Categorys.Tree_0;
					break;
				case 1:
					titles=Categorys.Tree_1;
					break;
				case 2:
					titles=Categorys.Tree_2;
					break;
				case 3:
					titles=Categorys.Tree_3;
					break;
				case 4:
					titles=Categorys.Tree_4;
					break;
				case 5:
					titles=Categorys.Tree_5;
					break;
				case 6:
					titles=Categorys.Tree_6;
					break;
				case 7:
					titles=Categorys.Tree_7;
					break;
				case 8:
					titles=Categorys.Tree_8;
					break;
				case 9:
					titles=Categorys.Tree_9;
					break;
				case 10:
					titles=Categorys.Tree_10;
					break;
				case 11:
					titles=Categorys.Tree_11;
					break;
				case 12:
					titles=Categorys.Tree_12;
					break;
				case 13:
					titles=Categorys.Tree_13;
					break;
				case 14:
					titles=Categorys.Tree_14;
					break;
				case 15:
					titles=Categorys.Tree_15;
					break;
				case 16:
					titles=Categorys.Tree_16;
					break;
				case 17:
					titles=Categorys.Tree_17;
					break;
				case 18:
					titles=Categorys.Tree_18;
					break;
				default:
					titles=Categorys.Tree_0;
					break;
				}
				treeAdapter.refleshData(titles);
				lv_cate_main.setItemChecked(pos, true);
			}
		});
		lv_cate_tree.setAdapter(treeAdapter);
		
	}
	private void initView() {
		iv_cate_back.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		tv_cate_search.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {				
				ct.startActivity(new Intent(ct,SearchActivity.class));
			}
		});		
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK ) {
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}
	
	public class mainTree extends BaseAdapter{
		public Context ctx;
		public String titles[];
		public int imgs[];
		
		public mainTree(Context ct, String[] titles, int[] imgs) {
			super();
			this.ctx = ct;
			this.titles = titles;
			this.imgs = imgs;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return titles.length;
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
			CateMainHolder holder=null;
			if(convertView==null){
				holder=new CateMainHolder();
				convertView=View.inflate(ctx, R.layout.cate_main_item, null);
				holder.iv_cate_main=(ImageView) convertView.findViewById(R.id.iv_cate_main);
				holder.tv_cate_main=(TextView) convertView.findViewById(R.id.tv_cate_main);
				convertView.setTag(holder);
			}else {
				holder=(CateMainHolder) convertView.getTag();
			}
			holder.iv_cate_main.setImageResource(imgs[position]);
			holder.tv_cate_main.setText(titles[position]);
			
			return convertView;
		}
		
	}
	public class CateMainHolder{
		public ImageView iv_cate_main;
		public TextView tv_cate_main;
	}
	public class RightAdapter extends BaseAdapter{
		public String titles[];
		public Context ctx;
		
		public RightAdapter(String[] titles, Context ctx) {
			super();
			this.titles = titles;
			this.ctx = ctx;
		}
		public void refleshData(String[] newtitles){
			this.titles=newtitles;
			notifyDataSetChanged();
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return titles.length;
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
			CateRightHolder holder=null;
			if(convertView==null){
				holder=new CateRightHolder();
				convertView=View.inflate(ctx, R.layout.cate_tree_item, null);
				holder.tv_cate_right=(TextView) convertView.findViewById(R.id.tv_cate_right);
				convertView.setTag(holder);
			}else{
				holder=(CateRightHolder) convertView.getTag();
			}
			holder.tv_cate_right.setText(titles[position]);
			return convertView;
		}
		
	}
	public class CateRightHolder{
		public TextView tv_cate_right;
	}
	
}
