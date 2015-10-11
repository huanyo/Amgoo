package com.amgoo.tool;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import com.amgoo.activity.R;

public class IndicatorView extends View
{

	private static final int RADIUS = 10;
	private Paint paintStroke;
	private int num = 3;
	private Paint paint;
	private float positionOffset;
	private int position;

	public void setNum(int num)
	{
		this.num = num;
	}

	public void setPositionOffset(int position, float positionOffset)
	{
		this.position = position;
		this.positionOffset = positionOffset;
		invalidate();
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		// 实心
		float dimension = getResources().getDimension(R.dimen.splash);

		DisplayMetrics dm = new DisplayMetrics();
		dm = getResources().getDisplayMetrics();
		int densityDPI = dm.densityDpi; // 屏幕密度（每寸像素：120/160/240/320）
		Log.e("_____________densityDPI___________", densityDPI + "");
		canvas.drawCircle(dimension + positionOffset * RADIUS * 3 + position * RADIUS * 3, 80, RADIUS, paint);
		// 空心
		for (int i = 0; i < num; i++)
		{
			canvas.drawCircle(dimension + RADIUS * 3 * i, 80, RADIUS, paintStroke);
		}
	}

	public IndicatorView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		paintStroke = new Paint(Paint.ANTI_ALIAS_FLAG);
		paintStroke.setStyle(Paint.Style.STROKE);
		paintStroke.setColor(Color.WHITE);
		paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setColor(Color.BLACK);

	}

}
