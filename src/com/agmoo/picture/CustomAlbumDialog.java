package com.agmoo.picture;



import java.io.File;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import com.amgoo.activity.R;

public class CustomAlbumDialog extends Dialog {

	private Button mbtn_1;
	private Button mbtn_2;
	private Button mbtn_3;
	 Context context;

	public CustomAlbumDialog(Context context) {
		super(context, R.style.spinner_dialog);
		this.context = context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_customalbum);
		initView();
		btnListener();
		
		

	}

	private void initView() 
	{
		mbtn_1 = (Button) findViewById(R.id.publishfun_btn_camera);
		mbtn_2 = (Button) findViewById(R.id.publishfun_btn_photo);
		mbtn_3 = (Button) findViewById(R.id.publishfun_btn_cancel);
	
	}
	
	

	private void btnListener() 
	{
		mbtn_1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				fromCamera();	
			}
		});
		mbtn_2.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				onCustomAlbumLsitener.init();
				dismiss();	
			}
		});
		mbtn_3.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				dismiss();
			}
		});
	}

	OnCustomAlbumListener  onCustomAlbumLsitener = null;
	


	public void setOnCustomAlbumLsitener(OnCustomAlbumListener l) {
		this.onCustomAlbumLsitener = l;
	}


	public interface OnCustomAlbumListener
	{
		public void init();
	}
	
	File  photo;
	public File getPhotoFile() {
		return photo;
	}
	public void setPhotoFile(File photo) 
	{
		this.photo = photo;
	}
	//选择存储位置
	Uri tempCropUri;
	public Uri getTempCropUri() {

			tempCropUri = Uri.fromFile(getPhotoFile());
			photoPath = getPhotoFile().getAbsolutePath();
		
	    return tempCropUri;
	}

	String photoPath;

	public String getPhotoPath() {
		return photoPath;
	}
	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}	



	File  tempFile = new File(Environment.getExternalStorageDirectory(),
    		PhotoFileUtils.getPhotoFileName());
	/**
	 * 打开系统相机
	 * @param uri
	 */
	protected void fromCamera() 
	{	
		
	    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);  
	    setPhotoFile(tempFile);	    
        intent.putExtra("camerasensortype", 2);// 调用前置摄像�? 
        intent.putExtra("autofocus", true);// 自动对焦  
        intent.putExtra("fullScreen", false);// 全屏  
        intent.putExtra("showActionIcons", false);  
        // 指定调用相机拍照后照片的储存路径  
		intent.putExtra(MediaStore.EXTRA_OUTPUT,  getTempCropUri());
		((Activity) context).startActivityForResult(intent, 1);
		dismiss();
	}
	
	
}
