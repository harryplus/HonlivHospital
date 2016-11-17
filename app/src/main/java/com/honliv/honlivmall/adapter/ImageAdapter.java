package com.honliv.honlivmall.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader.TileMode;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * 女装导航滚动图片
 * 
 * @author Administrator
 * 
 */

public class ImageAdapter extends BaseAdapter {
	private Context context;
	private int[] imageIDs;
	private ImageView[] images;

	public ImageAdapter(Context context, int[] imageIDs) {
		this.context = context;
		this.imageIDs = imageIDs;
		
		images = new ImageView[imageIDs.length];
	}

	@Override
	public int getCount() {
		return images.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return images[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return images[position];
	}

	//生成带有倒影效果的图片
	public void createReflectionBitmap() {
		
		int index = 0;
		int reflectionGap = 4;
		for(int imageID : imageIDs){
			//找到原图片
			Bitmap resourceBitmap = BitmapFactory.decodeResource(context.getResources(), imageID);
			
			int width = resourceBitmap.getWidth();
			int height = resourceBitmap.getHeight();
			//生成倒影图片
			/**
			 * source 生成倒影图片所使用的原图片
			 * x, y   左上角位置
			 *  width, height 倒影图片的宽高
			 *  Matrix m,  图片样式
			 */
			Matrix matrix = new Matrix();
			
			// x轴 -1  水平翻转
			// y轴 -1 垂直翻转
			matrix.setScale(1.0f, -1.0f);//1 正常
				
			
			Bitmap reflectionBitmap = Bitmap.createBitmap(resourceBitmap, 0, height/2, width, height/2, matrix, false);
			
			//合成图片
			Bitmap bitmap = Bitmap.createBitmap(width, height+ height/2, Config.ARGB_8888);
			Canvas canvas = new Canvas(bitmap);
			
			//绘制原图片
			canvas.drawBitmap(resourceBitmap, 0, 0, null);
			
			//绘制原图片与倒影图片之间的缝隙
			Paint defaultPaint = new Paint();
			canvas.drawRect(0, height, width, height + reflectionGap, defaultPaint);
			
			//绘制倒影图片
			canvas.drawBitmap(reflectionBitmap, 0, height + reflectionGap, null);
			
			
			//photoShop 遮罩、 渐变
			Paint paint = new Paint();
			
			//遮罩
			paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
			
			//渐变
			/**
			 * x0, y0 
			 * x1, y1
			 *  color0
			 *  color1
			 *  tile 填充模式
				//线性渐变填充 shader着色器
				//在位图上Y方向花砖模式 0x70ffffff, 0x00ffffff
				TileMode：（一共有三种） 
				CLAMP ：如果渲染器超出原始边界范围，会复制范围内边缘染色。 
				REPEAT ：横向和纵向的重复渲染器图片，平铺。 
				MIRROR ：横向和纵向的重复渲染器图片，这个和REPEAT 重复方式不一样，他是以镜像方式平铺。
			 */
			LinearGradient shader = new LinearGradient(0, height, 0, bitmap.getHeight(), 0x70ffffff, 0x00ffffff, TileMode.CLAMP);
			paint.setShader(shader);
			
			canvas.drawRect(0, height, width, bitmap.getHeight(), paint);
			
			//显示合成图片
			ImageView imageView = new ImageView(context);
			imageView.setImageBitmap(bitmap);
			
			images[index++] = imageView;
			
		}
	}

}
