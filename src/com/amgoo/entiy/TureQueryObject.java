package com.amgoo.entiy;

public class TureQueryObject
{
	int id;
	String imei_remark;
	String goods_boxinfo_id;
	String goods_id;
	String imei_2;
	String sale_user;
	String sale_time;
	String sale;
	String imei_1;

	
	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getImei_remark()
	{
		return imei_remark;
	}

	public void setImei_remark(String imei_remark)
	{
		this.imei_remark = imei_remark;
	}

	public String getGoods_boxinfo_id()
	{
		return goods_boxinfo_id;
	}

	public void setGoods_boxinfo_id(String goods_boxinfo_id)
	{
		this.goods_boxinfo_id = goods_boxinfo_id;
	}

	public String getGoods_id()
	{
		return goods_id;
	}

	public void setGoods_id(String goods_id)
	{
		this.goods_id = goods_id;
	}

	public String getImei_2()
	{
		return imei_2;
	}

	public void setImei_2(String imei_2)
	{
		this.imei_2 = imei_2;
	}

	public String getSale_user()
	{
		return sale_user;
	}

	public void setSale_user(String sale_user)
	{
		this.sale_user = sale_user;
	}

	public String getSale_time()
	{
		return sale_time;
	}

	public void setSale_time(String sale_time)
	{
		this.sale_time = sale_time;
	}

	public String getSale()
	{
		return sale;
	}

	public void setSale(String sale)
	{
		this.sale = sale;
	}

	public String getImei_1()
	{
		return imei_1;
	}

	public void setImei_1(String imei_1)
	{
		this.imei_1 = imei_1;
	}

	@Override
	public String toString()
	{
		return "TureQueryObject [id=" + id + ", imei_remark=" + imei_remark + ", goods_boxinfo_id=" + goods_boxinfo_id + ", goods_id=" + goods_id + ", imei_2=" + imei_2 + ", sale_user=" + sale_user
				+ ", sale_time=" + sale_time + ", sale=" + sale + ", imei_1=" + imei_1 + "]";
	}

}
