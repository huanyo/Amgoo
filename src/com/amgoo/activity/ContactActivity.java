package com.amgoo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class ContactActivity extends Activity implements OnClickListener
{ 

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact);
		TextView tvContactBack = (TextView) findViewById(R.id.tv_contactback);
		tvContactBack.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.contact, menu);
		return true;
	}

	@Override
	public void onClick(View v)
	{
       		switch (v.getId())
			{
			case R.id.tv_contactback:
				finish();
				break;

			default:
				break;
			}
	}

}
