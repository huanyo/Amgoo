package com.agmoo.picture;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.amgoo.activity.R;

/***********************************************************
 * @Description:朋友圈发布微信带图片工具的带删除功能
 ***********************************************************/

public class PhotoDeleteAct extends AppActivity {

	private ArrayList<View> listViews = null;
	private ViewPager pager;
	private MyPageAdapter adapter;
	private int count;
	public ArrayList<PhotoItem> bmp = new ArrayList<PhotoItem>();
	public List<String> del = new ArrayList<String>();
	RelativeLayout photo_relativeLayout;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_picture);

		photo_relativeLayout = (RelativeLayout) findViewById(R.id.photo_relativeLayout);
		photo_relativeLayout.setBackgroundColor(0x70000000);

		for (int i = 0; i < PhotoUtils.bitmaps.size(); i++) {
			bmp.add(PhotoUtils.bitmaps.get(i));
		}
		Button photo_bt_exit = (Button) findViewById(R.id.photo_bt_exit);
		photo_bt_exit.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				finish();
			}
		});
		Button photo_bt_del = (Button) findViewById(R.id.photo_bt_del);
		photo_bt_del.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (listViews.size() == 1) {
					PhotoUtils.bitmaps.clear();
					PhotoFileUtils.deleteDir();
					finish();
				} else {

					bmp.remove(count);
					pager.removeAllViews();
					listViews.remove(count);
					adapter.setListViews(listViews);
					adapter.notifyDataSetChanged();
				}
			}
		});
		Button photo_bt_enter = (Button) findViewById(R.id.photo_bt_enter);
		photo_bt_enter.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				PhotoUtils.bitmaps = bmp;

				finish();
			}
		});

		pager = (ViewPager) findViewById(R.id.viewpager);
		pager.setOnPageChangeListener(pageChangeListener);
		for (int i = 0; i < bmp.size(); i++) {
			initListViews(bmp.get(i));//
		}

		adapter = new MyPageAdapter(listViews);// 构�?adapter
		pager.setAdapter(adapter);// 设置适配�?
		Intent intent = getIntent();
		int id = intent.getIntExtra("ID", 0);
		pager.setCurrentItem(id);
	}
	@Override
	protected void backKeyCallBack() {
		super.backKeyCallBack();
		finish();
	}

	private void initListViews(PhotoItem item) {
		if (listViews == null)
			listViews = new ArrayList<View>();

		ImageView img = new ImageView(getApplicationContext());

		String logoUrl = PhotoFileUtils.getFileURL(item.getImagePath());

		img.setBackgroundColor(0xff000000);

		img.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));

		Bitmap bitmap = ImageUtil.loadBitmap(logoUrl,
				R.drawable.ico_activitylist_default);
		//缩略�?
		Bitmap extractThumbnail = ThumbnailUtils.extractThumbnail(bitmap, 480, 800);
		img.setImageBitmap(extractThumbnail);

		listViews.add(img);// 添加view
	}

	private OnPageChangeListener pageChangeListener = new OnPageChangeListener() {

		public void onPageSelected(int arg0) {// 页面选择响应函数
			count = arg0;
		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {// 滑动中�?。�?

		}

		public void onPageScrollStateChanged(int arg0) {// 滑动状�?改变

		}
	};

	class MyPageAdapter extends PagerAdapter {

		private ArrayList<View> listViews;// content

		private int size;// 页数

		public MyPageAdapter(ArrayList<View> listViews) {// 构�?函数
															// 初始化viewpager的时候给的一个页�?
			this.listViews = listViews;
			size = listViews == null ? 0 : listViews.size();
		}

		public void setListViews(ArrayList<View> listViews) {// 自己写的�?��方法用来添加数据
			this.listViews = listViews;
			size = listViews == null ? 0 : listViews.size();
		}

		public int getCount() {// 返回数量
			return size;
		}

		public int getItemPosition(Object object) {
			return POSITION_NONE;
		}

		public void destroyItem(View arg0, int arg1, Object arg2) {// �?��view对象
			((ViewPager) arg0).removeView(listViews.get(arg1 % size));
		}

		public void finishUpdate(View arg0) {
		}

		public Object instantiateItem(View arg0, int arg1) {// 返回view对象
			try 
			{
				((ViewPager) arg0).addView(listViews.get(arg1 % size), 0);

			} catch (Exception e) {
			}
			return listViews.get(arg1 % size);
		}

		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

	}
}
