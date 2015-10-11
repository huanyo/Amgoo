package com.amgoo.entiy;

public class FileClass
{

	FileClassObjectData data;
	String info;
	int status;

	public FileClassObjectData getData()
	{
		return data;
	}

	public void setData(FileClassObjectData data)
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
		return "FileClass [data=" + data + ", info=" + info + ", status=" + status + "]";
	}

}
