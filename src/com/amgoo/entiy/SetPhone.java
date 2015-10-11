package com.amgoo.entiy;

public class SetPhone
{

	String data;
	String info;
	int status;

	public String getData()
	{
		return data;
	}

	public void setData(String data)
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
		return "SetPhone [data=" + data + ", info=" + info + ", status=" + status + "]";
	}

}
