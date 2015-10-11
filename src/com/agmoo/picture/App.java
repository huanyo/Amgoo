package com.agmoo.picture;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.Toast;

/**
 * @describe APP activity
 */
public abstract class App
{
	public final static int MQ_NEW_MESSAGE = 100;
	private static final String TAG = App.class.getSimpleName();
	private static Activity currentActivity;
	private static boolean isFirst = true;
	private static AtomicBoolean isBackground = new AtomicBoolean(false);
	private static List<Activity> activityList = new ArrayList<Activity>();

	/**
	 * Activity keeper
	 * 
	 * @param activity
	 */
	public static void initAppContext(Activity activity)
	{
		currentActivity = activity;
		activityList.add(activity);
		initApp();
	}

	public static void initApp()
	{
		if (isFirst)
		{
			isFirst = false;
		}
	}

	public static void removeActivity(Activity activity)
	{
		if (activityList.contains(activity))
		{
			activityList.remove(activity);
		}
	}

	public static void finishAllActivity()
	{
		for (Activity act : activityList)
		{
			if (null != act)
			{
				act.finish();
			}
		}
		activityList.clear();
	}

	/**
	 * System API helper
	 * 
	 */
	public static LayoutInflater getLayoutInflater()
	{
		return LayoutInflater.from(currentActivity);
	}

	public static Activity getCurrentActivity()
	{
		return currentActivity;
	}

	public static void startActivity(Intent intent)
	{
		currentActivity.startActivity(intent);
	}

	public static void startActivityForResult(Intent intent, int requestCode)
	{
		currentActivity.startActivityForResult(intent, requestCode);
	}

	public static ArrayAdapter<String> buildArrayAdapter()
	{
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(currentActivity, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		return adapter;
	}

	/**
	 * Notify Dialog
	 * 
	 */
	public static AlertDialog.Builder buildDialog()
	{
		return new AlertDialog.Builder(currentActivity);
	}

	private static AppActivity _appActivity;

	public static void initChildActivity(AppActivity appActivity)
	{
		_appActivity = appActivity;
	}

	public static void postRunnable(Runnable task)
	{
		if (null != _appActivity)
		{
			_appActivity.runOnUiThread(task);
		}
	}

	public static boolean isBackground()
	{
		return isBackground.get();
	}

	public static void runInBackground()
	{
		App.isBackground.set(true);
	}

	public static void runOutBackground()
	{
		App.isBackground.set(false);
	}

	public static void dispose()
	{
		isFirst = true;
	}

	public static void toast(String mes)
	{
		toast(mes, Toast.LENGTH_LONG);
	}

	public static void toast(String mes, int duration)
	{
		Toast toast = Toast.makeText(currentActivity, mes, duration);
		toast.show();
	}
}
