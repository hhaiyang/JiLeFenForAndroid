package com.ugohb.android;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.UiSettings;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkRouteResult;
import com.ugohb.android.constant.Constants;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
public class MapActivity extends Activity{
	MapView mMapView = null;
	private AMap aMap;
	private  UiSettings aMapSet;
	private MarkerOptions markerOpt;
	ImageView iv_map_back;
	
	/**
	 * ·�߲�ѯ
	 */
	private RouteSearch mRouteSearch;
	private DriveRouteResult mDriveRouteResult;
	private BusRouteResult mBusRouteResult;
	private WalkRouteResult mWalkRouteResult;
	private LatLonPoint mStartPoint = new LatLonPoint(39.923271, 116.396034);//��㣬
	private LatLonPoint mEndPoint = new LatLonPoint(39.984947, 116.494689);//�յ㣬
	
	private final int ROUTE_TYPE_BUS = 1;
	private final int ROUTE_TYPE_DRIVE = 2;
	private final int ROUTE_TYPE_WALK = 3;
	
	private LinearLayout mBusResultLayout;
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		iv_map_back=(ImageView) findViewById(R.id.iv_map_back);
		//��ȡ��ͼ�ؼ�����
	    mMapView = (MapView) findViewById(R.id.map);
	    //��activityִ��onCreateʱִ��mMapView.onCreate(savedInstanceState)��ʵ�ֵ�ͼ�������ڹ���
	    mMapView.onCreate(savedInstanceState);
	    initMap();
	    initListen();
	}
	private void initListen() {
		iv_map_back.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
	}
	private void initMap() {
		if (aMap==null) {
			aMap=mMapView.getMap();
		}
		if (aMapSet==null) {
			aMapSet=aMap.getUiSettings();
		}
		if(markerOpt==null)
		{
			markerOpt=new MarkerOptions();
			markerOpt.anchor(0.5f, 0.5f);
			markerOpt.position(Constants.HUAIBEI);
			markerOpt.title("������");
			//markerOpt.snippet("������");
		}
		mRouteSearch = new RouteSearch(this);
		mBusResultLayout = (LinearLayout) findViewById(R.id.bus_result);
		
		
		setUpMap();
	}
	private void setUpMap() {
		aMapSet.setMyLocationButtonEnabled(true);
		aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.HUAIBEI,12f));
		aMap.addMarker(markerOpt);
		
	}	
	
	/**
	 * 
	 * @param view
	 */
	public void onBusClick(View view) {
		//searchRouteResult(ROUTE_TYPE_BUS, RouteSearch.BusDefault);
		
		mMapView.setVisibility(View.GONE);
		mBusResultLayout.setVisibility(View.VISIBLE);
	}
	
	public void onDriveClick(View view) {
		//searchRouteResult(ROUTE_TYPE_DRIVE, RouteSearch.DrivingDefault);
		
		mBusResultLayout.setVisibility(View.GONE);
	}

	public void onWalkClick(View view) {
		//searchRouteResult(ROUTE_TYPE_WALK, RouteSearch.WalkDefault);
		
		mBusResultLayout.setVisibility(View.GONE);
	}
	
	
	
	
	
	
	
	
	
	
	
	@Override
	  protected void onDestroy() {
	    super.onDestroy();
	    //��activityִ��onDestroyʱִ��mMapView.onDestroy()��ʵ�ֵ�ͼ�������ڹ���
	    mMapView.onDestroy();
	  }
	 @Override
	 protected void onResume() {
	    super.onResume();
	    //��activityִ��onResumeʱִ��mMapView.onResume ()��ʵ�ֵ�ͼ�������ڹ���
	    mMapView.onResume();
	    }
	 @Override
	 protected void onPause() {
	    super.onPause();
	    //��activityִ��onPauseʱִ��mMapView.onPause ()��ʵ�ֵ�ͼ�������ڹ���
	    mMapView.onPause();
	    }
	 @Override
	 protected void onSaveInstanceState(Bundle outState) {
	    super.onSaveInstanceState(outState);
	    //��activityִ��onSaveInstanceStateʱִ��mMapView.onSaveInstanceState (outState)��ʵ�ֵ�ͼ�������ڹ���
	    mMapView.onSaveInstanceState(outState);
	  }
   
}
