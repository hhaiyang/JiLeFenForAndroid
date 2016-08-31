package com.ugohb.android.pages;

import com.ugohb.android.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AddJiFenPager extends BasePage implements OnClickListener {

	private View addjifenview;
	//输入积分数额
	private EditText et_jifen_number;
	//输入积分代码
	private EditText et_jifen_code;
	//提交
	private Button btn_submit;

	public AddJiFenPager(Context ct) {
		super(ct);
	}

	@Override
	public View initView(LayoutInflater inflater) {
		addjifenview = inflater.inflate(R.layout.pager_add_jifen, null);
		initUI();
		initListener();
		return addjifenview;
	}


	private void initUI() {
		et_jifen_number=(EditText) addjifenview.findViewById(R.id.et_jifen_number);
		et_jifen_code=(EditText) addjifenview.findViewById(R.id.et_jifen_code);
		btn_submit=(Button) addjifenview.findViewById(R.id.btn_submit);
	}

	private void initListener() {
		btn_submit.setOnClickListener(this);
	}

	
	/**
	 * 点击事件的监听
	 * @param v
	 */
	public void onClick(View v) {
		
	}

	@Override
	public void initData() {		
	}
}
