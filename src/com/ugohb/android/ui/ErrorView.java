package com.ugohb.android.ui;

import com.ugohb.android.R;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * @Description:自定义对话框
 * @author http://blog.csdn.net/finddreams
 */
public class ErrorView extends RelativeLayout {

	private Context mContext;
	private View root;
	private ViewReloadListener reloadListener;
	private TextView btn_reload;

	public ErrorView(Context context) {
		super(context);
		this.mContext = context;
		initView();
	}
	private void initView() {
		root=View.inflate(mContext, R.layout.view_error,null);
		btn_reload=(TextView) root.findViewById(R.id.btn_reload);
		
			btn_reload.setOnClickListener(new OnClickListener() {				
				@Override
				public void onClick(View v) {
					if (reloadListener!=null) {
						reloadListener.onReload();
					}
				}
			});
		
	}
	public View getRootView(){
		return root;
	}
	
	public void setOnViewReloadListener(ViewReloadListener listener){
		reloadListener=listener;
	}
	public interface ViewReloadListener {
		public void onReload();
	}
}
