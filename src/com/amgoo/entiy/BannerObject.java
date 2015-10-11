package com.amgoo.entiy;

import java.util.ArrayList;

public class BannerObject
{
	ArrayList<BanerArray> carouseList = new ArrayList<BanerArray>();

	public ArrayList<BanerArray> getCarouseList()
	{
		return carouseList;
	}

	public void setCarouseList(ArrayList<BanerArray> carouseList)
	{
		this.carouseList = carouseList;
	}

	@Override
	public String toString()
	{
		return "BannerObject [carouseList=" + carouseList + "]";
	}

}
