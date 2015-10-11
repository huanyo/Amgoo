package com.amgoo.activity;

import java.util.ArrayList;

import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.widget.TextView;

import com.amgoo.entiy.TureQuery;
import com.amgoo.entiy.TureQueryObject;
import com.amgoo.tool.Contant;
import com.amgoo.util.DialogUtil;
import com.amgoo.util.HttpGetCallBack;
import com.amgoo.util.HttpQuest;
import com.amgoo.util.JsonParser;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;

public class TureQueryActivity extends Activity
{
	private Dialog mProgressLoading;
	private TextView tvTureQueryResult;
	private TextView tvQuerySaler;
	private TextView tvQueryTime;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ture_query);
		tvTureQueryResult = (TextView) findViewById(R.id.tv_turequeryresult);
		tvQuerySaler = (TextView) findViewById(R.id.tv_querysaler);
		tvQueryTime = (TextView) findViewById(R.id.tv_querytime);
		TureQuery();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.ture_query, menu);
		return true;
	}

	private void TureQuery()
	{
		TelephonyManager mTm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		String imei = mTm.getDeviceId();
		ArrayList<BasicNameValuePair> paramsList = new ArrayList<BasicNameValuePair>();
		paramsList.add(new BasicNameValuePair("imei", imei));
		HttpQuest.Get(Contant.ServerDataUrl.TUREQUERY, paramsList, new HttpGetCallBack()
		{
			@Override
			public void onStart()
			{

			}

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo)
			{
				stopProgress();
				TureQuery turequery = JsonParser.deserializeByJson(responseInfo.result, TureQuery.class);
				if (turequery.getStatus() == 1)
				{
					ArrayList<TureQueryObject> data = turequery.getData();
					for (int i = 0; i < data.size(); i++)
					{
						String sale_user = data.get(i).getSale_user();
						String sale_time = data.get(i).getSale_time();
						tvQuerySaler.setText(sale_user);
						tvQueryTime.setText(sale_time);
						tvTureQueryResult.setText(getResources().getText(R.string.turephone));

					}
				} else
				{
					tvTureQueryResult.setText(getResources().getText(R.string.falsephone));
				}
			}

			@Override
			public void onFailure(HttpException error, String msg)
			{
			}
		});
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
