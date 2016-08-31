package com.ugohb.android.ui;

import com.ugohb.android.R;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @Description:自定义对话框
 * @author http://blog.csdn.net/finddreams
 */
public class LoadingDialog extends ProgressDialog {

	private Context mContext;

	public LoadingDialog(Context context) {
		super(context);
		this.mContext = context;
		setCanceledOnTouchOutside(false);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loading_dialog);
	}
}
