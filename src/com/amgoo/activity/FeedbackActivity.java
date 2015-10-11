package com.amgoo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.agmoo.picture.RoateConsultActivity;

public class FeedbackActivity extends Activity implements OnClickListener
{

	private LinearLayout linRestart;
	private LinearLayout linWiFi;
	private LinearLayout linSystem;
	private LinearLayout linBattery;
	private LinearLayout linBlueTooth;
	private LinearLayout linUnSystem;
	private LinearLayout linSignal;
	private LinearLayout linCamera;
	private LinearLayout linOther;
	private Intent intent;
	private static final int RESTART = 1; // 重启的反馈
	private static final int WIFI = 2;// wifi的反馈
	private static final int SYSTEM = 3;// 系统的反馈
	private static final int BATTERY = 4;// 电池的反馈
	private static final int BLUETOOTH = 5;// 蓝牙的反馈
	private static final int UNSYSTEM = 6;// 非系统的反馈
	private static final int SIGNAL = 7;// 信号的反馈
	private static final int CAMERA = 8;// 相机的反馈
	private static final int OTHER = 9;// 其他的反馈

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_feedback);
		initview();
	}

	private void initview()
	{
		linRestart = (LinearLayout) findViewById(R.id.lin_restart);
		linWiFi = (LinearLayout) findViewById(R.id.lin_wifi);
		linSystem = (LinearLayout) findViewById(R.id.lin_system);
		linBattery = (LinearLayout) findViewById(R.id.lin_battery);
		linBlueTooth = (LinearLayout) findViewById(R.id.lin_bluetooth);
		linUnSystem = (LinearLayout) findViewById(R.id.lin_unsystem);
		linSignal = (LinearLayout) findViewById(R.id.lin_signal);
		linCamera = (LinearLayout) findViewById(R.id.lin_camera);
		linOther = (LinearLayout) findViewById(R.id.lin_other);
		TextView tvFeeBack = (TextView) findViewById(R.id.tv_feeback);
		linRestart.setOnClickListener(this);
		linWiFi.setOnClickListener(this);
		linSystem.setOnClickListener(this);
		linBattery.setOnClickListener(this);
		linBlueTooth.setOnClickListener(this);
		linUnSystem.setOnClickListener(this);
		linSignal.setOnClickListener(this);
		linCamera.setOnClickListener(this);
		linOther.setOnClickListener(this);
		tvFeeBack.setOnClickListener(this);
		RelativeLayout relFeeFack = (RelativeLayout) findViewById(R.id.rel_feefack);
		relFeeFack.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.feedback, menu);
		return true;
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.lin_restart:
			intent = new Intent(FeedbackActivity.this, RoateConsultActivity.class);
			intent.putExtra("signl", RESTART);
			startActivity(intent);
			break;
		case R.id.lin_wifi:
			intent = new Intent(FeedbackActivity.this, RoateConsultActivity.class);
			intent.putExtra("signl", WIFI);
			startActivity(intent);
			break;
		case R.id.lin_system:
			intent = new Intent(FeedbackActivity.this, RoateConsultActivity.class);
			intent.putExtra("signl", SYSTEM);
			startActivity(intent);
			break;
		case R.id.lin_battery:
			intent = new Intent(FeedbackActivity.this, RoateConsultActivity.class);
			intent.putExtra("signl", BATTERY);
			startActivity(intent);
			break;
		case R.id.lin_bluetooth:
			intent = new Intent(FeedbackActivity.this, RoateConsultActivity.class);
			intent.putExtra("signl", BLUETOOTH);
			startActivity(intent);
			break;
		case R.id.lin_unsystem:
			intent = new Intent(FeedbackActivity.this, RoateConsultActivity.class);
			intent.putExtra("signl", UNSYSTEM);
			startActivity(intent);
			break;
		case R.id.lin_signal:
			intent = new Intent(FeedbackActivity.this, RoateConsultActivity.class);
			intent.putExtra("signl", SIGNAL);
			startActivity(intent);
			break;
		case R.id.lin_camera:
			intent = new Intent(FeedbackActivity.this, RoateConsultActivity.class);
			intent.putExtra("signl", CAMERA);
			startActivity(intent);
			break;
		case R.id.lin_other:
			intent = new Intent(FeedbackActivity.this, RoateConsultActivity.class);
			intent.putExtra("signl", OTHER);
			startActivity(intent);
			break;
		case R.id.tv_feeback:
			finish();
			break;

		default:
			break;
		}
	}

}
