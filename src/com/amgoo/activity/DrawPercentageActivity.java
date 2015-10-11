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

public class DrawPercentageActivity extends Activity implements OnClickListener
{
	private ListView lvDrawPercentage;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_draw_percentage);
		lvDrawPercentage = (ListView) findViewById(R.id.lv_drawPercentage);
		PercentageRcordadapter adapter = new PercentageRcordadapter();
		lvDrawPercentage.setAdapter(adapter);
		TextView tvDrawBack = (TextView) findViewById(R.id.tv_drawback);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.draw_percentage, menu);
		return true;
	}

	class PercentageRcordadapter extends BaseAdapter
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
			View inflate = getLayoutInflater().inflate(R.layout.drawpercentage_item, null);
			return inflate;
		}

	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.tv_drawback:
			finish();
			break;

		default:
			break;
		}
	}
}
