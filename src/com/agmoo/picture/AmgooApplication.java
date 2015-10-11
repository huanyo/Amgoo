package com.agmoo.picture;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Vibrator;
import android.view.WindowManager;

import com.amgoo.activity.R;
import com.amgoo.loadimg.MyVolley;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

/**
 * 主Application
 */
public class AmgooApplication extends Application
{

	public static final String ROOT_DIR = "sdcard/agmoo/";
	public static final String IMG_DIR = "map/";
	public static final String TRAVEL_IMG_DIR = "travelmap/";
	public Vibrator mVibrator;
	public Handler mHander;
	public static int mScreenWidth = 0;
	public static int mScreenHeight = 0;
	private static AmgooApplication instance;
	private List<Activity> activityList = new LinkedList<Activity>();
	public static UserInfo userInfo = new UserInfo();
	public static boolean isMoreData = false;

	@Override
	public void onCreate()
	{
		super.onCreate();
		init();
		initImageLoader(getApplicationContext());
		getScreenData();
		
	}

	private void getScreenData()
	{
		WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		mScreenWidth = wm.getDefaultDisplay().getWidth();
		mScreenHeight = wm.getDefaultDisplay().getHeight();
	}

	/**
	 * 图片异步加载初始�?
	 * 
	 * @param context
	 */
	public static void initImageLoader(Context context)
	{
		DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder().showImageForEmptyUri(R.drawable.empty_photo).showImageOnFail(R.drawable.empty_photo).cacheInMemory(true).cacheOnDisc(true).build();
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context).threadPriority(Thread.NORM_PRIORITY - 2).denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator()).tasksProcessingOrder(QueueProcessingType.LIFO).writeDebugLogs() // app
				.build();
		ImageLoader.getInstance().init(config);

	}
	/**
	 * Hanlder回调的参�?
	 * 
	 */
	public void postHander(Handler hander)
	{
		this.mHander = hander;
	}

	public static AmgooApplication getInstance()
	{
		if (null == instance)
		{
			instance = new AmgooApplication();
		}
		return instance;
	}

	public void addActivity(Activity activity)
	{
		activityList.add(activity);
	}

	public void removeActivity(Activity activity)
	{
		if (activityList.contains(activity))
		{
			activityList.remove(activity);
		}
		if (activityList.size() == 0)
		{
			// 取消亮屏
			// if (wl != null) {
			// wl.release();
			// }
		}
	}
	private void init()
	{
		MyVolley.init(this);
	}

}
