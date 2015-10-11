package com.amgoo.zxing;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.Drawable;

public class ImageUtil {
 
    public static  Drawable getDrawable(Context context,int drawableId) {
        Drawable drawable = context.getResources().getDrawable(drawableId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                drawable.getMinimumHeight());
        return drawable;
    }
    
    /**
     * 写图片文件到SD卡
     * 
     * @throws IOException
     */
    public static void saveImageToSD(String filePath,
            Bitmap bitmap, DoneListener mDoneListener) throws IOException
    {
        saveImageToSD(filePath, bitmap, 100, mDoneListener);
    }

    public static void saveImageToSD(String filePath,
            Bitmap bitmap, int quality, DoneListener mDoneListener) throws IOException
    {
        File file = new File(filePath);
        if (!file.exists())
            file.createNewFile();
        if (bitmap != null)
        {
            FileOutputStream fos = new FileOutputStream(
                    file);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(CompressFormat.JPEG, quality, stream);
            byte[] bytes = stream.toByteArray();
            fos.write(bytes);
            fos.close();
            if (mDoneListener != null) {
                mDoneListener.done();
            }
        }
    }

    public interface DoneListener {
        void done();
    }

    public static void createShareImage(String imagePath, Bitmap bitmap, DoneListener mDoneListener)
    {
        try
        {
            ImageUtil.saveImageToSD(imagePath, bitmap, mDoneListener);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

