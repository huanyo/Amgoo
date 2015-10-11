package com.amgoo.tool;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
 * MD5 
 */
public class MD5
{

	private final static String[] strDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	public MD5()
	{
	}

	private static String byteToArrayString(byte bByte)
	{
		int iRet = bByte;

		if (iRet < 0)
		{
			iRet += 256;
		}
		int iD1 = iRet / 16;
		int iD2 = iRet % 16;
		return strDigits[iD1] + strDigits[iD2];
	}

	private static String byteToNum(byte bByte)
	{
		int iRet = bByte;
		System.out.println("iRet1=" + iRet);
		if (iRet < 0)
		{
			iRet += 256;
		}
		return String.valueOf(iRet);
	}

	private static String byteToString(byte[] bByte)
	{
		StringBuffer sBuffer = new StringBuffer();
		for (int i = 0; i < bByte.length; i++)
		{
			sBuffer.append(byteToArrayString(bByte[i]));
		}
		return sBuffer.toString();
	}

	public static String GetMD5Code(String strObj)
	{
		String resultString = null;
		try
		{
			resultString = new String(strObj);
			MessageDigest md = MessageDigest.getInstance("MD5");

			resultString = byteToString(md.digest(strObj.getBytes()));
		} catch (NoSuchAlgorithmException ex)
		{
			ex.printStackTrace();
		}
		return resultString;
	}

	public String getMD5Str(String str)
	{
		MessageDigest messageDigest = null;

		try
		{
			messageDigest = MessageDigest.getInstance("MD5");

			messageDigest.reset();

			messageDigest.update(str.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e)
		{
			System.out.println("NoSuchAlgorithmException caught!");
			System.exit(-1);
		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}

		byte[] byteArray = messageDigest.digest();

		StringBuffer md5StrBuff = new StringBuffer();

		for (int i = 0; i < byteArray.length; i++)
		{
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
				md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
			else
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
		}
		return md5StrBuff.toString().toLowerCase();
	}
}