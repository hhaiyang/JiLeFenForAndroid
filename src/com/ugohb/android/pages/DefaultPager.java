package com.ugohb.android.pages;

import com.ugohb.android.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

public class DefaultPager extends BasePage {

	private View default_view;

	public DefaultPager(Context ct) {
		super(ct);
	}

	@Override
	public View initView(LayoutInflater inflater) {
		default_view = inflater.inflate(R.layout.pager_default, null);
		return default_view;
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		
	}

}
