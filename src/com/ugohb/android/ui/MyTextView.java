package com.ugohb.android.ui;

import com.ugohb.android.R;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

public class MyTextView extends TextView{

	private String text;
	public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public MyTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyTextView(Context context) {
		super(context);
	}
	Paint paint;
	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		if(paint==null){
			paint=new Paint(Paint.ANTI_ALIAS_FLAG);
			paint.setStrokeWidth(2.0f);
			paint.setColor(getResources().getColor(R.color.COLOR_GREY));
		}
		canvas.drawLine(0, getHeight()/2, getWidth(),  getHeight()/2, paint);
	}
	
	

}
