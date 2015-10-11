package com.agmoo.picture;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

import com.amgoo.activity.R;

/***********************************************************
 * @Description:相册管理类
 ***********************************************************/
public class PhotoAlbumAct extends AppActivity
{
	private GridView aibumGV;
	private AlbumHelper helper;
	private List<PhotoAlbumItem> dataList;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_picture_bucket);
		showWaitingProgress();
		helper = AlbumHelper.getHelper();
		helper.init(getApplicationContext());
		String sdStatus = Environment.getExternalStorageState();
		if (!sdStatus.equals(Environment.MEDIA_MOUNTED))
		{ // 检查sd是否可用
			Log.v("TestFile", "SD card is not avaiable/writeable right now.");
			closeProgress();
			Toast.makeText(PhotoAlbumAct.this, getResources().getString(R.string.setsd), Toast.LENGTH_SHORT).show();
			return;
		} else
		{

			initData();
			initView();
			initActionBar();
		}
	}

	private void initData()
	{
		dataList = helper.getImagesBucketList(false);

	}

	private void initView()
	{
		aibumGV = (GridView) findViewById(R.id.gridview);
		aibumGV.setAdapter(new PhotoAlbumAdapter(dataList, this));
		closeProgress();
		aibumGV.setOnItemClickListener(aibumClickListener);

	}

	private void initActionBar()
	{
		setBarCenterText(getResources().getString(R.string.customAlbum_fromalbum));
		setBarLeftOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{

				backKeyCallBack();
			}
		});
		hideBarRight();

	}

	@Override
	protected void backKeyCallBack()
	{
		super.backKeyCallBack();
		System.gc();
		finish();
	}

	/**
	 * 相册点击事件
	 */
	OnItemClickListener aibumClickListener = new OnItemClickListener()
	{
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id)
		{

			Navigator.goToPictureMoreAct(dataList.get(position).imageList, 21);
		}
	};

	public static Intent createIntent()
	{
		Intent intent = new Intent(App.getCurrentActivity(), PhotoAlbumAct.class);
		return intent;
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{

		switch (resultCode)
		{
		case 21:

			finish();
			setResult(4);

			break;

		default:
			break;
		}
	};

}
