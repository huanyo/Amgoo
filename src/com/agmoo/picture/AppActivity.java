package com.agmoo.picture;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.amgoo.activity.R;

/**
 * @describe Activity基类，所有的Activity 都要继续于它，方便调用公用方法�?
 */
public class AppActivity extends Activity
{
	private ProgressDialog load;
	protected boolean abortCheckVersion = false;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		App.initAppContext(isChild() ? getParent() : this);
		super.onCreate(savedInstanceState);
	}

	public void closeProgress()
	{
		if (load != null)
		{
			load.dismiss();
		}
	}

	// protected UserInfo getUser()
	// {
	// return
	// SessionManager.getInstance(this.getApplicationContext()).getUser(this.getApplicationContext());
	// }
	@Override
	protected void onResume()
	{
		App.initAppContext(isChild() ? getParent() : this);
		super.onResume();
		App.runOutBackground();

		if (!abortCheckVersion)
		{
			// checkVersion();
		}
	}

	public void showWaitingProgress()
	{
		
		String title = getResources().getString(R.string.jiazai), message = getResources().getString(R.string.shaohou);
		showWaitingProgress(title, message);
	}

	protected void showWaitingProgress(String title, String message)
	{
		load = new ProgressDialog(this, title, message);
		load.show();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		super.onKeyDown(keyCode, event);
		switch (keyCode)
		{
		case KeyEvent.KEYCODE_MENU:

			break;
		case KeyEvent.KEYCODE_BACK:
			backKeyCallBack();
			break;
		}
		return false;
	}

	/**
	 * 界面返回监听
	 */
	protected void backKeyCallBack()
	{
		System.out.println("backKeyCallBack");
	}

	/**
	 * titleBar 左上角监听�?
	 * 
	 * @param listener
	 */
	protected void setBarLeftOnClickListener(View.OnClickListener listener)
	{
		findViewById(R.id.bar_iv_left).setOnClickListener(listener);
	}

	/**
	 * titleBar 右上角监听�?
	 * 
	 * @param listener
	 */
	protected void setBarRightOnClickListener(View.OnClickListener listener)
	{
		findViewById(R.id.bar_ly_right).setOnClickListener(listener);
	}

	/**
	 * titleBar 隐藏左上角按�?
	 * 
	 * @param listener
	 */
	protected void hideBarLeft()
	{
		findViewById(R.id.bar_iv_left).setVisibility(View.INVISIBLE);
	}

	// protected UserInfo getUser()
	// {
	// return
	// SessionManager.getInstance(this.getApplicationContext()).getUser(this.getApplicationContext());
	// }
	/**
	 * titleBar 隐藏右上角按�?
	 * 
	 * @param listener
	 */
	protected void hideBarRight()
	{
		findViewById(R.id.bar_iv_right).setVisibility(View.GONE);
	}

	protected void setBarRightDarwble(int resId)
	{
		ImageView ivRight = (ImageView) findViewById(R.id.bar_iv_right);
		ivRight.setImageResource(resId);
	}

	protected void setBarRightGone()
	{
		findViewById(R.id.bar_iv_right).setVisibility(View.GONE);
	}

	protected void setBarCenterText(String text)
	{
		TextView title = (TextView) findViewById(R.id.bar_tv_title);
		title.setText(text);
	}

	protected void setBarRightText(String text)
	{
		setBarRightGone();
		TextView rightText = (TextView) findViewById(R.id.bar_tv_right);
		rightText.setVisibility(View.VISIBLE);
		rightText.setText(text);
	}

}
