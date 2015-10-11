package com.amgoo.entiy;

public class Login
{
	LoginObject data;
	String info;
	int status;

	public LoginObject getData()
	{
		return data;
	}

	public void setData(LoginObject data)
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
		return "Login [data=" + data + ", info=" + info + ", status=" + status + "]";
	}

}
