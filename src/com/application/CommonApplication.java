package com.application;

import android.app.Application;
import android.util.Log;
import cn.jpush.android.api.JPushInterface;

public class CommonApplication extends Application {
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.e("debug", "commonApplication  is  oncreate");
		JPushInterface.setDebugMode(true);
		JPushInterface.init(this);  
		//µ˜”√JPush API…Ë÷√Alias
		JPushInterface.setAliasAndTags(
				this,
				"35", null);
	}

}
