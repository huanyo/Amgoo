package com.amgoo.zxing;

import java.math.BigDecimal;
import java.util.Collection;

public class TextUtil {
	public static boolean isValidate(String content) {
		return content != null && !"".equals(content.trim());
	}

	public static boolean isValidate(Collection<?> list) {
		return list != null && list.size() > 0;
	}

	public static String formatFloat(String money, String strFormat) {
		java.text.DecimalFormat df = new java.text.DecimalFormat(strFormat);
		return df.format(Double.valueOf(money));
	}

	public static String formatDouble(double number) {
		// 53.8
		BigDecimal bd = new BigDecimal(number+"");
		BigDecimal setScale = null;
		if (isDigits(number)) // 如果小数点第一位等于0
		{
			setScale = bd.setScale(0, bd.ROUND_DOWN);// 不四舍五入

		} else {
			// java.text.DecimalFormat df = new java.text.DecimalFormat("#0.0");
			// //四舍五入
			setScale = bd.setScale(1, bd.ROUND_DOWN);
			// return df.format(Double.valueOf(number));
		}
		return setScale.toString();
	}

	@SuppressWarnings("static-access")
	public static String returnPrice(double number) {
		BigDecimal bd = new BigDecimal(number+"");
		BigDecimal setScale = bd.setScale(1, BigDecimal.ROUND_DOWN);
		return setScale.toString();
	}

	// 秒转换为时间
	public static String countdown(long time) {
		int ss = 1000;
		int mi = ss * 60;
		int hh = mi * 60;
		long hour = (time) / hh;
		long minute = (time - hour * hh) / mi;
		long second = (time - hour * hh - minute * mi) / ss;
		String strHour = hour < 10 ? "0" + hour : "" + hour;
		String strMinute = minute < 10 ? "0" + minute : "" + minute;
		String strSecond = second < 10 ? "0" + second : "" + second;
		// Log.e("countdown", strHour + ":" + strMinute + ":" + strSecond);
		return strHour + "时" + strMinute + "分" + strSecond + "秒";
	}

	// 判断小數點第一位為0
	public static boolean isDigits(double number) {
		BigDecimal bd = new BigDecimal(number);
		BigDecimal setScale = bd.setScale(1, bd.ROUND_DOWN);
		Double valueOf = Double.valueOf(setScale.toString());
		if (valueOf % 1.0 == 0) {
			return true;
		}
		return false;
	}
}
