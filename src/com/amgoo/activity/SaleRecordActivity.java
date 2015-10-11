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

public class SaleRecordActivity extends Activity implements OnClickListener
{

	private ListView lvSaleRecord;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sale_record);
		lvSaleRecord = (ListView) findViewById(R.id.lv_salerecord);
		salerecordadapter adapter = new salerecordadapter();
		lvSaleRecord.setAdapter(adapter);
		TextView tvRecordBack = (TextView) findViewById(R.id.tv_recordback);
		tvRecordBack.setOnClickListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.sale_record, menu);
		return true;
	}

	class salerecordadapter extends BaseAdapter
	{

		@Override
		public int getCount()
		{
			return 10;
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
			View inflate = getLayoutInflater().inflate(R.layout.salerecord_item, null);
			return inflate;
		}

	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.tv_recordback:
			finish();
			break;

		default:
			break;
		}
	}
}
