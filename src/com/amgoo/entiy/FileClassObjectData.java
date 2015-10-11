package com.amgoo.entiy;

import java.util.ArrayList;

public class FileClassObjectData
{
	ArrayList<FilecCassObject> downtype = new ArrayList<FilecCassObject>();

	public ArrayList<FilecCassObject> getDowntype()
	{
		return downtype;
	}

	public void setDowntype(ArrayList<FilecCassObject> downtype)
	{
		this.downtype = downtype;
	}

	@Override
	public String toString()
	{
		return "FileClassObjectData [downtype=" + downtype + "]";
	}

}
