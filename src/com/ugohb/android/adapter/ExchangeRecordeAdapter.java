package com.ugohb.android.adapter;

import java.util.List;

import com.ugohb.android.R;
import com.ugohb.android.ui.MyTextView;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ExchangeRecordeAdapter<E> extends BaseAdapter implements
		OnClickListener {
	private Activity activity;
	private List<E> list;

	public ExchangeRecordeAdapter(Activity activity, List<E> list) {
		super();
		this.activity = activity;
		this.list = list;
		initDialog();
	}

	/**
	 * 初始化对话框
	 */
	private void initDialog() {
		initSeeCodeDialog();
		initTuiDingDialog();
		initTuiDingSucceedDialog();
	}

	/**
	 * 初始化退订成功对话框
	 */
	private AlertDialog tuiding_succeed_dialog;
	private View tuiding_succeed_dialog_view;

	private void initTuiDingSucceedDialog() {
		tuiding_succeed_dialog_view=View.inflate(activity, R.layout.tui_ding_succeed_dialog, null);
		tuiding_succeed_dialog =new  AlertDialog.Builder(activity).create();
		tuiding_succeed_dialog.setCanceledOnTouchOutside(false);
		
	}
	/**
	 *  显示退订成功对话框
	 */
	private void showTuiDingSucceedDialog() {
		tuiding_succeed_dialog.show();
		tuiding_succeed_dialog.getWindow().setContentView(tuiding_succeed_dialog_view);
	}

	/**
	 * 关闭退订成功对话框
	 */
	@SuppressWarnings("unused")
	private void dismissTuiDingSucceedDialog() {
		tuiding_succeed_dialog.dismiss();
	}
	


	/**
	 * 初始化退订对话框
	 */
	private AlertDialog tuiding_dialog;
	private View tuiding_dialog_view;
	private void initTuiDingDialog() {
	  tuiding_dialog_view = View.inflate(activity,
				R.layout.tui_ding_dialog, null);
		ImageButton tuiding_dialog_cancel=(ImageButton) tuiding_dialog_view.findViewById(R.id.btn_tuiding_dialog_cancel);
		Button tuiding_dialog_fangqi=(Button) tuiding_dialog_view.findViewById(R.id.btn_tuiding_dialog_fangqi);
		Button tuiding_dialog_zhenxi=(Button) tuiding_dialog_view.findViewById(R.id.btn_tuiding_dialog_zhenxi);
		tuiding_dialog_cancel.setOnClickListener(this);
		tuiding_dialog_zhenxi.setOnClickListener(this);
		tuiding_dialog_fangqi.setOnClickListener(this);
		
		tuiding_dialog=new AlertDialog.Builder(activity).create();
		tuiding_dialog.setCanceledOnTouchOutside(false);
	}

	/**
	 * 初始化查看兑换码对话框
	 */
	private AlertDialog see_code_dialog;
	View see_code_dialog_view;

	private void initSeeCodeDialog() {
		see_code_dialog_view =View.inflate(activity, R.layout.see_code_dialog, null);
		Button see_code_dialog_close = (Button) see_code_dialog_view
				.findViewById(R.id.btn_see_code_dialog_close);
		ImageButton see_code_dialog_cancel = (ImageButton) see_code_dialog_view
				.findViewById(R.id.btn_see_code_dialog_cancel);
		see_code_dialog_close.setOnClickListener(this);
		see_code_dialog_cancel.setOnClickListener(this);

		see_code_dialog =new AlertDialog.Builder(activity).create();
		// 设置点击外边缘不消失
		see_code_dialog.setCanceledOnTouchOutside(false);
	}

	/**
	 *  显示查看兑换码对话框
	 */
	private void showSeeCodeDialog() {
		see_code_dialog.show();
		see_code_dialog.getWindow().setContentView(see_code_dialog_view);
	}

	/**
	 * 关闭查看兑换码对话框
	 */
	private void dismissSeeCodeDialog() {
		see_code_dialog.dismiss();
	}

	/**
	 *  显示退订对话框
	 */
	private void showTuiDingDialog() {
		tuiding_dialog.show();
		tuiding_dialog.getWindow().setContentView(tuiding_dialog_view);
	}

	/**
	 * 关闭退订对话框
	 */
	private void dismissTuiDingDialog() {
		tuiding_dialog.dismiss();
	}

	public int getCount() {
		return list.size();
	}

	public Object getItem(int position) {
		return list.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		View view = null;
		if (convertView == null) {
			view = View.inflate(activity, R.layout.exchange_recorde_list_item,
					null);
		} else {
			view = convertView;
		}

		ViewHolder holder = ViewHolder.getViewHolder(view);
		if (position == 0||position == 3) {
			holder.ll_linelayout.setVisibility(View.VISIBLE);
			holder.get_succeed.setVisibility(View.INVISIBLE);
			holder.see_code.setOnClickListener(this);
			holder.tuiding.setOnClickListener(this);
			holder.left_title.setText("未领取");
			holder.left_title.setTextColor(activity.getResources().getColor(
					R.color.COLOR_RED));
		} else {
			holder.ll_linelayout.setVisibility(View.INVISIBLE);
			holder.get_succeed.setVisibility(View.VISIBLE);
			holder.left_title.setText("已领取");
			holder.left_title.setTextColor(activity.getResources().getColor(
					R.color.COLOR_GREEN));
		}

		return view;
	}

	static class ViewHolder {
		TextView get_succeed;
		TextView left_title;
		TextView item_title;
		TextView item_desc;
		TextView item_date;
		MyTextView money_number;
		TextView jifen_number;
		LinearLayout ll_linelayout;
		Button see_code;
		Button tuiding;
		ImageView imageView;

		public static ViewHolder getViewHolder(View view) {
			ViewHolder holder = (ViewHolder) view.getTag();
			if (holder == null) {
				ViewHolder viewholder = new ViewHolder();
				viewholder.tuiding = (Button) view
						.findViewById(R.id.btn_tuiding);
				viewholder.see_code = (Button) view
						.findViewById(R.id.btn_see_code);
				viewholder.get_succeed = (TextView) view
						.findViewById(R.id.get_succeed);
				viewholder.left_title = (TextView) view
						.findViewById(R.id.list_item_left_title);
				viewholder.item_title = (TextView) view
						.findViewById(R.id.list_item_title);
				viewholder.item_desc = (TextView) view
						.findViewById(R.id.list_item_desc);
				viewholder.item_date = (TextView) view
						.findViewById(R.id.list_item_date);
				viewholder.jifen_number = (TextView) view
						.findViewById(R.id.jifen_number);
				viewholder.money_number = (MyTextView) view
						.findViewById(R.id.money_number);
				viewholder.imageView = (ImageView) view
						.findViewById(R.id.list_item_iamgeview);
				viewholder.ll_linelayout = (LinearLayout) view
						.findViewById(R.id.list_ll_linelayout);
				view.setTag(viewholder);
				return viewholder;
			}
			return holder;
		}

	}

	// 点击事件的监听
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_see_code:
			showSeeCodeDialog();
			break;
		case R.id.btn_tuiding:
			showTuiDingDialog();
			break;
		case R.id.btn_see_code_dialog_cancel:
			dismissSeeCodeDialog();
			break;
		case R.id.btn_see_code_dialog_close:
			dismissSeeCodeDialog();
			break;
		case R.id.btn_tuiding_dialog_cancel:
			dismissTuiDingDialog();
			break;
		case R.id.btn_tuiding_dialog_zhenxi:
			dismissTuiDingDialog();
			break;
		case R.id.btn_tuiding_dialog_fangqi:
			btn_FangQi();
			break;
		}

	}

	private void btn_FangQi() {
		dismissTuiDingDialog();
		showTuiDingSucceedDialog();
		
	}

}
