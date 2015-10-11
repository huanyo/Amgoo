package com.amgoo.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.amgoo.util.WebViewUtil;

public class AgmooActivity extends Activity
{

	private WebView mWebView;
	private ProgressBar mProgressBar;
	private TextView mtopbarText;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_agmoo);
		mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
		mtopbarText = (TextView) findViewById(R.id.textView1);
		mWebView = (WebView) findViewById(R.id.webView1);
		mWebView = WebViewUtil.configWebViewSetting(this, mWebView); // 基本设置
		mWebView.setWebViewClient(new MyWebViewClient());
		mWebView.setWebChromeClient(new BaseWebChromeClient());
		mWebView.loadUrl("http://www.amgoo.com");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.agmoo, menu);
		return true;
	}

	class MyWebViewClient extends WebViewClient
	{
		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon)
		{
			super.onPageStarted(view, url, favicon);
			mProgressBar.setVisibility(View.VISIBLE);// 进度条显示
		}

		@Override
		public void onPageFinished(WebView view, String url)
		{
			super.onPageFinished(view, url);
			mProgressBar.setVisibility(View.GONE);
			// 解决图片不会下载的问题
			if (!view.getSettings().getLoadsImagesAutomatically())
			{
				view.getSettings().setLoadsImagesAutomatically(true);
			}
		}
	}

	class BaseWebChromeClient extends WebChromeClient
	{
		private int progress = 5;

		@Override
		public void onProgressChanged(WebView view, int newProgress)
		{
			if (mProgressBar != null)
			{
				mProgressBar.setVisibility(View.VISIBLE);
				if (newProgress < 100)
				{
					progress += 3;
					if (progress < 100)
					{
						mProgressBar.setProgress(progress);
					}
				} else
				{
					mProgressBar.setProgress(newProgress);
				}
				if (newProgress == 100)
				{
					mProgressBar.postDelayed(new Runnable()
					{
						@Override
						public void run()
						{
							mProgressBar.setProgress(0);
						}
					}, 1000);
				}
			}
		}

		@Override
		public void onReceivedTitle(WebView view, String title)
		{
			if ((title != null) && !("".equals(title)))
			{
				if (mtopbarText != null) // 设置标题
				{
					mtopbarText.setText("AMGOO官网");
				}
			}
		}
	}

}
