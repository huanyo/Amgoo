package com.amgoo.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.amgoo.tool.IndicatorView;

public class GuideActivity extends Activity implements OnPageChangeListener
{
	private ViewPager pager;
	private ArrayList<View> listpager = new ArrayList<View>();
	int[] aryImg = new int[] { R.drawable.guid11, R.drawable.guid33, R.drawable.guid22 };
	private IndicatorView mIndicatorView;
	private int[] mImageViews = new int[] { R.id.img_01, R.id.img_02, R.id.ImageView03 };
	private ArrayList<ImageView> mAllImg = new ArrayList<ImageView>();
	private boolean isDragging;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide);
		mIndicatorView = (IndicatorView) findViewById(R.id.indicatorView1);
		iniUI();
	}

	@Override
	public void onPageScrollStateChanged(int arg0)
	{
		switch (arg0)
		{
		case ViewPager.SCROLL_STATE_IDLE:
			isDragging = false;

			break;
		case ViewPager.SCROLL_STATE_DRAGGING:
			isDragging = true;

			break;
		case ViewPager.SCROLL_STATE_SETTLING:
			isDragging = false;

			break;
		default:
			break;
		}
	}

	private void iniUI()
	{
		for (int i = 0; i < aryImg.length; i++)
		{
			View inflate = getLayoutInflater().inflate(R.layout.pager_item, null);
			ImageView mImageView = (ImageView) inflate.findViewById(R.id.guide_img);
			mImageView.setBackgroundResource(aryImg[i]);

			if (i == aryImg.length - 1)
			{
				Button btn_experence = (Button) inflate.findViewById(R.id.btn_start);
				btn_experence.setVisibility(View.VISIBLE);
				btn_experence.setOnClickListener(new OnClickListener()
				{
					@Override
					public void onClick(View v)
					{
						startActivity(new Intent(GuideActivity.this, MainActivity.class));
						finish();

					}
				});
			}
			listpager.add(inflate);
		}

		// add adapter
		pager = (ViewPager) findViewById(R.id.pager);
		pager.setOnPageChangeListener(this);
		myPager pagerAdapter = new myPager();
		pager.setAdapter(pagerAdapter);

	}

	class myPager extends PagerAdapter
	{
		// new
		@Override
		public Object instantiateItem(View container, int position)
		{

			View inflate = listpager.get(position);
			pager.addView(inflate);
			return inflate;
		}

		@Override
		public void destroyItem(View container, int position, Object object)
		{

			View inflate = listpager.get(position);
			pager.removeView(inflate);
		}

		@Override
		public int getCount()
		{
			return listpager.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1)
		{
			return arg0 == arg1;
		}

	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.guide, menu);
		return true;
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2)
	{
		mIndicatorView.setPositionOffset(arg0 % mImageViews.length, arg1);

	}

	@Override
	public void onPageSelected(int arg0)
	{

	}

}
