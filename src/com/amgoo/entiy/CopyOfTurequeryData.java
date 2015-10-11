package com.amgoo.entiy;

public class CopyOfTurequeryData
{
	int status;
	TureQueryObject info;

	public int getStatus()
	{
		return status;
	}

	public void setStatus(int status)
	{
		this.status = status;
	}

	public TureQueryObject getInfo()
	{
		return info;
	}

	public void setInfo(TureQueryObject info)
	{
		this.info = info;
	}

	@Override
	public String toString()
	{
		return "TurequeryData [status=" + status + ", info=" + info + "]";
	}

}
