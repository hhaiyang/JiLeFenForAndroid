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
	//�����������
	private EditText et_jifen_number;
	//������ִ���
	private EditText et_jifen_code;
	//�ύ
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
	 * ����¼��ļ���
	 * @param v
	 */
	public void onClick(View v) {
		
	}

	@Override
	public void initData() {		
	}
}
