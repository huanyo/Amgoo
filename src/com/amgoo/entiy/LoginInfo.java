package com.amgoo.entiy;

public class LoginInfo
{

	String user_password;
	String user_headsculpture;
	String user_fullname;
	String user_petName;
	String user_whatisup;
	String user_mobile_imei_2;
	String user_mobile_imei_1;
	String user_address;
	String user_timezone_id;
	int user_gender;
	String user_telephone;
	String user_email;
	String user_mobilephone;
	String addtime;
	String Amgoo_ID;
	String user_Id;
	int sale_role;
	int finance_role;

	public int getSale_role()
	{
		return sale_role;
	}

	public void setSale_role(int sale_role)
	{
		this.sale_role = sale_role;
	}

	public int getFinance_role()
	{
		return finance_role;
	}

	public void setFinance_role(int finance_role)
	{
		this.finance_role = finance_role;
	}

	public String getUser_password()
	{
		return user_password;
	}

	public void setUser_password(String user_password)
	{
		this.user_password = user_password;
	}

	public String getUser_headsculpture()
	{
		return user_headsculpture;
	}

	public void setUser_headsculpture(String user_headsculpture)
	{
		this.user_headsculpture = user_headsculpture;
	}

	public String getUser_fullname()
	{
		return user_fullname;
	}

	public void setUser_fullname(String user_fullname)
	{
		this.user_fullname = user_fullname;
	}

	public String getUser_petName()
	{
		return user_petName;
	}

	public void setUser_petName(String user_petName)
	{
		this.user_petName = user_petName;
	}

	public String getUser_whatisup()
	{
		return user_whatisup;
	}

	public void setUser_whatisup(String user_whatisup)
	{
		this.user_whatisup = user_whatisup;
	}

	public String getUser_mobile_imei_2()
	{
		return user_mobile_imei_2;
	}

	public void setUser_mobile_imei_2(String user_mobile_imei_2)
	{
		this.user_mobile_imei_2 = user_mobile_imei_2;
	}

	public String getUser_mobile_imei_1()
	{
		return user_mobile_imei_1;
	}

	public void setUser_mobile_imei_1(String user_mobile_imei_1)
	{
		this.user_mobile_imei_1 = user_mobile_imei_1;
	}

	public String getUser_address()
	{
		return user_address;
	}

	public void setUser_address(String user_address)
	{
		this.user_address = user_address;
	}

	public String getUser_timezone_id()
	{
		return user_timezone_id;
	}

	public void setUser_timezone_id(String user_timezone_id)
	{
		this.user_timezone_id = user_timezone_id;
	}

	public int getUser_gender()
	{
		return user_gender;
	}

	public void setUser_gender(int user_gender)
	{
		this.user_gender = user_gender;
	}

	public String getUser_telephone()
	{
		return user_telephone;
	}

	public void setUser_telephone(String user_telephone)
	{
		this.user_telephone = user_telephone;
	}

	public String getUser_email()
	{
		return user_email;
	}

	public void setUser_email(String user_email)
	{
		this.user_email = user_email;
	}

	public String getUser_mobilephone()
	{
		return user_mobilephone;
	}

	public void setUser_mobilephone(String user_mobilephone)
	{
		this.user_mobilephone = user_mobilephone;
	}

	public String getAddtime()
	{
		return addtime;
	}

	public void setAddtime(String addtime)
	{
		this.addtime = addtime;
	}

	public String getAmgoo_ID()
	{
		return Amgoo_ID;
	}

	public void setAmgoo_ID(String amgoo_ID)
	{
		Amgoo_ID = amgoo_ID;
	}

	public String getUser_Id()
	{
		return user_Id;
	}

	public void setUser_Id(String user_Id)
	{
		this.user_Id = user_Id;
	}

	@Override
	public String toString()
	{
		return "LoginInfo [user_password=" + user_password + ", user_headsculpture=" + user_headsculpture + ", user_fullname=" + user_fullname + ", user_petName=" + user_petName + ", user_whatisup="
				+ user_whatisup + ", user_mobile_imei_2=" + user_mobile_imei_2 + ", user_mobile_imei_1=" + user_mobile_imei_1 + ", user_address=" + user_address + ", user_timezone_id="
				+ user_timezone_id + ", user_gender=" + user_gender + ", user_telephone=" + user_telephone + ", user_email=" + user_email + ", user_mobilephone=" + user_mobilephone + ", addtime="
				+ addtime + ", Amgoo_ID=" + Amgoo_ID + ", user_Id=" + user_Id + ", sale_role=" + sale_role + ", finance_role=" + finance_role + "]";
	}

}
