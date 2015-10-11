package com.amgoo.zxing;

import android.content.Context;
import android.text.Spanned;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.amgoo.activity.R;

public class ToastUtil
{
	private Toast mToast;
	private static ToastUtil mInstance;
	public static int SHORT = Toast.LENGTH_SHORT;
	public static int LONG = Toast.LENGTH_LONG;

	public static ToastUtil getInstance(Context context)
	{
		if (mInstance == null)
		{
			mInstance = new ToastUtil(context);
		}
		return mInstance;
	}

	private ToastUtil(Context context)
	{
		mToast = makeToast(context);
	}
 
	public Toast makeToast(Context context)
	{
		if (mToast == null)
		{
			mToast = new Toast(context);
			LayoutInflater inflater = LayoutInflater.from(context);
			View view = inflater.inflate(R.layout.toast, null);
			mToast.setView(view);
			mToast.setDuration(Toast.LENGTH_LONG);
			mToast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0,
                    200);
		}
		return mToast;
	}

	/**
	 * 两行toast
	 * */
	/**
	 * 
	 * @param content
	 *            要显示的文本
	 * @param duration
	 *            {@link Toast#LENGTH_SHORT} {@link Toast#LENGTH_LONG}
	 */
	public void show(int content, int durationTime)
	{
		try
		{
			mToast.setText(content);
			mToast.setDuration(durationTime);
			mToast.show();
		} catch (Exception e)
		{
			// LogUtils.e("e:" + e.toString());
		}
	}

	 
	/**
	 * 
	 * @param content
	 *            要显示的文本
	 * @param duration
	 *            {@link Toast#LENGTH_SHORT} {@link Toast#LENGTH_LONG}
	 */
	public void show(Spanned content, int duration)
	{
		// toast.cancel();
		mToast.setText(content);
		mToast.setDuration(duration);
		mToast.show();
	}

	/**
	 * 
	 * @param content
	 *            要显示的文本
	 * @param duration
	 *            {@link Toast#LENGTH_SHORT} {@link Toast#LENGTH_LONG}
	 */
	public void show(String content, int duration)
	{
		mToast.setText(content);
		mToast.setDuration(duration);
		mToast.show();
	}

	/**
	 * 
	 * @param content
	 *            要显示的文本
	 * @param duration
	 *            {@link Toast#LENGTH_SHORT} {@link Toast#LENGTH_LONG}
	 */
	public void showShort(String content)
	{
		mToast.setText(content);
		mToast.setDuration(Toast.LENGTH_SHORT);
		mToast.show();
	}

	/**
	 * 
	 * @param contentx
	 *            要显示的文本
	 * @param duration
	 *            {@link Toast#LENGTH_SHORT} {@link Toast#LENGTH_LONG}
	 */
	public void showLong(String content)
	{
		mToast.setText(content);
		mToast.setDuration(Toast.LENGTH_LONG);
		mToast.show();
	}

	/**
	 * 
	 * @param content
	 *            要显示的文本
	 * @param duration
	 *            {@link Toast#LENGTH_SHORT} {@link Toast#LENGTH_LONG}
	 */
	public void showErrorTips()
	{

		mToast.setDuration(Toast.LENGTH_LONG);
		mToast.show();
	}

}
