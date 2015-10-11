package com.amgoo.tool;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 时间戳的相互转换
 */
public class TimeChuo {

	// 字符串转时间
	public static String getTime(String time) {
		String re_time = null;
		SimpleDateFormat fomat = new SimpleDateFormat("yyyy/MM/dd");
		Date date;
		try {
			date = fomat.parse(time);
			long time2 = date.getTime();
			String str = String.valueOf(time2);
			re_time = str.substring(0, 10);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return re_time;

	}

	// 时间戳轉字符串
	public static String getStrTime(String cc_time) {
		String re_Strtime = null;
		SimpleDateFormat fomat = new SimpleDateFormat("yyyy/MM/dd");
		// cc_time=1291778220
		Long lcc_time = Long.valueOf(cc_time);
		re_Strtime = fomat.format(new Date(lcc_time * 1000L));

		return re_Strtime;

	}

}
