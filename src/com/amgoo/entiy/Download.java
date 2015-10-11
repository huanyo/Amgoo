package com.amgoo.entiy;

public class Download
{
	DownloadArry data;
	String info;
	int status;

	public DownloadArry getData()
	{
		return data;
	}

	public void setData(DownloadArry data)
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
		return "Download [data=" + data + ", info=" + info + ", status=" + status + "]";
	}

}
