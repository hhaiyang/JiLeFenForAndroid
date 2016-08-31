package com.ugohb.android.pages;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.ugohb.android.ListActivity;
import com.ugohb.android.R;
import com.ugohb.android.ShopActivity;
import com.ugohb.android.constant.ImagesResource;
import com.ugohb.android.domain.ListJson;
import com.ugohb.android.domain.ListJson.liItem;
import com.ugohb.android.ui.XListView;
import com.ugohb.android.ui.XListView.IXListViewListener;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ResultOfListPage extends BasePage{

	public XListView lv_list;
	public IXListViewListener listListener;
	private Handler mHandler;
	long Duration=2000;
	public ListJson mList;
	public liAdapter mAdapter;
	public ArrayList<liItem> results;
	View root;
	public ResultOfListPage(Context ct) {
		super(ct);
	}

	@Override
	public View initView(LayoutInflater inflater) {
		root=inflater.inflate(R.layout.page_list_result, null);
		initUI();
		initList();
		return root;
	}
	public void initUI(){
		lv_list=(XListView) root.findViewById(R.id.lv_list);
		results=new ArrayList<ListJson.liItem>();
		mHandler=new Handler();
		mAdapter=new liAdapter(ct);
	}
	@Override
	public void initData() {
		
	}
	public void getData(String dataUrl){
		HttpUtils http=new HttpUtils();
		http.send(HttpMethod.GET, dataUrl, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String msg) {
				
			}

			@Override
			public void onSuccess(ResponseInfo<String> res) {
				analyseData(res.result);				
			}
		});
	}
	protected void analyseData(String result) {
		Gson gson=new Gson();
		mList=gson.fromJson(result, ListJson.class);		
		results.addAll(mList.result);
		mAdapter.notifyDataSetChanged();
	}
	public void initList(){
		lv_list.setAdapter(mAdapter);
		lv_list.setPullRefreshEnable(true);
		lv_list.setPullLoadEnable(true);
		listListener=new IXListViewListener() {			
			@Override
			public void onRefresh() {
				mHandler.postDelayed(new Runnable() {					
					@Override
					public void run() {
						onLoad();
					}
				},Duration);				
			}
			
			@Override
			public void onLoadMore() {
				long start=0;
				long end=0;
				if (mList.isnext.equals("1")) {
					start= System.currentTimeMillis();
					getData(mList.next);
					end= System.currentTimeMillis();
				}else{
					Toast.makeText(ct, "没有更多数据了", Toast.LENGTH_SHORT).show();
				}
				long used=end-start;
				if(used>Duration){
					onLoad();
				}else{
					mHandler.postDelayed(new Runnable() {
						
						@Override
						public void run() {						
							onLoad();
						}
					},Duration-used);
				}
			}
		};
		lv_list.setXListViewListener(listListener);
		lv_list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int pos,
					long id) {
				ct.startActivity(new Intent(ct, ShopActivity.class));
			}
		});
		
	}
	private void onLoad() {
		lv_list.stopRefresh();
		lv_list.stopLoadMore();
		lv_list.setRefreshTime("刚刚");
	}
	public class liAdapter extends BaseAdapter{
		BitmapUtils but;
		Context ctx;

		public liAdapter(Context ctx) {
			super();
			this.ctx = ctx;
			but=new BitmapUtils(ctx);
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return results.size();
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
			LiViewHolder holder=null;
			if (convertView==null) {
				holder=new LiViewHolder();
				convertView=View.inflate(ctx, R.layout.list_view_item, null);
				holder.iv_list_thumb=(ImageView) convertView.findViewById(R.id.iv_list_thumb);
				holder.tv_list_item_name=(TextView) convertView.findViewById(R.id.tv_list_item_name);
				holder.tv_list_item_address=(TextView) convertView.findViewById(R.id.tv_list_item_address);
				holder.tv_list_item_newactivity=(TextView) convertView.findViewById(R.id.tv_list_item_newactivity);
				holder.tv_list_item_integraled=(TextView) convertView.findViewById(R.id.tv_list_item_integraled);
				
				holder.iv_list_thumb_int=(ImageView) convertView.findViewById(R.id.iv_list_thumb_int);
				holder.tv_list_diszhe=(TextView) convertView.findViewById(R.id.tv_list_diszhe);
				
				convertView.setTag(holder);
			}else {
				holder=(LiViewHolder) convertView.getTag();
			}
			
			liItem nsg=results.get(position);
			but.display(holder.iv_list_thumb, nsg.img);

			holder.tv_list_item_name.setText(nsg.name);
			holder.tv_list_item_address.setText(nsg.address);
			holder.tv_list_item_newactivity.setText(nsg.newactivity);
			holder.tv_list_item_integraled.setText(Html.fromHtml(nsg.integraled));
			holder.iv_list_thumb_int.setImageResource(
					ImagesResource.Shop_Mark_ids[Integer.parseInt(nsg.ints)-2]);
			holder.tv_list_diszhe.setText(nsg.discount+"折起");
			
			return convertView;
		}

	}
	public class LiViewHolder{
		public ImageView iv_list_thumb;
		public TextView tv_list_item_name;
		public TextView tv_list_item_address;
		public TextView tv_list_item_newactivity;
		public TextView tv_list_item_integraled;
		public ImageView iv_list_thumb_int;
		public TextView tv_list_diszhe;
	}
	
}
