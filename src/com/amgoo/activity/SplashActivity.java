package com.amgoo.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class SplashActivity extends Activity
{

	private ImageView mImageView;
	private ImageView mImageView1;
	private AnimationDrawable drawable;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		new Handler().postDelayed(new Runnable()
		{

			@Override
			public void run()
			{
				finish();
				overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);				
				SharedPreferences sp = getPreferences(Context.MODE_PRIVATE);
				boolean isFirst = sp.getBoolean("isFirst", true);
				if (isFirst)
				{
					sp.edit().putBoolean("isFirst", false).commit();
					startActivity(new Intent(SplashActivity.this, GuideActivity.class));
				} else
				{
					startActivity(new Intent(SplashActivity.this, MainActivity.class));
				}
			}
		}, 4000);

	}


	private void initAnimal()
	{

		drawable = (AnimationDrawable) mImageView.getDrawable();
		new Handler().postDelayed(new Runnable()
		{

			@Override
			public void run()
			{
				drawable.start();
			}
		}, 300);

	}

}
