package com.amgoo.entiy;


public class SeviceAdress
{

	SeviceObject data;
	String info;
	int status;

	public SeviceObject getData()
	{
		return data;
	}

	public void setData(SeviceObject data)
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
		return "SeviceAdress [data=" + data + ", info=" + info + ", status=" + status + "]";
	}

}
