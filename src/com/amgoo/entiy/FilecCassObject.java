package com.amgoo.entiy;

public class FilecCassObject
{

	String addtime;
	String name;
	int id;

	public String getAddtime()
	{
		return addtime;
	}

	public void setAddtime(String addtime)
	{
		this.addtime = addtime;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	@Override
	public String toString()
	{
		return "FilecCassObject [addtime=" + addtime + ", name=" + name + ", id=" + id + "]";
	}

}
