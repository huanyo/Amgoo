package com.amgoo.util;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
/**
 * 接口請求
 */
public class HttpQuest {
	public static void Get(String url,
			ArrayList<BasicNameValuePair> paramsList,
			final HttpGetCallBack httpGetCallBack) {
		RequestParams param = null;
		if (paramsList != null) {

			ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
			params.addAll(paramsList);
			param = new RequestParams();
			param.addQueryStringParameter(params);
		}
		HttpUtils http = new HttpUtils();
		http.configCurrentHttpCacheExpiry(0); // 设置当前接口缓存过期时间
		http.send(HttpRequest.HttpMethod.POST, url, param,
				new RequestCallBack<String>() {

					@Override
					public void onStart() {
						httpGetCallBack.onStart();
					}

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						httpGetCallBack.onFailure(arg0, arg1);
					}

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						httpGetCallBack.onSuccess(responseInfo);
					}
				});
	}
}