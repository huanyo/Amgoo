package com.amgoo.activity;

import android.app.TabActivity;
import android.os.Bundle;

import com.amgoo.util.AnimCommonInTab;

public abstract class BaseTabActivity extends TabActivity
{

	// protected Session mSession;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView();
		initView();
	}

	protected void setContentView(int layoutResId, Boolean isShowTitle)
	{
		setContentView(layoutResId);
	}

	protected abstract void setContentView();
	protected abstract void initView();

	@Override
	protected void onPause()
	{
		super.onPause();
		if (AnimCommonInTab.ANIM_IN != 0 && AnimCommonInTab.ANIM_OUT != 0)
		{
			super.overridePendingTransition(AnimCommonInTab.ANIM_IN, AnimCommonInTab.ANIM_OUT);
			AnimCommonInTab.clear();
		}
	}
}
