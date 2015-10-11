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

import com.amgoo.bigpicture.NoScrollGridView;
import com.amgoo.entiy.FeeAskDatial;
import com.amgoo.entiy.FeeAskDetailData;
import com.amgoo.entiy.FeeAskDetailObject;
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

public class FeeAskListActivity extends Activity implements IXListViewListener,OnItemClickListener
{
	private Dialog mProgressLoading;
	private int PageIndex = 1;
	private int mMaxpage;
	private Handler mHandler;
	private XListView lvFeeAskDetail;
	private int feedid;
	private AskDetailAdapter dapter;
	ArrayList<FeeAskDetailObject> feeaskdetailist = new ArrayList<FeeAskDetailObject>();
	Handler myHandler = new Handler()
	{
		public void handleMessage(Message msg)
		{
			if (msg.what == 0)
			{
				dapter.notifyDataSetChanged();
			} else
			{
				lvFeeAskDetail.NoData();
				dapter.notifyDataSetChanged();
				Toast.makeText(FeeAskListActivity.this, getResources().getText(R.string.nomoredata), Toast.LENGTH_SHORT).show();

			}
			super.handleMessage(msg);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fee_ask_listl);
		lvFeeAskDetail = (XListView) findViewById(R.id.lv_feeaskdetail);
		dapter = new AskDetailAdapter();
		lvFeeAskDetail.setAdapter(dapter);
		feedid = getIntent().getIntExtra("feed_id", 0);
		lvFeeAskDetail.setPullLoadEnable(true);
		lvFeeAskDetail.setXListViewListener(this);
		lvFeeAskDetail.setOnItemClickListener(this);
		mHandler = new Handler();
		getAskDetail(feedid, PageIndex);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.fee_ask_detail, menu);
		return true;
	}

	private void getAskDetail(int feedid, int PageIndex)
	{
		Login data = Variable.getVariable().getData();
		String amgoo_ID = data.getData().getUser().getAmgoo_ID();
		String user_password = data.getData().getUser().getUser_password();
		ArrayList<BasicNameValuePair> paramsList = new ArrayList<BasicNameValuePair>();
		paramsList.add(new BasicNameValuePair("Amgoo_ID", amgoo_ID));
		paramsList.add(new BasicNameValuePair("user_password", user_password));
		paramsList.add(new BasicNameValuePair("feedback_id", 10 + ""));
		paramsList.add(new BasicNameValuePair("p", PageIndex + ""));
		HttpQuest.Get(Contant.ServerDataUrl.FEEFACKASKDETAIL, paramsList, new HttpGetCallBack()
		{
			@Override
			public void onStart()
			{

			}

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo)
			{
				stopProgress();
				FeeAskDatial feeaskdetail = JsonParser.deserializeByJson(responseInfo.result, FeeAskDatial.class);
				FeeAskDetailData data2 = feeaskdetail.getData();
				FeeResultPage page = data2.getPage();
				int current_page = page.getCurrent_page();
				mMaxpage = current_page + 1;
				ArrayList<FeeAskDetailObject> feedback = data2.getFeedback();
				if (feeaskdetail.getStatus() == 1)
				{
					if (feedback != null && feedback.size() > 0)
					{

						feeaskdetailist.addAll(feedback);
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

	class AskDetailAdapter extends BaseAdapter
	{

		@Override
		public int getCount()
		{
			if (feeaskdetailist == null)
			{
				return 0;
			}
			return feeaskdetailist.size();
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
			View inflate = getLayoutInflater().inflate(R.layout.feeaskdetail_item, null);

			TextView tvFeeAskAmgooid = (TextView) inflate.findViewById(R.id.tv_feeaskamgooid);
			EditText tvFeeAskContent = (EditText) inflate.findViewById(R.id.tv_feeaskcontent);
			TextView tvFeetAskime = (TextView) inflate.findViewById(R.id.tv_feetaskime);
			NoScrollGridView gridFeeAskPicture = (NoScrollGridView) inflate.findViewById(R.id.grid_feeaskpicture);

			String feedback_datetime = feeaskdetailist.get(position).getFeedback_datetime();
			String feedback_description = feeaskdetailist.get(position).getFeedback_description();
			Login data = Variable.getVariable().getData();
			String amgoo_ID = data.getData().getUser().getAmgoo_ID();

			tvFeeAskAmgooid.setText(amgoo_ID);
			tvFeeAskContent.setText(feedback_description);
			String strTime = TimeChuo.getStrTime(feedback_datetime);
			tvFeetAskime.setText(strTime);
			return inflate;
		}

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
				feeaskdetailist.clear();
				PageIndex = 1;
				getAskDetail(feedid, 1);
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
					getAskDetail(feedid, PageIndex);
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
		lvFeeAskDetail.stopRefresh();
		lvFeeAskDetail.stopLoadMore();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日   HH:mm:ss");
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		String str = formatter.format(curDate);
		lvFeeAskDetail.setRefreshTime(str);
	}

	@Override
	public void onItemClick(AdapterView<?>  parent, View view, int position, long id) {
		int feed_id = feeaskdetailist.get(position - 1).getFeed_id();
		Intent intent = new Intent(this, FeedBackAskDetailActivity.class);
		intent.putExtra("feed_id", feed_id);
		startActivity(intent);
	}

}
