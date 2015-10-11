package com.agmoo.picture;

import android.text.TextUtils;

public class UserInfo extends BaseResponse
{

	private static final long serialVersionUID = -4743963769554533832L;

	private String regionCode = "";
	private String friendSwitch = "";
	private String positionSwitch = "";
	private String emergencyPhone = "";
	private String userId = "";
	private String carId = "";
	private String name = "";
	private String phone = "";
	private String gender = "";
	private String type = "";
	private String logoUrl = "";
	private String logoName = "";
	private String sex = "";
	private boolean updatePwd;
	private String obdId;
	private boolean isDemo;// 是否是体验号

	public String getObdId()
	{
		return obdId;
	}

	public void setObdId(String obdId)
	{
		this.obdId = obdId;
	}

	public boolean isDemo()
	{
		return isDemo;
	}

	public void setDemo(boolean isDemo)
	{
		this.isDemo = isDemo;
	}


	public String getPositionSwitch()
	{

		return positionSwitch;
	}

	public void setPositionSwitch(String positionSwitch)
	{
		this.positionSwitch = positionSwitch;
	}

	public String getEmergencyPhone()
	{
		return emergencyPhone;
	}

	public void setEmergencyPhone(String emergencyPhone)
	{
		this.emergencyPhone = emergencyPhone;
	}


	private boolean registfirst;

	public String getUserId()
	{
		return userId;
	}
	public String getLogoUrl() 
	{
		
		return logoUrl;
	}
	
	public void setLogoUrl(String logoUrl) 
	{
		this.logoUrl = logoUrl;
		setLogoName(logoUrl);
	}
	public void setLogoName(String logoUrl) 
	{
		if(!TextUtils.isEmpty(logoUrl))
		{
			int chick =1+logoUrl.lastIndexOf("/");
			this.logoName = logoUrl.substring(chick);			
		}
	}
	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	public boolean isRegistfirst()
	{
		return registfirst;
	}

	public void setRegistfirst(boolean registfirst)
	{
		this.registfirst = registfirst;
	}

	public String getSex()
	{
		return sex;
	}

	public void setSex(String sex)
	{
		this.sex = sex;
	}

	public UserInfo()
	{
		super();
	}

	public UserInfo(String result)
	{
		super();
	}

}
