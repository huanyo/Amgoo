package com.agmoo.picture;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amgoo.activity.R;

public class TitleHeaderBar extends FrameLayout
{
	// 左边按钮背景（The backgorund of "Back" button�?
	@SuppressWarnings("unused")
	private Drawable leftBackground;
	// 左边按钮图标（The image drawn on top of "Back" button�?
	@SuppressWarnings("unused")
	private Drawable leftDrawable;
	// 右面边按钮背景（The backgorund of "Home" button�?
	private Drawable rightBackground;
	// 右边按钮图标（The image drawn on top of "Home" button�?
	private Drawable rightDrawable;
	// 背景
	Drawable background;

	// 左边,右边按钮文字,中间文字
	@SuppressWarnings("unused")
	private CharSequence leftText, rightText, centerText;

	private View btnBarHome, btnLeft;
	ImageView icoLeft, arrowLeft;
	private Button rightBtn;
	private TextView barTitle;

	// 中间下面副标�?
	private TextView subTitle;

	private boolean showLeft, showRight;

	private View.OnClickListener barBackListener;
	private View.OnClickListener barHomeListener;

	public TitleHeaderBar(Context context, AttributeSet attrs)
	{
		super(context, attrs);

		LayoutInflater.from(context).inflate(R.layout.view_header, this, true);
		if (!isInEditMode())
		{
			init(context, attrs);
		}
	}

	public TitleHeaderBar(Context context)
	{
		this(context, null);
	}

	private void init(Context context, AttributeSet attrs)
	{
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ActionBar);

		// Back button
		leftBackground = a.getDrawable(R.styleable.ActionBar_leftBackground);
		leftDrawable = a.getDrawable(R.styleable.ActionBar_leftDrawable);
		leftText = a.getText(R.styleable.ActionBar_leftText);

		background = a.getDrawable(R.styleable.ActionBar_background);

		// Home button
		rightBackground = a.getDrawable(R.styleable.ActionBar_rightBackground);
		rightDrawable = a.getDrawable(R.styleable.ActionBar_rightDrawable);
		rightText = a.getText(R.styleable.ActionBar_rightText);

		// Title
		centerText = a.getText(R.styleable.ActionBar_barTitle);

		showLeft = a.getBoolean(R.styleable.ActionBar_showLeft, true);
		showRight = a.getBoolean(R.styleable.ActionBar_showRight, true);

		a.recycle();
	}

	public void initSubTile()
	{
		subTitle = (TextView) findViewById(R.id.tvCenterSubTitle);
		subTitle.setVisibility(View.VISIBLE);
	}

	public void setSubTitle(String st)
	{
		subTitle.setText(st);
	}

	public void setTitle(String t)
	{
		barTitle.setText(t);
	}

	private void initView()
	{
		arrowLeft = (ImageView) findViewById(R.id.arrowLeft);
		btnLeft = findViewById(R.id.bar_iv_left);

		// image logo
		// icoLeft = (ImageView) findViewById(R.id.icoLeft);
		barTitle = (TextView) findViewById(R.id.bar_tv_title);
		rightBtn = (Button) findViewById(R.id.bar_ly_right);
		LinearLayout lyCenter = (LinearLayout) findViewById(R.id.lyCenter);
		btnBarHome = findViewById(R.id.btnBarHome);

//		if (!showLeft)
//		{
//			arrowLeft.setVisibility(View.GONE);
//		}
		// if (!showRight)
		// {
		// btnBarHome.setVisibility(View.GONE);
		// }

		if (null != rightText)
		{
			rightBtn.setText(rightText);
		}
		if (null != background)
		{
			lyCenter.setBackgroundDrawable(background);
		}
		if (null != rightBackground)
		{
			rightBtn.setBackgroundDrawable(rightBackground);
		}
		if (null != rightDrawable)
		{
			rightBtn.setCompoundDrawablesWithIntrinsicBounds(null, rightDrawable, null, null);
		}
		if (null != rightText)
		{
			rightBtn.setText(rightText);
		}
		if (null != centerText)
		{
			barTitle.setText(centerText);
		}

		if (null != barBackListener)
		{
			btnLeft.setOnClickListener(barBackListener);
		}

		if (null != barHomeListener)
		{
			rightBtn.setOnClickListener(barHomeListener);
		}
	}

	@Override
	protected void onFinishInflate()
	{
		initView();
		super.onFinishInflate();
	}

	public void setBarBackListener(View.OnClickListener barBackListener)
	{
		this.barBackListener = barBackListener;
	}

	public void setBarHomeListener(View.OnClickListener barHomeListener)
	{
		this.barHomeListener = barHomeListener;
	}

	public void setRightText(String txt)
	{
		rightText = txt;
		if (null != rightText)
		{
			rightBtn.setText(rightText);
		}
	}

	private boolean rightTextClickable;

	public void setRightTextClickable(boolean clickable)
	{
		rightTextClickable = clickable;
		if (rightTextClickable)
		{
			// rightBtn.setTextColor(0xFFFFFF);
			rightBtn.setTextColor(getResources().getColor(R.color.white));
		} else
		{
			// rightBtn.setTextColor(0x4681C5);
			rightBtn.setTextColor(getResources().getColor(R.color.check_text_color));
		}
	}

	public boolean isRightTextClickable()
	{
		return rightTextClickable;
	}
}
