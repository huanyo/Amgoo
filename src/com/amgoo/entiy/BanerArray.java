package com.amgoo.entiy;

public class BanerArray
{
	String name;
	String content;
	String img;
	int id;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public String getImg()
	{
		return img;
	}

	public void setImg(String img)
	{
		this.img = img;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	@Override
	public String toString()
	{
		return "BanerArray [name=" + name + ", content=" + content + ", img=" + img + ", id=" + id + "]";
	}

}
