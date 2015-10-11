package com.amgoo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class SexSelectActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sex_select);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.sex_select, menu);
		return true;
	}

}
