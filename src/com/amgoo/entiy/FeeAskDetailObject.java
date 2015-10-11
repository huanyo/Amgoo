package com.amgoo.entiy;

public class FeeAskDetailObject
{

	int feed_id;
	int type;
	String feedback_img4;
	String feedback_img3;
	String feedback_img2;
	String feedback_img1;
	String feedback_description;
	String feedback_title;
	String feedback_datetime;
	int feedback_type;
	int user_id;
	int feedback_id;

	public int getFeed_id()
	{
		return feed_id;
	}

	public void setFeed_id(int feed_id)
	{
		this.feed_id = feed_id;
	}

	public int getType()
	{
		return type;
	}

	public void setType(int type)
	{
		this.type = type;
	}

	public String getFeedback_img4()
	{
		return feedback_img4;
	}

	public void setFeedback_img4(String feedback_img4)
	{
		this.feedback_img4 = feedback_img4;
	}

	public String getFeedback_img3()
	{
		return feedback_img3;
	}

	public void setFeedback_img3(String feedback_img3)
	{
		this.feedback_img3 = feedback_img3;
	}

	public String getFeedback_img2()
	{
		return feedback_img2;
	}

	public void setFeedback_img2(String feedback_img2)
	{
		this.feedback_img2 = feedback_img2;
	}

	public String getFeedback_img1()
	{
		return feedback_img1;
	}

	public void setFeedback_img1(String feedback_img1)
	{
		this.feedback_img1 = feedback_img1;
	}

	public String getFeedback_description()
	{
		return feedback_description;
	}

	public void setFeedback_description(String feedback_description)
	{
		this.feedback_description = feedback_description;
	}

	public String getFeedback_title()
	{
		return feedback_title;
	}

	public void setFeedback_title(String feedback_title)
	{
		this.feedback_title = feedback_title;
	}

	public String getFeedback_datetime()
	{
		return feedback_datetime;
	}

	public void setFeedback_datetime(String feedback_datetime)
	{
		this.feedback_datetime = feedback_datetime;
	}

	public int getFeedback_type()
	{
		return feedback_type;
	}

	public void setFeedback_type(int feedback_type)
	{
		this.feedback_type = feedback_type;
	}

	public int getUser_id()
	{
		return user_id;
	}

	public void setUser_id(int user_id)
	{
		this.user_id = user_id;
	}

	public int getFeedback_id()
	{
		return feedback_id;
	}

	public void setFeedback_id(int feedback_id)
	{
		this.feedback_id = feedback_id;
	}

	@Override
	public String toString()
	{
		return "FeeAskDetailObject [feed_id=" + feed_id + ", type=" + type + ", feedback_img4=" + feedback_img4 + ", feedback_img3=" + feedback_img3 + ", feedback_img2=" + feedback_img2
				+ ", feedback_img1=" + feedback_img1 + ", feedback_description=" + feedback_description + ", feedback_title=" + feedback_title + ", feedback_datetime=" + feedback_datetime
				+ ", feedback_type=" + feedback_type + ", user_id=" + user_id + ", feedback_id=" + feedback_id + "]";
	}

}
