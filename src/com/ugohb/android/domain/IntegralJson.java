package com.ugohb.android.domain;

import java.util.ArrayList;

public class IntegralJson {
	public String status;
	public String flag;
	public ArrayList<shopItem> shops;
	public class shopItem{
		public String id;
		public String title;
		public String brief;
		public String thumb;
		public String price;
		public String cost;
	}	
}
