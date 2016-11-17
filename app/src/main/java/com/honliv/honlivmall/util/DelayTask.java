package com.honliv.honlivmall.util;

import android.os.Handler;

public abstract class DelayTask {
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			runOnUiThread();
		}
	};

	/**
	 * �ӳ�ִ�еķ���
	 * @param delayTime ��ʱʱ�� ����ֵ
	 */
	public void execute(final long delayTime){
		new Thread(){
			public void run() {
				try {
					Thread.sleep(delayTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				handler.sendEmptyMessage(0);
			}
		}.start();

	}
	/**
	 * ��ʱ֮�������߳�����ִ�еķ���.
	 */
	protected abstract void runOnUiThread();
}
