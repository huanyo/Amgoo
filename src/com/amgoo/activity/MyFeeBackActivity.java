package com.amgoo.activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.amgoo.bigpicture.ImagePagerActivity;
import com.amgoo.bigpicture.NoScrollGridAdapter;
import com.amgoo.bigpicture.NoScrollGridView;
import com.amgoo.entiy.FeeBackResult;
import com.amgoo.entiy.FeeResultData;
import com.amgoo.entiy.FeeResultObject;
import com.amgoo.entiy.FeeResultPage;
import com.amgoo.entiy.Login;
import com.amgoo.tool.Contant;
import com.amgoo.tool.TimeChuo;
import com.amgoo.tool.Variable;
import com.amgoo.tool.XListView;
import com.amgoo.tool.XListView.IXListViewListener;
import com.amgoo.util.DialogUtil;
import com.amgoo.util.HttpGetCallBack;
import com.amgoo.util.HttpQuest;
import com.amgoo.util.JsonParser;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;

public class MyFeeBackActivity extends Activity implements IXListViewListener, OnItemClickListener
{

	private XListView lvMyFeeBack;
	private Dialog mProgressLoading;
	private int PageIndex = 1;
	private int mMaxpage;
	private Handler mHandler;
	private String amgoo_ID;
	private String user_password;
	private Myadapter adapter;
	ArrayList<FeeResultObject> feebacklist = new ArrayList<FeeResultObject>();
	Handler myHandler = new Handler()
	{
		public void handleMessage(Message msg)
		{
			if (msg.what == 0)
			{
				adapter.notifyDataSetChanged();
			} else
			{
				lvMyFeeBack.NoData();
				adapter.notifyDataSetChanged();
				Toast.makeText(MyFeeBackActivity.this, getResources().getText(R.string.nomoredata), Toast.LENGTH_SHORT).show();

			}
			super.handleMessage(msg);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_fee_back);
		Login data = Variable.getVariable().getData();
		amgoo_ID = data.getData().getUser().getAmgoo_ID();
		user_password = data.getData().getUser().getUser_password();
		lvMyFeeBack = (XListView) findViewById(R.id.lv_myfeeback);
		adapter = new Myadapter();
		lvMyFeeBack.setAdapter(adapter);
		lvMyFeeBack.setPullLoadEnable(true);
		lvMyFeeBack.setXListViewListener(this);
		mHandler = new Handler();
		getFeeBack(amgoo_ID, user_password, PageIndex);
		lvMyFeeBack.setOnItemClickListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.my_fee_back, menu);
		return true;
	}

	class Myadapter extends BaseAdapter
	{

		@Override
		public int getCount()
		{
			if (feebacklist == null)
			{
				return 0;
			}
			return feebacklist.size();
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
			ViewHolder holder;
			if (convertView == null)
			{
				holder = new ViewHolder();
				int type = feebacklist.get(position).getType();
				if (type == 1)
				{
					convertView = View.inflate(MyFeeBackActivity.this, R.layout.userask_item, null);
					holder.tvFeeAmgooid = (TextView) convertView.findViewById(R.id.tv_feeamgooid);
					holder.tvfeeContent = (EditText) convertView.findViewById(R.id.tv_feecontent);
					holder.tvFeeTime = (TextView) convertView.findViewById(R.id.tv_feetime);
					holder.gridFeePicture = (NoScrollGridView) convertView.findViewById(R.id.grid_feepicture);
					holder.tvFeeAmgooid.setText(amgoo_ID);
					String feedback_datetime = feebacklist.get(position).getFeedback_datetime();
					String strTime = TimeChuo.getStrTime(feedback_datetime);
					holder.tvFeeTime.setText(strTime);
				}
				if (type == 2)
				{
					convertView = View.inflate(MyFeeBackActivity.this, R.layout.adminfee_item, null);
					holder.tvAdmin = (TextView) convertView.findViewById(R.id.tv_admin);
					holder.gridFeePicture = (NoScrollGridView) convertView.findViewById(R.id.grid_admin);
					holder.tvFeeTime = (TextView) convertView.findViewById(R.id.tv_admintime);
					holder.tvfeeContent = (EditText) convertView.findViewById(R.id.tv_admincontent);
					holder.tvAdmin.setText("admin");
					String feedback_datetime = feebacklist.get(position).getFeedback_datetime();
					String strTime = TimeChuo.getStrTime(feedback_datetime);
					holder.tvFeeTime.setText(strTime);
				}
				convertView.setTag(holder);
			} else
			{
				holder = (ViewHolder) convertView.getTag();
			}
			final ArrayList<String> imgarr2 = feebacklist.get(position).getImgarr();
			String feedback_description = feebacklist.get(position).getFeedback_description();
			holder.tvfeeContent.setText(feedback_description);
			if (imgarr2 != null)
			{
				holder.gridFeePicture.setAdapter(new NoScrollGridAdapter(MyFeeBackActivity.this, imgarr2));
			}
			// 查看大图
			holder.gridFeePicture.setOnItemClickListener(new OnItemClickListener()
			{
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id)
				{
					imageBrower(position, imgarr2);
				}
			});
			return convertView;
		}
	}

	class ViewHolder
	{
		private TextView tvFeeAmgooid;
		private TextView tvFeeTime;
		private TextView tvAdmin;
		private EditText tvfeeContent;
		private NoScrollGridView gridFeePicture;
	}

	// 打开图片查看器
	protected void imageBrower(int position, ArrayList<String> urls2)
	{
		Intent intent = new Intent(MyFeeBackActivity.this, ImagePagerActivity.class);
		intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_URLS, urls2);
		intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_INDEX, position);
		MyFeeBackActivity.this.startActivity(intent);
	}

	private void getFeeBack(String amgoo_ID, String user_password, int PageIndex)
	{
		ArrayList<BasicNameValuePair> paramsList = new ArrayList<BasicNameValuePair>();
		paramsList.add(new BasicNameValuePair("Amgoo_ID", amgoo_ID));
		paramsList.add(new BasicNameValuePair("user_password", user_password));
		paramsList.add(new BasicNameValuePair("P", PageIndex + ""));
		HttpQuest.Get(Contant.ServerDataUrl.FEEFACKASK, paramsList, new HttpGetCallBack()
		{
			@Override
			public void onStart()
			{
			}

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo)
			{

				stopProgress();
				FeeBackResult feebackresult = JsonParser.deserializeByJson(responseInfo.result, FeeBackResult.class);
				FeeResultData data = feebackresult.getData();
				FeeResultPage page = data.getPage();
				int current_page = page.getCurrent_page();
				mMaxpage = current_page + 1;
				ArrayList<FeeResultObject> feedback = data.getFeedback();
				if (feebackresult.getStatus() == 1)
				{
					if (feedback != null && feedback.size() > 0)
					{
						feebacklist.addAll(feedback);
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
	public void onRefresh()
	{

		mHandler.postDelayed(new Runnable()
		{
			@Override
			public void run()
			{
				feebacklist.clear();
				PageIndex = 1;
				getFeeBack(amgoo_ID, user_password, 1);
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
					getFeeBack(amgoo_ID, user_password, PageIndex);
					myHandler.sendEmptyMessage(0);
					onLoad();
				} else
				{
					myHandler.sendEmptyMessage(1);
				}
			}
		}, 2000);

	}

	private void onLoad()
	{
		lvMyFeeBack.stopRefresh();
		lvMyFeeBack.stopLoadMore();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日   HH:mm:ss");
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		String str = formatter.format(curDate);
		lvMyFeeBack.setRefreshTime(str);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id)
	{
		int feed_id = feebacklist.get(position - 1).getFeed_id();
		Intent intent = new Intent(MyFeeBackActivity.this, FeeAskListActivity.class);
		intent.putExtra("feed_id", feed_id);
		startActivity(intent);

	}

}
