package com.amgoo.loadimg;

import java.io.UnsupportedEncodingException;

import org.apache.http.protocol.HTTP;

import android.widget.ImageView;

import com.amgoo.activity.R;
import com.android.volley.NetworkResponse;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
/**
 * 公共类从服务器现在json文件 、和下载图片
 * caowei
 * 2015-8-13
 */


public class VolleyUtil
{
	private static ImageLoader mImageLoader;

	public static void httpGet(String strUrl, Listener<String> listener)
	{
		// 从服务器下载数据
		RequestQueue queue = MyVolley.getRequestQueue();
		StringRequest request = new MyStringRequest(Method.GET, strUrl, listener, null);
		queue.add(request);
	}

//	public static void LoadImage(String ImgName, ImageView imgView)
//	{
//		ImageLoader imageLoader2 = MyVolley.getImageLoader();
//		imageLoader2.get(urladress + ImgName, ImageLoader.getImageListener(imgView, R.drawable.empty_photo, android.R.drawable.ic_delete));
//
//	}

	public static void LoadImageBanner(String ImgName, ImageView imgView)
	{
		ImageLoader imageLoader2 = MyVolley.getImageLoader();
		imageLoader2.get(ImgName, ImageLoader.getImageListener(imgView, R.drawable.empty_photo, android.R.drawable.ic_delete));

	}

	private VolleyUtil()
	{
	}

	public static class MyStringRequest extends StringRequest
	{
		@Override
		protected Response<String> parseNetworkResponse(NetworkResponse response)
		{
			String string = null;
			try
			{
				string = new String(response.data, HTTP.UTF_8);
				// new String(response.data, "utf-8");
			} catch (UnsupportedEncodingException e)
			{
				e.printStackTrace();
			}
			return Response.success(string, HttpHeaderParser.parseCacheHeaders(response));
		}

		public MyStringRequest(int method, String url, Listener<String> listener, ErrorListener errorListener)
		{
			super(method, url, listener, errorListener);
			// TODO Auto-generated constructor stub
		}

	}
}
