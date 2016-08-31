package com.ugohb.android;


import java.util.ArrayList;
import java.util.List;

import com.ugohb.android.adapter.ExchangeRecordeAdapter;
import com.ugohb.android.utils.Utils;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;

public class DuiActivity extends Activity implements OnClickListener {

	private ListView listView;
	private ImageButton ib_back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dui);
		initUI();
		initListener();
	}

	private void initListener() {
		ib_back.setOnClickListener(this);
		listView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Utils.showToast(getApplicationContext(), "ƒ„—°‘Ò¡À"+position);
			}
			
		});
		
		
		
		
	}

	private void initUI() {
		ib_back = (ImageButton) findViewById(R.id.tv_back);
		
		listView = (ListView) findViewById(R.id.exchange_recorde_listview);
		List<String> list=new ArrayList<String>();
		list.add("noiasdf");
		list.add("noiasdf");
		list.add("noiasdf");
		list.add("noiasdf");
		list.add("noiasdf");
		list.add("noiasdf");
		list.add("noiasdf");
		list.add("noiasdf");
		list.add("noiasdf");
		list.add("noiasdf");
		list.add("noiasdf");
		list.add("noiasdf");
		list.add("noiasdf");
		list.add("noiasdf");
		list.add("noiasdf");
		
		listView.setAdapter(new ExchangeRecordeAdapter<String>(this, list));
		
	}

	public void onClick(View v) {
		Utils.showToast(this, "∑µªÿ");
		
	}
	
	


}
