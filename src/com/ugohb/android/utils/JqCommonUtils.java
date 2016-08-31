package com.ugohb.android.utils;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.MeasureSpec;
import android.widget.ListAdapter;
import android.widget.ListView;

public class JqCommonUtils {
	public	static void delay(int ms){  
        try {  
            Thread.currentThread();  
            Thread.sleep(ms);  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }   
    } 
	/**
	 * 根据手机的分辨率仿 dp 的单使 转成丿 px(像素)
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率仿 px(像素) 的单使 转成丿 dp
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

    public static void setTotalHeightofListView(ListView listView) {  
        ListAdapter mAdapter = listView.getAdapter();   
       if (mAdapter == null) {  
           return;  
       }  
        int totalHeight = 0;  
        for (int i = 0; i < mAdapter.getCount(); i++) {  
            View mView = mAdapter.getView(i, null, listView);  
            mView.measure(  
                    MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),  
                    MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));  
            //mView.measure(0, 0);  
            totalHeight += mView.getMeasuredHeight();
        }  
        ViewGroup.LayoutParams params = listView.getLayoutParams();  
        params.height = totalHeight           + (listView.getDividerHeight() * (mAdapter.getCount() - 1));  
        listView.setLayoutParams(params);  
        listView.requestLayout();      
    }  
}
