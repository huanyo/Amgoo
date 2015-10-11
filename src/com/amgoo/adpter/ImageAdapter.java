package com.amgoo.adpter;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import com.amgoo.activity.ProductActivity;
import com.amgoo.activity.R;
import com.amgoo.loadimg.VolleyUtil;

public class ImageAdapter extends BaseAdapter
{
	private List<String> imageUrls; // 图片地址list
	private Context context;
	private ImageAdapter self;
	Uri uri;
	Intent intent;
	ImageView imageView;

	// public static Integer[] imgs = { R.drawable.banner1, R.drawable.banner2,
	// R.drawable.banner3, };

	public ImageAdapter(List<String> imageUrls, Context context)
	{
		this.imageUrls = imageUrls;
		this.context = context;
		this.self = this;
	}

	public int getCount()
	{
		return Integer.MAX_VALUE;
	}

	public Object getItem(int position)
	{
		return position; // 当前位置对图片总长度取余
	}

	public long getItemId(int position)
	{
		return position;
	}

	@SuppressWarnings("unused")
	private Handler mHandler = new Handler()
	{
		public void handleMessage(Message msg)
		{
			try
			{
				switch (msg.what)
				{
				case 0:
				{
					self.notifyDataSetChanged();
				}
					break;
				}

				super.handleMessage(msg);
			} catch (Exception e)
			{
			}
		}
	};

	public View getView(int position, View convertView, ViewGroup parent)
	{

		if (convertView == null)
		{
			convertView = LayoutInflater.from(context).inflate(R.layout.item, null); // 实例化convertView
			imageView = (ImageView) convertView.findViewById(R.id.gallery_image);
			Gallery.LayoutParams params = new Gallery.LayoutParams(Gallery.LayoutParams.WRAP_CONTENT, Gallery.LayoutParams.WRAP_CONTENT);
			convertView.setLayoutParams(params);
			VolleyUtil.LoadImageBanner(imageUrls.get(position % imageUrls.size()), imageView);
			imageView.setScaleType(ImageView.ScaleType.FIT_XY);
			((ProductActivity) context).changePointView(position % imageUrls.size());
		}
		return convertView;
	}
}
