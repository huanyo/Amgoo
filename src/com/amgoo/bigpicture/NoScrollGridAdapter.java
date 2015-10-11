package com.amgoo.bigpicture;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap.Config;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.amgoo.activity.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class NoScrollGridAdapter extends BaseAdapter
{

	private Context ctx;
	private ArrayList<String> imageUrls;

	public NoScrollGridAdapter(Context ctx, ArrayList<String> urls)
	{
		this.ctx = ctx;
		this.imageUrls = urls;
	}

	@Override
	public int getCount()
	{
		return imageUrls == null ? 0 : imageUrls.size();
	}

	@Override
	public Object getItem(int position)
	{
		return imageUrls.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		View view = View.inflate(ctx, R.layout.item_gridview, null);
		ImageView imageView = (ImageView) view.findViewById(R.id.iv_image);
		DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisc(true).bitmapConfig(Config.RGB_565).build();
		ImageLoader.getInstance().displayImage(imageUrls.get(position), imageView, options);
		return view;
	}

}
