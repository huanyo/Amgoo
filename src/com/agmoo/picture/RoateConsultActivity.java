package com.agmoo.picture;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer.TrackInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.agmoo.picture.CustomAlbumDialog.OnCustomAlbumListener;
import com.amgoo.activity.R;
import com.amgoo.entiy.Result;
import com.amgoo.tool.Contant;
import com.amgoo.tool.ImageTools;
import com.amgoo.tool.Variable;
import com.amgoo.util.DialogUtil;
import com.amgoo.util.JsonParser;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

public class RoateConsultActivity extends AppActivity
{
	private GridView noScrollgridview;
	private GridImageAdapter adapter;
	private EditText mEd_Content;
	private Dialog mProgressLoading;
	private TitleHeaderBar titleBar;
	private boolean ifContainObdData;
	private static final int RESTART = 1; // 重启的反馈
	private static final int WIFI = 2;// wifi的反馈
	private static final int SYSTEM = 3;// 系统的反馈
	private static final int BATTERY = 4;// 电池的反馈
	private static final int BLUETOOTH = 5;// 蓝牙的反馈
	private static final int UNSYSTEM = 6;// 非系统的反馈
	private static final int SIGNAL = 7;// 信号的反馈
	private static final int CAMERA = 8;// 相机的反馈
	private static final int OTHER = 9;// 其他的反馈
	private CustomAlbumDialog selDialog;
	private String barTitle;
	private int sign;
	private String[] mImageParams;
	private String bitmapToBase64;

	protected void onCreate(Bundle savedInstanceState)
	{
		try
		{
			super.onCreate(savedInstanceState);
			setContentView(R.layout.act_main_picture);
			Intent intent = getIntent();
			sign = intent.getIntExtra("signl", 0);
			titleBar = (TitleHeaderBar) findViewById(R.id.pageHeader);
			setActionBar();
			initView();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		mImageParams = new String[] { "feedback_img1", "feedback_img2", "feedback_img3", "feedback_img4" };
	}

	public static Intent createIntent()
	{
		Intent intent = new Intent(App.getCurrentActivity(), RoateConsultActivity.class);
		return intent;
	}

	public static Intent createIntent(TrackInfo trackInfo)
	{
		Intent intent = new Intent(App.getCurrentActivity(), RoateConsultActivity.class);
		intent.putExtra("share", true);
		intent.putExtra("trackInfo", trackInfo);
		return intent;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if (resultCode == 0)
			return;
		if (requestCode == 1)
		{
			if (PhotoUtils.bitmaps.size() < 4) // 添加照片的个数
			{
				PhotoItem item = new PhotoItem();
				item.setThumbnailPath(selDialog.getPhotoPath());
				item.setImagePath(selDialog.getPhotoPath());
				PhotoUtils.addImageItem(item);
				adapter.notifyDataSetInvalidated();
			} else
			{

			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	private void setActionBar()
	{
		switch (sign)
		{
		case RESTART:
			setBarCenterText(getResources().getString(R.string.restart));
			break;
		case WIFI:
			setBarCenterText(getResources().getString(R.string.wifi));
			break;
		case SYSTEM:
			setBarCenterText(getResources().getString(R.string.sys));
			break;
		case BATTERY:
			setBarCenterText(getResources().getString(R.string.batty));
			break;
		case BLUETOOTH:
			setBarCenterText(getResources().getString(R.string.btooth));
			break;
		case UNSYSTEM:
			setBarCenterText(getResources().getString(R.string.unsys));
			break;
		case SIGNAL:
			setBarCenterText(getResources().getString(R.string.sigl));
			break;
		case CAMERA:
			setBarCenterText(getResources().getString(R.string.camer));
			break;
		case OTHER:
			setBarCenterText(getResources().getString(R.string.other1));
			break;
		default:
			break;
		}

		setBarLeftOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				backKeyCallBack();
			}
		});
		setBarRightOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				uploadRemoteCheck();
				// titleBar.setRightTextClickable(false);
			}

		});
	}

	private void uploadRemoteCheck()
	{
		RequestParams params = new RequestParams();
		String content = mEd_Content.getText().toString();
		if (content == null || "".equals(content))
		{
			Toast.makeText(RoateConsultActivity.this, getResources().getText(R.string.feebackcontent), Toast.LENGTH_SHORT).show();
			return;
		}
		if (content != null)
		{// BasicNameValuePair
			ArrayList<NameValuePair> paramsList = new ArrayList<NameValuePair>();
//			ArrayList<BasicNameValuePair> paramsList = new ArrayList<BasicNameValuePair>();
			for (int i = 0; i < PhotoUtils.bitmaps.size(); i++)
			{
				String imagePath = PhotoUtils.bitmaps.get(i).getImagePath();
				Bitmap photo = ImageTools.getBitmapFromPath(getApplicationContext(), imagePath);
				bitmapToBase64 = ImageTools.bitmapToBase64(photo);
				paramsList.add(new BasicNameValuePair(mImageParams[i], bitmapToBase64));
			}
			for (int j = 0; j < 4 - PhotoUtils.bitmaps.size(); j++)
			{
				paramsList.add(new BasicNameValuePair(mImageParams[j + PhotoUtils.bitmaps.size()], ""));
			}
			String amgoo_ID = Variable.getVariable().getData().getData().getUser().getAmgoo_ID();
			String user_password = Variable.getVariable().getData().getData().getUser().getUser_password();
			paramsList.add(new BasicNameValuePair("Amgoo_ID", amgoo_ID));
			paramsList.add(new BasicNameValuePair("user_password", user_password));
			paramsList.add(new BasicNameValuePair("feedback_type", sign + ""));
			paramsList.add(new BasicNameValuePair("feedback_title", ""));
			paramsList.add(new BasicNameValuePair("feedback_description", content));
			params.addBodyParameter(paramsList);
			HttpUtils http = new HttpUtils();
			http.configCurrentHttpCacheExpiry(30); // 设置当前接口缓存过期时间
			http.send(HttpRequest.HttpMethod.POST, Contant.ServerDataUrl.FEEFACK, params, new RequestCallBack<String>()
			{
				@Override
				public void onStart()
				{
					startProgress();
				}
				@Override
				public void onSuccess(ResponseInfo<String> responseInfo)
				{
					Log.e("----------------------------------", responseInfo.result + "----------------");
					stopProgress();
					Result feeback = JsonParser.deserializeByJson(responseInfo.result, Result.class);
					if (feeback.getStatus() == 1)
					{
						Toast.makeText(RoateConsultActivity.this, getResources().getText(R.string.setscucess), Toast.LENGTH_SHORT).show();
					}
					PhotoUtils.bitmaps.clear();
					titleBar.setRightTextClickable(true);
				}

				@Override
				public void onFailure(HttpException error, String msg)
				{
					stopProgress();
				}
			});
		}
	}

	@Override
	protected void backKeyCallBack()
	{
		super.backKeyCallBack();
		finish();
	}

	@Override
	protected void onResume()
	{
		closeProgress();
		adapter.notifyDataSetChanged();
		super.onResume();
	}

	protected void onRestart()
	{
		closeProgress();
		adapter.notifyDataSetChanged();
		super.onRestart();
	};

	public void initView()
	{
		PhotoUtils.bitmaps.clear();
		ifContainObdData = getIntent().getBooleanExtra("if_contain_obd_data", false);
		LinearLayout obdHintLayout = (LinearLayout) findViewById(R.id.obd_hint_layout);
		View bottomLine = (View) findViewById(R.id.bottom_line);
		if (ifContainObdData)
		{
			obdHintLayout.setVisibility(View.VISIBLE);
			bottomLine.setVisibility(View.VISIBLE);
		}
		noScrollgridview = (GridView) findViewById(R.id.noScrollgridview);
		noScrollgridview.setSelector(new ColorDrawable(Color.TRANSPARENT));
		adapter = new GridImageAdapter(this);
		noScrollgridview.setAdapter(adapter);
		adapter.notifyDataSetChanged();
		mEd_Content = (EditText) findViewById(R.id.publishfun_editText_something);
		mEd_Content.addTextChangedListener(new TextWatcher()
		{
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count)
			{
				// titleBar.setRightTextClickable(!TextUtils.isEmpty(s));
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after)
			{

			}
			@Override
			public void afterTextChanged(Editable s)
			{

			}
		});

		setPicture();
	}

	private void setPicture()
	{
		noScrollgridview.setOnItemClickListener(new OnItemClickListener()
		{

			public void onItemClick(AdapterView<?> arg0, View arg1, int index, long arg3)
			{
				if (index == PhotoUtils.bitmaps.size())
				{
					selDialog = new CustomAlbumDialog(RoateConsultActivity.this);
					selDialog.show();
					selDialog.setOnCustomAlbumLsitener(new OnCustomAlbumListener()
					{
						@Override
						public void init()
						{

							Intent intent = new Intent(RoateConsultActivity.this, PhotoAlbumAct.class);
							startActivity(intent);
						}
					});
				} else
				{
					Intent intent = new Intent(RoateConsultActivity.this, PhotoDeleteAct.class);
					intent.putExtra("ID", index);
					startActivity(intent);
				}
			}
		});
	}

	protected void stopProgress()
	{
		if (mProgressLoading != null)
		{
			mProgressLoading.dismiss();
			mProgressLoading = null;
		}
	}
	protected void startProgress()
	{
		if (mProgressLoading == null)
		{
			mProgressLoading = DialogUtil.createProgressDialog(this, getResources().getText(R.string.jiazai) + "", false);
		}
		if (!isFinishing())
		{
			mProgressLoading.show();
		}
	}

}