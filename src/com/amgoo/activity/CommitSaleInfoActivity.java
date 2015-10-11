package com.amgoo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class CommitSaleInfoActivity extends Activity implements OnClickListener
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_commit_sale_info);
		TextView tvInfoBack = (TextView) findViewById(R.id.tv_infoback);
		tvInfoBack.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.commit_sale_info, menu);
		return true;
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.tv_infoback:
			finish();
			break;

		default:
			break;
		}
	}

}
