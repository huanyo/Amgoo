package com.amgoo.activity;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amgoo.country.CharacterParserUtil;
import com.amgoo.country.CountryActivity;
import com.amgoo.country.CountrySortModel;
import com.amgoo.country.GetCountryNameSort;
import com.amgoo.entiy.Regist;
import com.amgoo.tool.Contant;
import com.amgoo.tool.MD5;
import com.amgoo.util.DialogUtil;
import com.amgoo.util.HttpGetCallBack;
import com.amgoo.util.HttpQuest;
import com.amgoo.util.JsonParser;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;

public class RegiestActivity extends Activity implements OnClickListener
{
	private Dialog mProgressLoading;
	private GetCountryNameSort countryChangeUtil;
	private CharacterParserUtil characterParserUtil;
	private List<CountrySortModel> mAllCountryList;
	String beforText = null;
	private TextView tvRegCountry;
	private EditText editTextcountryNum;
	private Button btnRegist;
	private String niCheng;
	private String country;
	private String user;
	private String passWord;
	private EditText edRegPsw;
	private EditText edRegUser;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_regiest);
		initview();
		initCountryList();
		setListener();
	}

	@SuppressLint("NewApi")
	private void initview()
	{
		tvRegCountry = (TextView) findViewById(R.id.tv_regcountry);
		editTextcountryNum = (EditText) findViewById(R.id.editText_countryNum);
		LinearLayout linContry = (LinearLayout) findViewById(R.id.lin_contry);
		btnRegist = (Button) findViewById(R.id.btn_regist);
		EditText edNiCheng = (EditText) findViewById(R.id.ed_nicheng);
		edRegUser = (EditText) findViewById(R.id.ed_reguser);
		edRegPsw = (EditText) findViewById(R.id.ed_regpsw);
		mAllCountryList = new ArrayList<CountrySortModel>();
		countryChangeUtil = new GetCountryNameSort();
		characterParserUtil = new CharacterParserUtil();
		linContry.setOnClickListener(this);
		btnRegist.setOnClickListener(this);
		niCheng = edNiCheng.getText().toString().trim();

		// user = edRegUser.getText().toString().trim();
		// passWord = edRegPsw.getText().toString().trim();
		// if (niCheng == null || country == null || user == null || passWord ==
		// null || niCheng.isEmpty() || country.isEmpty() || user.isEmpty() ||
		// passWord.isEmpty())
		// {
		// btnRegist.setClickable(false);
		// btnRegist.setBackgroundResource(R.color.bule1);
		// } else
		// {
		// btnRegist.setClickable(true);
		// btnRegist.setBackgroundResource(R.color.topbar_bg);
		// }
	}

	private void setResgist()
	{
		country = tvRegCountry.getText().toString().trim();
		passWord = edRegPsw.getText().toString().trim();
		user = edRegUser.getText().toString().trim();
		MD5 md5 = new MD5();
		String psw = md5.getMD5Str(passWord);
		TelephonyManager mTm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		final String imei = mTm.getDeviceId();
		ArrayList<BasicNameValuePair> paramsList = new ArrayList<BasicNameValuePair>();
		paramsList.add(new BasicNameValuePair("user_timezone_id", country));
		paramsList.add(new BasicNameValuePair("Amgoo_ID", user));
		paramsList.add(new BasicNameValuePair("user_password", psw));
		paramsList.add(new BasicNameValuePair("user_mobile_imei_1", imei));
		Log.e("--------------country", country + "------");
		Log.e("--------------user", user + "------");
		Log.e("--------------psw", psw + "------");
		Log.e("--------------imei", imei + "------");
		HttpQuest.Get(Contant.ServerDataUrl.REGIST, paramsList, new HttpGetCallBack()
		{
			@Override
			public void onStart()
			{
			}

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo)
			{
				Log.e("---------------responseInfo", responseInfo.result + "------");
				stopProgress();
				Regist regiset = JsonParser.deserializeByJson(responseInfo.result, Regist.class);
				if (regiset.getStatus() == 1)
				{
					Toast.makeText(RegiestActivity.this, getResources().getText(R.string.registsucess), Toast.LENGTH_SHORT).show();
					finish();
				}
			}

			@Override
			public void onFailure(HttpException error, String msg)
			{
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.regiest, menu);
		return true;
	}

	private void initCountryList()
	{
		String[] countryList = getResources().getStringArray(R.array.country_code_list_ch);

		for (int i = 0, length = countryList.length; i < length; i++)
		{
			String[] country = countryList[i].split("\\*");

			String countryName = country[0];
			String countryNumber = country[1];
			String countrySortKey = characterParserUtil.getSelling(countryName);
			CountrySortModel countrySortModel = new CountrySortModel(countryName, countryNumber, countrySortKey);
			String sortLetter = countryChangeUtil.getSortLetterBySortKey(countrySortKey);
			if (sortLetter == null)
			{
				sortLetter = countryChangeUtil.getSortLetterBySortKey(countryName);
			}

			countrySortModel.sortLetters = sortLetter;
			mAllCountryList.add(countrySortModel);
		}

	}

	private void setListener()
	{

		editTextcountryNum.addTextChangedListener(new TextWatcher()
		{

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count)
			{

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after)
			{
				beforText = s.toString();
			}

			@Override
			public void afterTextChanged(Editable s)
			{
				String contentString = editTextcountryNum.getText().toString();

				CharSequence contentSeq = editTextcountryNum.getText();

				if (contentString.length() > 1)
				{
					// 按照输入内容进行匹配
					List<CountrySortModel> fileterList = (ArrayList<CountrySortModel>) countryChangeUtil.search(contentString, mAllCountryList);

					if (fileterList.size() == 1)
					{
						tvRegCountry.setText(fileterList.get(0).countryName);
					} else
					{
						tvRegCountry.setText("国家代码无效");
					}

				} else
				{
					if (contentString.length() == 0)
					{
						editTextcountryNum.setText(beforText);
						tvRegCountry.setText("从列表选择");
					} else if (contentString.length() == 1 && contentString.equals("+"))
					{
						tvRegCountry.setText("从列表选择");
					}

				}

				if (contentSeq instanceof Spannable)
				{
					Spannable spannable = (Spannable) contentSeq;
					Selection.setSelection(spannable, contentSeq.length());
				}
			}
		});

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		switch (requestCode)
		{
		case 12:
			if (resultCode == RESULT_OK)
			{
				Bundle bundle = data.getExtras();
				String countryName = bundle.getString("countryName");
				String countryNumber = bundle.getString("countryNumber");
				editTextcountryNum.setText(countryNumber);
				tvRegCountry.setText(countryName);
			}
			break;

		default:
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.lin_contry:
			Intent intent1 = new Intent();
			intent1.setClass(RegiestActivity.this, CountryActivity.class);
			startActivityForResult(intent1, 12);
			break;
		case R.id.btn_regist:
			setResgist();

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
}
