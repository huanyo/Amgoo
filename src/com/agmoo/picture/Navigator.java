package com.agmoo.picture;

import java.util.List;

import android.content.Intent;

public class Navigator
{

	public static void goToPictureMoreAct(List<PhotoItem> list, int resultCode)
	{
		startActivityForResult(PhotoMoreAct.createIntent(list), resultCode);
	}

	public static void startActivityForResult(Intent intent, int requestCode)
	{
		App.startActivityForResult(intent, requestCode);
	}
}
