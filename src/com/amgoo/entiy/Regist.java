package com.amgoo.entiy;

import java.util.Arrays;

public class Regist
{
	String[] data;
	String info;
	int status;

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

	public String[] getData()
	{
		return data;
	}

	public void setData(String[] data)
	{
		this.data = data;
	}

	@Override
	public String toString()
	{
		return "Regist [data=" + Arrays.toString(data) + ", info=" + info + ", status=" + status + "]";
	}


}
