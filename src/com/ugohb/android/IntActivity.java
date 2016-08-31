package com.ugohb.android;

import java.util.Arrays;

import com.ugohb.android.ui.WheelView;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class IntActivity extends Activity implements  OnTouchListener, OnClickListener{
	
	//输入框下面的线
	private View money_underline,name_underline,card_number_underline,iphone_number_underline;
	
	//输入金额
	private EditText et_input_money;
	//输入姓名
	private EditText et_input_name;
	//输入卡号
	private EditText et_input_card_number;
	//输入手机号
	private EditText et_input_iphone_number;
	//滑动选择器
	private WheelView wheelview;
	
	private LinearLayout ll_scroll_select;
	//选择银行卡类型
	private LinearLayout ll_select_bank_sort;
	
	//显示银行卡类型
	private TextView tv_bank_sort;
	
	//确定
	private Button btn_ok;
	//提交
	private Button btn_submit;
	//取消
	private ImageButton btn_cancel;
	
	private String select_bank_sort;
	
	
	 private static final String[] PLANETS = new String[]{"工商银行", "农业银行", "淮北农业银行", "中国银行", "中国工商银行"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_int);
		initUI();
		initListener();
	}

	
	private int presscolor;
	private View reminder_dialog_view;
	private View check_dialog_view;
	private View succeed_dialog_view;
	private void initUI() {
		money_underline=findViewById(R.id.money_underline);
		name_underline=findViewById(R.id.name_underline);
		iphone_number_underline=findViewById(R.id.iphone_number_underline);
		card_number_underline=findViewById(R.id.card_number_underline);
		
		et_input_name=(EditText) findViewById(R.id.et_input_name);
		et_input_money=(EditText) findViewById(R.id.et_input_money);
		et_input_card_number=(EditText) findViewById(R.id.et_input_card_number);
		et_input_iphone_number=(EditText) findViewById(R.id.et_input_iphone_number);
		
		ll_scroll_select=(LinearLayout) findViewById(R.id.ll_scroll_select);
		ll_select_bank_sort=(LinearLayout) findViewById(R.id.ll_select_bank_sort);
		btn_cancel=(ImageButton) findViewById(R.id.btn_cancel);
		btn_ok=(Button) findViewById(R.id.btn_ok);
		btn_submit=(Button) findViewById(R.id.btn_submit);
		tv_bank_sort=(TextView) findViewById(R.id.tv_bank_sort);
		presscolor=getResources().getColor(R.color.COLOR_RED);
//		money_underline.setBackgroundResource(R.color.COLOR_RED);
		
		//滑动选择器
		wheelview = (WheelView) findViewById(R.id.wheelview);
		wheelview.setOffset(1);
		wheelview.setItems(Arrays.asList(PLANETS));
		
		//对话框
		reminder_dialog_view = View.inflate(this, R.layout.reminder_dialog, null);
		check_dialog_view=View.inflate(this, R.layout.check_dialog, null);
		succeed_dialog_view=View.inflate(this, R.layout.succeed_dialog, null);
		
		//初始化对话框
		initDialog();
	}

	private void initListener() {
		et_input_name.setOnTouchListener(this);
		et_input_money.setOnTouchListener(this);
		et_input_card_number.setOnTouchListener(this);
		et_input_iphone_number.setOnTouchListener(this);
		ll_select_bank_sort.setOnClickListener(this);
		btn_cancel.setOnClickListener(this);
		btn_ok.setOnClickListener(this);
		btn_submit.setOnClickListener(this);
		
		//对话框监听
		reminder_dialog_back.setOnClickListener(this);
		reminder_dialog_cancel.setOnClickListener(this);
		reminder_dialog_next.setOnClickListener(this);
		check_dialog_alter.setOnClickListener(this);
		check_dialog_cancel.setOnClickListener(this);
		check_dialog_submit.setOnClickListener(this);
		succeed_dialog_cancel.setOnClickListener(this);
		
		//滑动监听事件
		wheelview.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
			@Override
			public void onSelected(int selectedIndex, String item) {
				select_bank_sort=item;
			}
		});
	}

	
	/**
	 * 初始化对话框
	 */
	private void initDialog() {
		initReminderDialog();
		initCheckDialog();
		initSucceedDialog();
	}
	
	/**
	 * 初始化成功对话框
	 */
	ImageButton succeed_dialog_cancel;
	AlertDialog succeed_dialog;
	private void initSucceedDialog() {
			succeed_dialog_cancel=(ImageButton) succeed_dialog_view.findViewById(R.id.btn_succeed_dialog_cancel);
			
			succeed_dialog=new AlertDialog.Builder(this).create();
			succeed_dialog.setCanceledOnTouchOutside(false);
	}


	/**
	 * 初始化核对对话框
	 */
	ImageButton check_dialog_cancel;
	Button check_dialog_alter;
	Button check_dialog_submit;
	AlertDialog check_dialog;
	private void initCheckDialog() {
		check_dialog_cancel=(ImageButton)check_dialog_view.findViewById(R.id.btn_check_dialog_cancel);
		check_dialog_alter=(Button) check_dialog_view.findViewById(R.id.btn_check_dialog_alter);
		check_dialog_submit=(Button)check_dialog_view.findViewById(R.id.btn_check_dialog_submit);
	
		check_dialog=new AlertDialog.Builder(this).create();
		check_dialog.setCanceledOnTouchOutside(false);
	}


	/**
	 * 初始化提醒化对话框
	 */
	AlertDialog reminder_dialog;
	ImageButton reminder_dialog_cancel;
	Button reminder_dialog_back;
	Button reminder_dialog_next;
	private void initReminderDialog(){
		reminder_dialog_cancel=(ImageButton) reminder_dialog_view.findViewById(R.id.btn_reminder_dialog_cancel);
		reminder_dialog_back=(Button) reminder_dialog_view.findViewById(R.id.btn_reminder_dialog_back);
		reminder_dialog_next=(Button) reminder_dialog_view.findViewById(R.id.btn_reminder_dialog_next);
	
		reminder_dialog=new AlertDialog.Builder(this).create();
		reminder_dialog.setCanceledOnTouchOutside(false);
	}
	
	/**
	 * 显示提醒化对话框
	 */
	private void showReminderDialog() {
		reminder_dialog.show();
		reminder_dialog.getWindow().setContentView(reminder_dialog_view);
		
	}

	/**
	 * 显示核对化对话框
	 */
	private void showCheckDialog() {
		check_dialog.show();
		check_dialog.getWindow().setContentView(check_dialog_view);
		
	}

	/**
	 * 显示成功对话框
	 */
	private void showSecceedDialog() {
		succeed_dialog.show();
		succeed_dialog.getWindow().setContentView(succeed_dialog_view);
		
	}
	
	
	/**
	 * 触屏事件的监听
	 */

	public boolean onTouch(View v, MotionEvent event) {
		switch (v.getId()) {
		case R.id.et_input_money:
			setUnderLineBackgroundColor(1);
			break;
		case R.id.et_input_name:
			setUnderLineBackgroundColor(2);
			break;
		case R.id.et_input_iphone_number:
			setUnderLineBackgroundColor(3);
			break;
		case R.id.et_input_card_number:
			setUnderLineBackgroundColor(4);
			break;
		}
		return false;
	}
	
	int defaultcolor;

	
	/**
	 * 设置下滑线的背景颜色
	 */
	public void setUnderLineBackgroundColor(int id){
		defaultcolor=getResources().getColor(R.color.COLOR_UNDERLINE);
		if(id==1){
			money_underline.setBackgroundColor(presscolor);
			name_underline.setBackgroundColor(defaultcolor);
			iphone_number_underline.setBackgroundColor(defaultcolor);
			card_number_underline.setBackgroundColor(defaultcolor);
		}else if(id==2){
			money_underline.setBackgroundColor(defaultcolor);
			name_underline.setBackgroundColor(presscolor);
			iphone_number_underline.setBackgroundColor(defaultcolor);
			card_number_underline.setBackgroundColor(defaultcolor);
		}else if(id==3){
			money_underline.setBackgroundColor(defaultcolor);
			name_underline.setBackgroundColor(defaultcolor);
			iphone_number_underline.setBackgroundColor(presscolor);
			card_number_underline.setBackgroundColor(defaultcolor);
		}else if(id==4){
			money_underline.setBackgroundColor(defaultcolor);
			name_underline.setBackgroundColor(defaultcolor);
			iphone_number_underline.setBackgroundColor(defaultcolor);
			card_number_underline.setBackgroundColor(presscolor);
		}else{
			money_underline.setBackgroundColor(defaultcolor);
			name_underline.setBackgroundColor(defaultcolor);
			iphone_number_underline.setBackgroundColor(defaultcolor);
			card_number_underline.setBackgroundColor(defaultcolor);
		}
		
	}
	/**
	 * 点击事件的监听
	 */

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_cancel:
			btn_Cancel();
			break;
		case R.id.btn_ok:
			btn_Ok();
			break;
		case R.id.btn_submit:
			btn_Submit();
			break;
		case R.id.ll_select_bank_sort:
			btn_SelectBankSort();
			break;
		case R.id.btn_reminder_dialog_cancel:
			btn_DismissReminderDialog();
			break;
		case R.id.btn_reminder_dialog_back:
			btn_DismissReminderDialog();
			break;
		case R.id.btn_reminder_dialog_next:
			btn_ReminderDialogNext();
			break;
		case R.id.btn_check_dialog_cancel:
			btn_DismissCheckDialog();
			break;
		case R.id.btn_check_dialog_alter:
			btn_DismissCheckDialog();
			break;
		case R.id.btn_check_dialog_submit:
			btn_CheckDialogSubmit();
			break;
		case R.id.btn_succeed_dialog_cancel:
			btn_DismissSucceedDialog();
			break;
		}
		
	}
	
	
	//成功对话框消失
	private void btn_DismissSucceedDialog() {
		succeed_dialog.dismiss();
	}
	
	//核对对话框中的提交
	private void btn_CheckDialogSubmit() {
		btn_DismissCheckDialog();
		showSecceedDialog();
	}

	//核对对话框消失
	private void btn_DismissCheckDialog() {
		check_dialog.dismiss();
	}

	//提醒对话框的下一步
	private void btn_ReminderDialogNext() {
		btn_DismissReminderDialog();
		showCheckDialog();
	}

	//提醒对话框消失
	private void btn_DismissReminderDialog() {
		reminder_dialog.dismiss();
	}
	//	选择银行类型
	private void btn_SelectBankSort() {
		setUnderLineBackgroundColor(5);
		ll_scroll_select.setVisibility(View.VISIBLE);
		btn_cancel.setVisibility(View.VISIBLE);
	}

	//提交
	private void btn_Submit() {
		
	}

	//滑动选择器中的确定
	private void btn_Ok() {
		ll_scroll_select.setVisibility(View.GONE);
		btn_cancel.setVisibility(View.GONE);
		tv_bank_sort.setText(select_bank_sort);
		showReminderDialog();
	}

	//滑动选择器中的取消
	private void btn_Cancel() {
		ll_scroll_select.setVisibility(View.GONE);
		btn_cancel.setVisibility(View.GONE);
	}

}
