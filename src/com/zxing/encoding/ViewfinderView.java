/*
 * Copyright (C) 2008 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zxing.encoding;

import java.io.InputStream;
import java.util.Collection;
import java.util.HashSet;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import com.amgoo.activity.R;
import com.google.zxing.ResultPoint;

/**
 * This view is overlaid on top of the camera preview. It adds the viewfinder
 * rectangle and partial transparency outside it, as well as the laser scanner
 * animation and result points. 锟皆讹拷锟斤拷锟絍iew锟斤拷锟斤拷锟斤拷时锟叫硷拷锟斤拷示锟斤拷
 */
public final class ViewfinderView extends View
{

	private static final int[] SCANNER_ALPHA = { 0, 64, 128, 192, 255, 192, 128, 64 };
	private static final long ANIMATION_DELAY = 100L;
	private static final int OPAQUE = 0xFF;

	private final Paint paint;
	private Bitmap resultBitmap;
	private final int maskColor;
	private final int resultColor;
	private final int frameColor;
	private final int laserColor;
	private final int resultPointColor;
	private int scannerAlpha;
	private Collection<ResultPoint> possibleResultPoints;
	private Collection<ResultPoint> lastPossibleResultPoints;
	private int toplaser = 1;

	// This constructor is used when the class is built from an XML resource.
	public ViewfinderView(Context context, AttributeSet attrs)
	{
		super(context, attrs);

		// Initialize these once for performance rather than calling them every
		// time in onDraw().
		paint = new Paint();
		Resources resources = getResources();
		maskColor = resources.getColor(R.color.viewfinder_mask);
		resultColor = resources.getColor(R.color.result_view);
		frameColor = resources.getColor(R.color.viewfinder_frame);
		laserColor = resources.getColor(R.color.viewfinder_laser);
		resultPointColor = resources.getColor(R.color.possible_result_points);
		scannerAlpha = 0;
		possibleResultPoints = new HashSet<ResultPoint>(5);
	}

	@Override
	public void onDraw(Canvas canvas)
	{
		Rect frame = CameraManager.get().getFramingRect();
		if (frame == null)
		{
			return;
		}

		paint.setColor(getResources().getColor(R.color.bg_color));

		float distance_8 = getResources().getDimension(R.dimen.distance_8);
		float distance_9 = getResources().getDimension(R.dimen.distance_9);
		float distance_25 = getResources().getDimension(R.dimen.distance_25);
		float distance_27 = getResources().getDimension(R.dimen.distance_27);
		// 宸︿笂瑙�
		canvas.drawRect(frame.left, frame.top - distance_8, frame.left + distance_25, frame.top, paint);

		canvas.drawRect(frame.left - distance_8, frame.top - distance_8, frame.left, frame.top + distance_27, paint);

		// 鍙充笂瑙�
		canvas.drawRect(frame.right - distance_25, frame.top - distance_8, frame.right, frame.top, paint);

		canvas.drawRect(frame.right, frame.top - distance_8, frame.right + distance_9, frame.top + distance_27, paint);

		// 宸︿笅瑙�
		canvas.drawRect(frame.left, frame.bottom, frame.left + distance_25, frame.bottom + distance_9, paint);

		canvas.drawRect(frame.left - distance_8, frame.bottom - distance_27, frame.left, frame.bottom + distance_9, paint);

		// 鍙充笅瑙�
		canvas.drawRect(frame.right - distance_25, frame.bottom, frame.right, frame.bottom + distance_9, paint);

		canvas.drawRect(frame.right, frame.bottom - distance_27, frame.right + distance_8, frame.bottom + distance_9, paint);

		// // line

		int width = canvas.getWidth();
		int height = canvas.getHeight();

		// Draw the exterior (i.e. outside the framing rect) darkened
		paint.setColor(resultBitmap != null ? resultColor : maskColor);
		canvas.drawRect(0, 0, width, frame.top, paint);
		canvas.drawRect(0, frame.top, frame.left, frame.bottom + 1, paint);
		canvas.drawRect(frame.right + 1, frame.top, width, frame.bottom + 1, paint);
		canvas.drawRect(0, frame.bottom + 1, width, height, paint);

		if (resultBitmap != null)
		{
			// Draw the opaque result bitmap over the scanning rectangle
			paint.setAlpha(OPAQUE);
			canvas.drawBitmap(resultBitmap, frame.left, frame.top, paint);
		} else
		{

			// Draw a two pixel solid black border inside the framing rect
			paint.setColor(frameColor);
			canvas.drawRect(frame.left, frame.top, frame.right + 1, frame.top + 2, paint);
			canvas.drawRect(frame.left, frame.top + 2, frame.left + 2, frame.bottom - 1, paint);
			canvas.drawRect(frame.right - 1, frame.top, frame.right + 1, frame.bottom - 1, paint);
			canvas.drawRect(frame.left, frame.bottom - 1, frame.right + 1, frame.bottom + 1, paint);

			// Draw a red "laser scanner" line through the middle to show
			// decoding is active
			paint.setColor(laserColor);
			paint.setAlpha(SCANNER_ALPHA[scannerAlpha]);
			scannerAlpha = (scannerAlpha + 1) % SCANNER_ALPHA.length;

			if ((frame.top + toplaser) < frame.height() + frame.top - 20)
			{
				Resources r = this.getContext().getResources();
				InputStream is = r.openRawResource(R.drawable.faguang);
				BitmapDrawable bmpDraw = new BitmapDrawable(is);
				Bitmap bmp = bmpDraw.getBitmap();

				Rect src = new Rect(0, 0, bmp.getWidth(), bmp.getHeight());
				RectF dst = new RectF(frame.left + 2, frame.top + toplaser, frame.right - 1, frame.top + toplaser + bmp.getHeight() + 20);
				canvas.drawBitmap(bmp, src, dst, null);

				toplaser += 5;
				invalidate();

			} else
			{
				toplaser = 1;
				invalidate();
			}

			Collection<ResultPoint> currentPossible = possibleResultPoints;
			Collection<ResultPoint> currentLast = lastPossibleResultPoints;
			if (currentPossible.isEmpty())
			{
				lastPossibleResultPoints = null;
			} else
			{
				possibleResultPoints = new HashSet<ResultPoint>(5);
				lastPossibleResultPoints = currentPossible;
				paint.setAlpha(OPAQUE);
				paint.setColor(resultPointColor);
				for (ResultPoint point : currentPossible)
				{
					canvas.drawCircle(frame.left + point.getX(), frame.top + point.getY(), 6.0f, paint);
				}
			}
			if (currentLast != null)
			{
				paint.setAlpha(OPAQUE / 2);
				paint.setColor(resultPointColor);
				for (ResultPoint point : currentLast)
				{
					canvas.drawCircle(frame.left + point.getX(), frame.top + point.getY(), 3.0f, paint);
				}
			}

			// Request another update at the animation interval, but only
			// repaint the laser line,
			// not the entire viewfinder mask.
			postInvalidateDelayed(ANIMATION_DELAY, frame.left, frame.top, frame.right, frame.bottom);
		}
	}

	public void drawViewfinder()
	{
		resultBitmap = null;
		invalidate();
	}

	/**
	 * Draw a bitmap with the result points highlighted instead of the live
	 * scanning display.
	 * 
	 * @param barcode
	 *            An image of the decoded barcode.
	 */
	public void drawResultBitmap(Bitmap barcode)
	{
		resultBitmap = barcode;
		invalidate();
	}

	public void addPossibleResultPoint(ResultPoint point)
	{
		possibleResultPoints.add(point);
	}

	// 灏咲rawable杞寲涓築itmap
	public static Bitmap drawableToBitmap(Drawable drawable)
	{
		int width = drawable.getIntrinsicWidth();
		int height = drawable.getIntrinsicHeight();
		Bitmap bitmap = Bitmap.createBitmap(width, height, drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
		Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, width, height);
		drawable.draw(canvas);
		return bitmap;

	}
}
