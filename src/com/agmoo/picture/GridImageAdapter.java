package com.agmoo.picture;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.amgoo.activity.R;

public class GridImageAdapter extends BaseAdapter
{
	private LayoutInflater inflater; // 视图容器
	private Context context;

	public GridImageAdapter(Context context)
	{
		this.context = context;
		inflater = LayoutInflater.from(context);

	}

	public int getCount()
	{
		return (PhotoUtils.bitmaps.size() + 1);
	}

	public Object getItem(int arg0)
	{

		return arg0;
	}

	public long getItemId(int arg0)
	{
		return arg0;
	}

	public View getView(int position, View convertView, ViewGroup parent)
	{

		ViewHolder holder = null;
		if (convertView == null)
		{

			convertView = inflater.inflate(R.layout.gv_album_published_grida, parent, false);
			holder = new ViewHolder();
			holder.image = (ImageView) convertView.findViewById(R.id.item_grida_image);
			convertView.setTag(holder);
		} else
		{
			holder = (ViewHolder) convertView.getTag();
		}
		// 图片布局
		if (position == PhotoUtils.bitmaps.size())
		{

			holder.image.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.icon_addphoto));

			if (position == 4) // �?张后，便隐藏添加图片的按�?
			{
				holder.image.setVisibility(View.GONE);
			} else
			{
				holder.image.setVisibility(View.VISIBLE);
			}

		}

		else
		{
			PhotoItem item = PhotoUtils.bitmaps.get(position);
			if (item.thumbnailPath != null)
			{
				String small_img = PhotoFileUtils.getFileURL(item.thumbnailPath);
				ImageUtil.getImage(small_img, holder.image, R.drawable.ico_activitylist_default);

			} else
			{
				String img = PhotoFileUtils.getFileURL(item.imagePath);
				ImageUtil.getImage(img, holder.image, R.drawable.ico_activitylist_default);
			}
		}
		notifyDataSetChanged();
		return convertView;
	}

	public class ViewHolder
	{
		public ImageView image;
	}
}
