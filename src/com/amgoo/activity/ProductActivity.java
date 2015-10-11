package com.amgoo.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amgoo.adpter.ImageAdapter;
import com.amgoo.adpter.PublicAdapter;
import com.amgoo.entiy.BanerArray;
import com.amgoo.entiy.Banner;
import com.amgoo.entiy.BannerObject;
import com.amgoo.tool.Contant;
import com.amgoo.tool.MyJsonWriter;
import com.amgoo.util.DialogUtil;
import com.amgoo.util.GuideGallery;
import com.amgoo.util.HttpGetCallBack;
import com.amgoo.util.HttpQuest;
import com.amgoo.util.JsonParser;
import com.amgoo.zxing.CaptureActivity;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;

public class ProductActivity extends Activity implements OnClickListener, OnItemClickListener
{
	public GuideGallery images_ga;
	public boolean timeFlag = true;
	public ImageTimerTask timeTaks = null;
	int gallerypisition = 0;
	public static int size;
	private Thread timeThread = null;
	Timer autoGallery = new Timer(); // Timer
	private boolean isExit = false;
	Intent intent;
	private int positon = 0;
	// public static Integer[] imgs = { R.drawable.banner1, R.drawable.banner2,
	// R.drawable.banner3, };
	private ArrayList<BanerArray> carouseList = new ArrayList<BanerArray>();
	private Dialog mProgressLoading;
	private List<String> imageUrls = new ArrayList<String>(); // 图片地址list

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_product);
		timeTaks = new ImageTimerTask();
		autoGallery.scheduleAtFixedRate(timeTaks, 3000, 3000);
		timeThread = new Thread()
		{
			public void run()
			{
				while (!isExit)
				{
					try
					{
						Thread.sleep(1500);
					} catch (InterruptedException e)
					{
						e.printStackTrace();
					}
					synchronized (timeTaks)
					{
						if (!timeFlag)
						{
							timeTaks.timeCondition = true;
							timeTaks.notifyAll();
						}
					}
					timeFlag = true;
				}
			};
		};
		timeThread.start();
		initview();
		getBanner();
		// MyJsonReader read = new MyJsonReader();
		// ArrayList<BanerArray> jsonData = read.getJsonData();
		// carouseList = jsonData;
	}

	private void initview()
	{
		TextView TopBarRight = (TextView) findViewById(R.id.mTopBarRight);
		gridproduct = (GridView) findViewById(R.id.grid_product);
		TopBarRight.setOnClickListener(this);
		padapter = new phoneadapter();
		gridproduct.setAdapter(padapter);
		gridproduct.setOnItemClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.product, menu);
		return true;
	}

	private void getBanner()
	{
		ArrayList<BasicNameValuePair> paramsList = new ArrayList<BasicNameValuePair>();
		HttpQuest.Get(Contant.ServerDataUrl.BANNER, paramsList, new HttpGetCallBack()
		{
			@Override
			public void onStart()
			{
			}

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo)
			{
				Banner banner = JsonParser.deserializeByJson(responseInfo.result, Banner.class);
				BannerObject data = banner.getData();
				carouseList = data.getCarouseList();
				if (banner.getStatus() == 1)
				{
					if (carouseList != null)
					{
//						MyJsonWriter write = new MyJsonWriter(carouseList);
						for (int i = 0; i < carouseList.size(); i++)
						{
							String img = carouseList.get(i).getImg();
							if (img != null)
							{
								imageUrls.add(img);
							}
						}
						init();
						ImageAdapter imageAdapter = new ImageAdapter(imageUrls, ProductActivity.this);
						images_ga.setAdapter(imageAdapter);
						size = carouseList.size();
					}

				} else
				{
					Toast.makeText(ProductActivity.this, banner.getInfo(), Toast.LENGTH_SHORT).show();
				}

			}

			@Override
			public void onFailure(HttpException error, String msg)
			{
			}
		});
	}

	private void init()
	{
		// GuideGallery
		images_ga = (GuideGallery) findViewById(R.id.image_wall_gallery);
		images_ga.setImageActivity(this);
		ImageAdapter imageAdapter = new ImageAdapter(imageUrls, this);
		images_ga.setAdapter(imageAdapter);
		LinearLayout pointLinear = (LinearLayout) findViewById(R.id.gallery_point_linear);
		pointLinear.setBackgroundColor(Color.argb(0, 0, 0, 0));
		for (int i = 0; i < carouseList.size(); i++)
		{
			ImageView pointView = new ImageView(this);
			if (i == 0)
			{
				pointView.setBackgroundResource(R.drawable.icon_slider_direct_blue_actived);
			} else
			{
				pointView.setBackgroundResource(R.drawable.icon_slider_direct);
			}
			pointLinear.addView(pointView);
		}

	}

	public void changePointView(int cur)
	{
		LinearLayout pointLinear = (LinearLayout) findViewById(R.id.gallery_point_linear);
		View view = pointLinear.getChildAt(positon); // position
		View curView = pointLinear.getChildAt(cur);// cur
		if (view != null && curView != null)
		{
			ImageView pointView = (ImageView) view;
			ImageView curPointView = (ImageView) curView;
			pointView.setBackgroundResource(R.drawable.icon_slider_direct);
			curPointView.setBackgroundResource(R.drawable.icon_slider_direct_blue_actived);
			positon = cur;
		}
	}

	public class ImageTimerTask extends TimerTask
	{
		public volatile boolean timeCondition = true;

		public void run()
		{
			synchronized (this)
			{
				while (!timeCondition)
				{
					try
					{
						Thread.sleep(100);
						wait();
					} catch (InterruptedException e)
					{
						Thread.interrupted();
					}
				}
			}
			try
			{
				gallerypisition = images_ga.getSelectedItemPosition() + 1;
				System.out.println(gallerypisition + "");
				Message msg = new Message();
				Bundle date = new Bundle();
				date.putInt("pos", gallerypisition);
				msg.setData(date);
				msg.what = 1;
				autoGalleryHandler.sendMessage(msg);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	final Handler autoGalleryHandler = new Handler()
	{
		public void handleMessage(Message message)
		{
			super.handleMessage(message);
			switch (message.what)
			{
			case 1:
				images_ga.setSelection(message.getData().getInt("pos"));
				break;
			}
		}
	};
	private GridView gridproduct;
	private phoneadapter padapter;

	@Override
	protected void onResume()
	{
		super.onResume();
		timeFlag = false;

	}

	@Override
	protected void onPause()
	{
		super.onPause();
		timeTaks.timeCondition = false;
	}

	class phoneadapter extends PublicAdapter
	{

		private int clickTemp = -1;

		// 标识选择的Item
		public void setSeclection(int position)
		{
			clickTemp = position;
			notifyDataSetChanged();
		}

		@Override
		public int getCount()
		{
			return 10;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{
			View inflate = getLayoutInflater().inflate(R.layout.product_item, null);
			if (clickTemp == position)
			{
				inflate.setBackgroundResource(R.drawable.btn_pink_cur);

			} else
			{
				inflate.setBackgroundColor(Color.TRANSPARENT);
			}
			ImageView imgphone = (ImageView) inflate.findViewById(R.id.img_phone);
			TextView tvname = (TextView) inflate.findViewById(R.id.tv_name);
			TextView tvattribute = (TextView) inflate.findViewById(R.id.tv_attribute);
			return inflate;
		}
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.mTopBarRight:
			Intent openCameraIntent = new Intent(ProductActivity.this, CaptureActivity.class);
			startActivityForResult(openCameraIntent, 0);
			break;

		default:
			break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id)
	{
		padapter.setSeclection(position);
		padapter.notifyDataSetChanged();
		Intent intent = new Intent(ProductActivity.this, ProductDetailActivity.class);
		startActivity(intent);

	}

	protected void stopProgress()
	{
		if (mProgressLoading != null)
		{
			mProgressLoading.dismiss();
			mProgressLoading = null;
		}
	}

	protected void startProgress()
	{
		if (mProgressLoading == null)
		{
			mProgressLoading = DialogUtil.createProgressDialog(this, getResources().getText(R.string.jiazai) + "", false);
		}
		if (!isFinishing())
		{
			mProgressLoading.show();
		}
	}

}
