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
import com.amgoo.entiy.SetPhone;
import com.amgoo.tool.ClearEditText;
import com.amgoo.tool.Contant;
import com.amgoo.tool.Variable;
import com.amgoo.util.DialogUtil;
import com.amgoo.util.HttpGetCallBack;
import com.amgoo.util.HttpQuest;
import com.amgoo.util.JsonParser;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;

public class TelActivity extends Activity implements OnClickListener
{
	private ClearEditText phoneContext;
	private Dialog mProgressLoading;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tel);
		TextView tvPhoneSend = (TextView) findViewById(R.id.tv_phonesend);
		TextView tvTelBack = (TextView) findViewById(R.id.tv_telback);
		phoneContext = (ClearEditText) findViewById(R.id.phoneContext);
		tvPhoneSend.setOnClickListener(this);
		tvTelBack.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.tel, menu);
		return true;
	}

	@Override
	public void onClick(View v)
	{

		switch (v.getId())
		{
		case R.id.tv_phonesend:
			setTelNum();

			break;
		case R.id.tv_telback:
			finish();
			break;

		default:
			break;
		}
	}

	private void setTelNum()
	{

		final String telnum = phoneContext.getText().toString().trim();
		if (telnum != null)
		{
			Login data = Variable.getVariable().getData();
			String amgoo_ID = data.getData().getUser().getAmgoo_ID();
			String user_password = data.getData().getUser().getUser_password();
			ArrayList<BasicNameValuePair> paramsList = new ArrayList<BasicNameValuePair>();
			paramsList.add(new BasicNameValuePair("Amgoo_ID", amgoo_ID));
			paramsList.add(new BasicNameValuePair("user_password", user_password));
			paramsList.add(new BasicNameValuePair("user_telephone", telnum));
			HttpQuest.Get(Contant.ServerDataUrl.TELPHONE, paramsList, new HttpGetCallBack()
			{
				@Override
				public void onStart()
				{
				}

				@Override
				public void onSuccess(ResponseInfo<String> responseInfo)
				{
					stopProgress();
					SetPhone setphone = JsonParser.deserializeByJson(responseInfo.result, SetPhone.class);
					if (setphone.getStatus() == 1)
					{
						Variable.getVariable().getData().getData().getUser().setUser_telephone(telnum);
						Toast.makeText(TelActivity.this, getResources().getText(R.string.setscucess), Toast.LENGTH_SHORT).show();
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
