package com.amgoo.activity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.message.BasicNameValuePair;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amgoo.entiy.Login;
import com.amgoo.entiy.Result;
import com.amgoo.tool.CamerDialog;
import com.amgoo.tool.CamerDialog.OnCamerDialogListener;
import com.amgoo.tool.Contant;
import com.amgoo.tool.ImageTools;
import com.amgoo.tool.SelectPicPopupWindow;
import com.amgoo.tool.Variable;
import com.amgoo.util.DialogUtil;
import com.amgoo.util.HttpGetCallBack;
import com.amgoo.util.HttpQuest;
import com.amgoo.util.JsonParser;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;

public class MyActivity extends Activity implements OnClickListener

{
	SelectPicPopupWindow menuWindow;
	private int REQUEST_CODE;
	private static final int TAKE_PICTURE = 0;
	private static final int CHOOSE_PICTURE = 1;
	private static final int CROP = 2;
	private static final int CROP_PICTURE = 3;
	private static final int SCALE = 5;// 照片缩小比例
	private CamerDialog selDialog;
	private LinearLayout linMywork;
	private TextView tvMainLogin;
	private RelativeLayout relAreadyLogin;
	private RelativeLayout relNotLogin;
	private Dialog mProgressLoading;
	private LinearLayout linFinanceApply;
	private LinearLayout linSalerApply;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my);
		initview();
	}

	private void initview()
	{

		TextView tvname = (TextView) findViewById(R.id.tv_name);
		LinearLayout linset = (LinearLayout) findViewById(R.id.lin_set);
		LinearLayout linCommitInfo = (LinearLayout) findViewById(R.id.lin_commitinfo);
		ImageView imghead = (ImageView) findViewById(R.id.img_head);
		linMywork = (LinearLayout) findViewById(R.id.lin_mywork);
		tvMainLogin = (TextView) findViewById(R.id.tv_mainlogin);
		LinearLayout linSaleRecord = (LinearLayout) findViewById(R.id.lin_salerecord);
		LinearLayout linDaiFuKuan = (LinearLayout) findViewById(R.id.lin_daifukuan);
		LinearLayout linAreadyPay = (LinearLayout) findViewById(R.id.lin_areadypay);
		LinearLayout linDrawPercentage = (LinearLayout) findViewById(R.id.lin_drawpercentage);
		LinearLayout linMyFeeback = (LinearLayout) findViewById(R.id.lin_myfeeback);
		relAreadyLogin = (RelativeLayout) findViewById(R.id.rel_areadylogin);
		relNotLogin = (RelativeLayout) findViewById(R.id.rel_notlogin);
		linFinanceApply = (LinearLayout) findViewById(R.id.lin_finapply);
		linSalerApply = (LinearLayout) findViewById(R.id.lin_salapply);
		Login data = com.amgoo.tool.Variable.getVariable().getData();
		linset.setOnClickListener(this);
		imghead.setOnClickListener(this);
		tvname.setOnClickListener(this);
		linMywork.setOnClickListener(this);
		tvMainLogin.setOnClickListener(this);
		linSaleRecord.setOnClickListener(this);
		linDaiFuKuan.setOnClickListener(this);
		linCommitInfo.setOnClickListener(this);
		linAreadyPay.setOnClickListener(this);
		linDrawPercentage.setOnClickListener(this);
		linFinanceApply.setOnClickListener(this);
		linSalerApply.setOnClickListener(this);
		linMyFeeback.setOnClickListener(this);

	}

	@Override
	protected void onResume()
	{
		super.onResume();
		Login data = com.amgoo.tool.Variable.getVariable().getData();
		if (data == null)
		{
			relNotLogin.setVisibility(View.VISIBLE);
			relAreadyLogin.setVisibility(View.GONE);
		} else
		{
			relNotLogin.setVisibility(View.GONE);
			relAreadyLogin.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.my, menu);
		return true;
	}

	@SuppressLint("NewApi")
	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.lin_set:
			Intent intent = new Intent(MyActivity.this, SetActivity.class);
			startActivity(intent);
			break;
		case R.id.img_head:
			setPicture();
			break;
		case R.id.tv_name:
			Intent intent2 = new Intent(MyActivity.this, MyinfoActivity.class);
			startActivity(intent2);
			break;
		case R.id.lin_mywork:
			Intent intent3 = new Intent(MyActivity.this, MyWorkActivity.class);
			startActivity(intent3);
			break;
		case R.id.tv_mainlogin:
			Intent intent4 = new Intent(MyActivity.this, LoginActivity.class);
			startActivity(intent4);
			// relNotLogin.setVisibility(View.GONE);
			// relAreadyLogin.setVisibility(View.VISIBLE);
			break;
		case R.id.lin_salerecord:
			Intent intent5 = new Intent(MyActivity.this, SaleRecordActivity.class);
			startActivity(intent5);
			break;
		case R.id.lin_daifukuan:
			Intent intent6 = new Intent(MyActivity.this, AccountPaidActivity.class);
			startActivity(intent6);
			break;
		case R.id.lin_commitinfo:
			Intent intent7 = new Intent(MyActivity.this, CommitSaleInfoActivity.class);
			startActivity(intent7);
			break;
		case R.id.lin_areadypay:
			Intent intent8 = new Intent(MyActivity.this, AccountAreadyPayActivity.class);
			startActivity(intent8);
			break;
		case R.id.lin_drawpercentage:
			Intent intent9 = new Intent(MyActivity.this, DrawPercentageActivity.class);
			startActivity(intent9);
			break;
		case R.id.lin_finapply:
			Login data1 = Variable.getVariable().getData();
			String amgoo_ID = data1.getData().getUser().getAmgoo_ID();
			String user_password = data1.getData().getUser().getUser_password();
			if (amgoo_ID != null && user_password != null && amgoo_ID.isEmpty() && user_password.isEmpty())
			{
				setAdress(2);
			} else
			{
				Toast.makeText(MyActivity.this, "先登录", Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.lin_salapply:

			Login data = Variable.getVariable().getData();
			String amgoo_ID1 = data.getData().getUser().getAmgoo_ID();
			String user_password1 = data.getData().getUser().getUser_password();
			if (amgoo_ID1 != null && user_password1 != null && amgoo_ID1.isEmpty() && user_password1.isEmpty())
			{
				setAdress(1);
			} else
			{
				Toast.makeText(MyActivity.this, "先登录", Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.lin_myfeeback:
			Intent intent10 = new Intent(MyActivity.this, MyFeeBackActivity.class);
			startActivity(intent10);
			break;
		default:
			break;
		}
	}

	private void setPicture()
	{
		selDialog = new CamerDialog(MyActivity.this);
		selDialog.show();
		selDialog.setOnCamerDialogLsitener(new OnCamerDialogListener()
		{
			@Override
			public void initcamer()
			{
				takePic(true);
			}

			@Override
			public void initpickpicture()
			{
				choosePic(true);
			}
		});
	}

	private void takePic(final boolean crop)
	{
		Uri imageUri = null;
		String fileName = null;
		Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		if (crop)
		{
			REQUEST_CODE = CROP;
			// 删除上一次截图的临时文件
			SharedPreferences sharedPreferences = getSharedPreferences("temp", Context.MODE_WORLD_WRITEABLE);
			ImageTools.deletePhotoAtPathAndName(Environment.getExternalStorageDirectory().getAbsolutePath(), sharedPreferences.getString("tempName", ""));

			// 保存本次截图临时文件名字
			fileName = String.valueOf(System.currentTimeMillis()) + ".jpg";
			Editor editor = sharedPreferences.edit();
			editor.putString("tempName", fileName);
			editor.commit();
		} else
		{
			REQUEST_CODE = TAKE_PICTURE;
			fileName = "image.jpg";
		}
		imageUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), fileName));
		// 指定照片保存路径（SD卡），image.jpg为一个临时文件，每次拍照后这个图片都会被替换
		openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
		startActivityForResult(openCameraIntent, REQUEST_CODE);
	}

	private void choosePic(final boolean crop)
	{
		Intent openAlbumIntent = new Intent(Intent.ACTION_GET_CONTENT);
		if (crop)
		{
			REQUEST_CODE = CROP;
		} else
		{
			REQUEST_CODE = CHOOSE_PICTURE;
		}
		openAlbumIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
		startActivityForResult(openAlbumIntent, REQUEST_CODE);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{

		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK)
		{
			switch (requestCode)
			{
			case TAKE_PICTURE:
				// 将保存在本地的图片取出并缩小后显示在界面上
				Bitmap bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + "/image.jpg");
				Bitmap newBitmap = ImageTools.zoomBitmap(bitmap, bitmap.getWidth() / SCALE, bitmap.getHeight() / SCALE);
				// 由于Bitmap内存占用较大，这里需要回收内存，否则会报out of memory异常
				bitmap.recycle();

				// 将处理过的图片显示在界面上，并保存到本地
				ImageTools.savePhotoToSDCard(newBitmap, Environment.getExternalStorageDirectory().getAbsolutePath(), String.valueOf(System.currentTimeMillis()));
				// doBaoCunFile(Environment.getExternalStorageDirectory().getAbsolutePath()
				// + String.valueOf(System.currentTimeMillis()));
				// updownImg(ImageTools.bitmapToBase64(newBitmap)); // TODO
				break;

			case CHOOSE_PICTURE:
				ContentResolver resolver = getContentResolver();
				// 照片的原始资源地址)
				Uri originalUri = data.getData();
				// doBaoCunFile(originalUri.toString());//TODO
				try
				{
					// 使用ContentProvider通过URI获取原始图片
					Bitmap photo = MediaStore.Images.Media.getBitmap(resolver, originalUri);
					if (photo != null)
					{
						// 为防止原始图片过大导致内存溢出，这里先缩小原图显示，然后释放原始Bitmap占用的内存
						Bitmap smallBitmap = ImageTools.zoomBitmap(photo, photo.getWidth() / SCALE, photo.getHeight() / SCALE);
						// 释放原始图片占用的内存，防止out of memory异常发生
						photo.recycle();
						// updownImg(ImageTools.bitmapToBase64(smallBitmap)); //
						// TODO
					}
				} catch (FileNotFoundException e)
				{
					e.printStackTrace();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
				break;

			case CROP:
				Uri uri = null;
				if (data != null)
				{
					uri = data.getData();
					System.out.println("Data");
				} else
				{
					System.out.println("File");
					String fileName = getSharedPreferences("temp", Context.MODE_WORLD_WRITEABLE).getString("tempName", "");
					uri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), fileName));
				}
				cropImage(uri, 200, 200, CROP_PICTURE);

				break;

			case CROP_PICTURE:
				Bitmap photo = null;
				Uri photoUri = data.getData();
				if (photoUri != null)
				{
					photo = BitmapFactory.decodeFile(photoUri.getPath());
					// updownImg(ImageTools.bitmapToBase64(photo)); // TODO
				}
				if (photo == null)
				{
					Bundle extra = data.getExtras();
					if (extra != null)
					{
						photo = (Bitmap) extra.get("data");
						ByteArrayOutputStream stream = new ByteArrayOutputStream();
						photo.compress(Bitmap.CompressFormat.JPEG, 60, stream); // 压缩
						BitMaptoSDcard(photo); // TODO 存到缓存
						File sdDir = null;
						boolean sdCardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED); // 判断sd卡是否存在
						if (sdCardExist)
						{
							sdDir = Environment.getExternalStorageDirectory();// 获取根目录
						}
						String path = sdDir.getPath();
						// updownImg(ImageTools.bitmapToBase64(photo)); // TODO
					}
				}
				break;
			default:
				break;
			}
		}
	}

	// 调用系统的截取图片
	public void cropImage(Uri uri, int outputX, int outputY, int requestCode)
	{
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		intent.putExtra("crop", "true");
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		intent.putExtra("outputX", outputX);
		intent.putExtra("outputY", outputY);
		intent.putExtra("outputFormat", "JPEG");
		intent.putExtra("noFaceDetection", true);
		intent.putExtra("return-data", true);
		startActivityForResult(intent, requestCode);
	}

	private void setAdress(final int type)
	{
		Login data = Variable.getVariable().getData();
		String amgoo_ID = data.getData().getUser().getAmgoo_ID();
		String user_password = data.getData().getUser().getUser_password();
		ArrayList<BasicNameValuePair> paramsList = new ArrayList<BasicNameValuePair>();
		paramsList.add(new BasicNameValuePair("Amgoo_ID", amgoo_ID));
		paramsList.add(new BasicNameValuePair("user_password", user_password));
		paramsList.add(new BasicNameValuePair("role", type + ""));
		HttpQuest.Get(Contant.ServerDataUrl.APPLYFINORSALER, paramsList, new HttpGetCallBack()
		{
			@Override
			public void onStart()
			{

			}

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo)
			{
				stopProgress();
				Result result = JsonParser.deserializeByJson(responseInfo.result, Result.class);
				if (result.getStatus() == 1)
				{
					if (type == 1)
					{
						Toast.makeText(MyActivity.this, getResources().getText(R.string.saler), Toast.LENGTH_SHORT).show();
						linSalerApply.setClickable(false);
					}
					if (type == 2)
					{
						Toast.makeText(MyActivity.this, getResources().getText(R.string.financer), Toast.LENGTH_SHORT).show();
						linFinanceApply.setClickable(false);
					}
				}
			}

			@Override
			public void onFailure(HttpException error, String msg)
			{

			}
		});
	}

	private String BitMaptoSDcard(Bitmap photo)
	{
		File sdDir = null;
		boolean sdCardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED); // 判断sd卡是否存在
		if (sdCardExist)
		{
			sdDir = Environment.getExternalStorageDirectory();// 获取根目录
		} else
		{
			Toast.makeText(this, getResources().getText(R.string.setsd), Toast.LENGTH_LONG).show();
			return null;
		}
		String path = sdDir.getPath();
		File bitmapFile = new File(path + "/agmoo/head1.png");
		if (!bitmapFile.getParentFile().exists()) // 目录不存在,穿件
		{
			bitmapFile.getParentFile().mkdirs();
		}
		if (!bitmapFile.exists())
		{
			// 目标文件开始创建想
			try
			{
				bitmapFile.createNewFile();
				Log.e("绝对路径", bitmapFile.getAbsolutePath());
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		// 读

		FileOutputStream b = null;
		try
		{
			b = new FileOutputStream(bitmapFile);
			photo.compress(Bitmap.CompressFormat.JPEG, 30, b);// );//30
			// 是压缩率，表示压缩70%;
			// 如果不压缩是100，表示压缩率为0
		} catch (FileNotFoundException e)
		{
			Log.e("e", e.toString());
			e.printStackTrace();
		} finally
		{
			try
			{
				b.flush();
				b.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}

		}
		return path + "/agmoo/head1.png";
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
