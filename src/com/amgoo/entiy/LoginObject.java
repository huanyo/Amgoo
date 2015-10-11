package com.amgoo.entiy;

public class LoginObject
{

	LoginInfo user;

	public LoginInfo getUser()
	{
		return user;
	}

	public void setUser(LoginInfo user)
	{
		this.user = user;
	}

	@Override
	public String toString()
	{
		return "LoginObject [user=" + user + "]";
	}

}
