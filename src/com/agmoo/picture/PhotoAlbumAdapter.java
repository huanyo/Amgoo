package com.agmoo.picture;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amgoo.activity.R;


/***********************************************************
 * @Description:相册适配器
 ***********************************************************/
public class PhotoAlbumAdapter extends BaseAdapter {
	
	List<PhotoAlbumItem> dataList;
	private Context context;


	public PhotoAlbumAdapter(List<PhotoAlbumItem> dataList,
			Context context) 
	{
		this.dataList =  dataList;
		this.context = context;
	}
	@Override
	public int getCount(){
		int count = 0;
		if (dataList != null) 
		{
			count = dataList.size();
		}
		return count;
	}
	@Override
	public Object getItem(int position) {
		return dataList.get(position);
	}
	@Override
	public long getItemId(int position) {
		return position;   
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		if(convertView == null)
		{
			convertView = (LinearLayout)LayoutInflater.from(context).inflate(R.layout.gv_album_bucket, null);
		}
		ImageView iv = ViewHolder.get(convertView, R.id.image);
		TextView tv= ViewHolder.get(convertView, R.id.name);
		TextView count= ViewHolder.get(convertView, R.id.count);
	
		/** 通过ID 生成缩略图*/
		PhotoAlbumItem item = dataList.get(position);
		
		tv.setText(item.bucketName);
		count.setText(item.count + context.getString(R.string.zhang));
		
		if (item.imageList != null && item.imageList.size() > 0) 
		{
			
			String thumbPath = item.imageList.get(0).thumbnailPath;
			String sourcePath = item.imageList.get(0).imagePath;
					
			if(thumbPath !=null)
			{		
				String small_img = PictureFileUtils.getFileURL(thumbPath);
				iv.setTag(small_img);
				ImageUtil.getImage(small_img, iv,R.drawable.ico_activitylist_default);
			}
			else
			{
				String source_img = PictureFileUtils.getFileURL(sourcePath);
				iv.setTag(source_img);
				ImageUtil.getImage(source_img, iv,R.drawable.ico_activitylist_default);
			}
		
		} 
	
		return convertView;
	}
	
	

}

