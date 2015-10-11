package com.amgoo.util;

import java.util.Collection;

 
/**
 * 判断为空工具类
 * @author hurry
 * @version create time： 2015-4-12 8:11:45 AM
 */
public class TextUtil
{
	public static boolean isValidate(String content)
	{
		return content != null && !"".equals(content.trim());
	}

	public static boolean isValidate(Collection<?> list)
	{
		return list != null && list.size() > 0;
	}

	 
}
