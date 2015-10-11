package com.amgoo.activity;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.amgoo.entiy.Download;
import com.amgoo.entiy.DownloadArry;
import com.amgoo.entiy.DownloadObject;
import com.amgoo.entiy.DownloadPage;
import com.amgoo.tool.Contant;
import com.amgoo.tool.XListView;
import com.amgoo.tool.XListView.IXListViewListener;
import com.amgoo.util.DialogUtil;
import com.amgoo.util.HttpDownloader;
import com.amgoo.util.HttpGetCallBack;
import com.amgoo.util.HttpQuest;
import com.amgoo.util.JsonParser;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;

public class DownLoadFileActivity extends Activity implements IXListViewListener,OnItemClickListener
{
	private Dialog mProgressLoading;
	private XListView lvDownload;
	private fileDownloadAdapter adpter;
	private int PageIndex = 1;
	private int mMaxpage;
	private Handler mHandler;
	private int id;
	private ArrayList<DownloadObject> downlist = new ArrayList<DownloadObject>();
	String filename;
	Handler myHandler = new Handler()
	{
		public void handleMessage(Message msg)
		{
			if (msg.what == 0)
			{
				adpter.notifyDataSetChanged();
			} else
			{
				lvDownload.NoData();
				adpter.notifyDataSetChanged();
				Toast.makeText(DownLoadFileActivity.this, getResources().getText(R.string.nomoredata), Toast.LENGTH_SHORT).show();

			}
			super.handleMessage(msg);
		}
	};
	private String url;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_down_load_file);
		lvDownload = (XListView) findViewById(R.id.lv_download);
		lvDownload.setPullLoadEnable(true);
		lvDownload.setXListViewListener(this);
		lvDownload.setOnItemClickListener(this);
		mHandler = new Handler();
		adpter = new fileDownloadAdapter();
		lvDownload.setAdapter(adpter);
		id = getIntent().getIntExtra("id", 0);
		getDownload(id, PageIndex);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.down_load_file, menu);
		return true;
	}

	class fileDownloadAdapter extends BaseAdapter
	{

		@Override
		public int getCount()
		{
			if (downlist == null)
			{
				return 0;
			}
			return downlist.size();
		}

		@Override
		public Object getItem(int position)
		{
			return position;
		}

		@Override
		public long getItemId(int position)
		{
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{
			View inflate = getLayoutInflater().inflate(R.layout.download_item, null);
			TextView tvDownName = (TextView) inflate.findViewById(R.id.tv_downname);
			TextView tvFileUrl = (TextView) inflate.findViewById(R.id.tv_fileurl);
			String name = downlist.get(position).getName();
			String url = downlist.get(position).getUrl();
			tvDownName.setText(name);
			Log.e("-------------------------------", url);
			return inflate;
		}

	}

	private void getDownload(int id, int PageIndex)
	{
		ArrayList<BasicNameValuePair> paramsList = new ArrayList<BasicNameValuePair>();
		paramsList.add(new BasicNameValuePair("type", id + ""));
		paramsList.add(new BasicNameValuePair("p", PageIndex + ""));
		HttpQuest.Get(Contant.ServerDataUrl.FILEDOWNLOAD, paramsList, new HttpGetCallBack()
		{
			@Override
			public void onStart()
			{
			}

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo)
			{
				stopProgress();
				Download download = JsonParser.deserializeByJson(responseInfo.result, Download.class);
				DownloadArry data = download.getData();
				ArrayList<DownloadObject> download2 = data.getDownload();
				DownloadPage page = data.getPage();
				int current_page = page.getCurrent_page();
				mMaxpage = current_page + 1;
				if (download.getStatus() == 1)
				{
					downlist.addAll(download2);
					myHandler.sendEmptyMessage(0);
				}
			}

			@Override
			public void onFailure(HttpException error, String msg)
			{

			}
		});

	}

	private void onLoad()
	{
		lvDownload.stopRefresh();
		lvDownload.stopLoadMore();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日   HH:mm:ss");
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		String str = formatter.format(curDate);
		lvDownload.setRefreshTime(str);
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

	@Override
	public void onRefresh()
	{
		mHandler.postDelayed(new Runnable()
		{
			@Override
			public void run()
			{
				downlist.clear();
				PageIndex = 1;
				getDownload(id, 1);
				onLoad();
			}
		}, 2000);

	}

	@Override
	public void onLoadMore()
	{

		mHandler.postDelayed(new Runnable()
		{

			@Override
			public void run()
			{
				PageIndex++;
				if (PageIndex < mMaxpage) // TODO
				{
					getDownload(id, PageIndex);
					myHandler.sendEmptyMessage(0);
					onLoad();
				} else
				{
					myHandler.sendEmptyMessage(1);
				}
			}
		}, 2000);

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		
		url = downlist.get(arg2).getUrl();
		//创建一个匿名线程用于下载文件
		new Thread()
		{
			public void run()
			{						
				String encodedURL = getEncodedURL(url);
				String file = filename();
				HttpDownloader httpDownloader=new HttpDownloader();
				int result=httpDownloader.download(encodedURL,"Azone/",file);
			}
		}.start();
	
	}
	  public String filename()
	    {
	       if (filename == null || "".equals(filename)) {
	        filename = url.substring(url.lastIndexOf("/") + 1);
	          }
	         return filename;
	    }
	    
	    public static String getEncodedURL(String url)
	    {
	        if (url == null)
	       {
	           return null;
	       }
	          int pathEnd = url.lastIndexOf('/') + 1;
	          int index = -1;
	    // 获取编码后的URL
	       String downUrlPath = url.substring(0, pathEnd);
	       String downURLFile = "";
	       if ((index = url.indexOf("?")) > -1)
	       {
	    // 对文件名编码
	          downURLFile = URLEncoder.encode(url.substring(pathEnd, index));
	       }
	      else
	       {
	            downURLFile = URLEncoder.encode(url.substring(pathEnd, url.length()));
	       }
	             return downUrlPath + downURLFile;
	    }

}
