package com.ugohb.android.domain;

import java.util.ArrayList;

public class HomeJson {
	public String status;
	public ArrayList<fl> flash;
	public class fl{
		public String id;
		public String images;
		public String title;
		public String url;
	}
	public ArrayList<homeItem> hot;
	public class homeItem{
		public String id;
		public String integral;
		public String name;
		public String thumb;
		public String title;
	}
	public ArrayList<channelItem> channel;
	public class channelItem{
		public String cid;
		public String banner;
		public ArrayList<homeItem> list;
	}
	
	
}
