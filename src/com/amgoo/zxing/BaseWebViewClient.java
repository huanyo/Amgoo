package com.amgoo.zxing;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Message;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class BaseWebViewClient extends WebViewClient
{
    private static int API = android.os.Build.VERSION.SDK_INT;
    private Activity mActivity;

    public BaseWebViewClient(Activity activity) {
        mActivity = activity;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url)
    {
        if (url.contains("tmall://")) {
            return true;
        }
        return super.shouldOverrideUrlLoading(view, url);
    }

	@Override
	public void onPageStarted(WebView view, String url, Bitmap favicon)
	{
		super.onPageStarted(view, url, favicon);
		// 3.0以下系统要再这里做登录跳转判断
		if (API < 11 && url.contains("fanhuan/login"))
		{
			view.stopLoading();
			ActivityUtil.SwitchToLoginActivity(mActivity);
		}
	}

    @Override
    public void onReceivedSslError(WebView view,
            final SslErrorHandler handler, SslError error) {
        if (error.getPrimaryError() == SslError.SSL_UNTRUSTED) {}
        else {
            handler.proceed();
        }
    }

    @Override
    public void onFormResubmission(WebView view, final Message dontResend,
            final Message resend) {}

    @Override
    public void onPageFinished(WebView view, String url)
    {
        super.onPageFinished(view, url);
        if (!view.getSettings().getLoadsImagesAutomatically()) {
            view.getSettings().setLoadsImagesAutomatically(true);
        }
    }
}
