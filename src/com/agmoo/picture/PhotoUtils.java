package com.agmoo.picture;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

/***************************************************** 
 * @Description:图片工具类
 ****************************************************/

public class PhotoUtils
{
	
	private static final String Tag = "PictureUtils";
	public static ArrayList<PhotoItem> bitmaps =new ArrayList<PhotoItem>();

	

	/**
	 * 移除Bitmap图片
	 * 
	 * @param bitmap
	 */
	public static void removeBitMap(Bitmap bitmap)
	{
		bitmaps.remove(bitmap);
	}
	/**
	 * 图片路径转化为Bitmap
	 * 
	 * @param path
	 * @throws IOException 
	 */

	public static void addImageItem(PhotoItem item) 
	{
		bitmaps.add(item);		
	}
	
	
	public static Bitmap big(Bitmap bitmap) {
		  Matrix matrix = new Matrix();
		  matrix.postScale(1.5f,1.5f); //长和宽放大缩小的比例
		  Bitmap resizeBmp = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
		  return resizeBmp;
		 }

//	public static Bitmap small(Bitmap bitmap)
//	{
//		  Matrix matrix = new Matrix();
//		  matrix.postScale(0.5f,0.5f); //长和宽放大缩小的比例
//		  Bitmap small = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
//		  return small;
//    }
	
	
	
	
	/**
	 * 图片生成工具
	 * 
	 * 路径转化为Bitmap 
	 * @param path
	 * @param w
	 * @param h
	 * @return
	 */
	 public static Bitmap convertToBitmap(String path, int w, int h) 
	 {
		             BitmapFactory.Options opts = new BitmapFactory.Options();
		         	
		             
		             // 设置为ture只获取图片大�?
		             opts.inJustDecodeBounds = true;
		             opts.inPreferredConfig = Bitmap.Config.ARGB_8888;
		             BitmapFactory.decodeFile(path, opts) ;        
		              // 返回为空
		              int width = opts.outWidth;
		              int height = opts.outHeight;
		              float scaleWidth = 0.f, scaleHeight = 0.f;
		              if (width  > w || height > h) 
		              {
		                 // 缩放
		                 scaleWidth  = Math.round(width / w);
		                 scaleHeight = Math.round(height/ h);
		              }
		            	    
		             opts.inJustDecodeBounds = false;       
		             float scale = Math.max(scaleWidth, scaleHeight);			      
		             opts.inSampleSize = (int) scale;	                         
		             BufferedInputStream in = null;
						try 
						{
							in = new BufferedInputStream(new FileInputStream(
									new File(path)));
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} 
		             Bitmap bitmap = BitmapFactory.decodeStream(in, null,opts);
		             ByteArrayOutputStream stream = new ByteArrayOutputStream();
		             bitmap.compress(Bitmap.CompressFormat.JPEG, 65, stream);// (0-100)压缩文件
		     	
		     		try
		     		{
						stream.flush();
						stream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
		             return bitmap;		             
		         }

}
