package com.amgoo.entiy;

public class Banner
{
	BannerObject data;
	String info;
	int status;

	public BannerObject getData()
	{
		return data;
	}

	public void setData(BannerObject data)
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
		return "Banner [data=" + data + ", info=" + info + ", status=" + status + "]";
	}

}
