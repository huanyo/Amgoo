package com.amgoo.util;


/**
 * 
 * @author harry
 * tabhost中的子activity跳转动画使用
 *
 */
public class AnimCommonInTab {

	public static int ANIM_IN = 0;
	public static int ANIM_OUT = 0;

	public static void set(int in, int out) {
		ANIM_IN = in;
		ANIM_OUT = out;
	}

	public static void clear() {
		ANIM_IN = 0;
		ANIM_OUT = 0;
	}
}
