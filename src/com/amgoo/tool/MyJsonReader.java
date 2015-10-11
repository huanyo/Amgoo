package com.amgoo.tool;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.amgoo.entiy.BanerArray;

public class MyJsonReader
{
	String jsonData;

	public MyJsonReader(String jsonData)
	{
		this.jsonData = jsonData;
	}


	public ArrayList<BanerArray> getJsonData()
	{
		ArrayList<BanerArray> bannerread = new ArrayList<BanerArray>();
		try
		{
			JSONObject jsonObject = new JSONObject(jsonData);
			JSONArray jsonArray = jsonObject.getJSONArray("banner");
			int len = jsonArray.length();
			for (int i = 0; i < jsonArray.length(); i++)
			{
				JSONObject json = jsonArray.getJSONObject(i);
				BanerArray banner = new BanerArray();
				banner.setImg(json.optString("img"));
				bannerread.add(banner);
			}
		} catch (JSONException e)
		{
			e.printStackTrace();
		}
		return bannerread;
	}
}
