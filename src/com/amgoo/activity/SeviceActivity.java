package com.amgoo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class SeviceActivity extends Activity implements OnClickListener
{

	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sevice);
		initview();

	}

	private void initview()
	{
		LinearLayout linAgmoo = (LinearLayout) findViewById(R.id.lin_agmoo);
		LinearLayout linFeeBook = (LinearLayout) findViewById(R.id.lin_facebook);
		LinearLayout linTwitter = (LinearLayout) findViewById(R.id.lin_twitter);
		LinearLayout linCustomerSuggest = (LinearLayout) findViewById(R.id.lin_customersuggest);
		LinearLayout linSeviceAdress = (LinearLayout) findViewById(R.id.lin_seviceadress);
		LinearLayout linSalePolicy = (LinearLayout) findViewById(R.id.lin_salepolicy);
		LinearLayout linPhoneQuery = (LinearLayout) findViewById(R.id.lin_phonequery);
		linAgmoo.setOnClickListener(this);
		linFeeBook.setOnClickListener(this);
		linTwitter.setOnClickListener(this);
		linCustomerSuggest.setOnClickListener(this);
		linSeviceAdress.setOnClickListener(this);
		linSalePolicy.setOnClickListener(this);
		linPhoneQuery.setOnClickListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.sevice, menu);
		return true;
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.lin_agmoo:
			intent = new Intent(SeviceActivity.this, AgmooActivity.class);
			startActivity(intent);
			break;
		case R.id.lin_facebook:
			intent = new Intent(SeviceActivity.this, FaceBookActivity.class);
			startActivity(intent);
			break;
		case R.id.lin_twitter:
			intent = new Intent(SeviceActivity.this, TwitterActivity.class);
			startActivity(intent);
			break;
		case R.id.lin_customersuggest:
			intent = new Intent(SeviceActivity.this, FeedbackActivity.class);
			startActivity(intent);
			break;
		case R.id.lin_seviceadress:
			intent = new Intent(SeviceActivity.this, SeviceAdressActivity.class);
			startActivity(intent);
			break;
		case R.id.lin_salepolicy:
			// intent = new Intent(SeviceActivity.this, FeedbackActivity.class);
			// startActivity(intent);
			break;
		case R.id.lin_phonequery:
			intent = new Intent(SeviceActivity.this, TureQueryActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
	}

}
