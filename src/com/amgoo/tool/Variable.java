package com.amgoo.tool;

import com.amgoo.entiy.Login;

/**
 * 全局变量手机号码用户id等
 */
// 全局变量
public class Variable
{
	private static Variable Variable;

	private Variable()
	{
	}

	public static Variable getVariable()
	{
		if (Variable == null)
		{
			Variable = new Variable();
		}
		return Variable;
	}

	private Login data;

	public Login getData()
	{
		return data;
	}

	public void setData(Login data)
	{
		this.data = data;
	}

}
