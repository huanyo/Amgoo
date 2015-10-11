package com.agmoo.picture;

import java.io.Serializable;

/**
 * 展示图片对象
 * 
 * @author Administrator
 * 
 */
public class PhotoItem implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1021850701109877866L;
	public String imageId;
	public String thumbnailPath;	
	public String imagePath;
	public boolean selected;
	
	public String getImageId() {
		return imageId;
	}
	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
	public String getThumbnailPath() {
		return thumbnailPath;
	}
	public void setThumbnailPath(String thumbnailPath) {
		this.thumbnailPath = thumbnailPath;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	@Override
	public String toString() {
		return "PhotoItem [imageId=" + imageId + ", thumbnailPath="
				+ thumbnailPath + ", imagePath=" + imagePath + ", selected="
				+ selected + "]";
	}
	
	
	
	
}
