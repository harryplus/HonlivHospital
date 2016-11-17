package com.honliv.honlivmall.util;

import android.app.Activity;
import android.app.Application;

import java.util.LinkedList;
import java.util.List;


/**
 * 退出所有activity,事先要加进来
 * @author Administrator
 *
 */
public class ExitManager extends Application {
	private List<Activity> activityList=new LinkedList<Activity>();
	   private static ExitManager instance;
	   
	   private ExitManager(){
		   
	   }
	   public static ExitManager getInstance(){
		   if (instance==null) {
			instance=new ExitManager();
		}
		   return instance;
	   }
	   public void addActivity(Activity activity){
		   activityList.add(activity);
	   }
	   public void exit(){
		   for(Activity activity:activityList){
			     if(!activity.isFinishing()){
			    	  activity.finish();
			     }
		   }
		   int id=android.os.Process.myPid();
		   if(id!=0){
			   android.os.Process
				.killProcess(id);
		   }
	   }
}
