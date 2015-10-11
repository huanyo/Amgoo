package com.amgoo.tool;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.amgoo.activity.R;

public class CamerDialog extends Dialog
{

	private Button mbtn_1;
	private Button mbtn_2;
	private Button mbtn_3;
	Context context;

	public CamerDialog(Context context)
	{
		super(context, R.style.spinner_dialog);
		this.context = context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_camer);
		initView();
		btnListener();

	}

	private void initView()
	{
		mbtn_1 = (Button) findViewById(R.id.publishfun_btn_camera);
		mbtn_2 = (Button) findViewById(R.id.publishfun_btn_photo);
		mbtn_3 = (Button) findViewById(R.id.lin_cancle);

	}

	private void btnListener()
	{
		mbtn_1.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
			{
				OnCamerDialogLsitener.initcamer();
				dismiss();
			}
		});
		mbtn_2.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
			{
				OnCamerDialogLsitener.initpickpicture();
				dismiss();
			}
		});
		mbtn_3.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
			{
				dismiss();
			}
		});
	}

	OnCamerDialogListener OnCamerDialogLsitener = null;

	public interface OnCamerDialogListener
	{
		public void initcamer();

		public void initpickpicture();

	}

	public void setOnCamerDialogLsitener(OnCamerDialogListener l)
	{
		this.OnCamerDialogLsitener = l;
	}

}
