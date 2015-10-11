package com.amgoo.activity;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class FeedBackAskDetailActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_feed_back_ask_detail);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.feed_back_ask_detail, menu);
		return true;
	}

}
