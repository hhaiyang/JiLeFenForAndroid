package com.ugohb.android.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SPUtils {
	public static final String SP_NAME="config";
	public static boolean getSPboolen(Context ct, String key,
			boolean defVal) {
		SharedPreferences sp=ct.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
		return sp.getBoolean(key, defVal);		
	}
	public static void setSPboolean(Context ct, String key, boolean val){
		SharedPreferences sp=ct.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
		sp.edit().putBoolean(key, val).commit();
	}
}
