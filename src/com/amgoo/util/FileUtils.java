package com.amgoo.util;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


public class FileUtils {
	private String SDPATH=null;

	public String getSDPATH()
	{
		return SDPATH;
	}
	public FileUtils()
	{
		//��õ�ǰ�ⲿ�洢�豸SD����Ŀ¼
		SDPATH=Environment.getExternalStorageDirectory()+"/";
	}

	//�����ļ�
	public File createFile(String fileName) throws IOException
	{
		File file=new File(SDPATH+fileName);
		file.createNewFile();
		return file;
	}
	//����Ŀ¼
	public File createDir(String fileName) throws IOException
	{
		File dir=new File(SDPATH+fileName);		
		dir.mkdir();
		return dir;
	}
	//�ж��ļ��Ƿ����
	public boolean isExist(String fileName)
	{
		File file=new File(SDPATH+fileName);
		return file.exists();
	}
	
	public File writeToSDPATHFromInput(String path,String fileName,InputStream inputstream)
	{
		File file=null;
		OutputStream outputstream=null;
	    try
		{
	    	createDir(path);
	    	file=createFile(path+fileName);
	    	outputstream=new FileOutputStream(file);
	    	int count = 0;
	    	byte buffer[]=new byte[1024];
//	    	do
//			{
//				int numread = inputstream.read(buffer);
//				count += numread;
//				progress = (int) (((float) count / length) * 100);
				// ���½���
//				mHandler.sendEmptyMessage(DOWN_UPDATE);
//				if (numread <= 0)
//				{
//					// �������֪ͨ��װ
//					mHandler.sendEmptyMessage(DOWN_OVER);
//					break;
//				}
//				outputstream.write(buffer,0,numread);
//			} while ((inputstream.read(buffer))!=-1);// ���ȡ����ֹͣ����.
	    	//���������е����������뵽buffer�л��棬Ȼ���������д���ļ���
	    	while((inputstream.read(buffer))!=-1)
	    	{
	    		outputstream.write(buffer);
	    	}
		}
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
	    finally
	    {
	    	try {
				outputstream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	    return file;
	}
//	private Handler mHandler = new Handler()
//	{
//		public void handleMessage(Message msg)
//		{
//			switch (msg.what)
//			{
//			case DOWN_UPDATE:
//				mProgress.setProgress(progress);
//				break;
//			case DOWN_OVER:
////				Toast.makeText(mContext, "DOWN_OVER", Toast.LENGTH_LONG).show();
//				break;
//			case DOWNLOAD_FAILED:
////				Toast.makeText(mContext, "A net connection failed. Please try again later.", Toast.LENGTH_LONG).show();
//				break;
//			default:
//				break;
//			}
//		};
//	};
//	public void showDownloadDialog()
//	{
//		AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mContext);
//		dialogBuilder.create();
//		LayoutInflater inflater = LayoutInflater.from(mContext);
//		View view = inflater.inflate(R.layout.progress, null);
//		mProgress = (ProgressBar) view.findViewById(R.id.progress);
//		proJindu = (TextView) view.findViewById(R.id.pro_jindu);
//		proJindu.setText("%"+progress);
//		dialogBuilder.setView(view);
//		dialogBuilder.show();
//		dialogBuilder.setCancelable(false);
//	}
}
