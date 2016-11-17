package com.honliv.honlivmall.util;

import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.widget.Toast;

import com.honliv.honlivmall.R;

/**
 * 提示信息的管理
 * Context 为Activity,this,不能为getApplicationContext()
 */
public class PromptManager {
	private static ProgressDialog dialog;
	/**
	 * 显示没图标的联网对话
	 * @param context
	 */
	public static void showCommonProgressDialog(Context context){
		if (dialog != null && dialog.isShowing()) {
			//dialog.dismiss();
		}else{
			dialog = new ProgressDialog(context);
			//dialog.setTitle(R.string.app_name);
	
			dialog.setMessage("请稍候，数据加载中…");
			dialog.show();
		}
	}
	
	public static void showProgressDialog(Context context) {
		dialog = new ProgressDialog(context);
		dialog.setIcon(R.drawable.ic_launcher);
		dialog.setTitle(R.string.app_name);

		dialog.setMessage("请等候，数据加载中……");
		dialog.show();
	}

	public static void closeProgressDialog() {
		if (dialog != null && dialog.isShowing()) {
			dialog.dismiss();
		}
	}
	/**
	 * 当判断当前手机没有网络时使用
	 * @param context
	 */
	public static void showNoNetWork(final Context context) {
		Builder builder = new Builder(context);
		builder.setIcon(R.drawable.ic_launcher)//
				.setTitle(R.string.app_name)//
				.setMessage("当前无网络").setPositiveButton("设置", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// 跳转到系统的网络设置界面
						/*Intent intent=new Intent();
						intent.setClassName("com.android.settings", "com.android.settings.WirelessSettings");
						context.startActivity(intent);*/

						if(android.os.Build.VERSION.SDK_INT > 10 ){
						     //3.0以上打开设置界面，也可以直接用ACTION_WIRELESS_SETTINGS打开到wifi界面
							context.startActivity(new Intent(android.provider.Settings.ACTION_SETTINGS));
						} else {
							context.startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
						}
					}
				}).setNegativeButton("知道了", null).show();
	}

	/**
	 * 退出系统
	 * @param context
	 */
	public static void showExitSystem(final Context context) {
		Builder builder = new Builder(context);
		builder.setIcon(R.drawable.ic_launcher)//
				.setTitle(R.string.app_name)//
				.setMessage("是否退出应用").setPositiveButton("确定", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
						Editor edit = sp.edit();
						edit.putInt("isLogin", -1);
						edit.commit();
						LogUtil.info("退出系统islogin==="+sp.getInt("isLogin", -111));

						android.os.Process.killProcess(android.os.Process.myPid());
						//多个Activity——懒人听书：没有彻底退出应用
						//将所有用到的Activity都存起来，获取全部，干掉
						//BaseActivity——onCreated——放到容器中
					}
				})//
				.setNegativeButton("取消", null)//
				.show();
	}
	/**
	 * 显示错误提示框
	 *
	 * @param context
	 * @param msg
	 */
	public static void showErrorDialog(Context context, String msg) {
		new Builder(context)//
				.setIcon(R.drawable.ic_launcher)//
				.setTitle(R.string.app_name)//
				.setMessage(msg)//
				.setNegativeButton(context.getString(R.string.is_positive), null)//
				.show();
	}
	public static void showToast(Context context, String msg) {
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}

	public static void showToast(Context context, int msgResId) {
		Toast.makeText(context, msgResId, Toast.LENGTH_LONG).show();
	}
	//当测试阶段时true
	private static final boolean isShow = false;
	/**
	 * 测试用
	 * 在正式投入市场：删
	 * @param context
	 * @param msg
	 */
	public static void showToastTest(Context context, String msg) {
		if (isShow) {
			Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
		}
	}
}
