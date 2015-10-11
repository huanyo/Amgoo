package com.amgoo.entiy;

public class DownloadPage
{
	int current_page;
	int page_size;
	int total_pages;
	int total_rows;

	public int getCurrent_page()
	{
		return current_page;
	}

	public void setCurrent_page(int current_page)
	{
		this.current_page = current_page;
	}

	public int getPage_size()
	{
		return page_size;
	}

	public void setPage_size(int page_size)
	{
		this.page_size = page_size;
	}

	public int getTotal_pages()
	{
		return total_pages;
	}

	public void setTotal_pages(int total_pages)
	{
		this.total_pages = total_pages;
	}

	public int getTotal_rows()
	{
		return total_rows;
	}

	public void setTotal_rows(int total_rows)
	{
		this.total_rows = total_rows;
	}

	@Override
	public String toString()
	{
		return "DownloadPage [current_page=" + current_page + ", page_size=" + page_size + ", total_pages=" + total_pages + ", total_rows=" + total_rows + "]";
	}

}
