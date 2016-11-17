package com.honliv.honlivmall.activity;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;

import com.honliv.honlivmall.R;

public class AboutActivity extends BaseActivity {

	@Override
	public void initCreate() {
		// TODO Auto-generated method stub
		setContentView(R.layout.about_activity);
//		BottomManager.getInstanse().init(this);
		initView();
	}

	private void initView() {
//		findViewById(R.id.myCenter).setBackgroundResource(R.drawable.bar_mycenter_selected);//个人中心
//		((TextView) findViewById(R.id.tvmyCenter)).setTextColor(getResources().getColor(R.color.background_red));
	}
	
	public void goBack(View view){
		Intent intent = new Intent();
//		intent.setClass(getApplicationContext(), SettingActivity.class);
		startActivity(intent);
		overridePendingTransition(R.anim.tran_pre_in, R.anim.tran_pre_out);
		finish();
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {//处理返回键
		if(keyCode == KeyEvent.KEYCODE_BACK){//返回值被点击了
			//PromptManager.showExitSystem(this);
			Intent intent = new Intent();
//			intent.setClass(getApplicationContext(), SettingActivity.class);
			startActivity(intent);
			overridePendingTransition(R.anim.tran_pre_in, R.anim.tran_pre_out);
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}
	
	
}
