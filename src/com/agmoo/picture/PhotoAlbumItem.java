package com.agmoo.picture;

import java.util.List;

/**
 * 图片目录的相册对像
 * 
 * @author Administrator
 * 
 */
public class PhotoAlbumItem {
	public int count = 0;
	public String bucketName;
	public List<PhotoItem> imageList;
	@Override
	public String toString() {
		return "PhotoAlbumItem [count=" + count + ", bucketName="
				+ bucketName + ", imageList=" + imageList + "]";
	}
	
	

}
