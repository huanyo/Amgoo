package com.amgoo.zxing;

import android.app.Application;
import android.content.Context;


public class FanhuanApplication extends Application
{
	private static FanhuanApplication instance;
	// app启动状态判别，防止app被杀进程软件强杀后，应用一些静态变量导致的空指针异常。
	public static int mAppState;
	// mainActivity启动状态判别,判断是否直接打开开抢提醒。
	private boolean mainActivityIsRunning = false;
	public static Context mContext;
	/* 淘宝用户授权信息 */
	private String receivePushMsg;
	private String receivePushId;
	public static int notificationId = 0;

	public boolean isMainActivityIsRunning()
	{
		return mainActivityIsRunning;
	}

	public void setMainActivityIsRunning(boolean mainActivityIsRunning)
	{
		this.mainActivityIsRunning = mainActivityIsRunning;
	}

	public String getReceivePushId()
	{
		return receivePushId;
	}

	public void setReceivePushId(String receivePushId)
	{
		this.receivePushId = receivePushId;
	}

	public String getReceivePushMsg()
	{
		return receivePushMsg;
	}

	public void setReceivePushMsg(String receivePushMsg)
	{
		this.receivePushMsg = receivePushMsg;
	}

	public FanhuanApplication()
	{
		instance = this;
	}

	public static FanhuanApplication getInstance()
	{
		if (instance == null)
		{
			instance = new FanhuanApplication();
		}
		return instance;
	}

	public synchronized static void setAppState(int state)
	{
		mAppState = state;
	}

	@Override
	public void onCreate()
	{
		super.onCreate();
		mAppState = -1;
		mContext = this.getApplicationContext();
	}

}
