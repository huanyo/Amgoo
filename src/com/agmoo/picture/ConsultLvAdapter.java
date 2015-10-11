package com.agmoo.picture;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.agmoo.picture.QuestInfo.QuestAnswer;
import com.agmoo.picture.QuestInfo.Question;
import com.amgoo.activity.R;

public class ConsultLvAdapter extends BaseAdapter
{
	private Context context;
	private QuestInfo mQuestInfo;
	private UserInfo userInfo;

	public ConsultLvAdapter(Context context, QuestInfo mQuestInfo)// , UserInfo
																	// userInfo)
	{
		this.context = context;
		this.mQuestInfo = mQuestInfo;
		this.userInfo = userInfo;
	}

	public void refresh(QuestInfo mQuestInfo)
	{
		this.mQuestInfo = mQuestInfo;
		this.notifyDataSetChanged();
	}

	@Override
	public int getCount()
	{
		return mQuestInfo == null ? 0 : mQuestInfo.getResults().size();
	}

	@Override
	public Object getItem(int position)
	{
		return null;
	}

	@Override
	public long getItemId(int position)
	{
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		convertView = LayoutInflater.from(context).inflate(R.layout.lv_consult_list, null);
		TextView tvCreateTime = (TextView) convertView.findViewById(R.id.tv_create_time);
		TextView tvAccept = (TextView) convertView.findViewById(R.id.tv_accept);
		ImageView imgUserIcon = (ImageView) convertView.findViewById(R.id.img_user_icon); // 已采纳人的头�?
		TextView tvQuestDescription = (TextView) convertView.findViewById(R.id.tv_quest_description);
		TextView tvAnswerAccount = (TextView) convertView.findViewById(R.id.tv_answer_account);
		LinearLayout llAnswerIcon = (LinearLayout) convertView.findViewById(R.id.ll_answer_icon);

		Question quesion = mQuestInfo.getResults().get(position);
		tvQuestDescription.setText(quesion.getDescription());
		tvAnswerAccount.setText(quesion.getAnswerCount() + context.getString(R.string.answer));
		tvCreateTime.setText(quesion.getUpdateDate());
		// 如果有答案被采纳，要显示的内�?
		if (quesion.isResolved()) // 是否已解�?
		{
			imgUserIcon.setVisibility(View.VISIBLE);
			tvAccept.setBackgroundResource(R.drawable.quest_agree_bg);
			tvAccept.setText(context.getString(R.string.solve));
			tvAccept.setTextColor(context.getResources().getColor(R.color.light_blue));
			// 获取被采纳�?的头�?
			ArrayList<QuestAnswer> answer = quesion.getAnswers();
			for (QuestAnswer questAnswer : answer)
			{
				if (questAnswer.getAccept().equals("Y"))
				{
					ImageUtil.getImage(userInfo.getLogoUrl(), imgUserIcon, 100, R.drawable.ico_default_logo);
				}
			}
		} else
		{
			imgUserIcon.setVisibility(View.GONE);
		}

		// 动太添加回答问题者的头像
		if (quesion.getAnswers() != null && quesion.getAnswers().size() > 0)
		{
			for (int i = 0; i < quesion.getAnswers().size(); i++)
			{
				ImageView imageView = new ImageView(context);
				imageView.setLayoutParams(new LinearLayout.LayoutParams(50, 50)); // 适配�?
				llAnswerIcon.addView(imageView);
				ImageUtil.getImage(quesion.getAnswers().get(i).getUser().getLogoPath(), imageView, R.drawable.ico_default_logo);

			}
		}

		return convertView;
	}

}
