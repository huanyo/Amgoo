package com.amgoo.zxing;

import android.app.Activity;
import android.content.Intent;

import com.amgoo.activity.MyActivity;
import com.amgoo.activity.ProductActivity;
import com.amgoo.activity.R;
import com.amgoo.activity.SchoolActivity;
import com.amgoo.activity.SeviceActivity;

public class ActivityUtil
{

	private static Intent intent;

	public static void SwitchToLoginActivity(Activity context)
	{
		intent = new Intent(context, ProductActivity.class);
		intent = new Intent(context, SchoolActivity.class);
		intent = new Intent(context, SeviceActivity.class);
		intent = new Intent(context, MyActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		context.startActivityForResult(intent, 0);
		context.overridePendingTransition(R.anim.push_bottom_in, 0);
	}

	// public static void SwitchToSchoolActivity(Activity context)
	// {
	// Intent intent = new Intent(context, SchoolActivity.class);
	// intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	// context.startActivityForResult(intent, 0);
	// context.overridePendingTransition(R.anim.push_bottom_in, 0);
	// }
	//
	// public static void SwitchToSeviceActivity(Activity context)
	// {
	// Intent intent = new Intent(context, SeviceActivity.class);
	// intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	// context.startActivityForResult(intent, 0);
	// context.overridePendingTransition(R.anim.push_bottom_in, 0);
	// }
	//
	// public static void SwitchToMyActivity(Activity context)
	// {
	// Intent intent = new Intent(context, MyActivity.class);
	// intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	// context.startActivityForResult(intent, 0);
	// context.overridePendingTransition(R.anim.push_bottom_in, 0);
	// }

}
