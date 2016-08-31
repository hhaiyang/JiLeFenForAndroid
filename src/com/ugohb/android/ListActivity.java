package com.ugohb.android;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.ugohb.android.constant.Categorys;
import com.ugohb.android.constant.Site;
import com.ugohb.android.domain.ListJson;
import com.ugohb.android.pages.ResultOfListPage;
import com.ugohb.android.ui.ErrorView;
import com.ugohb.android.ui.ErrorView.ViewReloadListener;
import com.ugohb.android.utils.JqCommonUtils;
import com.ugohb.android.utils.JqViewUtils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

public class ListActivity extends Activity {
	@ViewInject(R.id.tv_list_cate)
	public TextView tv_list_cate;
	@ViewInject(R.id.tv_list_area)
	public TextView tv_list_area;
	@ViewInject(R.id.tv_list_sort)
	public TextView tv_list_sort;
	/***
	 * TAG
	 */
	public final static int CATEGORAY=0; 
	public final static int AREA=1; 
	public final static int SORT=2; 
	public final static int DEFAULT=999; 
	public Drawable DropDownIconActived;
	public Drawable DropDownIconNormal;
	public static int curPop=0;
	public TextView curTv;
	
	
	@ViewInject(R.id.iv_cate_back)
	public ImageView iv_cate_back;
	@ViewInject(R.id.tv_list_title)
	public TextView tv_list_title;
	@ViewInject(R.id.iv_list_search)
	public ImageView iv_list_search;
	
	public Context ctx;
	public ResultOfListPage resultPage;
	
	@ViewInject(R.id.view_cate_bar)
	public View view_cate_bar;
	@ViewInject(R.id.ll_header_bar)
	public LinearLayout ll_header_bar;
	/**
	 * popupwindow
	 */
	public PopupWindow popupWindow;
	public WindowManager wm;
	public View select;
	public int popW;
	public int popH;
	public ListView lv_list_cate;
	public popLiAdapter popAdapter;
	public ArrayList<String> lvs;
	public ArrayList<String> popCates;
	public ArrayList<String> popSort;
	public ArrayList<String> popArea;
	
	@ViewInject(R.id.ll_container)
	public LinearLayout ll_container;
	JqViewUtils JVU;
	ErrorView error;
	public Handler mhandler;
	public int Duration=2000;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		ViewUtils.inject(this);
		ctx=this;
		resultPage=new ResultOfListPage(ctx);
		initUI();
		initPop();
		initListen();
		getData(Site.URL_LIST);
	}
	public void initUI(){
		mhandler=new Handler();
		error=new ErrorView(ctx);
		JVU=new JqViewUtils(ctx, ll_container);
		JVU.ShowLoading();
		error.setOnViewReloadListener(new ViewReloadListener() {			
			@Override
			public void onReload() {
				JVU.ShowLoading();
				getData(Site.URL_LIST);
			}
		});
	}
	
	public void initPop(){
		DropDownIconActived = getResources().getDrawable(R.drawable.ic_dropdown_actived);
		DropDownIconNormal=getResources().getDrawable(R.drawable.ic_dropdown_normal);
		select=View.inflate(ctx, R.layout.view_popwindow, null);
		lv_list_cate=(ListView) select.findViewById(R.id.lv_list_cate);
		
		popCates=new ArrayList<String>();
		for (int i = 0; i < Categorys.cate.length; i++) {
			popCates.add(Categorys.cate[i]);
		}
		lvs=popCates;		
		popSort=new ArrayList<String>();
		popSort.add("升序 ↑");
		popSort.add("降序 ↓");
		popArea=new ArrayList<String>();
		popArea.add("淮北市");
		popArea.add("濉溪县");		
		popAdapter=new popLiAdapter();
		lv_list_cate.setAdapter(popAdapter);
		/**
		 * 等待窗口绘制完毕执行此方法
		 */
		view_cate_bar.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			
			@Override
			public void onGlobalLayout() {
				initPopRect();
				view_cate_bar.getViewTreeObserver().removeGlobalOnLayoutListener(this);
			}
		});
	}
	public void getData(String dataUrl){
		HttpUtils http=new HttpUtils();
		http.send(HttpMethod.GET, dataUrl, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String msg) {
				mhandler.postDelayed(new Runnable() {					
					@Override
					public void run() {	
						JVU.setView(error.getRootView());
						JVU.SwitchPage();
					}
				}, Duration);
			}

			@Override
			public void onSuccess(ResponseInfo<String> res) {
				analyseData(res.result);				
			}
		});
	}
	protected void analyseData(String result) {
		Gson gson=new Gson();
		resultPage.mList=gson.fromJson(result, ListJson.class);		
		resultPage.results.addAll(resultPage.mList.result);
		resultPage.mAdapter.notifyDataSetChanged();
		mhandler.postDelayed(new Runnable() {			
			@Override
			public void run() {
				JVU.setView(resultPage.getRootView());
				JVU.SwitchPage();
			}
		}, Duration);
	}

	public void initListen(){
		tv_list_cate.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				showPop(0,popCates);
			}
		});
		tv_list_area.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				showPop(1,popArea);
			}
		});
		tv_list_sort.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				showPop(2,popSort);
			}
		});
		
		
		/***
		 * 
		 */
		iv_cate_back.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				goDie(true);
			}
		});
		iv_list_search.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				Intent search=new Intent();
				search.setClass(getBaseContext(), SearchActivity.class);
				startActivity(search);
			}
		});
	}
	public void setTagChecked(int pos){
		tv_list_cate.setTextColor(getResources().getColor(R.color.font_black_content));
		tv_list_area.setTextColor(getResources().getColor(R.color.font_black_content));
		tv_list_sort.setTextColor(getResources().getColor(R.color.font_black_content));	

		tv_list_cate.setCompoundDrawablesWithIntrinsicBounds(null, null, DropDownIconNormal, null);
		tv_list_area.setCompoundDrawablesWithIntrinsicBounds(null, null, DropDownIconNormal, null);
		tv_list_sort.setCompoundDrawablesWithIntrinsicBounds(null, null, DropDownIconNormal, null);
		switch (pos) {
			case CATEGORAY:
				curTv=tv_list_cate;
				break;
			case AREA:
				curTv=tv_list_area;
				break;
			case SORT:
				curTv=tv_list_sort;
				break;
			default:
				curTv=null;
				break;
		}
		if(curTv!=null){
			curTv.setTextColor(getResources().getColor(R.color.green));
			curTv.setCompoundDrawablesWithIntrinsicBounds(null, null, DropDownIconActived, null);
		}
	}
	protected void showPop(int pos,ArrayList<String> popLV) {
		if (popupWindow != null && popupWindow.isShowing()) {  
            if (curPop==pos) {
            	popupWindow.dismiss();  
                popupWindow = null;
                setTagChecked(DEFAULT);
                
			}else{
				lvs=popLV;
				popAdapter.notifyDataSetChanged();
				curPop=pos;
                setTagChecked(pos);
			}
        } else{
        	lvs=popLV;
			popAdapter.notifyDataSetChanged();
			curPop=pos;
    		popViewIn(select);
            setTagChecked(pos);
        }
	}
	

	
	public  void popViewIn(final View window) {
		CreatePopWin(window);
		Animation fade_in=AnimationUtils.loadAnimation(this, R.anim.fade_in);
		window.startAnimation(fade_in);
		
		window.setOnTouchListener(new OnTouchListener() {  
            @Override  
            public boolean onTouch(View v, MotionEvent event) {
            	dismissPop(window);

        		if(curTv!=null){
        			curTv.setTextColor(getResources().getColor(R.color.font_black_content));
        			curTv.setCompoundDrawablesWithIntrinsicBounds(null, null, DropDownIconNormal, null);
        			curTv=null;
        		}
                return false;  
            }
        });  
	}
	public void dismissPop(View window){
		Animation drop_up=AnimationUtils.loadAnimation(this, R.anim.drop_up);
		window.startAnimation(drop_up);
		if (popupWindow != null && popupWindow.isShowing()) {  
            popupWindow.dismiss();  
            popupWindow = null;  
        }  
	}
	/**
	 * 初始化popupwindow
	 */
	public void initPopRect(){
		WindowManager wm = this.getWindowManager();
		int HeaderH=view_cate_bar.getHeight();
		int CateH=view_cate_bar.getHeight();
		
		Rect rectangle= new Rect();
		Window win= getWindow();
		win.getDecorView().getWindowVisibleDisplayFrame(rectangle);
		int statusBarHeight= rectangle.top;
		
		popH=wm.getDefaultDisplay().getHeight()-HeaderH-CateH
		-statusBarHeight+JqCommonUtils.dip2px(ctx, 8);
		popW=wm.getDefaultDisplay().getWidth();
	}
	/**
	 * 创建pop
	 * @param window
	 */
	public void CreatePopWin(View window){
		popupWindow = new PopupWindow(window,popW,popH);
		popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		popupWindow.showAtLocation(window,Gravity.BOTTOM|Gravity.CENTER,0,0);
	}
	
	
	public class popLiAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return lvs.size();
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
			TextView popli=null;
			if (convertView==null) {
				convertView=View.inflate(ctx, R.layout.list_pop_item, null);
				popli=(TextView) convertView.findViewById(R.id.tv_list_item_pop);
				convertView.setTag(popli);				
			}else{
				popli=(TextView) convertView.getTag();
			}
			popli.setText(lvs.get(position));
			return convertView;
		}
		
	}
	
	@Override
	public void onBackPressed() {
		goDie(true);
	}

	public void goDie(boolean die) {
		if(die==true){
			if (popupWindow != null && popupWindow.isShowing()) {  
	            popupWindow.dismiss();  
	            popupWindow = null;  
	        } else{
	        	finish();
	        }	
		}	
	}
}
