package com.amgoo.zxing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.amgoo.activity.R;

public class ResultActivity extends Activity
{
	private String mSaomiaoResult;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result);
		initData();
		initilizeTopBar();
		initView();
	}

	private void initData()
	{
		Intent intent = getIntent();
		mSaomiaoResult = intent.getStringExtra("result");
	}

	private void initView()
	{
		TextView erweima_result = (TextView) findViewById(R.id.erweima_result);
		erweima_result.setText(mSaomiaoResult);
	}

	private void initilizeTopBar()
	{
		View mTopBarBack = findViewById(R.id.mTopBarBack);
		mTopBarBack.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent Intent = new Intent(ResultActivity.this, CaptureActivity.class);
				startActivity(Intent);
				finish();
			}
		});
		TextView mTopBarText = (TextView) findViewById(R.id.mTopBarText);
		mTopBarText.setText("扫描结果");
	}

}
