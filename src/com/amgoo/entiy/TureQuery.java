package com.amgoo.entiy;

import java.util.ArrayList;

public class TureQuery
{

	ArrayList<TureQueryObject> data = new ArrayList<TureQueryObject>();
	String info;
	int status;


	public ArrayList<TureQueryObject> getData()
	{
		return data;
	}

	public void setData(ArrayList<TureQueryObject> data)
	{
		this.data = data;
	}

	public String getInfo()
	{
		return info;
	}

	public void setInfo(String info)
	{
		this.info = info;
	}

	public int getStatus()
	{
		return status;
	}

	public void setStatus(int status)
	{
		this.status = status;
	}

	@Override
	public String toString()
	{
		return "TureQuery [data=" + data + ", info=" + info + ", status=" + status + "]";
	}
}
