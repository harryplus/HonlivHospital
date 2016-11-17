package com.honliv.honlivmall.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.telephony.TelephonyManager;

import com.honliv.honlivmall.R;
import com.honliv.honlivmall.activity.FlashActivity;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

public class CommonUtils{
	private static final Class TAG = CommonUtils.class;
	private static Context mAppContext = null;
	
	private static Activity mMainActivity = null;
	
	private static String mUserID = null;
	
	private static String mDeviceName = null;
	
	private static String mOSVersion = null;
	
	private static String mHomePath = null;
	
	private static String mVersion = null;
	
	private static String mInstallSrc = null;
		
	private static String mMacAddr = null;
	
	public static String getSDCardPath(){
		File sdDir = null;
		boolean sdCardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);   //判断sd卡是否存在
		if(sdCardExist){
			sdDir = Environment.getExternalStorageDirectory();//获取跟目录
		}
		return sdDir.toString();
	}

	public static String getHomePath(){
		if(mHomePath == null){
			File sdDir = null;
			boolean sdCardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);   //判断sd卡是否存在
			if(sdCardExist){                               
				sdDir = Environment.getExternalStorageDirectory();//获取跟目录
				String temp = sdDir.toString() + "/shoujiduoduo/Ring/";
				LogUtil.info(TAG, TAG+": soft dir = " + temp);
				File f = new File(temp);
				boolean b1 = f.isDirectory();
				LogUtil.info(TAG, TAG+": b1 = " + Boolean.toString(b1));
				if(!b1){
					boolean b2 = f.mkdirs();
					LogUtil.info(TAG, TAG+": b2 = " + Boolean.toString(b2));
					if(b2)
						mHomePath = temp;
				}
				else{
					mHomePath = temp;
				}
			}   
		}
		if(mHomePath == null)
			LogUtil.info(TAG, TAG+": mHomePath = null");
		else
			LogUtil.info(TAG, TAG+": mHomePath = " + mHomePath);
		return mHomePath;
	}
	
	public static String getUserID(){
		if(mUserID == null){
			try{
				generateUserID();
			}catch(Exception e){
				
			}
			if(mUserID == null)
				mUserID = "DEFAULT_USER";
		}
		return mUserID;
	}
	
//	public static String getMacAddr(){
//		if(mMacAddr == null){
//			if(mAppContext != null){
//				WifiManager wm = (WifiManager)(mAppContext.getSystemService(Context.WIFI_SERVICE));
//				if(wm != null){
//					mMacAddr = wm.getConnectionInfo().getMacAddress();
//				}
//			}
//			if(mMacAddr == null){
//				mMacAddr = "MAC_ADDR_UNAVAILABLE";
//			}
//		}
//		return mMacAddr;
//	}
	
	public static void setContext(Context cx){
		mAppContext = cx;
	}
	
	public static void setMainActivity(Activity activity){
		mMainActivity = activity;
	}
	
	public static Activity getMainActivity(){
		return mMainActivity;
	}
	
	public static NetworkInfo getNetworkInfo(){
		if(mAppContext == null)
			return null;
		ConnectivityManager cm = (ConnectivityManager)mAppContext.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		return ni;
	}

	public static int getMobileNetworkType(){
		if(mAppContext == null)
			return -1;
		TelephonyManager cm = (TelephonyManager)mAppContext.getSystemService(Context.TELEPHONY_SERVICE);
		if (cm == null)
			return -1;
		return cm.getNetworkType();
	}
	
	public static void generateUserID(){
		if(mAppContext == null)
			return;
		TelephonyManager tm = (TelephonyManager) mAppContext.getSystemService(Context.TELEPHONY_SERVICE);
		if(tm != null){
			String imei = tm.getDeviceId();
			if(imei == null){
				String cpuid = getCPUSerial();
				if(!cpuid.equals("")){
					mUserID = cpuid;
				}
			}
			else{
				mUserID = imei;
			}
		}
	}
	
	
	/**
	  * 获取CPU序列号
	  * 
	  * @return CPU序列号(16位)
	  * 读取失败为"0000000000000000"
	  */
	private static String getCPUSerial() {
	        String str = null, strCPU = null, cpuAddress = null;
	        try {
	                //读取CPU信息
	                Process pp = Runtime.getRuntime().exec("cat /proc/cpuinfo");
	                InputStreamReader ir = new InputStreamReader(pp.getInputStream());
	                LineNumberReader input = new LineNumberReader(ir);
	                //查找CPU序列号
	                for (int i = 1; i < 100; i++) {
	                        str = input.readLine();
	                        if (str != null) {
	                                //查找到序列号所在行
	                                if (str.indexOf("Serial") > -1) {
	                                        //提取序列号
	                                        strCPU = str.substring(str.indexOf(":") + 1,
	                                                        str.length());
	                                        //去空格
	                                        cpuAddress = strCPU.trim();
	                                        break;
	                                }
	                        }else{
	                                //文件结尾
	                                break;
	                        }
	                }
	        } catch (IOException ex) {
	                //赋予默认值
	                ex.printStackTrace();
	                return null;
	        }
	        return cpuAddress;
	}	
	
	public static String getDeviceName(){
		if(mDeviceName == null){
			mDeviceName = android.os.Build.BRAND + ">" + android.os.Build.PRODUCT + ">" + android.os.Build.MODEL;
			if(mDeviceName == null)
				mDeviceName = "Unknown_Model";
		}
		return mDeviceName;
	}
	
	public static String getAndroidVersion(){
		if(mOSVersion == null){
			mOSVersion = android.os.Build.VERSION.SDK + android.os.Build.VERSION.RELEASE;
			if(mOSVersion == null)
				mOSVersion = "Unknown_Version";
		}
		return mOSVersion;
	}
	
	public static String getInstallSrc(){
		if(mInstallSrc == null){
			PackageInfo info;
			ApplicationInfo ai;
			String channel = "unknown";
			try {
				info = mAppContext.getPackageManager().getPackageInfo(mAppContext.getPackageName(), 0);
				ai = mAppContext.getPackageManager().getApplicationInfo(mAppContext.getPackageName(), PackageManager.GET_META_DATA);
				if(ai != null){
					Bundle bundle = ai.metaData;
					if(bundle != null){
						channel = bundle.getString("UMENG_CHANNEL");
						LogUtil.info(TAG, "channel = " + channel);
					}
				}
				mInstallSrc = mAppContext.getResources().getString(R.string.app_version_prefix)
								+ info.versionName 
								+ "_"
								+channel
								+ mAppContext.getResources().getString(R.string.install_source_suffix);
			} catch (NameNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return mAppContext.getResources().getString(R.string.app_version_prefix)
				+ "0.0.0.0" 
				+ mAppContext.getResources().getString(R.string.install_source_suffix);
			}			
		}
		LogUtil.info(TAG, "mInstallSrc = " + mInstallSrc);
		return mInstallSrc;
	}
	
	public static String getPackageName(){
		return mAppContext.getPackageName();
	}
	
	public static String getVersion(){
		
		if(mVersion == null){
			PackageInfo info;
			try {
				info = mAppContext.getPackageManager().getPackageInfo(mAppContext.getPackageName(), 0);
				mVersion = mAppContext.getResources().getString(R.string.app_version_prefix) + info.versionName;
			} catch (NameNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return mAppContext.getResources().getString(R.string.app_version_prefix) + "0.0.0.0";
			}			
		}
		return mVersion;
	}	
	
	public static String getEnglishName(){
		return mAppContext.getResources().getString(R.string.english_name);
	}
	
	//启动安装应用
	public static void installPackage(String apkPath){
		Uri uri = Uri.fromFile( new File(apkPath));
		Intent intent = new Intent(Intent.ACTION_VIEW );
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setDataAndType(uri, "application/vnd.android.package-archive" ); 
		mAppContext.startActivity(intent);
	}
	
	
	//检测输入的路径是否是目录，如果不是，则创建目录
	public static boolean isDir(String path){
		File fPath = new File(path);
		if(fPath == null){
			return false;
		}
		return fPath.isDirectory() || fPath.mkdirs();
	}
	
	/**
	 * 为当前应用添加桌面快捷方式
	 * 
	 * @param cx
	 *            快捷方式名称
	 */
	public static void addShortcut(Context cx) {
	    Intent shortcut = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");

	    Intent shortcutIntent = new Intent(Intent.ACTION_MAIN);
	    shortcutIntent.addCategory(Intent.CATEGORY_LAUNCHER);
	    shortcutIntent.setClass(cx, FlashActivity.class);
	    
	    shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
	    // 获取当前应用名称
	    String title = null;
	    try {
	        final PackageManager pm = cx.getPackageManager();
	        title = pm.getApplicationLabel(
	                	pm.getApplicationInfo(cx.getPackageName(),
	                    PackageManager.GET_META_DATA)
	                   ).toString();
	    } catch (Exception e) {
	    	return;
	    }
	    // 快捷方式名称
	    shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, title);
	    // 不允许重复创建（不一定有效）
	    shortcut.putExtra("duplicate", false);
	    // 快捷方式的图标
	    Parcelable iconResource = Intent.ShortcutIconResource.fromContext(cx, R.drawable.android_ms_icon);
	    shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, iconResource);
	    cx.sendBroadcast(shortcut);
	}
	
	/**
	 * 删除当前应用的桌面快捷方式
	 * 
	 * @param cx
	 */
	public static void delShortcut(Context cx) {
	    Intent shortcut = new Intent(
	            "com.android.launcher.action.UNINSTALL_SHORTCUT");
	    // 获取当前应用名称
	    String title = null;
	    try {
	        final PackageManager pm = cx.getPackageManager();
	        title = pm.getApplicationLabel(
	                pm.getApplicationInfo(cx.getPackageName(),
	                        PackageManager.GET_META_DATA)).toString();
	    } catch (Exception e) {
	    }
	    // 快捷方式名称
	    shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, title);
	    Intent shortcutIntent = cx.getPackageManager().getLaunchIntentForPackage(cx.getPackageName());
	    shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
	    cx.sendBroadcast(shortcut);
	}
	
	/**
	 * 判断桌面是否已添加快捷方式
	 * 
	 * @param cx
	 *            快捷方式名称
	 * @return
	 */
	public static boolean hasShortcut(Context cx) {
	    boolean result = false;
	    // 获取当前应用名称
	    String title = null;
	    try {
	       /* final PackageManager pm = cx.getPackageManager();
	        title = pm.getApplicationLabel(
	                pm.getApplicationInfo(cx.getPackageName(),
	                        PackageManager.GET_META_DATA)).toString();*/
	    	 title = cx.getResources().getString(R.string.app_name);
	    } catch (Exception e) {
	    }

	    final String uriStr;
	    if (android.os.Build.VERSION.SDK_INT < 8) {
	        uriStr = "content://com.android.launcher.settings/favorites?notify=true";
	    } else {
	        uriStr = "content://com.android.launcher2.settings/favorites?notify=true";
	    }
	    final Uri CONTENT_URI = Uri.parse(uriStr);
	    final Cursor c = cx.getContentResolver().query(CONTENT_URI, null,
	            "title=?", new String[] { title }, null);
	    if (c != null && c.getCount() > 0) {
	        result = true;
	    }
	    return result;
	}
	
	public static String getXmlNodeValue(NamedNodeMap map, String nodeName){
		Node node = map.getNamedItem(nodeName);
		if(node == null)
			return "";
		String nodeValue = node.getNodeValue();
		if(nodeValue == null)
			return "";
		return nodeValue;
	}
	
	public static AssetFileDescriptor getAssetsFileDescriptor(String str){
		if(mAppContext == null)
			return null;
		AssetFileDescriptor afd = null;
		try {
			afd = mAppContext.getResources().getAssets().openFd("silence.aac");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		if(afd != null){
			return afd;
		}
		return null;
	}
	
	
    /** 
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素) 
     */  
    public static int dip2px(float dpValue) {  
    	if(mAppContext == null){
    		return -1;
    	}
        final float scale = mAppContext.getResources().getDisplayMetrics().density;  
        return (int) (dpValue * scale + 0.5f);  
    }  
  
    /** 
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp 
     */  
    public static int px2dip(float pxValue) {
    	if(mAppContext == null){
    		return -1;
    	}
        final float scale = mAppContext.getResources().getDisplayMetrics().density;  
        return (int) (pxValue / scale + 0.5f);  
    }  
}

