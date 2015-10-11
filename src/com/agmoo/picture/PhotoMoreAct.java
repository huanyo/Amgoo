package com.agmoo.picture;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.amgoo.activity.R;

/***********************************************************
 * @Description:相册图片选择-多张选择功能
 ***********************************************************/

public class PhotoMoreAct extends AppActivity
{

	GridView gridView;
	Button mSubmit;
	private boolean isClick = false;
	private PhotoAdappter adapter;
	private AlbumHelper helper;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.act_picturemove_grid);
		showWaitingProgress();

		initActionBar();
		initView();
		initData();// 获取图片列表
		selectedImage();// 选择完图片提�?
	}

	List<PhotoItem> dataList;

	@SuppressWarnings("unchecked")
	private void initData()
	{
		helper = AlbumHelper.getHelper();
		helper.init(getApplicationContext());
		dataList = (List<PhotoItem>) getIntent().getSerializableExtra("images");
		gridView.setSelector(R.color.transparent);
		adapter = new PhotoAdappter(PhotoMoreAct.this, dataList);
		gridView.setAdapter(adapter);
		closeProgress();
	}

	private void initView()
	{
		// TODO Auto-generated method stub
		gridView = (GridView) findViewById(R.id.gridview);
		mSubmit = (Button) findViewById(R.id.bt);
		gridView.setOnItemClickListener(gvItemClickListener);
	}

	private void initActionBar()
	{
		setBarLeftOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				backKeyCallBack();
			}
		});
		setBarCenterText(getResources().getString(R.string.selectpicture));
		setBarRightText(getResources().getString(R.string.customAlbum_cancel));
		setBarRightOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				backKeyCallBack();
			}
		});
	}

	@Override
	protected void backKeyCallBack()
	{
		// TODO Auto-generated method stub
		super.backKeyCallBack();
		System.gc();
		finish();
	}

	public static Intent createIntent(List<PhotoItem> list)
	{
		Intent intent = new Intent(App.getCurrentActivity(), PhotoMoreAct.class);
		intent.putExtra("images", (Serializable) list);
		return intent;
	}

	private void selectedImage()
	{

		mSubmit.setOnClickListener(new OnClickListener()
		{

			public void onClick(View v)
			{

				if (isClick)
				{

					return;
				}
				isClick = true;

				setResult(21);

				mSubmit.post(new Runnable()
				{

					@Override
					public void run()
					{

						// TODO Auto-generated method stub
						for (int i = 0; i < imgItem.size(); i++)
						{
							PhotoItem item = new PhotoItem();
							// 原图
							item.setImagePath(imgItem.get(i).getImagePath());
							item.setThumbnailPath(imgItem.get(i).getThumbnailPath());
							item.setImageId(imgItem.get(i).getImageId());
							PhotoUtils.addImageItem(item);

							//
						}
					}
				});

				mSubmit.postDelayed(new Runnable()
				{

					@Override
					public void run()
					{
						// TODO Auto-generated method stub
						backKeyCallBack();
					}
				}, 1000);
			}

		});
	}

	public ArrayList<PhotoItem> imgItem = new ArrayList<PhotoItem>();

	private int selectTotal = 0;
	private OnItemClickListener gvItemClickListener = new OnItemClickListener()
	{
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id)
		{

			if (dataList.get(position).isSelected())
			{
				if ((selectTotal + PhotoUtils.bitmaps.size()) >= 0)
				{
					selectTotal--;
					dataList.get(position).setSelected(false);
					imgItem.remove(dataList.get(position));
					mSubmit.setText(getResources().getString(R.string.cheive) + "(" + (selectTotal + PhotoUtils.bitmaps.size()) + ")");
				}
			} else
			{
				if ((selectTotal + PhotoUtils.bitmaps.size()) < 4)
				{
					dataList.get(position).setSelected(true);
					selectTotal++;
					imgItem.add(dataList.get(position));

				} else
				{
					Toast.makeText(PhotoMoreAct.this, getResources().getString(R.string.picture_filtering), Toast.LENGTH_SHORT).show();
				}
				mSubmit.setText(getResources().getString(R.string.cheive) + "(" + (selectTotal + PhotoUtils.bitmaps.size()) + ")");

			}
			adapter.notifyDataSetChanged();

		}
	};

}
