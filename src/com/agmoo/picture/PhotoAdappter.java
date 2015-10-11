package com.agmoo.picture;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.amgoo.activity.R;

/***********************************************************
 * @Description:相册图片适配�?
 ***********************************************************/
public class PhotoAdappter extends BaseAdapter
{
	private Context context;
	private List<PhotoItem> list;

	public PhotoAdappter(Context context, List<PhotoItem> list)
	{
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount()
	{

		int count = 0;
		if (list != null)
		{
			count = list.size();
		}
		return count;
	}

	@Override
	public PhotoItem getItem(int position)
	{
		return list.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent)
	{

		if (convertView == null)
		{
			convertView = View.inflate(context, R.layout.gv_album_grid, null);
		}

		ImageView iv = ViewHolder.get(convertView, R.id.image);
		ImageView selected = ViewHolder.get(convertView, R.id.isselected);
		TextView text = ViewHolder.get(convertView, R.id.item_image_grid_text);

		if (list.get(position).isSelected())
		{
			selected.setSelected(true);
			text.setBackgroundResource(R.drawable.bgd_relatly_line);
		} else
		{
			selected.setSelected(false);
			text.setBackgroundColor(0x00000000);
		}

		PhotoItem item = list.get(position);
		if (item.thumbnailPath != null)
		{

			String small_img = PhotoFileUtils.getFileURL(item.thumbnailPath);
			iv.setTag(small_img);
			ImageUtil.getImage(small_img, iv, R.drawable.ico_camera);

		} else
		{
			String img = PhotoFileUtils.getFileURL(item.imagePath);
			iv.setTag(img);
			ImageUtil.getImage(img, iv, R.drawable.ico_camera);
		}

		return convertView;
	}

}
