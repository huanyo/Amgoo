package com.agmoo.picture;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;

/***********************************************************
 * @Description:图片文件夹帮助类
 ***********************************************************/

@SuppressLint("SimpleDateFormat")
public class PhotoFileUtils {

	public static String SDPATH = Environment.getExternalStorageDirectory()
			+ "/carcore_imgcache/";
	
	public static File getImageFile(Bitmap photo) {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		photo.compress(Bitmap.CompressFormat.JPEG, 75, stream);// (0-100)压缩文件
		// 把图片显示在ImageView控件�?
		byte[] byteArray = stream.toByteArray();
		// 此处可以把Bitmap保存到sd卡中
		String filepath = Environment.getExternalStorageDirectory() + "/temp.jpg";
		File file = new File(filepath);
		FileOutputStream fops;
		try
		{
			fops = new FileOutputStream(file);
			fops.write(byteArray);
		    fops.flush();
		    fops.close();
		} 
		catch (Exception e1) 
		{
			e1.printStackTrace();
		}
		return file;
	}

	public static String saveBitmap(Bitmap bm, String picName) {
		Log.e("", "保存图片");
		String path = "";
		try {

			if (!isFileExist("")) {
				@SuppressWarnings("unused")
				File temp = createSDDir("");
			}

			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");// 获取当前时间，进�?��转化为字符串
			Date date = new Date();
			String str = format.format(date);

			File f = new File(SDPATH, str + picName + ".JPEG");
			path = f.getPath();
			if (f.exists()) {
				f.delete();
			}
			FileOutputStream out = new FileOutputStream(f);
			bm.compress(Bitmap.CompressFormat.JPEG, 60, out);
			out.flush();
			out.close();
			Log.e("", "已经保存");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

	public static File createSDDir(String dirName) throws IOException {
		File dir = new File(SDPATH + dirName);
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {

			System.out.println("createSDDir:" + dir.getAbsolutePath());
			System.out.println("createSDDir:" + dir.mkdir());
		}
		return dir;
	}

	public static boolean isFileExist(String fileName) {
		File file = new File(SDPATH + fileName);
		file.isFile();
		return file.exists();
	}

	public static void delFile(String fileName) {
		File file = new File(SDPATH + fileName);
		if (file.isFile()) {
			file.delete();
		}
		file.exists();
	}

	public static void deleteDir() {
		File dir = new File(SDPATH);
		if (dir == null || !dir.exists() || !dir.isDirectory())
			return;

		for (File file : dir.listFiles()) {
			if (file.isFile())
				file.delete(); // 删除�?��文件
			else if (file.isDirectory())
				deleteDir(); // 递规的方式删除文件夹
		}
		dir.delete();// 删除目录本身
	}

	public static boolean fileIsExists(String path) {
		try {
			File f = new File(path);
			if (!f.exists()) {
				return false;
			}
		} catch (Exception e) {

			return false;
		}
		return true;
	}
	
	public static String getFileURL(String path)
	{
			
		return new String("File://"+path);	
	}
	
	
	 // 使用系统当前日期加以调整作为照片的名�?
	public static String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "'IMG'_yyyyMMdd_HHmmss");
        return dateFormat.format(date) + ".jpg";
    }

}
