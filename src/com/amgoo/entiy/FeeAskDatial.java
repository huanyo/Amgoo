package com.amgoo.entiy;

public class FeeAskDatial
{

	FeeAskDetailData data;
	String info;
	int status;

	public FeeAskDetailData getData()
	{
		return data;
	}

	public void setData(FeeAskDetailData data)
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
		return "FeeAskDatial [data=" + data + ", info=" + info + ", status=" + status + "]";
	}

}
