package com.amgoo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.amgoo.tool.ClearEditText;

public class EmailActivity extends Activity implements OnClickListener
{
	private ClearEditText emailContext;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_email);
		emailContext = (ClearEditText) findViewById(R.id.emailContext);
		TextView tvEmailSend = (TextView) findViewById(R.id.tv_emailsend);
		TextView tvEmailBack = (TextView) findViewById(R.id.tv_emailback);
		tvEmailSend.setOnClickListener(this);
		tvEmailBack.setOnClickListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.email, menu);
		return true;
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.tv_emailsend:
			String email = emailContext.getText().toString().trim();
			Intent intent = new Intent(EmailActivity.this, MyinfoActivity.class);
			intent.putExtra("email", email);
			setResult(11, intent);
			finish();
			break;
		case R.id.tv_emailback:
			finish();
			break;

		default:
			break;
		}
	}

}
