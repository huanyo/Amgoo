package com.amgoo.entiy;

import java.util.ArrayList;

public class DownloadArry
{
	ArrayList<DownloadObject> download = new ArrayList<DownloadObject>();
	DownloadPage page;
	
	
	public DownloadPage getPage()
	{
		return page;
	}

	public void setPage(DownloadPage page)
	{
		this.page = page;
	}

	public ArrayList<DownloadObject> getDownload()
	{
		return download;
	}

	public void setDownload(ArrayList<DownloadObject> download)
	{
		this.download = download;
	}

	@Override
	public String toString()
	{
		return "DownloadArry [download=" + download + ", page=" + page + "]";
	}


}
