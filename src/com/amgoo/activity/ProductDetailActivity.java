package com.amgoo.activity;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amgoo.adpter.BannerAdapter;
import com.amgoo.util.BannerGallery;

public class ProductDetailActivity extends Activity implements OnClickListener
{

	private TextView tvGuige;
	private TextView tvZhuyaospel;
	private TextView tvGaiguan;
	private RelativeLayout relGaiguan;
	private RelativeLayout relSpel;
	private LinearLayout solGuige;
	public BannerGallery images_ga;
	private int positon = 0;
	int gallerypisition = 0;
	public boolean timeFlag = true;
	public ImageTimerTask timeTaks = null;
	private boolean isExit = false;
	Timer autoGallery = new Timer();
	private Thread timeThread = null;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_product_detail);
		timeTaks = new ImageTimerTask();
		timeThread = new Thread()
		{
			public void run()
			{
				while (!isExit)
				{
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
		init();

	}

	private void initview()
	{
		tvGaiguan = (TextView) findViewById(R.id.tv_gaiguan);
		tvZhuyaospel = (TextView) findViewById(R.id.tv_zhuyaospel);
		tvGuige = (TextView) findViewById(R.id.tv_guige);
		relGaiguan = (RelativeLayout) findViewById(R.id.rel_gaiguan);
		relSpel = (RelativeLayout) findViewById(R.id.rel_spel);
		solGuige = (LinearLayout) findViewById(R.id.sol_guige);
		viewLeft = findViewById(R.id.view_left);
		viewMiddle = findViewById(R.id.view_middle);
		viewRight = findViewById(R.id.view_right);
		TextView tvDatileBack = (TextView) findViewById(R.id.tv_datileback);
		tvGaiguan.setOnClickListener(this);
		tvZhuyaospel.setOnClickListener(this);
		tvGuige.setOnClickListener(this);
		tvDatileBack.setOnClickListener(this);
	}

	private void init()
	{

		// GuideGallery
		images_ga = (BannerGallery) findViewById(R.id.image_wall_gallery);
		images_ga.setImageActivity(this);
		BannerAdapter imageAdapter = new BannerAdapter(this);
		images_ga.setAdapter(imageAdapter);
		LinearLayout pointLinear = (LinearLayout) findViewById(R.id.gallery_point_linear);
		pointLinear.setBackgroundColor(Color.argb(0, 0, 0, 0));
		for (int i = 0; i < 2; i++)
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

			try
			{
				gallerypisition = images_ga.getSelectedItemPosition() + 1;
				System.out.println(gallerypisition + "");
				Message msg = new Message();
				Bundle date = new Bundle();
				date.putInt("pos", gallerypisition); //
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
	private View viewLeft;
	private View viewMiddle;
	private View viewRight;

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.product_detail, menu);
		return true;
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{

		case R.id.tv_gaiguan:
			relGaiguan.setVisibility(View.VISIBLE);
			relSpel.setVisibility(View.GONE);
			solGuige.setVisibility(View.GONE);
			tvGaiguan.setTextColor(getResources().getColor(R.color.red));
			tvZhuyaospel.setTextColor(getResources().getColor(R.color.menu_text_color));
			tvGuige.setTextColor(getResources().getColor(R.color.menu_text_color));
			viewLeft.setBackgroundResource(R.drawable.scrollbar);
			viewRight.setBackgroundResource(R.drawable.progress_bar_bg);
			viewMiddle.setBackgroundResource(R.drawable.progress_bar_bg);
			break;
		case R.id.tv_zhuyaospel:
			relGaiguan.setVisibility(View.GONE);
			relSpel.setVisibility(View.VISIBLE);
			solGuige.setVisibility(View.GONE);
			tvZhuyaospel.setTextColor(getResources().getColor(R.color.red));
			tvGaiguan.setTextColor(getResources().getColor(R.color.menu_text_color));
			tvGuige.setTextColor(getResources().getColor(R.color.menu_text_color));
			viewLeft.setBackgroundResource(R.drawable.progress_bar_bg);
			viewRight.setBackgroundResource(R.drawable.progress_bar_bg);
			viewMiddle.setBackgroundResource(R.drawable.scrollbar);
			break;
		case R.id.tv_guige:
			relGaiguan.setVisibility(View.GONE);
			relSpel.setVisibility(View.GONE);
			solGuige.setVisibility(View.VISIBLE);
			tvGuige.setTextColor(getResources().getColor(R.color.red));
			tvZhuyaospel.setTextColor(getResources().getColor(R.color.menu_text_color));
			tvGaiguan.setTextColor(getResources().getColor(R.color.menu_text_color));
			viewLeft.setBackgroundResource(R.drawable.progress_bar_bg);
			viewRight.setBackgroundResource(R.drawable.scrollbar);
			viewMiddle.setBackgroundResource(R.drawable.progress_bar_bg);
			break;
		case R.id.tv_datileback:
			finish();
			break;

		default:
			break;
		}
	}

}
