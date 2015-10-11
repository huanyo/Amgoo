package com.amgoo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class AccountAreadyPayActivity extends Activity implements OnClickListener
{
	private ListView lvAccountPaid;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account_aready_pay);
		lvAccountPaid = (ListView) findViewById(R.id.lv_accountpaid);
		TextView tvAccountAreadyBack = (TextView) findViewById(R.id.tv_accountareadyback);
		accountpaidadapter adapter = new accountpaidadapter();
		lvAccountPaid.setAdapter(adapter);
		tvAccountAreadyBack.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.account_aready_pay, menu);
		return true;
	}

	class accountpaidadapter extends BaseAdapter
	{

		@Override
		public int getCount()
		{
			return 8;
		}

		@Override
		public Object getItem(int position)
		{
			return position;
		}

		@Override
		public long getItemId(int position)
		{
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{
			View inflate = getLayoutInflater().inflate(R.layout.accountareadypay_item, null);
			return inflate;
		}

	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.tv_accountareadyback:
			finish();
			break;

		default:
			break;
		}
	}
}
