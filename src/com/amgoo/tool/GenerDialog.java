package com.amgoo.tool;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.amgoo.activity.R;

public class GenerDialog extends Dialog
{

	private Button mbtn_1;
	private Button mbtn_2;
	Context context;

	public GenerDialog(Context context)
	{
		super(context, R.style.spinner_dialog);
		this.context = context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_gener);
		initView();
		btnListener();

	}

	private void initView()
	{
		mbtn_1 = (Button) findViewById(R.id.publishfun_btn_camera);
		mbtn_2 = (Button) findViewById(R.id.publishfun_btn_photo);

	}

	private void btnListener()
	{
		mbtn_1.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
			{
				OnGenerDialogLsitener.initman();
				dismiss();
			}
		});
		mbtn_2.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
			{
				OnGenerDialogLsitener.initwoman();
				dismiss();
			}
		});
	}

	OnGenerDialogListener OnGenerDialogLsitener = null;

	public interface OnGenerDialogListener
	{
		public void initman();

		public void initwoman();

	}

	public void setOnGenerDialogLsitener(OnGenerDialogListener l)
	{
		this.OnGenerDialogLsitener = l;
	}

}
