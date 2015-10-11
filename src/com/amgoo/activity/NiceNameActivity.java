package com.amgoo.activity;

import java.util.ArrayList;

import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.amgoo.entiy.Login;
import com.amgoo.entiy.SetNiceName;
import com.amgoo.tool.ClearEditText;
import com.amgoo.tool.Contant;
import com.amgoo.tool.Variable;
import com.amgoo.util.DialogUtil;
import com.amgoo.util.HttpGetCallBack;
import com.amgoo.util.HttpQuest;
import com.amgoo.util.JsonParser;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;

public class NiceNameActivity extends Activity implements OnClickListener
{
	private TextView tvBack;
	private TextView tvSend;
	private ClearEditText userNichenContext;
	private Dialog mProgressLoading;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nice_name);
		initview();
	}

	private void initview()
	{
		tvBack = (TextView) findViewById(R.id.tv_back);
		tvSend = (TextView) findViewById(R.id.tv_nicenamesend);
		userNichenContext = (ClearEditText) findViewById(R.id.userNichenContext);
		tvBack.setOnClickListener(this);
		tvSend.setOnClickListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.nice_name, menu);
		return true;
	}

	@Override
	public void onClick(View v)
	{

		switch (v.getId())
		{
		case R.id.tv_back:
			finish();
			break;
		case R.id.tv_nicenamesend:
			setNiceName();
			
			break;

		default:
			break;
		}
	}

	private void setNiceName()
	{
		final String nicename = userNichenContext.getText().toString().trim();
		if (nicename != null)
		{
			Login data = Variable.getVariable().getData();
			String amgoo_ID = data.getData().getUser().getAmgoo_ID();
			String user_password = data.getData().getUser().getUser_password();
			ArrayList<BasicNameValuePair> paramsList = new ArrayList<BasicNameValuePair>();
			paramsList.add(new BasicNameValuePair("Amgoo_ID", amgoo_ID));
			paramsList.add(new BasicNameValuePair("user_password", user_password));
			paramsList.add(new BasicNameValuePair("user_petName", nicename));
			HttpQuest.Get(Contant.ServerDataUrl.NICENAME, paramsList, new HttpGetCallBack()
			{
				@Override
				public void onStart()
				{
				}

				@Override
				public void onSuccess(ResponseInfo<String> responseInfo)
				{
					stopProgress();
					SetNiceName setnicename = JsonParser.deserializeByJson(responseInfo.result, SetNiceName.class);
					if (setnicename.getStatus() == 1)
					{
						Variable.getVariable().getData().getData().getUser().setUser_petName(nicename);
						Toast.makeText(NiceNameActivity.this, getResources().getText(R.string.setscucess), Toast.LENGTH_SHORT).show();
						finish();
					}
				}

				@Override
				public void onFailure(HttpException error, String msg)
				{
				}
			});
		}

	}

	protected void stopProgress()
	{
		if (mProgressLoading != null)
		{
			mProgressLoading.dismiss();
			mProgressLoading = null;
		}
	}

	protected void startProgress()
	{
		if (mProgressLoading == null)
		{
			mProgressLoading = DialogUtil.createProgressDialog(this, getResources().getText(R.string.jiazai) + "", false);
		}
		if (!isFinishing())
		{
			mProgressLoading.show();
		}
	}
}
