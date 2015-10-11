package com.amgoo.util;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amgoo.activity.R;

/**
 * 对话框的帮助
 */
public class DialogUtil {
	/**
	 * ListDialog的回调接口
	 */
	public interface OnListDialogItemClickListener {
		public void onListDialogItemClick(int which);
	}

	public static final int TYPE_BLUE = 1;
	public static final int TYPE_RED = 2;
	public static final int TYPE_CANCEL = 3;
	public static final int TYPE_WHITE = 4;

	public static Dialog createProgressDialog(Context context,
			String loadingText, boolean cancelable) {
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.progress_dialog, null);
		LinearLayout layout = (LinearLayout) view.findViewById(R.id.ll_loading);// 加载布局
		// main.xml中的ImageView
		TextView loadingTextView = (TextView) view
				.findViewById(R.id.txt_progress_msg);
		loadingTextView.setText("" + loadingText);
		Dialog loadingDialog = new Dialog(context, R.style.Theme_Dialog);// 创建自定义样式dialog
		loadingDialog.setCancelable(cancelable);// 不可以用“返回键”取�?
		loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
		return loadingDialog;
	}
}
