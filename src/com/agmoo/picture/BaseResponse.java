package com.agmoo.picture;

import java.io.Serializable;

import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;

/**
 * @describe 对象基类
 */
public class BaseResponse implements Serializable
{
	private static final long serialVersionUID = -6986084624736761508L;
	public static final String COLUMN_RESP_CODE = "respCode";
	public static final String COLUMN_RESP_DESC = "respDesc";
	public static final String SUCCESS = "0";
	protected String respCode;
	protected String respDesc;
	public static final String EMPTY_SYSM = "";

	public BaseResponse parse(Object result)
	{
		BaseResponse response = new BaseResponse();
		JSONObject jObj = null;
		if (result instanceof String)
		{
			jObj = (JSONObject) JSONValue.parse((String) result);
		} else
		{
			jObj = (JSONObject) result;
		}
		basicParse(jObj);
		return response;
	}

	protected void basicParse(JSONObject jObj)
	{
		this.setRespCode(getStringValue(COLUMN_RESP_CODE, jObj));
		this.setRespDesc(getStringValue(COLUMN_RESP_DESC, jObj));

	}

	public String getStringValue(String name, JSONObject jObj)
	{
		if (jObj.containsKey(name))
		{
			Object val = jObj.get(name);
			if (null != val)
				return val.toString();
		}
		return EMPTY_SYSM;
	}

	public boolean isSuccess()
	{
		return BaseResponse.SUCCESS.equals(respCode);
	}

	public String getRespCode()
	{
		return respCode;
	}

	public void setRespCode(String respCode)
	{
		this.respCode = respCode;
	}

	public String getRespDesc()
	{
		return respDesc;
	}

	public void setRespDesc(String respDesc)
	{
		this.respDesc = respDesc;
	}
}
