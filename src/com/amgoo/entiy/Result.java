package com.amgoo.entiy;

public class Result
{
	ResultObject data;
	String info;
	int status;

	public ResultObject getData()
	{
		return data;
	}

	public void setData(ResultObject data)
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
		return "AddFeeback [data=" + data + ", info=" + info + ", status=" + status + "]";
	}

}
