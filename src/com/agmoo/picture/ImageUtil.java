package com.agmoo.picture;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

/***********************************************************
 * @Description:图片异步加载�?
 ***********************************************************/
public class ImageUtil
{
	/**
	 * 常用参数说明�?
	 * 
	 * //设置图片在下载期间显示的图片 showStubImage(R.drawable.ic_launcher)
	 * 
	 * //设置图片Uri为空或是错误的时候显示的图片 showImageForEmptyUri(R.drawable.ic_empty)
	 * 
	 * //设置图片加载/解码过程中错误时候显示的图片 showImageOnFail(R.drawable.ic_error)
	 * 
	 * //设置图片在下载前是否重置，复�? resetViewBeforeLoading()
	 * 
	 * //设置下载的图片是否缓存在内存�? cacheInMemory()
	 * 
	 * //设置下载的图片是否缓存在SD卡中 cacheOnDisc()
	 * 
	 * //设置图片的解码类�? bitmapConfig(Bitmap.Config.RGB_565)
	 * 
	 * //设置图片的解码配�? decodingOptions(android.graphics.BitmapFactory.Options
	 * decodingOptions)
	 * 
	 * //设置图片下载前的延迟 delayBeforeLoading(int delayInMillis)
	 * 
	 * //设置额外的内容给ImageDownloader extraForDownloader(Object extra)
	 * 
	 * //设置图片加入缓存前，对bitmap进行设置 preProcessor(BitmapProcessor preProcessor)
	 * 
	 * //设置显示前的图片，显示后这个图片�?��保留在缓存中 postProcessor(BitmapProcessor postProcessor)
	 * 
	 * //设置图片以如何的编码方式显示 imageScaleType(ImageScaleType imageScaleType)
	 */
	/**
	 * 图片的显示方�?
	 * 
	 * @param displayer
	 *            :
	 * 
	 *            RoundedBitmapDisplayer(int roundPixels)设置圆角图片
	 *            FakeBitmapDisplayer()这个类什么都没做 FadeInBitmapDisplayer(int
	 *            durationMillis)设置图片渐显的时�? SimpleBitmapDisplayer()正常显示�?��图片
	 */
	/**
	 * 图片的缩放方�?
	 * 
	 * @param imageScaleType
	 *            :
	 * 
	 *            imageScaleType(ImageScaleType imageScaleType) imageScaleType:
	 *            EXACTLY :图像将完全按比例缩小的目标大�? EXACTLY_STRETCHED:图片会缩放到目标大小完全
	 *            IN_SAMPLE_INT:图像将被二次采样的整数�?
	 *            IN_SAMPLE_POWER_OF_2:图片将降�?倍，直到下一减少步骤，使图像更小的目标大�? NONE:图片不会调整
	 *            �??
	 */

	private static DisplayImageOptions options;

	public static Bitmap loadDefaultBitmap(String uri)
	{
		options = new DisplayImageOptions.Builder().bitmapConfig(Bitmap.Config.RGB_565)// 设置图片的解码类�?
				.imageScaleType(ImageScaleType.NONE).displayer(new SimpleBitmapDisplayer()).resetViewBeforeLoading(true).cacheInMemory(true)// 加入内存
				.cacheOnDisc(true)// 加入SD卡缓�?
				.build();
		Bitmap bitmap = ImageLoader.getInstance().loadImageSync(uri, options);

		return bitmap;
	}

	public static Bitmap getsamllBitmap(String uri, final int defaultImage)
	{
		Bitmap bitmap = getBitmap(uri, 200, 200, defaultImage);
		return bitmap;
	}

	// 地址转URL 异步加载
	public static Bitmap getBitmap(String uri, int width, int height, final int defaultImage)
	{
		options = new DisplayImageOptions.Builder().showImageOnLoading(defaultImage)// 设置读取中时的默认图
				.showImageForEmptyUri(defaultImage)// 设置图片地址为空时的默认�?
				.showImageOnFail(defaultImage)// 设置加载失败时的默认�?
				.bitmapConfig(Bitmap.Config.RGB_565)// 设置图片的解码类�?
				.imageScaleType(ImageScaleType.NONE).cacheInMemory(true)// 加入内存
				.cacheOnDisc(true)// 加入SD卡缓�?
				.build();
		ImageSize targetImageSize = new ImageSize(width, height);
		Bitmap bitmap = ImageLoader.getInstance().loadImageSync(uri, targetImageSize, options);
		return bitmap;

	}

	// 地址转URL 异步加载
	public static Bitmap loadBitmap(String uri, final int defaultImage)
	{
		options = new DisplayImageOptions.Builder().showImageOnLoading(defaultImage)// 设置读取中时的默认图
				.showImageForEmptyUri(defaultImage)// 设置图片地址为空时的默认�?
				.showImageOnFail(defaultImage)// 设置加载失败时的默认�?
				.bitmapConfig(Bitmap.Config.RGB_565)// 设置图片的解码类�?
				.imageScaleType(ImageScaleType.NONE).cacheInMemory(true)// 加入内存
				.cacheOnDisc(true)// 加入SD卡缓�?
				.build();
		ImageSize targetImageSize = new ImageSize(800, 480);

		Bitmap bitmap = ImageLoader.getInstance().loadImageSync(uri, targetImageSize, options);
		return bitmap;

	}

	// 带圆�?
	public static void getImage(String uri, ImageView view, int roundPixels, final int defaultImage)
	{
		options = new DisplayImageOptions.Builder().showImageOnLoading(defaultImage)// 设置读取中时的默认图
				.showImageForEmptyUri(defaultImage)// 设置图片地址为空时的默认�?
				.showImageOnFail(defaultImage)// 设置加载失败时的默认�?
				.bitmapConfig(Bitmap.Config.RGB_565)// 设置图片的解码类�?
				.imageScaleType(ImageScaleType.EXACTLY_STRETCHED).displayer(new RoundedBitmapDisplayer(roundPixels)).resetViewBeforeLoading(true).cacheInMemory(true)// 加入内存
				.cacheOnDisc(true)// 加入SD卡缓�?
				.build();

		ImageLoader.getInstance().displayImage(uri, view, options);

	}

	// 图片异步加载

	public static void getImage(String uri, ImageView view, final int defaultImage)
	{
		options = new DisplayImageOptions.Builder().showImageOnLoading(defaultImage)// 设置读取中时的默认图
				.showImageForEmptyUri(defaultImage)// 设置图片地址为空时的默认�?
				.showImageOnFail(defaultImage)// 设置加载失败时的默认�?
				.bitmapConfig(Bitmap.Config.RGB_565)// 设置图片的解码类�?
				.imageScaleType(ImageScaleType.EXACTLY_STRETCHED).resetViewBeforeLoading(true).cacheInMemory(true)// 加入内存
				.cacheOnDisc(true)// 加入SD卡缓�?
				.build();

		ImageLoader.getInstance().displayImage(uri, view, options);
	}

	// 图片异步加载 带监�?
	public static void loadAndListen(String uri, ImageView view, final int defaultImage, ImageLoadingListener listenr)
	{
		options = new DisplayImageOptions.Builder().showImageOnLoading(defaultImage)// 设置读取中时的默认图
				.showImageForEmptyUri(defaultImage)// 设置图片地址为空时的默认�?
				.showImageOnFail(defaultImage)// 设置加载失败时的默认�?
				.bitmapConfig(Bitmap.Config.RGB_565)// 设置图片的解码类�?
				.cacheInMemory(true)// 加入内存
				.cacheOnDisc(true)// 加入SD卡缓�?
				.imageScaleType(ImageScaleType.NONE).displayer(new FadeInBitmapDisplayer(500)).build();

		ImageLoader.getInstance().displayImage(uri, view, listenr);
	}

}
