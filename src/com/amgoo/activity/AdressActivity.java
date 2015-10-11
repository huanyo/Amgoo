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
import com.amgoo.entiy.SetAdress;
import com.amgoo.tool.ClearEditText;
import com.amgoo.tool.Contant;
import com.amgoo.tool.Variable;
import com.amgoo.util.DialogUtil;
import com.amgoo.util.HttpGetCallBack;
import com.amgoo.util.HttpQuest;
import com.amgoo.util.JsonParser;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;

public class AdressActivity extends Activity implements OnClickListener
{
	private ClearEditText adressContext;
	private Dialog mProgressLoading;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_adress);
		adressContext = (ClearEditText) findViewById(R.id.adressContext);
		TextView tvAdressSend = (TextView) findViewById(R.id.tv_adresssend);
		TextView tvAdressBack = (TextView) findViewById(R.id.tv_adressback);
		tvAdressBack.setOnClickListener(this);
		tvAdressSend.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.adress, menu);
		return true;
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.tv_adresssend:
			setAdress();
			break;
		case R.id.tv_adressback:
			finish();
			break;

		default:
			break;
		}
	}

	private void setAdress()
	{

		final String adress = adressContext.getText().toString().trim();
		if (adress != null)
		{
			Login data = Variable.getVariable().getData();
			String amgoo_ID = data.getData().getUser().getAmgoo_ID();
			String user_password = data.getData().getUser().getUser_password();
			ArrayList<BasicNameValuePair> paramsList = new ArrayList<BasicNameValuePair>();
			paramsList.add(new BasicNameValuePair("Amgoo_ID", amgoo_ID));
			paramsList.add(new BasicNameValuePair("user_password", user_password));
			paramsList.add(new BasicNameValuePair("user_address", adress));
			HttpQuest.Get(Contant.ServerDataUrl.ADRESS, paramsList, new HttpGetCallBack()
			{
				@Override
				public void onStart()
				{
				}

				@Override
				public void onSuccess(ResponseInfo<String> responseInfo)
				{
					stopProgress();
					SetAdress setadress = JsonParser.deserializeByJson(responseInfo.result, SetAdress.class);
					if (setadress.getStatus() == 1)
					{
						Variable.getVariable().getData().getData().getUser().setUser_address(adress);
						Toast.makeText(AdressActivity.this, getResources().getText(R.string.setscucess), Toast.LENGTH_SHORT).show();
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
