package com.honliv.honlivmall.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.baidu.mobstat.StatService;
import com.honliv.honlivmall.GloableParams;
import com.honliv.honlivmall.R;
import com.honliv.honlivmall.bean.Product;
import com.honliv.honlivmall.util.NetUtil;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 所有activity的基类，一些公共的事情就在这做
 * @author Administrator
 *
 */
public abstract class BaseActivity extends Activity {

	public SharedPreferences sp;
	public TextView shopCarNumTV;
	public Bundle savedInstanceState;

	Intent intent;
	public BaseActivity() {
		super();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.savedInstanceState = savedInstanceState;
		intent = new Intent();
		sp = getSharedPreferences("config", MODE_PRIVATE);
		
		initCreate();
	}
	
	/**
	 * 1.设置View.setContentView(R.layout.activity_);
	 * 2.BottomManager.getInstanse().init(this);
	 */
	protected abstract void initCreate();
	
	/**
	 * 初始化底部购物车商品
	 */
	protected  void initShopCarNumber(){
//		shopCarNumTV = (TextView)findViewById(R.id.textShopCarNum);
		DbUtils db = DbUtils.create(this);
		List<Product> productList=null;
		try {
			if(GloableParams.USERID>0){
				productList = db.findAll(Selector.from(Product.class)
						.where("userId","=", GloableParams.USERID));
			}else{
				productList = db.findAll(Selector.from(Product.class)
						.where("userId","=",-100));
			}
		} catch (DbException e) {
			e.printStackTrace();
		}
		if(productList!=null){
			if(productList.size() > 0){
				int num = 0;
				for (int i = 0; i < productList.size(); i++) {
					num+=productList.get(i).getNumber();
				}
				shopCarNumTV.setText(""+num);
				shopCarNumTV.setVisibility(View.VISIBLE);
			}else{
				shopCarNumTV.setVisibility(View.INVISIBLE);
			}
		}else{
			shopCarNumTV.setVisibility(View.INVISIBLE);
		}
	}
	
	/**
	 * 初始化底部购物车商品
	 */
	protected  void initShopCarNumber(int number){
		shopCarNumTV.setText(""+number);
		shopCarNumTV.setVisibility(View.VISIBLE);
	}
	
	/**
	 * String 返回值类型，可以自己修改
	 * @author Administrator
	 *
	 * @param <Params>
	 */
	protected abstract class MyHttpTask<Params> extends AsyncTask<Params, Void, Object> {
		 public final AsyncTask<Params, Void, Object> executeProxy(Params... params) {
			 if(NetUtil.checkNet(BaseActivity.this)){//在每次异步前先判断网络
					return execute(params);
				}else{
//					PromptManager.showNoNetWork(BaseActivity.this);
					return null;
				}
		 }
	}
	
	/**
	 *动画进入下一个界面
	 * @param clazz 下一个界面activity.class
	 */
	 protected void animNextActivity(Class<? extends Activity> clazz){
		intent.setClass(getApplicationContext(),clazz);
		startActivity(intent);
		overridePendingTransition(R.anim.tran_next_in, R.anim.tran_next_out);//下个
	}
	
	/**
	 *动画进入上一个界面
	 * @param clazz 上一个界面activity.class
	 */
	 protected void animPreActivity(Class<? extends Activity> clazz){
		intent.setClass(getApplicationContext(), clazz);
		startActivity(intent);
		overridePendingTransition(R.anim.tran_pre_in, R.anim.tran_pre_out);// 上个
	}
	 
	@Override
    public void onOptionsMenuClosed(Menu menu) {
       // Toast.makeText(this, "选项菜单关闭了", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
       /* Toast.makeText(this,
                "选项菜单显示之前onPrepareOptionsMenu方法会被调用，你可以用此方法来根据打当时的情况调整菜单",
                Toast.LENGTH_LONG).show();*/
        // 如果返回false，此方法就把用户点击menu的动作给消费了，onCreateOptionsMenu方法将不会被调用
        return true;
    }
    /*****************创建MENU菜单项************************/
    
    /**
	 * 清理所有的缓存
	 */
	public void cleanCacheAll(){
		//利用系统的一个特性，当申请的内存超过现有空闲内存时，系统会自动清理掉缓存
		
//		PromptManager.showToast(getApplicationContext(), "正在清理中...");
		
		PackageManager pm = getPackageManager();
		Method[] methods = PackageManager.class.getMethods();
		try {
			for(Method method : methods){
				if("freeStorageAndNotify".equals(method.getName())){
//					method.invoke(pm, Integer.MAX_VALUE,new IPackageDataObserver.Stub() {
//						public void onRemoveCompleted(String packageName, boolean succeeded)
//								throws RemoteException {
//							cacheHandler.sendEmptyMessage(1);
//						}
//					});
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Handler cacheHandler = new Handler(){
		public void handleMessage(Message msg) {
			if(1==msg.what){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
//				PromptManager.showToast(getApplicationContext(), "恭喜你，缓存已经全部清除，手机达到最佳状态");
			}
		}
	};
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {//处理返回键
		if(keyCode == KeyEvent.KEYCODE_BACK){//返回值被点击了
			finish();
			overridePendingTransition(R.anim.tran_pre_in, R.anim.tran_pre_out);
		}
		return super.onKeyDown(keyCode, event);
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		StatService.onResume(this);
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		StatService.onPause(this);
	}
}
