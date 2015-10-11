package com.agmoo.picture;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.agmoo.picture.XListView.IXListViewListener;
import com.amgoo.activity.R;

public class ConsultActivity extends AppActivity implements IXListViewListener, OnClickListener
{
	private XListView mListConsult;
	private int mTotalPage = 1;
	private int mCurrentPage = 2;
	private QuestInfo mQuestInfo;
	private ConsultLvAdapter mConsultLvAdapter;
	private TextView mTvNoneData;

	// private TextView mTvQuestTime;
	// private TextView mTvQuestAgain;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_consult);
		initView();
		// getQuestInfo(false);
	}

	private void initView()
	{
		initActionBar();
		initListView();
		RelativeLayout rlStartConsult = (RelativeLayout) findViewById(R.id.rl_start_consult);
		rlStartConsult.setOnClickListener(this);
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		stopLoad();
	}

	private void stopLoad()
	{
		mListConsult.stopLoadMore();
		mListConsult.stopRefresh();
	}

	private void initListView()
	{
		mListConsult = (XListView) findViewById(R.id.list_consult);
		mTvNoneData = (TextView) findViewById(R.id.tv_none_data);
		// View headerView =
		// getLayoutInflater().inflate(R.layout.lv_header_consult, null);
		mListConsult.setXListViewListener(this); // TODO
		mListConsult.setPullLoadEnable(true);
		mConsultLvAdapter = new ConsultLvAdapter(this, mQuestInfo);// ,
																	// getUser());
		mListConsult.setAdapter(mConsultLvAdapter);
		// mListConsult.setOnItemClickListener(this);
	}

	private void initActionBar()
	{

		setBarCenterText(getString(R.string.consult_quest_list));
		setBarLeftOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				backKeyCallBack();
			}
		});
		hideBarRight();
	}

	@Override
	protected void backKeyCallBack()
	{
		super.backKeyCallBack();
		finish();
	}

	@Override
	public void onRefresh() // 下拉刷新
	{
		closeProgress();
		// getQuestInfo(true);
	}

	@Override
	public void onLoadMore() // 上拉加载更多
	{
		if (mCurrentPage - 1 == mTotalPage)
		{
			stopLoad();
			Toast.makeText(ConsultActivity.this,  getResources().getString(R.string.last), Toast.LENGTH_SHORT).show();
			return;
		}
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.rl_start_consult:
			Intent intent = new Intent(this, RoateConsultActivity.class);
			intent.putExtra("consult_title", getResources().getString(R.string.feeback));
			startActivityForResult(intent, 0);

			break;
		// case R.id.tv_quest_again:
		// questAgain();
		// break;
		default:
			break;
		}
	}

	// private void questAgain()
	// {
	// showWaitingProgress();
	// if (!HttpUtils.isNetworkAvailable(this))
	// {
	// closeProgress();
	// return;
	// }
	// NetworkRequest.QuestAgain(mQuestInfo.getResults().get(0).getQuestionID(),
	// new Listener<String>()
	// {
	// @Override
	// public void onResponse(String result)
	// {
	// closeProgress();
	// LOG.e("onScuess:" + result.toString());
	// }
	// }, new ErrorListener()
	// {
	// @Override
	// public void onErrorResponse(VolleyError error)
	// {
	// closeProgress();
	// LOG.d("error_B = " + error);
	// toast(error.toString());
	// }
	// });
	// }

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		// getQuestInfo(false);
	}

	// private void getQuestInfo(boolean isRefresh)
	// {
	// if (!isRefresh)
	// {
	// showWaitingProgress();
	// }
	// if (!HttpUtils.isNetworkAvailable(this))
	// {
	// closeProgress();
	// stopLoad();
	// return;
	// }
	// NetworkRequest.getQuestInfo(getUser().getUserId(), new Listener<String>()
	// {
	//
	// @Override
	// public void onResponse(String result)
	// {
	// closeProgress();
	// stopLoad();
	// LOG.e("onScuess:" + result.toString());
	// mQuestInfo = JsonParser.deserializeByJson(result, QuestInfo.class);
	// if (mQuestInfo.getRespCode() == 0)
	// {
	// if (mQuestInfo.getResults().size() > 0)
	// {
	// mConsultLvAdapter.refresh(mQuestInfo);
	// mListConsult.setVisibility(View.VISIBLE);
	// mTvNoneData.setVisibility(View.GONE);
	// } else
	// {
	// mTvNoneData.setVisibility(View.VISIBLE);
	// mListConsult.setVisibility(View.GONE);
	// }
	// } else
	// {
	// toast(mQuestInfo.getRespDesc());
	// }
	//
	// }
	// }, new ErrorListener()
	// {
	//
	// @Override
	// public void onErrorResponse(VolleyError error)
	// {
	//
	// closeProgress();
	// stopLoad();
	// LOG.d("error_B = " + error);
	// toast(error.toString());
	// }
	// });
	//
	// }

	// @Override
	// public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long
	// arg3)
	// {
	// Intent intent = new Intent(this, ConsultQuestDeatilctivity.class);
	// intent.putExtra("questionId", mQuestInfo.getResults().get(arg2 -
	// 1).getQuestionID()); // 下拉动画
	// intent.putExtra("answerPosition", arg2 - 1); // 下拉动画
	// startActivity(intent);
	// }
}
