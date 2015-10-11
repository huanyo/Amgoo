package com.amgoo.util;

import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
/**
 * 接口回調
 */
public abstract class HttpGetCallBack extends RequestCallBack<String> {
 @Override
 public abstract void onSuccess(ResponseInfo<String> responseInfo);

 @Override
 public abstract void onFailure(HttpException error, String msg);

 @Override
 public abstract void onStart();

}
