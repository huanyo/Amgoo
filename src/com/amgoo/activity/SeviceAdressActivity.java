package com.amgoo.activity;

import java.util.ArrayList;

import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.amgoo.entiy.SeviceAdress;
import com.amgoo.entiy.SeviceArray;
import com.amgoo.entiy.SeviceObject;
import com.amgoo.tool.Contant;
import com.amgoo.util.DialogUtil;
import com.amgoo.util.HttpGetCallBack;
import com.amgoo.util.HttpQuest;
import com.amgoo.util.JsonParser;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;

public class SeviceAdressActivity extends Activity implements OnClickListener
{

	private ListView lvSeviceAdress;
	private Dialog mProgressLoading;
	private seviceAdressdapter adpter;
	private ArrayList<SeviceArray> serviceList = new ArrayList<SeviceArray>();
	Handler myHandler = new Handler()
	{
		public void handleMessage(Message msg)
		{
			if (msg.what == 0)
			{
				adpter.notifyDataSetChanged();
			}
			super.handleMessage(msg);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sevice_adress);
		lvSeviceAdress = (ListView) findViewById(R.id.lv_seviceadress);
		adpter = new seviceAdressdapter();
		lvSeviceAdress.setAdapter(adpter);
		TextView tvSeviceBack = (TextView) findViewById(R.id.tv_seviceback);
		tvSeviceBack.setOnClickListener(this);
		getseviceadress();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.sevice_adress, menu);
		return true;
	}

	class seviceAdressdapter extends BaseAdapter
	{

		@Override
		public int getCount()
		{
			if (serviceList == null)
			{
				return 0;
			}
			return serviceList.size();
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
			View inflate = getLayoutInflater().inflate(R.layout.seviceadress_item, null);
			TextView tvContry = (TextView)inflate.findViewById(R.id.tv_contry);
			TextView tvAdmin = (TextView)inflate.findViewById(R.id.tv_admin);
			TextView tvSeviceAdress = (TextView)inflate.findViewById(R.id.tv_seviceadress);
			TextView tvSeviceAdressTel = (TextView)inflate.findViewById(R.id.tv_seviceadresstel);
			String service_network_nation = serviceList.get(position).getService_network_nation();
			String service_network_name = serviceList.get(position).getService_network_name();
			String service_network_address = serviceList.get(position).getService_network_address();
			String service_network_telephone = serviceList.get(position).getService_network_telephone();

			Log.e("-------------------", service_network_nation+"");
			tvContry.setText(service_network_nation);
			tvAdmin.setText(service_network_name);
			tvSeviceAdress.setText(service_network_address);
			tvSeviceAdressTel.setText(service_network_telephone);

			return inflate;
		}

	}

	private void getseviceadress()           
	{
		ArrayList<BasicNameValuePair> paramsList = new ArrayList<BasicNameValuePair>();
		// paramsList.add(new BasicNameValuePair("jiang", "0"));
		HttpQuest.Get(Contant.ServerDataUrl.SEVICEADRESS, paramsList, new HttpGetCallBack()
		{
			@Override
			public void onStart()
			{
			}

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo)
			{
				stopProgress();
				SeviceAdress seviceadress = JsonParser.deserializeByJson(responseInfo.result, SeviceAdress.class);
				if (seviceadress.getStatus() == 1)
				{
					SeviceObject data = seviceadress.getData();
					serviceList = data.getServiceList();
					if (serviceList != null && serviceList.size() > 0)
					{
						myHandler.sendEmptyMessage(0);
					}
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
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.tv_seviceback:
			finish();
			break;

		default:
			break;
		}
	}
}
