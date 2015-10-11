package com.amgoo.zxing;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.webkit.DownloadListener;

public class WebViewDownLoadListener implements DownloadListener
{
    private Context context;

    public WebViewDownLoadListener(Context context)
    {
        super();
        this.context = context;
    }

    @Override
    public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength)
    {
        if (url.endsWith(".apk")) {
            Uri uri = Uri.parse(url);
            Intent viewIntent = new Intent(Intent.ACTION_VIEW, uri);
            context.startActivity(viewIntent);
        }
    }
}
