package com.agmoo.picture;



import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.amgoo.activity.R;

public class ProgressDialog extends Dialog{

	String title;
	String message;
	Context context;
	private ImageView spaceshipImage;
	private Animation hyperspaceJumpAnimation;
	public ProgressDialog(Context context, String title, String message) 
	{
		super(context, R.style.progress_dialog);
		// TODO Auto-generated constructor stub
		this.title = title;
		this.message = message;
		this.context =context;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_progress);	
		initView();
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		super.show();
		spaceshipImage.startAnimation(hyperspaceJumpAnimation);  
	}
	

	
	private void initView() {
		spaceshipImage = (ImageView)findViewById(R.id.img);  
	        TextView tv_title = (TextView)findViewById(R.id.tv_title);// 鎻愮ず鏍囬
	        TextView tipTextView = (TextView) findViewById(R.id.tipTextView);// 鎻愮ず鏂囧瓧  
	        hyperspaceJumpAnimation = AnimationUtils.loadAnimation(  
	                context, R.anim.progress_animation);  
	        spaceshipImage.startAnimation(hyperspaceJumpAnimation);  
	       
	        if(TextUtils.isEmpty(title))
	        {
	        	 tv_title.setVisibility(View.GONE);      	 
	        }
	        else
	        {
	        	tv_title.setVisibility(View.VISIBLE);      	 
	        	tv_title.setText(title);
	        }
	        tipTextView.setText(message);// 璁剧疆鍔犺浇淇℃�? 
	}

	

}
