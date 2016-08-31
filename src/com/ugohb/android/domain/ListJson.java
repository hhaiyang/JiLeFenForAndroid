package com.ugohb.android.domain;

import java.util.ArrayList;

public class ListJson {
	public String status;
	public String flag;
	public String isnext;
	public String next;
	public ArrayList<liItem> result;
	public class liItem{
		public String id;
		public String name;
		public String address;
		public String newactivity;
		public String img;
		public String integraled;
		public String ints;
		public String discount;
	}	
}
