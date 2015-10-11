package com.amgoo.entiy;

import java.util.ArrayList;

public class FeeResultData
{
	FeeResultPage page;
	ArrayList<FeeResultObject> feedback = new ArrayList<FeeResultObject>();

	public FeeResultPage getPage()
	{
		return page;
	}

	public void setPage(FeeResultPage page)
	{
		this.page = page;
	}

	public ArrayList<FeeResultObject> getFeedback()
	{
		return feedback;
	}

	public void setFeedback(ArrayList<FeeResultObject> feedback)
	{
		this.feedback = feedback;
	}

	@Override
	public String toString()
	{
		return "FeeResultData [page=" + page + ", feedback=" + feedback + "]";
	}

}
