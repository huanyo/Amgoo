package com.amgoo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.amgoo.adpter.PublicAdapter;

public class SchoolActivity extends Activity
{

	private GridView gridschool;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_school); 
		initview();
	}

	private void initview()
	{
		gridschool = (GridView) findViewById(R.id.grid_school);
		Schooladapter schooladapter = new Schooladapter();
		gridschool.setAdapter(schooladapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.school, menu);
		return true;
	}

	class Schooladapter extends PublicAdapter
	{

		@Override
		public int getCount()
		{
			return 10;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{
			View inflate = getLayoutInflater().inflate(R.layout.school_item, null);
			ImageView imgshowphone = (ImageView) inflate.findViewById(R.id.img_showphone);
			TextView tvphonename = (TextView) inflate.findViewById(R.id.tv_phonename);

			return inflate;
		}

	}

}
