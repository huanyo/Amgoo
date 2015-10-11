package com.amgoo.activity;

import java.util.ArrayList;

import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
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
import android.widget.ListView;
import android.widget.TextView;

import com.amgoo.entiy.FileClass;
import com.amgoo.entiy.FileClassObjectData;
import com.amgoo.entiy.FilecCassObject;
import com.amgoo.tool.Contant;
import com.amgoo.util.DialogUtil;
import com.amgoo.util.HttpGetCallBack;
import com.amgoo.util.HttpQuest;
import com.amgoo.util.JsonParser;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;

public class FileClassActivity extends Activity implements OnItemClickListener
{

	private Dialog mProgressLoading;
	ArrayList<FilecCassObject> downtype = new ArrayList<FilecCassObject>();
	private fileadapter adapter;
	private ListView lvFileClass;

	Handler myHandler = new Handler()
	{
		public void handleMessage(Message msg)
		{
			if (msg.what == 0)
			{
				adapter.notifyDataSetChanged();
			}
			super.handleMessage(msg);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_file_class);
		lvFileClass = (ListView) findViewById(R.id.lv_fileclass);
		adapter = new fileadapter();
		lvFileClass.setAdapter(adapter);
		lvFileClass.setOnItemClickListener(this);
		getFileClass();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.file_class, menu);
		return true;
	}

	class fileadapter extends BaseAdapter
	{

		@Override
		public int getCount()
		{
			if (downtype == null)
			{
				return 0;
			}
			return downtype.size();
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
			View inflate = getLayoutInflater().inflate(R.layout.fileclass_item, null);
			TextView tvFileClassName = (TextView) inflate.findViewById(R.id.tv_fileclassname);
			TextView tvFileClassTime = (TextView) inflate.findViewById(R.id.tv_fileclasstime);
			String addtime = downtype.get(position).getAddtime();
			String name = downtype.get(position).getName();
			tvFileClassName.setText(name);
			tvFileClassTime.setText(addtime);
			return inflate;
		}

	}

	private void getFileClass()
	{
		ArrayList<BasicNameValuePair> paramsList = new ArrayList<BasicNameValuePair>();
		HttpQuest.Get(Contant.ServerDataUrl.FILECLASS, paramsList, new HttpGetCallBack()
		{
			@Override
			public void onStart()
			{
			}

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo)
			{
				stopProgress();
				FileClass fileclass = JsonParser.deserializeByJson(responseInfo.result, FileClass.class);
				FileClassObjectData data = fileclass.getData();
				downtype = data.getDowntype();
				if (fileclass.getStatus() == 1)
				{
					myHandler.sendEmptyMessage(0);
				}
			}

			@Override
			public void onFailure(HttpException error, String msg)
			{
			}
		});

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
	public void onItemClick(AdapterView<?> parent, View view, int position, long id)
	{
		
		int id2 = downtype.get(position).getId();
		Intent intent = new Intent(FileClassActivity.this, DownLoadFileActivity.class);
		intent.putExtra("id", id2);
		startActivity(intent);
	}
}
