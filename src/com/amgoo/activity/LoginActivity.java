package com.amgoo.activity;

import java.util.ArrayList;

import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.amgoo.entiy.Login;
import com.amgoo.tool.Contant;
import com.amgoo.tool.MD5;
import com.amgoo.tool.Variable;
import com.amgoo.util.DialogUtil;
import com.amgoo.util.HttpGetCallBack;
import com.amgoo.util.HttpQuest;
import com.amgoo.util.JsonParser;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;

public class LoginActivity extends Activity implements OnClickListener
{
	String beforText = null;
	private EditText edUser;
	private EditText edPassWord;
	private Dialog mProgressLoading;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_login);
		initView();
	}

	private void initView()
	{
		TextView tvRegiest = (TextView) findViewById(R.id.tv_regiest);
		Button btnLogin = (Button) findViewById(R.id.btn_login);
		tvRegiest.setOnClickListener(this);
		edUser = (EditText) findViewById(R.id.ed_user);
		edPassWord = (EditText) findViewById(R.id.ed_password);
		btnLogin.setOnClickListener(this);
	}

	private void getLogin()
	{
		String User = edUser.getText().toString().trim();
		String PassWord = edPassWord.getText().toString().trim();
		MD5 getMD5 = new MD5();
		String pwd = getMD5.GetMD5Code(PassWord);
		TelephonyManager mTm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		final String imei = mTm.getDeviceId();
		if (User != null && PassWord != null)
		{
			ArrayList<BasicNameValuePair> paramsList = new ArrayList<BasicNameValuePair>();
			paramsList.add(new BasicNameValuePair("Amgoo_ID", User));
			paramsList.add(new BasicNameValuePair("user_password", pwd));
			paramsList.add(new BasicNameValuePair("user_mobile_imei_1", imei));
			HttpQuest.Get(Contant.ServerDataUrl.LOGIN, paramsList, new HttpGetCallBack()
			{
				@Override
				public void onStart()
				{
				}

				@Override
				public void onSuccess(ResponseInfo<String> responseInfo)
				{
					stopProgress();
					Login login = JsonParser.deserializeByJson(responseInfo.result, Login.class);
					if (login.getStatus() == 1)
					{
						Variable.getVariable().setData(login);
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	@Override
	public void onClick(View v)
	{

		switch (v.getId())
		{
		case R.id.btn_login:
			getLogin();
			break;
		case R.id.tv_regiest:

			Intent intent = new Intent(LoginActivity.this, RegiestActivity.class);
			startActivity(intent);
			break;
		default:
			break;
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

	// private void getInfo()
	// {
	// TelephonyManager mTm = (TelephonyManager)
	// getSystemService(TELEPHONY_SERVICE);
	// String imei = mTm.getDeviceId();
	// String imsi = mTm.getSubscriberId();
	// String mtype = android.os.Build.MODEL; // 手机型号
	// String numer = mTm.getLine1Number(); // 手机号码，有的可得，有的不可得
	// }
}
