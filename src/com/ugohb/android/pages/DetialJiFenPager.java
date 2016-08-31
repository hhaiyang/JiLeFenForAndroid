package com.ugohb.android.pages;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.ugohb.android.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class DetialJiFenPager extends BasePage implements OnDateChangedListener, OnClickListener{
	private ListView listView;
	private View detial_jifen_view,view;
	//日期选择器
	private DatePicker dp_start,dp_end;
	private String[] dates=new String[]{"2015年07月05日5:03","2015年07月05日5:03","2015年07月05日5:03","2015年07月05日5:03",
			"2015年07月05日5:03","2015年07月05日5:03"};
	private String[]  names=new String[]{"红星美凯龙-美迪家具","红星美凯龙-美迪家具","红星美凯龙-美迪家具","红星美凯龙-美迪家具",
			"红星美凯龙-美迪家具","红星美凯龙-美迪家具",};

	private ListView detial_listview;
	public PopupWindow popupWindow;
	private TextView et_selector;
	//箭头
	private ImageView iamgeview;
	
	public DetialJiFenPager(Context ct) {
		super(ct);
	}
	@Override
	public View initView(LayoutInflater inflater) {
		
		detial_jifen_view = inflater.inflate(R.layout.pager_detial_jifen, null);
		view = inflater.inflate(R.layout.pager_detial_jifen_selector_lei, null);
		initUI();  
		initListener();
		initListView();
		initDatePicker();
		return detial_jifen_view;
	}
	


	private void initUI() {
		listView=(ListView) detial_jifen_view.findViewById(R.id.listview);
		dp_start=(DatePicker) detial_jifen_view.findViewById(R.id.dp_start);
		dp_end=(DatePicker) detial_jifen_view.findViewById(R.id.dp_end);
		et_selector=(EditText) detial_jifen_view.findViewById(R.id.et_selector);
		
		iamgeview=(ImageView) detial_jifen_view.findViewById(R.id.iamgeview);
		detial_listview = (ListView) view.findViewById(R.id.detial_listview);
		detial_listview.setBackgroundResource(R.drawable.listview_background);
	
		
	}

	private void initListener() {
		detial_listview.setOnItemClickListener(new OnItemClickListener() {
			
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				et_selector.setText(objects[position]);
				if(popupWindow!=null){
					popupWindow.dismiss();
				}
				
			}
		});
		iamgeview.setOnClickListener(this);
		et_selector.setOnClickListener(this);
		
	}
	
	/**
	 * 初始化日期选择器 
	 */
	@SuppressLint("NewApi")
	private void initDatePicker() {
		Calendar calendar = Calendar.getInstance();
		dp_start.setMaxDate(System.currentTimeMillis());
		dp_start.init(calendar.get(Calendar.YEAR),
				calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH), this);
		dp_end.setMaxDate(System.currentTimeMillis());
		dp_end.init(calendar.get(Calendar.YEAR),
				calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH), this);
		((ViewGroup)((ViewGroup) dp_start.getChildAt(0)).getChildAt(0)).getChildAt(2).setVisibility(View.GONE);
		((ViewGroup)((ViewGroup) dp_end.getChildAt(0)).getChildAt(0)).getChildAt(2).setVisibility(View.GONE);
	}
	String[] objects;
	private void initListView() {
		listView.setAdapter(new DetialPagerAdapter());
		objects=new String[]{"现金积分","免费积分"};
		ArrayAdapter< String> arrayAdapter=new ArrayAdapter<String>(ct, android.R.layout.simple_list_item_1, objects);
		detial_listview.setAdapter(arrayAdapter);
	}


	
	class DetialPagerAdapter extends BaseAdapter{

		public int getCount() {
			return 7;
		}

		public Object getItem(int position) {
			return null;
		}

		public long getItemId(int position) {
			return 0;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			if(convertView==null){
				convertView=View.inflate(ct, R.layout.pager_detial_list_item, null);
			}
			return convertView;
		}
		
	}



	/**
	 * 时间改变是调用
	 */
	public void onDateChanged(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
	}

	/**
	 * 点击事件的处理
	 */
	public void onClick(View v) {
		if(popupWindow!=null){
			popupWindow.dismiss();
			popupWindow=null;
		}else{
			popupWindow=new PopupWindow(view,et_selector.getWidth(),-2);
			popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
			popupWindow.setFocusable(true);
			popupWindow.setOutsideTouchable(true);
			popupWindow.showAsDropDown(et_selector, 0, -8);//设置 弹出窗口，显示的位置
		}
	}
	
	/**
	 * 判断popwindow是不是正在显示
	 * @return
	 */
	public boolean isPopShowing(){
		if(popupWindow!=null){
			return popupWindow.isShowing();
		}else{
			return false;
		}
	}
	@Override
	public void initData() {
		// TODO Auto-generated method stub
		
	}

	
	
	
 }
