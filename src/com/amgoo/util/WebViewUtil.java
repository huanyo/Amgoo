package com.amgoo.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * @author harry
 * @version Creat on 2014-9-1上午10:58:47
 */
public class WebViewUtil {
    /**
     * 配置webview
     */
    private static int API = android.os.Build.VERSION.SDK_INT;

    @SuppressLint("NewApi")
	public static WebView configWebViewSetting(Context context, WebView webview) {
        webview.setHorizontalScrollBarEnabled(false);// 水平不显示
        webview.setVerticalScrollBarEnabled(false); // 垂直不显示
        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);
        // 开启 database storage API 功能
        settings.setDatabaseEnabled(true);
        // 开启 Application Caches 功能
        webview.setDrawingCacheBackgroundColor(0x00000000);
        webview.setFocusableInTouchMode(true);
        webview.setFocusable(true);
        webview.setAnimationCacheEnabled(false);
        webview.setDrawingCacheEnabled(true);
        webview.setBackgroundColor(context.getResources().getColor(
                android.R.color.white));
        webview.setWillNotCacheDrawing(false);
        webview.setAlwaysDrawnWithCacheEnabled(true);
        webview.setScrollbarFadingEnabled(true);
        webview.setSaveEnabled(true);
        if (API < 18) {
            settings.setAppCacheMaxSize(Long.MAX_VALUE);
        }
        if (API < 19) {
            settings.setDatabasePath(context.getFilesDir().getAbsolutePath()
                    + "/databases");
        }
        settings.setDomStorageEnabled(true);
        settings.setAppCachePath(context.getCacheDir().toString());
        settings.setAppCacheEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setGeolocationDatabasePath(context.getCacheDir()
                .getAbsolutePath());
        settings.setAllowFileAccess(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        settings.setAllowContentAccess(true);
        settings.setDefaultTextEncodingName("utf-8");
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        if (API < 18) {
            settings.setSavePassword(true);
        }
        settings.setSaveFormData(true);
        if (API > 16) {
            settings.setAllowFileAccessFromFileURLs(false);
            settings.setAllowUniversalAccessFromFileURLs(false);
        }
        /*
         * 默认情况html代码下载到WebView后，webkit开始解析网页各个节点，
         * 发现有外部样式文件或者外部脚本文件时，会异步发起网络请求下载文件
         * ，但如果在这之前也有解析到image节点，那势必也会发起网络请求下载相应的图片
         * 。在网络情况较差的情况下，过多的网络请求就会造成带宽紧张，影响到css或js文件加载完成的时间
         * ，造成页面空白loading过久。解决的方法就是告诉WebView先不要自动加载图片，等页面finish后再发起图片加载。
         * 在系统API在19以上的版本作了兼容
         * 因为4.4以上系统在onPageFinished时再恢复图片加载时,如果存在多张图片引用的是相同的src时,
         * 会只有一个image标签得到加载，因而对于这样的系统我们就先直接加载。
         */
        if (Build.VERSION.SDK_INT >= 19) {
            settings.setLoadsImagesAutomatically(true);
        }
        else {
            settings.setLoadsImagesAutomatically(false);
        }
        return webview;
    
}}
