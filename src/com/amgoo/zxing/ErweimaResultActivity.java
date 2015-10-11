package com.amgoo.zxing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.amgoo.activity.R;

public class ErweimaResultActivity extends Activity implements OnClickListener
{
    private String mSaomiaoResult;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_erweima_result);
        initData();
        initilizeTopBar();
        initView();
    }

    private void initData()
    {
        Intent intent = getIntent();
        mSaomiaoResult = intent.getStringExtra("result");
    }

    private void initView()
    {
        TextView erweima_result = (TextView) findViewById(R.id.erweima_result);
        erweima_result.setText(mSaomiaoResult);
        Button continueVisit = (Button) findViewById(R.id.continueVisit);
        continueVisit.setOnClickListener(this);
    }

    private void initilizeTopBar()
    {
        View mTopBarBack = findViewById(R.id.mTopBarBack);
        mTopBarBack.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent Intent = new Intent(ErweimaResultActivity.this, CaptureActivity.class);
                startActivity(Intent);
                finish();
            }
        });
        TextView mTopBarText = (TextView) findViewById(R.id.mTopBarText);
        mTopBarText.setText("扫描结果");
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.continueVisit:
                finish();
                toIe();
                break;
            default:
                break;
        }
    }

    private void toIe()
    {
        Intent intent = new Intent(this, SaoMiaoResultActivity.class);
        intent.putExtra("mSaomiaoResult", mSaomiaoResult);
        startActivity(intent);
    }
}
