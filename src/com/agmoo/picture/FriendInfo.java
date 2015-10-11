package com.agmoo.picture;

import java.util.ArrayList;
import java.util.List;

public class FriendInfo extends BaseResponse
{
	private static final long serialVersionUID = 8086772884263426570L;
	private String friendId;
	private String logoUrl;
	private String friendshipId;
	private String friendshipSwitch;
	private List<String> picList = new ArrayList<String>();

	public List<String> getPicList()
	{
		return picList;
	}

	// //TODO 新增功能
	// private String album;
	//
	// public String getAlbum() {
	// return album;
	// }
	// public void setAlbum(String album) {
	// this.album = album;
	// }


	public String getFriendshipSwitch()
	{
		return friendshipSwitch;
	}

	public void setFriendshipSwitch(String friendshipSwitch)
	{
		this.friendshipSwitch = friendshipSwitch;
	}

	public String getFriendshipId()
	{
		return friendshipId;
	}

	public void setFriendshipId(String friendshipId)
	{
		this.friendshipId = friendshipId;
	}



	public String getFriendId()
	{
		return friendId;
	}

	public void setFriendId(String friendId)
	{
		this.friendId = friendId;
	}


	public String getLogoUrl()
	{
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl)
	{
		this.logoUrl = logoUrl;
	}

	public FriendInfo()
	{
		super();
	}

	public FriendInfo(Object result)
	{
		super();
		this.parse(result);
	}


}
