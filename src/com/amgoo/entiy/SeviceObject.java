package com.amgoo.entiy;

import java.util.ArrayList;

public class SeviceObject
{
	ArrayList<SeviceArray> serviceList = new ArrayList<SeviceArray>();

	public ArrayList<SeviceArray> getServiceList()
	{
		return serviceList;
	}

	public void setServiceList(ArrayList<SeviceArray> serviceList)
	{
		this.serviceList = serviceList;
	}

	@Override
	public String toString()
	{
		return "SeviceObject [serviceList=" + serviceList + "]";
	}

	
}
