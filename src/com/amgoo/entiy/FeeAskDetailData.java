package com.amgoo.entiy;

import java.util.ArrayList;

public class FeeAskDetailData
{

	ArrayList<FeeAskDetailObject> feedback = new ArrayList<FeeAskDetailObject>();
	FeeResultPage page;

	public ArrayList<FeeAskDetailObject> getFeedback()
	{
		return feedback;
	}

	public void setFeedback(ArrayList<FeeAskDetailObject> feedback)
	{
		this.feedback = feedback;
	}

	public FeeResultPage getPage()
	{
		return page;
	}

	public void setPage(FeeResultPage page)
	{
		this.page = page;
	}

	@Override
	public String toString()
	{
		return "FeeAskDetailData [feedback=" + feedback + ", page=" + page + "]";
	}

}
