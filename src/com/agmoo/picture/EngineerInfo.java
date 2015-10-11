package com.agmoo.picture;

public class EngineerInfo
{
	private String userID;
	private String name;
	private String nickname;
	private String phone;
	private String email;
	private String sex;
	private String logoPath;
	private String type;
	private String createTime;
	private String updateTime;

	public String getUserID()
	{
		return userID;
	}

	public void setUserID(String userID)
	{
		this.userID = userID;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getNickname()
	{
		return nickname;
	}

	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}

	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getSex()
	{
		return sex;
	}

	public void setSex(String sex)
	{
		this.sex = sex;
	}

	public String getLogoPath()
	{
		return logoPath;
	}

	public void setLogoPath(String logoPath)
	{
		this.logoPath = logoPath;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(String createTime)
	{
		this.createTime = createTime;
	}

	public String getUpdateTime()
	{
		return updateTime;
	}

	public void setUpdateTime(String updateTime)
	{
		this.updateTime = updateTime;
	}

	@Override
	public String toString()
	{
		return "EngineerInfo [userID=" + userID + ", name=" + name + ", nickname=" + nickname + ", phone=" + phone + ", email=" + email + ", sex="
				+ sex + ", logoPath=" + logoPath + ", type=" + type + ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}

}
