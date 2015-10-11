package com.amgoo.zxing;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;

public abstract class BaseActivity extends Activity {
    private Dialog mProgressLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (FanhuanApplication.mAppState == -1) {
        }
        else {
            setContentView();
            initializeData();
            initializeViews();
        }
    }

    protected void setContentView(int layoutResID, boolean isShowTitle) {
        setContentView(layoutResID);
    }

    protected abstract void setContentView();

    protected abstract void initializeData();

    protected abstract void initializeViews();


    /**
     * the method to start the progressbar when load data
     */
//    protected void startProgress()
//    {
//        if (mProgressLoading == null)
//        {
//            mProgressLoading = DialogUtil.createProgressDialog(this, getString(R.string.loading), true);
//        }
//        if (!isFinishing()) {
//            mProgressLoading.show();
//        }
//    }

    /**
     * the method to stop the progressbar when loaded data
     */
    protected void stopProgress()
    {
        if (mProgressLoading != null)
        {
            mProgressLoading.dismiss();
            mProgressLoading = null;
        }
    }

    @Override
    public void onBackPressed() {
        stopProgress();
        super.onBackPressed();
    }
}
