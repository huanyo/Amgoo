package com.amgoo.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amgoo.tool.DataCleanManager;
import com.amgoo.tool.Variable;

public class SetActivity extends Activity implements OnClickListener
{
	private TextView tvVision;
	private TextView tvDatasize;
	private LinearLayout linDataClean;
	private LinearLayout linContactMy;
	private LinearLayout linExit;
	private LinearLayout linFileDownload;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_set);
		tvVision = (TextView) findViewById(R.id.tv_vision);
		tvDatasize = (TextView) findViewById(R.id.tv_datasize);
		linDataClean = (LinearLayout) findViewById(R.id.lin_dataclean);
		linContactMy = (LinearLayout) findViewById(R.id.lin_contactmy);
		linFileDownload = (LinearLayout) findViewById(R.id.lin_filedownload);
		linExit = (LinearLayout) findViewById(R.id.lin_exit);
		linDataClean.setOnClickListener(this);
		linExit.setOnClickListener(this);
		linFileDownload.setOnClickListener(this);
		tvVision.setText(getVersion());
		try
		{
			String totalCacheSize = DataCleanManager.getTotalCacheSize(SetActivity.this);
			tvDatasize.setText(totalCacheSize);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		linContactMy.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.set, menu);
		return true;
	}

	public String getVersion()
	{
		try
		{
			PackageManager manager = getPackageManager();
			PackageInfo info = manager.getPackageInfo(getPackageName(), 0);
			String version = info.versionName;
			return "版本号：v" + version;
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	@Override
	protected void onResume()
	{
		super.onResume();

	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.lin_dataclean:
			DataCleanManager.clearAllCache(SetActivity.this);
			try
			{
				String totalCacheSize = DataCleanManager.getTotalCacheSize(SetActivity.this);
				tvDatasize.setText(totalCacheSize);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			Toast.makeText(this, getResources().getText(R.string.cleandata), Toast.LENGTH_SHORT).show();

			break;
		case R.id.lin_contactmy:
			Intent intent = new Intent(SetActivity.this, ContactActivity.class);
			startActivity(intent);
			break;
		case R.id.lin_exit:
			Variable.getVariable().setData(null);
			finish();
			break;
		case R.id.lin_filedownload:
			Intent intent1 = new Intent(SetActivity.this, FileClassActivity.class);
			startActivity(intent1);
			break;

		default:
			break;
		}
	}
}
