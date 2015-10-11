package com.amgoo.zxing;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.amgoo.activity.R;

public class SaoMiaoResultActivity extends BaseActivity
{
	private TextView mTopBarText;
	private String url;

	@Override
	protected void setContentView()
	{
		setContentView(R.layout.activity_sao_miao_result);
	}

	@Override
	protected void initializeData()
	{
	}

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void initializeViews()
	{
		initilizeTopBar();
		url = getIntent().getStringExtra("mSaomiaoResult");
		WebView webview = (WebView) findViewById(R.id.erweima_jieguodengluye);
		webview.setHorizontalScrollBarEnabled(false);// 水平不显示
		webview.setVerticalScrollBarEnabled(false); // 垂直不显示
		webview.getSettings().setJavaScriptEnabled(true);
		webview.setWebChromeClient(new MyWebChromeClient());
		webview.setWebViewClient(new BaseWebViewClient(SaoMiaoResultActivity.this));
		webview.setDownloadListener(new WebViewDownLoadListener(this));
		if (TextUtil.isValidate(url))
		{
			webview.loadUrl(url);
		}
	}
            
	private void initilizeTopBar()
	{
		View mTopBarBack = findViewById(R.id.mTopBarBack);
		mTopBarBack.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				finish();
				startActivity(new Intent(SaoMiaoResultActivity.this, CaptureActivity.class));
			}
		});
		mTopBarText = (TextView) findViewById(R.id.mTopBarText);
		mTopBarText.setText("");
	}

	class MyWebChromeClient extends WebChromeClient
	{
		@Override
		public void onReceivedTitle(WebView view, String title)
		{
			super.onReceivedTitle(view, title);
			if (getIntent().getStringExtra("mSaomiaoResult").contains(".fanhuan.com"))
			{
				mTopBarText.setText("AMGOO");
			} else
			{
				mTopBarText.setText(title);
			}
		}

		@Override
		public boolean onJsAlert(WebView view, String url, String message, JsResult result)
		{
			Toast.makeText(SaoMiaoResultActivity.this, message, Toast.LENGTH_SHORT).show(); // 对话框渲染
			return true;
		}
	}
}
