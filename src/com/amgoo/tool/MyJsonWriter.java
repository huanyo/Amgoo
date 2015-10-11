package com.amgoo.tool;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import com.amgoo.entiy.BanerArray;

public class MyJsonWriter
{
	ArrayList<BanerArray> bannerarray;
	File saveFile;

	public MyJsonWriter(ArrayList<BanerArray> bannerarray)
	{
		this.bannerarray = bannerarray;
	}

	public void setFilePath(String filepath)
	{
		saveFile = new File(filepath);
		try
		{
			saveFile.createNewFile();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public String getJsonData()
	{
		String jsonData = null;
		try
		{
			StringBuilder builder = new StringBuilder();
			ArrayList<String> folksData = new ArrayList<String>();
			JSONArray array = new JSONArray();
			for (int i = 0; i < bannerarray.size(); i++)
			{
				BanerArray banerArray = bannerarray.get(i);
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("img", banerArray.getImg());
				folksData.add(jsonObject.toString());
				array.put(jsonObject);
			}
			int len = array.length();
			jsonData = new JSONStringer().object().key("banner").value(array).endObject().toString();
			System.out.println(jsonData);
			writeData(jsonData);
		} catch (JSONException e)
		{
			e.printStackTrace();
		}
		return jsonData;
	}

	private void writeData(String jsonData)
	{
		try
		{
			BufferedReader reader = new BufferedReader(new StringReader(jsonData));
			BufferedWriter writer = new BufferedWriter(new FileWriter(saveFile));
			int len = 0;
			char[] buffer = new char[1024];
			while ((len = reader.read(buffer)) != -1)
			{
				writer.write(buffer, 0, len);
			}
			writer.flush();
			writer.close();
			reader.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

}
