package com.amgoo.tool;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.util.Base64;

/**
 * Tools for handler picture
 * 
 * @author Ryan.Tang
 * 
 */
public final class ImageTools
{
	public final static int DEAFUTL_BITMAP_WIDTH_SIZE = 1280;
	public final static int DEAFUTL_BITMAP_HEIGHT_SIZE = 1280;
	/**
	 * Transfer drawable to bitmap
	 * 
	 * @param drawable
	 * @return
	 */
	public static Bitmap drawableToBitmap(Drawable drawable)
	{
		int w = drawable.getIntrinsicWidth();
		int h = drawable.getIntrinsicHeight();

		Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565;
		Bitmap bitmap = Bitmap.createBitmap(w, h, config);
		Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, w, h);
		drawable.draw(canvas);
		return bitmap;
	}

	/**
	 * Bitmap to drawable
	 * 
	 * @param bitmap
	 * @return
	 */
	public static Drawable bitmapToDrawable(Bitmap bitmap)
	{
		return new BitmapDrawable(bitmap);
	}

	/**
	 * Input stream to bitmap
	 * 
	 * @param inputStream
	 * @return
	 * @throws Exception
	 */
	public static Bitmap inputStreamToBitmap(InputStream inputStream) throws Exception
	{
		return BitmapFactory.decodeStream(inputStream);
	}

	/**
	 * Byte transfer to bitmap
	 * 
	 * @param byteArray
	 * @return
	 */
	public static Bitmap byteToBitmap(byte[] byteArray)
	{
		if (byteArray.length != 0)
		{
			return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
		} else
		{
			return null;
		}
	}

	/**
	 * Byte transfer to drawable
	 * 
	 * @param byteArray
	 * @return
	 */
	public static Drawable byteToDrawable(byte[] byteArray)
	{
		ByteArrayInputStream ins = null;
		if (byteArray != null)
		{
			ins = new ByteArrayInputStream(byteArray);
		}
		return Drawable.createFromStream(ins, null);
	}

	/**
	 * Bitmap transfer to bytes
	 * 
	 * @param byteArray
	 * @return
	 */
	public static byte[] bitmapToBytes(Bitmap bm)
	{
		byte[] bytes = null;
		if (bm != null)
		{
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
			bytes = baos.toByteArray();
		}
		return bytes;
	}

	/**
	 * Drawable transfer to bytes
	 * 
	 * @param drawable
	 * @return
	 */
	public static byte[] drawableToBytes(Drawable drawable)
	{
		BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
		Bitmap bitmap = bitmapDrawable.getBitmap();
		byte[] bytes = bitmapToBytes(bitmap);
		;
		return bytes;
	}

	/**
	 * Base64 to byte[] //
	 */
	// public static byte[] base64ToBytes(String base64) throws IOException {
	// byte[] bytes = Base64.decode(base64);
	// return bytes;
	// }
	//
	// /**
	// * Byte[] to base64
	// */
	// public static String bytesTobase64(byte[] bytes) {
	// String base64 = Base64.encode(bytes);
	// return base64;
	// }

	/**
	 * Create reflection images
	 * 
	 * @param bitmap
	 * @return
	 */
	public static Bitmap createReflectionImageWithOrigin(Bitmap bitmap)
	{
		final int reflectionGap = 4;
		int w = bitmap.getWidth();
		int h = bitmap.getHeight();

		Matrix matrix = new Matrix();
		matrix.preScale(1, -1);

		Bitmap reflectionImage = Bitmap.createBitmap(bitmap, 0, h / 2, w, h / 2, matrix, false);

		Bitmap bitmapWithReflection = Bitmap.createBitmap(w, (h + h / 2), Config.ARGB_8888);

		Canvas canvas = new Canvas(bitmapWithReflection);
		canvas.drawBitmap(bitmap, 0, 0, null);
		Paint deafalutPaint = new Paint();
		canvas.drawRect(0, h, w, h + reflectionGap, deafalutPaint);

		canvas.drawBitmap(reflectionImage, 0, h + reflectionGap, null);

		Paint paint = new Paint();
		LinearGradient shader = new LinearGradient(0, bitmap.getHeight(), 0, bitmapWithReflection.getHeight() + reflectionGap, 0x70ffffff, 0x00ffffff, TileMode.CLAMP);
		paint.setShader(shader);
		// Set the Transfer mode to be porter duff and destination in
		paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
		// Draw a rectangle using the paint with our linear gradient
		canvas.drawRect(0, h, w, bitmapWithReflection.getHeight() + reflectionGap, paint);

		return bitmapWithReflection;
	}

	/**
	 * Get rounded corner images
	 * 
	 * @param bitmap
	 * @param roundPx
	 *            5 10
	 * @return
	 */
	public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, float roundPx)
	{
		int w = bitmap.getWidth();
		int h = bitmap.getHeight();
		Bitmap output = Bitmap.createBitmap(w, h, Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, w, h);
		final RectF rectF = new RectF(rect);
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);

		return output;
	}

	/**
	 * Resize the bitmap
	 * 
	 * @param bitmap
	 * @param width
	 * @param height
	 * @return
	 */
	public static Bitmap zoomBitmap(Bitmap bitmap, int width, int height)
	{
		int w = bitmap.getWidth();
		int h = bitmap.getHeight();
		Matrix matrix = new Matrix();
		float scaleWidth = ((float) width / w);
		float scaleHeight = ((float) height / h);
		matrix.postScale(scaleWidth, scaleHeight);
		Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
		return newbmp;
	}

	/**
	 * Resize the drawable
	 * 
	 * @param drawable
	 * @param w
	 * @param h
	 * @return
	 */
	public static Drawable zoomDrawable(Drawable drawable, int w, int h)
	{
		int width = drawable.getIntrinsicWidth();
		int height = drawable.getIntrinsicHeight();
		Bitmap oldbmp = drawableToBitmap(drawable);
		Matrix matrix = new Matrix();
		float sx = ((float) w / width);
		float sy = ((float) h / height);
		matrix.postScale(sx, sy);
		Bitmap newbmp = Bitmap.createBitmap(oldbmp, 0, 0, width, height, matrix, true);
		return new BitmapDrawable(newbmp);
	}

	/**
	 * Get images from SD card by path and the name of image
	 * 
	 * @param photoName
	 * @return
	 */
	public static Bitmap getPhotoFromSDCard(String path, String photoName)
	{
		Bitmap photoBitmap = BitmapFactory.decodeFile(path + "/" + photoName + ".png");
		if (photoBitmap == null)
		{
			return null;
		} else
		{
			return photoBitmap;
		}
	}

	/**
	 * Check the SD card
	 * 
	 * @return
	 */
	public static boolean checkSDCardAvailable()
	{
		return android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
	}

	/**
	 * Get image from SD card by path and the name of image
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean findPhotoFromSDCard(String path, String photoName)
	{
		boolean flag = false;

		if (checkSDCardAvailable())
		{
			File dir = new File(path);
			if (dir.exists())
			{
				File folders = new File(path);
				File photoFile[] = folders.listFiles();
				for (int i = 0; i < photoFile.length; i++)
				{
					String fileName = photoFile[i].getName().split("\\.")[0];
					if (fileName.equals(photoName))
					{
						flag = true;
					}
				}
			} else
			{
				flag = false;
			}
			// File file = new File(path + "/" + photoName + ".jpg" );
			// if (file.exists()) {
			// flag = true;
			// }else {
			// flag = false;
			// }

		} else
		{
			flag = false;
		}
		return flag;
	}

	public static String bitmapToBase64(Bitmap bitmap)
	{

		String result = null;
		ByteArrayOutputStream baos = null;
		try
		{
			if (bitmap != null)
			{
				baos = new ByteArrayOutputStream();
				bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

				baos.flush();
				baos.close();

				byte[] bitmapBytes = baos.toByteArray();
				result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				if (baos != null)
				{
					baos.flush();
					baos.close();
				}
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * Save image to the SD card
	 * 
	 * @param photoBitmap
	 * @param photoName
	 * @param path
	 */
	public static void savePhotoToSDCard(Bitmap photoBitmap, String path, String photoName)
	{
		if (checkSDCardAvailable())
		{
			File dir = new File(path);
			if (!dir.exists())
			{
				dir.mkdirs();
			}

			File photoFile = new File(path, photoName + ".png");
			FileOutputStream fileOutputStream = null;
			try
			{
				fileOutputStream = new FileOutputStream(photoFile);
				if (photoBitmap != null)
				{
					if (photoBitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream))
					{
						fileOutputStream.flush();
						// fileOutputStream.close();
					}
				}
			} catch (FileNotFoundException e)
			{
				photoFile.delete();
				e.printStackTrace();
			} catch (IOException e)
			{
				photoFile.delete();
				e.printStackTrace();
			} finally
			{
				try
				{
					fileOutputStream.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
	}

	public static Bitmap base64ToBitmap(String base64Data)
	{
		byte[] bytes = Base64.decode(base64Data, Base64.DEFAULT);
		return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
	}

	/**
	 * Delete the image from SD card
	 * 
	 * @param context
	 * @param path
	 *            file:///sdcard/temp.jpg
	 */
	public static void deleteAllPhoto(String path)
	{
		if (checkSDCardAvailable())
		{
			File folder = new File(path);
			File[] files = folder.listFiles();
			for (int i = 0; i < files.length; i++)
			{
				files[i].delete();
			}
		}
	}

	public static void deletePhotoAtPathAndName(String path, String fileName)
	{
		if (checkSDCardAvailable())
		{
			File folder = new File(path);
			File[] files = folder.listFiles();
			for (int i = 0; i < files.length; i++)
			{
				System.out.println(files[i].getName());
				if (files[i].getName().equals(fileName))
				{
					files[i].delete();
				}
			}
		}
	}

	/**
	 * 
	 * @param context
	 * @param path
	 *            路徑path
	 * 
	 * @return
	 */
	public static Bitmap getBitmapFromPath(Context context, String path)
	{
		int bitmapWidthHeight[] = getBtimapWidthHeight(context, path);
		int width = bitmapWidthHeight[0];
		int height = bitmapWidthHeight[1];
		int sampleSize = 1;
		int qulitySize = 200;
		int destWidth = DEAFUTL_BITMAP_WIDTH_SIZE;
		int destHeight = DEAFUTL_BITMAP_HEIGHT_SIZE;
		return getBitmap(context, path, width, height, sampleSize, qulitySize, destWidth, destHeight);

	}

	/**
	 * 
	 * @param context
	 * @param sourceUri
	 * @return
	 */
	public static Bitmap getBitmapFromUri(Context context, Uri sourceUri)
	{
		int bitmapWidthHeight[] = getBtimapWidthHeight(context, sourceUri);
		int width = bitmapWidthHeight[0];
		int height = bitmapWidthHeight[1];
		int sampleSize = 1;
		int qulitySize = 200;
		int destWidth = DEAFUTL_BITMAP_WIDTH_SIZE;
		int destHeight = DEAFUTL_BITMAP_HEIGHT_SIZE;
		return getBitmap(context, sourceUri, width, height, sampleSize, qulitySize, destWidth, destHeight);

	}

	/**
	 * 
	 * @param context
	 * @param sourceUri
	 *            路徑uri
	 * @param sampleSize
	 *            需要压缩的默认值 如果为零的话 sampleSize为 1 这个只随着 destWidth， destHeight 变化
	 *            这个值一般不传
	 * @param qulitySize
	 *            图片的质量 比如（200k）
	 * @param destWidth
	 *            如果为0 默认为 DEAFUTL_BITMAP_WIDTH_SIZE
	 * @param destHeight
	 *            如果为0 默认为 DEAFUTL_BITMAP_HEIGHT_SIZE
	 * @return
	 */
	public static Bitmap getBitmapFromUri(Context context, Uri sourceUri, int sampleSize, int qulitySize, int destWidth, int destHeight)
	{
		int bitmapWidthHeight[] = getBtimapWidthHeight(context, sourceUri);
		int width = bitmapWidthHeight[0];
		int height = bitmapWidthHeight[1];
		return getBitmap(context, sourceUri, width, height, sampleSize, qulitySize, destWidth, destHeight);

	}

	public static int[] getBtimapWidthHeight(Context context, Uri sourceUri)
	{
		int[] wH = new int[2];
		InputStream is = null;
		try
		{

			is = getInputStream(context, sourceUri);

			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = true;
			BitmapFactory.decodeStream(is, null, options);

			wH[0] = options.outWidth;
			wH[1] = options.outHeight;

		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			if (is != null)
			{
				try
				{
					is.close();
				} catch (IOException ignored)
				{
				}
			}
		}
		return wH;
	}

	public static int[] getBtimapWidthHeight(Context context, String path)
	{
		int[] wH = new int[2];
		InputStream is = null;
		try
		{

			is = new java.io.FileInputStream(path);
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = true;
			BitmapFactory.decodeStream(is, null, options);

			wH[0] = options.outWidth;
			wH[1] = options.outHeight;

		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			if (is != null)
			{
				try
				{
					is.close();
				} catch (IOException ignored)
				{
				}
			}
		}
		return wH;
	}

	/**
	 * 此处写方法描述
	 * 
	 * @Title: getBitmap
	 * @param intent
	 * @return
	 * @return void
	 * @date 2012-12-13 下午8:22:23
	 */
	private static Bitmap getBitmap(Context context, Uri sourceUri, int width, int height, int sampleSize, int qulitySize, int destWidth, int destHeight)
	{
		if (sampleSize == 0)
		{
			sampleSize = 1;
		}
		if (destWidth == 0)
		{
			destWidth = DEAFUTL_BITMAP_WIDTH_SIZE;
		}
		if (destHeight == 0)
		{
			destHeight = DEAFUTL_BITMAP_HEIGHT_SIZE;
		}

		Bitmap bitmap = null;
		int sSize = sampleSize;
		InputStream is = null;
		try
		{

			try
			{
				is = getInputStream(context, sourceUri);
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			while ((width / sSize > destWidth) || (height / sSize > destHeight))
			{
				sSize *= 2;
			}

			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inSampleSize = sSize;
			bitmap = BitmapFactory.decodeStream(is, null, options);
		} catch (OutOfMemoryError error)
		{
		} finally
		{
			if (is != null)
			{
				try
				{
					is.close();
				} catch (IOException ignored)
				{
				}
			}
		}

		String path = getFilePath(context, sourceUri);
		// 判断图片是不是旋转了90度，是的话就进行纠正。
		boolean isBitmapRotate = isRotateImage(path);
		if (isBitmapRotate)
		{
			return rotateBitmap(compressBitmapQulity(bitmap, qulitySize));
		}

		return compressBitmapQulity(bitmap, qulitySize);
	}

	/**
	 * 此处写方法描述
	 * 
	 * @Title: getBitmap
	 * @param intent
	 * @return
	 * @return void
	 * @date 2012-12-13 下午8:22:23
	 */
	private static Bitmap getBitmap(Context context, String path, int width, int height, int sampleSize, int qulitySize, int destWidth, int destHeight)
	{
		if (sampleSize == 0)
		{
			sampleSize = 1;
		}
		if (destWidth == 0)
		{
			destWidth = DEAFUTL_BITMAP_WIDTH_SIZE;
		}
		if (destHeight == 0)
		{
			destHeight = DEAFUTL_BITMAP_HEIGHT_SIZE;
		}

		Bitmap bitmap = null;
		int sSize = sampleSize;
		InputStream is = null;
		try
		{

			try
			{
				is = new java.io.FileInputStream(path);
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			while ((width / sSize > destWidth) || (height / sSize > destHeight))
			{
				sSize *= 2;
			}

			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inSampleSize = sSize;
			bitmap = BitmapFactory.decodeStream(is, null, options);
		} catch (OutOfMemoryError error)
		{
		} finally
		{
			if (is != null)
			{
				try
				{
					is.close();
				} catch (IOException ignored)
				{
				}
			}
		}

		// String path = getFilePath(context, sourceUri);
		// 判断图片是不是旋转了90度，是的话就进行纠正。
		boolean isBitmapRotate = isRotateImage(path);
		if (isBitmapRotate)
		{
			return rotateBitmap(compressBitmapQulity(bitmap, qulitySize));
		}

		return compressBitmapQulity(bitmap, qulitySize);
	}

	/**
	 * 获取输入流
	 * 
	 * @Title: getInputStream
	 * @param mUri
	 * @return
	 * @return InputStream
	 * @date 2012-12-14 上午9:00:31
	 */
	private static InputStream getInputStream(Context context, Uri mUri) throws IOException
	{
		try
		{
			if (mUri.getScheme().equals("file"))
			{
				return new java.io.FileInputStream(mUri.getPath());
			} else
			{
				return context.getContentResolver().openInputStream(mUri);
			}
		} catch (FileNotFoundException ex)
		{
			return null;
		}
	}

	/**
	 * 压缩图片的质量
	 * 
	 * @param sourceBitmap
	 *            原始图片
	 * @param size
	 *            压缩到多少k（比如30k）
	 * @return
	 */
	private static Bitmap compressBitmapQulity(Bitmap sourceBitmap, int size)
	{
		if (sourceBitmap == null)
		{
			return null;
		}
		Bitmap bitmap = null;
		try
		{
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			sourceBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);//
			// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
			int options = 100;
			while (baos.toByteArray().length / 1024 > size)
			{ //
				// 循环判断如果压缩后图片是否大于30kb,大于继续压缩
				baos.reset();// 重置baos即清空baos
				sourceBitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);//
				// 这里压缩options%，把压缩后的数据存放到baos中
				options -= 10;// 每次都减少10
			}
			ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//
			// 把压缩后的数据baos存放到ByteArrayInputStream中
			bitmap = BitmapFactory.decodeStream(isBm, null, null);//
			// 把ByteArrayInputStream数据生成图片
		} catch (OutOfMemoryError e)
		{
		}

		return bitmap;
	}

	/**
	 * 根据Uri返回文件路径
	 * 
	 * @Title: getInputString
	 * @param mUri
	 * @return
	 * @return String
	 * @date 2012-12-14 上午9:14:19
	 */
	private static String getFilePath(Context context, Uri mUri)
	{
		try
		{
			if (mUri.getScheme().equals("file"))
			{
				return mUri.getPath();
			} else
			{
				return getFilePathByUri(context, mUri);
			}
		} catch (FileNotFoundException ex)
		{
			return null;
		}
	}

	/**
	 * 此处写方法描述
	 * 
	 * @Title: getFilePathByUri
	 * @param mUri
	 * @return
	 * @return String
	 * @date 2012-12-14 上午9:16:33
	 */
	private static String getFilePathByUri(Context context, Uri mUri) throws FileNotFoundException
	{
		String imgPath;
		Cursor cursor = context.getContentResolver().query(mUri, null, null, null, null);
		cursor.moveToFirst();
		imgPath = cursor.getString(1); // 图片文件路径
		return imgPath;
	}

	/**
	 * 判断是否旋转
	 * 
	 * @Title: rotateImage
	 * @param path
	 * @return void
	 * @date 2012-12-14 上午10:58:26
	 */
	private static boolean isRotateImage(String path)
	{

		try
		{
			ExifInterface exifInterface = new ExifInterface(path);

			int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

			if (orientation == ExifInterface.ORIENTATION_ROTATE_90)
			{
				return true;
			}

		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 旋转原图
	 * 
	 * @Title: initBitmap
	 * @return void
	 * @date 2012-12-13 下午5:37:15
	 */
	private static Bitmap rotateBitmap(Bitmap souceBitmap)
	{
		if (souceBitmap == null)
		{
			return null;
		}

		Matrix m = new Matrix();
		m.setRotate(90);
		int width = souceBitmap.getWidth();
		int height = souceBitmap.getHeight();

		try
		{
			return Bitmap.createBitmap(souceBitmap, 0, 0, width, height, m, true);
		} catch (OutOfMemoryError ooe)
		{

			// m.postScale((float) 1 / sampleSize, (float) 1 / sampleSize);
			// mBitmap = Bitmap
			// .createBitmap(mBitmap, 0, 0, width, height, m, true);

		}
		return null;
	}

}
