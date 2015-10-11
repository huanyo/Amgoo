package com.amgoo.activity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amgoo.entiy.Login;
import com.amgoo.tool.CamerDialog;
import com.amgoo.tool.CamerDialog.OnCamerDialogListener;
import com.amgoo.tool.GenerDialog;
import com.amgoo.tool.GenerDialog.OnGenerDialogListener;
import com.amgoo.tool.ImageTools;
import com.amgoo.tool.Variable;

public class MyinfoActivity extends Activity implements OnClickListener
{
	private RelativeLayout linGener;
	private GenerDialog selDialog;
	private TextView tvSex;
	private ImageView imgSethead;
	private int REQUEST_CODE;
	private static final int TAKE_PICTURE = 0;
	private static final int CHOOSE_PICTURE = 1;
	private static final int CROP = 2;
	private static final int CROP_PICTURE = 3;
	private static final int SCALE = 5;// 照片缩小比例
	private CamerDialog camerDialog;
	private RelativeLayout RelNicheng;
	private RelativeLayout Relipone;
	private RelativeLayout reEmail;
	private RelativeLayout relTel;
	private RelativeLayout relAdress;
	private RelativeLayout relpersonsign;
	private TextView tvMaiNiceName;
	private TextView tvMainPhone;
	private TextView tvMainEmail;
	private TextView tvMainTel;
	private TextView tvMainAdress;
	private TextView tvMainPerson;
	private TextView tvAmgooId;
	private TextView tvMainName;
	private TextView tvMainCountry;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_myinfo);
		linGener = (RelativeLayout) findViewById(R.id.lin_gener);
		imgSethead = (ImageView) findViewById(R.id.img_sethead);
		RelNicheng = (RelativeLayout) findViewById(R.id.rel_nicheng);
		Relipone = (RelativeLayout) findViewById(R.id.rel_ipone);
		reEmail = (RelativeLayout) findViewById(R.id.rel_email);
		relTel = (RelativeLayout) findViewById(R.id.rel_tel);
		relAdress = (RelativeLayout) findViewById(R.id.rel_adress);
		relpersonsign = (RelativeLayout) findViewById(R.id.rel_personsign);

		tvAmgooId = (TextView) findViewById(R.id.tv_amgooid);
		tvMaiNiceName = (TextView) findViewById(R.id.tv_mainnicename);
		tvMainName = (TextView) findViewById(R.id.tv_mainname);
		tvSex = (TextView) findViewById(R.id.tv_sex);
		tvMainPhone = (TextView) findViewById(R.id.tv_mainphone);
		tvMainEmail = (TextView) findViewById(R.id.tv_mainemail);
		tvMainTel = (TextView) findViewById(R.id.tv_maintel);
		tvMainAdress = (TextView) findViewById(R.id.tv_mainadress);
		tvMainCountry = (TextView) findViewById(R.id.tv_maincountry);
		tvMainPerson = (TextView) findViewById(R.id.tv_mainperson);

		TextView tvMainInfo = (TextView) findViewById(R.id.tv_maininfo);

		linGener.setOnClickListener(this);
		imgSethead.setOnClickListener(this);
		RelNicheng.setOnClickListener(this);
		Relipone.setOnClickListener(this);
		reEmail.setOnClickListener(this);
		relTel.setOnClickListener(this);
		relAdress.setOnClickListener(this);
		relpersonsign.setOnClickListener(this);
		tvMainInfo.setOnClickListener(this);

	}

	@Override
	protected void onResume()
	{

		super.onResume();
		tvAmgooId = (TextView) findViewById(R.id.tv_amgooid);
		tvMaiNiceName = (TextView) findViewById(R.id.tv_mainnicename);
		tvMainName = (TextView) findViewById(R.id.tv_mainname);
		tvSex = (TextView) findViewById(R.id.tv_sex);
		tvMainPhone = (TextView) findViewById(R.id.tv_mainphone);
		tvMainEmail = (TextView) findViewById(R.id.tv_mainemail);
		tvMainTel = (TextView) findViewById(R.id.tv_maintel);
		tvMainAdress = (TextView) findViewById(R.id.tv_mainadress);
		tvMainCountry = (TextView) findViewById(R.id.tv_maincountry);
		tvMainPerson = (TextView) findViewById(R.id.tv_mainperson);

		Login data = Variable.getVariable().getData();
		String amgoo_ID = data.getData().getUser().getAmgoo_ID();
		String user_petName = data.getData().getUser().getUser_petName();
		String user_fullname = data.getData().getUser().getUser_fullname();
		String user_mobilephone = data.getData().getUser().getUser_mobilephone();
		String user_email = data.getData().getUser().getUser_email();
		String user_telephone = data.getData().getUser().getUser_telephone();
		String user_timezone_id = data.getData().getUser().getUser_timezone_id();
		String user_address = data.getData().getUser().getUser_address();
		int user_gender = data.getData().getUser().getUser_gender();
		String user_whatisup = data.getData().getUser().getUser_whatisup();

		if (amgoo_ID != null)
		{
			tvAmgooId.setText(amgoo_ID);
		}
		if (user_petName != null)
		{
			tvMaiNiceName.setText(user_petName);
		}
		if (user_fullname != null)
		{
			tvMainName.setText(user_fullname);
		}
		if (user_mobilephone != null)
		{
			tvMainPhone.setText(user_mobilephone);
		}
		if (user_email != null)
		{
			tvMainEmail.setText(user_email);
		}
		if (user_telephone != null)
		{
			tvMainTel.setText(user_telephone);
		}
		tvMainCountry.setText(user_timezone_id);
		if (user_address != null)
		{
			tvMainAdress.setText(user_address);
		}
		if (user_whatisup != null)
		{
			tvMainPerson.setText(user_whatisup);
		}
		if (user_gender == 0)
		{
			tvSex.setText("男");
		}
		if (user_gender == 1)
		{
			tvSex.setText("nv");
		}
		if (user_timezone_id != null)
		{
			tvMainCountry.setText(user_timezone_id);
		}
		if (user_fullname != null)
		{
			tvMainName.setText("user_fullname");
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.myinfo, menu);
		return true;
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.lin_gener:
			setGener();
			break;
		case R.id.img_sethead:
			setPicture();
			break;
		case R.id.rel_nicheng:
			Intent intent = new Intent(MyinfoActivity.this, NiceNameActivity.class);
			startActivityForResult(intent, 0);
			break;
		case R.id.rel_ipone:
			Intent intent1 = new Intent(MyinfoActivity.this, IponeNumberActivity.class);
			startActivityForResult(intent1, 0);
			break;
		case R.id.rel_email:
			Intent intent2 = new Intent(MyinfoActivity.this, EmailActivity.class);
			startActivityForResult(intent2, 0);
			break;
		case R.id.rel_tel:
			Intent intent3 = new Intent(MyinfoActivity.this, TelActivity.class);
			startActivityForResult(intent3, 0);
			break;
		case R.id.rel_adress:
			Intent intent4 = new Intent(MyinfoActivity.this, AdressActivity.class);
			startActivityForResult(intent4, 0);
			break;
		case R.id.rel_personsign:
			Intent intent5 = new Intent(MyinfoActivity.this, PersonSignActivity.class);
			startActivityForResult(intent5, 0);
			break;
		case R.id.tv_maininfo:
			finish();
			break;
		default:
			break;
		}
	}

	private void setGener()
	{
		selDialog = new GenerDialog(MyinfoActivity.this);
		selDialog.show();
		selDialog.setOnGenerDialogLsitener(new OnGenerDialogListener()
		{
			@Override
			public void initman()
			{
				tvSex.setText(getResources().getText(R.string.genman));
			}

			@Override
			public void initwoman()
			{
				tvSex.setText(getResources().getText(R.string.genwomen));
			}
		});
	}

	private void setPicture()
	{
		camerDialog = new CamerDialog(MyinfoActivity.this);
		camerDialog.show();
		camerDialog.setOnCamerDialogLsitener(new OnCamerDialogListener()
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

}
