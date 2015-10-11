package com.amgoo.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends BaseTabActivity
{
	private TabHost mTabHost;
	private static final String TAB_HOMEPAGE = "homepage";
	private static final String TAB_SCHOOL = "school";
	private static final String TAB_SERVICE = "service";
	private static final String TAB_MY = "my";
	private long exitTime = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

	}

	public TabHost getmTabHost()
	{
		return mTabHost;
	}

	private View createTabView(Context context, String textRes, int imageResource)
	{
		View view = LayoutInflater.from(context).inflate(R.layout.main_tab_item, null);
		Drawable drawable = getResources().getDrawable(imageResource);
		drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
		TextView tabText = (TextView) view.findViewById(R.id.tv_tab_text);
		tabText.setText(textRes);
		tabText.setCompoundDrawables(null, drawable, null, null);
		return view;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void setContentView()
	{
		setContentView(R.layout.activity_main);
	}

	@Override
	protected void initView()
	{
		mTabHost = (TabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup();
		View v1 = createTabView(getApplicationContext(), "产品", R.drawable.tab_btn_zdm);
		TabSpec tab1 = mTabHost.newTabSpec(TAB_HOMEPAGE).setIndicator(v1).setContent(new Intent(this, ProductActivity.class));
		View v2 = createTabView(getApplicationContext(), "学堂", R.drawable.tab_btn_fh);
		TabSpec tab2 = mTabHost.newTabSpec(TAB_SCHOOL).setIndicator(v2).setContent(new Intent(this, SchoolActivity.class));
		View v3 = createTabView(getApplicationContext(), "服务", R.drawable.tab_btn_fx);
		TabSpec tab3 = mTabHost.newTabSpec(TAB_SERVICE).setIndicator(v3).setContent(new Intent(this, SeviceActivity.class));
		View v4 = createTabView(getApplicationContext(), "我的", R.drawable.tab_btn_my);
		TabSpec tab4 = mTabHost.newTabSpec(TAB_MY).setIndicator(v4).setContent(new Intent(this, MyActivity.class));
		mTabHost.addTab(tab1);
		mTabHost.addTab(tab2);
		mTabHost.addTab(tab3);
		mTabHost.addTab(tab4);
		v1.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				mTabHost.setCurrentTab(0);
			}
		});
		v2.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				mTabHost.setCurrentTab(1);
			}
		});
		v3.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				mTabHost.setCurrentTab(2);
			}
		});
		v4.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				mTabHost.setCurrentTab(3);
			}
		});
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event)
	{
		if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN && event.getRepeatCount() == 0)
		{
			if ((System.currentTimeMillis() - exitTime) > 2000)
			{
				Toast.makeText(this, getResources().getText(R.string.quit), Toast.LENGTH_SHORT).show();
				exitTime = System.currentTimeMillis();
			} else
			{
				finish();
			}
			return true;
		}
		return super.dispatchKeyEvent(event);
	}

}
