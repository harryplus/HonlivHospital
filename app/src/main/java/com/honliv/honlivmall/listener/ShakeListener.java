package com.honliv.honlivmall.listener;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Vibrator;

/**
 * 摇一摇震动器相关
 * @author Administrator
 *
 */
public abstract class  ShakeListener implements SensorEventListener {

	private float lastX;
	private float lastY;
	private float lastZ;
	private long lastTime;
	
	private long duration = 100;//摇荡时间开关
	
	private float shake;// 单次增量计算
	private float totleShake;// 总增量
	private float switchValue = 220;// 判断是否摇晃手机的阈值

	private Context context;
	
	public ShakeListener(Context context) {
		super();
		this.context = context;
	}

	private void init() {
		lastX = 0;
		lastY = 0;
		lastZ = 0;
		lastTime = 0;
		
		shake = 0;
		totleShake = 0;
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {

	}

	@Override
	public void onSensorChanged(SensorEvent event) {

		if (lastTime == 0) {//起始摇
			lastX = event.values[SensorManager.DATA_X];
			lastY = event.values[SensorManager.DATA_Y];
			lastZ = event.values[SensorManager.DATA_Z];

			lastTime = System.currentTimeMillis();
		} else {
			long currentTime = System.currentTimeMillis();
			if(currentTime - lastTime >= duration){
				// 第二点：判断采样的时间间隔大于等于100毫秒，保存三个轴的加速度信息
				// 起点与第二个点增量的计算（各个轴的增量，然后汇总）
				float x = event.values[SensorManager.DATA_X];
				float y = event.values[SensorManager.DATA_Y];
				float z = event.values[SensorManager.DATA_Z];
				
				float dx = Math.abs(x - lastX);
				float dy = Math.abs(y - lastY);
				float dz = Math.abs(z - lastZ);
				
			//	System.out.println("dx="+dx+",dy="+dy+",dz="+dz);
				if(dx<1){
					dx = 0;
				}
				
				if(dy<1){
					dy = 0;
				}
				if(dz <1){
					dz = 0;
				}
				
				shake = dx + dy + dz;
				if(shake == 0){
					init();
				}
				totleShake += shake;
				if(totleShake >= switchValue){
					// 摇晃手机// 机选
					successShake();
					
					// 震动提示用户
					vibrator();
					
					// 还原数据,防止计数累计
					init();
				}else{
					lastX = event.values[SensorManager.DATA_X];
					lastY = event.values[SensorManager.DATA_Y];
					lastZ = event.values[SensorManager.DATA_Z];

					lastTime = System.currentTimeMillis();
				}
			}
		}
	}

	public abstract void successShake();//摇晃手机成功,机选

	private Vibrator manager;
	private void vibrator() {
		if(manager == null){
			manager =  (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
		}
		manager.vibrate(100);
	}

}
