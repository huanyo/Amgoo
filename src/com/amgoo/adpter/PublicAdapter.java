package com.amgoo.adpter;

import android.widget.BaseAdapter;

public abstract class PublicAdapter extends BaseAdapter
{

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

}
