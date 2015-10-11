/*      						
 * Copyright 2010 Beijing Xinwei, Inc. All rights reserved.
 * 
 * History:
 * ------------------------------------------------------------------------------
 * Date    	|  Who  		|  What  
 * 2015��3��30��	| duanbokan 	| 	create the file                       
 */

package com.amgoo.country;

import android.graphics.drawable.Drawable;

/**
 * 
 * 类简要描述
 * 
 * <p>
 * 类详细描述
 * 
 */
public class CountryModel
{
	// ������
	public String countryName;
	
	// ��Ҵ���
	public String countryNumber;
	
	public String simpleCountryNumber;
	
	// ��������д
	public String countrySortKey;
	
	// ���ͼ��
	public Drawable contactPhoto;
	
	public CountryModel(String countryName, String countryNumber, String countrySortKey)
	{
		super();
		this.countryName = countryName;
		this.countryNumber = countryNumber;
		this.countrySortKey = countrySortKey;
		if (countryNumber != null)
		{
			this.simpleCountryNumber = countryNumber.replaceAll("\\-|\\s", "");
		}
	}
	
}
