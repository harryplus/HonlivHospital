package com.honliv.honlivmall.activity;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.SharedPreferences.Editor;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.honliv.honlivmall.GloableParams;
import com.honliv.honlivmall.R;
import com.honliv.honlivmall.adapter.MyAdapter;
import com.honliv.honlivmall.bean.AddressInfo;
import com.honliv.honlivmall.util.PromptManager;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * 新增地址
 * @author Administrator
 *
 */
public class AddAddressActivity extends BaseActivity {

	private EditText addressNameET;
	private EditText addressPhoneET;
	private TextView addaddresslineTV;
	private EditText addressDetailET;
	private EditText addressZipCodeET;
	private AddressInfo addressInfo;
	private AddressInfo addressPreInfo;//上一个界面传递过来的地址
	
	private   String[] lineitems;//线路
	private LinearLayout addAddressBottomLL;
	private TextView addressMessageTV;
	private TextView editAddressTitle;
	
	private List<AddressInfo> lineList; //线路列表
	
	/**********以下三级联动*************/
	private Spinner spinner1 = null;
	private Spinner spinner2=null;
	private Spinner spinner3=null;
	private String province=null;
	private String city=null;
	private String district=null;
	
	private String saveAddress = "";//湖南省湘西土家族苗族自治州 看看是不是有地址
	/**********以上三级联动*************/
	
	@Override
	public void initCreate() {
		setContentView(R.layout.address_edit);
//		BottomManager.getInstanse().init(this);
		
		initView();
		initData();
		initSpinner1();
	}
	protected void initLineDate() {
		Editor editor = sp.edit();
		editor.putInt("linesupplierId", 0);
		editor.commit();
		
		lineitems = new String[lineList.size()];
		
		for (int i = 0; i < lineList.size(); i++) {
			lineitems[i] = lineList.get(i).getName();
		}
	}

	private void initData() {
		addressPreInfo = (AddressInfo)this.getIntent().getSerializableExtra("addressInfo");
		
		if(addressPreInfo != null){//编辑地址
			addressNameET.setText(addressPreInfo.getName()+"");
			addressPhoneET.setText(addressPreInfo.getPhone()+"");
			/*String addressAreaStr = addressPreInfo.getAddressArea();
			if(addressAreaStr.contains("北京市")){
				int index = addressAreaStr.indexOf("北京市");
				addressAreaStr = addressAreaStr.substring(index+3);
			}*/
			addressDetailET.setText(""+addressPreInfo.getAreaDetail()+"");
			seleteLineLL.setVisibility(View.GONE);
			
			if(addressPreInfo.getAddressArea()!=null){//从上个界面传递过来的省市区
				saveAddress = addressPreInfo.getAddressArea();
			}else{
				getServiceAddressName();
			}
		}else{
			addAddressBottomLL.setVisibility(View.GONE);
			addressMessageTV.setVisibility(View.GONE);
			seleteLineLL.setVisibility(View.GONE);
			editAddressTitle.setText("新增地址");
		}
	}
	private void initView() {
//		findViewById(R.id.myCenter).setBackgroundResource(R.drawable.bar_mycenter_selected);//个人中心
//		((TextView) findViewById(R.id.tvmyCenter)).setTextColor(getResources().getColor(R.color.background_red));
		addressNameET = (EditText) findViewById(R.id.add_address_name);
		addressPhoneET = (EditText) findViewById(R.id.add_address_mobile);
		addressDetailET = (EditText) findViewById(R.id.add_address_detail);
		addressZipCodeET = (EditText) findViewById(R.id.add_address_zipcode);
		addAddressBottomLL = (LinearLayout) findViewById(R.id.add_address_bottom_LL);
		addressMessageTV = (TextView) findViewById(R.id.add_address_message);
		editAddressTitle = (TextView) findViewById(R.id.edit_address_title);
		
		addaddresslineTV = (TextView) findViewById(R.id.add_address_line);
		seleteLineLL = (LinearLayout) findViewById(R.id.seleteLine_LL);
	
		spinner1=(Spinner)findViewById(R.id.spinner1);
        spinner2=(Spinner)findViewById(R.id.spinner2);
        spinner3=(Spinner)findViewById(R.id.spinner3);
		spinner1.setPrompt("省");
		spinner2.setPrompt("城市");		
		spinner3.setPrompt("地区");
	
	}
	private void getServiceAddressName() {
		new MyHttpTask<Integer>(){
			@Override
			protected void onPreExecute() {
				PromptManager.showCommonProgressDialog(AddAddressActivity.this);
				super.onPreExecute();
			}
			@Override
			protected Object doInBackground(Integer... params) {
//				AddressEngine engine = BeanFactory.getImpl(AddressEngine.class);
//				return engine.getServiceAddressSelectName(addressPreInfo.getRegionId());
				return null;
			}
			@Override
			protected void onPostExecute(Object result) {
				// TODO Auto-generated method stub
				super.onPostExecute(result);
				PromptManager.closeProgressDialog();
				if(result != null){
					saveAddress = (String) result;
				}else{
					PromptManager.showToast(AddAddressActivity.this, "服务器忙，请稍后重试！！！");
					return;
				}
			}
		}.executeProxy(0);
	}
	
	/**
	 * 新增地址时要选择线路
	 * @param view
	 */
	public void seleteLine(View view){
	
		Builder builder = new Builder(this);
		builder.setTitle("请选择线路");
		int which = sp.getInt("linesupplierId", 0);
		builder.setSingleChoiceItems(lineitems, which,
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Editor editor = sp.edit();
						editor.putInt("linesupplierId", lineList.get(which).getId());
						editor.commit();
						dialog.dismiss();
						addaddresslineTV.setText(lineitems[which]);
					}
				});
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				//s.equals("s");
			}
		});
		builder.show();
	}
	/**
	 * 提交保存,新增地址
	 * @param view
	 */
	public void addressSave(View view){
		String addressName = addressNameET.getText().toString().trim();
		if (StringUtils.isBlank(addressName)) {
			PromptManager.showToast(this, "收货人名字不能为空");

			return;
		} else if (addressName.length() < 2) {
			PromptManager.showToast(this, "名字字数过少");
			return;
		}
		
		String mobilePhoneStr = addressPhoneET.getText().toString().trim();
		if (StringUtils.isBlank(mobilePhoneStr)) {
			PromptManager.showToast(this, "手机号不能为空");
			return;
		}
		// 这里要加上一个匹配手机号的格式。。String regex = "1[3458][0-9]{9}";
		String regPhone = "^1([38][0-9]|4[57]|5[^4]|7[0678]|)\\d{8}$"; // 定义正则

		boolean flagPhone = mobilePhoneStr.matches(regPhone);
		if (!flagPhone) {
			PromptManager.showToast(this, "请填写正确的手机号");
			return;
		}
		int regionId = sp.getInt("regionId", 0);
		if (regionId == 0) {
			regionId = addressPreInfo.getRegionId();
		}
		if (regionId == 0) {
			PromptManager.showToast(this, "请重新选择区域,再提交");
			return;
		}
		
		String addressDetail= addressDetailET.getText().toString().trim();
		if (StringUtils.isBlank(addressDetail)) {
			PromptManager.showToast(this, "详细地址不能为空");
			return;
		} else if (addressDetail.length() < 6) {
			PromptManager.showToast(this, "详细地址字数过少");
			return;
		}

		int userId = GloableParams.USERID;//sp.getInt("isLogin", -1);
		if(userId < 0){
			PromptManager.showToast(this, "登录状态错误，请重新登录！");
			return;
		}
		addressInfo = new AddressInfo();
		if(addressPreInfo!=null){
			addressInfo.setId(addressPreInfo.getId());
		}
		
		addressInfo.setUserId(userId);
		addressInfo.setName(addressName);
		addressInfo.setPhone(mobilePhoneStr);
		addressInfo.setRegionId(regionId);//regionId
		addressInfo.setAreaDetail(addressDetail);
		getServiceProductList(true);
		isAddressSave = false;
	
	}
	
	private void getServiceProductList(final boolean isSave){
		new MyHttpTask<Integer>() {
			@Override
			protected void onPreExecute() {
				PromptManager.showCommonProgressDialog(AddAddressActivity.this);
				super.onPreExecute();
			}
			@Override
			protected Object doInBackground(Integer... params) {
//				AddressEngine engine = BeanFactory.getImpl(AddressEngine.class);
//				if(addressPreInfo!=null){
//					//保存地址
//					return engine.getServiceSaveAddress(addressInfo);
//				}else{
//					//新增加地址
//					return engine.getServiceAddAddress(addressInfo);
//				}
				return null;
			}
			
			protected void onPostExecute(Object result){
				super.onPostExecute(result);
				PromptManager.closeProgressDialog();
				if(result != null){
					List<AddressInfo> addressList  = ((List<AddressInfo>)result);
				/*	if(isUpload){
						//加载更多
						if(productlist.size()==0){
							PromptManager.showMyToast(ProductListActivity.this, "暂无更多内容~");
						}else{
							currentProductList.addAll(productlist);
							adapter.notifyDataSetChanged();
						}
						isUpload = false;
					}else{*/
						if(addressList.size()==0){
							overridePendingTransition(R.anim.tran_pre_in, R.anim.tran_pre_out);
							
						}else{
							//有返回东西 ,解析出来数据，设置给屏幕
							/*Intent data=new Intent();  
							data.putExtra("addressList",(Serializable)addressList);
							
							LogUtil.info("返回前的打印==111==="+addressList);
						     //请求代码可以自己设置，这里设置成20  
						     setResult(20, data); */ 
							
						     overridePendingTransition(R.anim.tran_pre_in, R.anim.tran_pre_out);
						}
						if(isSave)
							finish();
				}else{
					PromptManager.showToast(AddAddressActivity.this, "服务器忙，请稍后重试！！！");
				}
			}
		}.executeProxy(0);
	}
	private boolean isAddressSave = true;//是否保存执行成功了
	private LinearLayout seleteLineLL;
	/**
	 * 设为默认地址。
	 * @param view
	 */
	public void addressDefault(View view){
		
		addressSave(null);
		if(isAddressSave){
			return;
		}
		if(addressPreInfo==null ||addressPreInfo.getId()<1){
			return;
		}
		new MyHttpTask<Integer>() {
			@Override
			protected void onPreExecute() {
				PromptManager.showCommonProgressDialog(AddAddressActivity.this);
				super.onPreExecute();
			}
			@Override
			protected Object doInBackground(Integer... params) {
//				AddressEngine engine = BeanFactory.getImpl(AddressEngine.class);
//				return engine.getServiceSetDefaultAddress(GloableParams.USERID, addressPreInfo.getId());
				return null;
			}
			protected void onPostExecute(Object result){
				super.onPostExecute(result);
				PromptManager.closeProgressDialog();
				if(result != null){
					Boolean isSuccess  = (Boolean)result;
						if(isSuccess){
							PromptManager.showToast(AddAddressActivity.this, "设置成功");
							GloableParams.SETDEFAULTADDRESS = true;
							overridePendingTransition(R.anim.tran_pre_in, R.anim.tran_pre_out);
							finish();
						}else{
							PromptManager.showToast(AddAddressActivity.this, "服务器忙，请稍后重试！！！");
						}
				}else{
					PromptManager.showToast(AddAddressActivity.this, "服务器忙，请稍后重试！！！");
				}
			}
		}.executeProxy(0);
	}
	/**
	 * 删除地址
	 * @param view
	 */
	public void deleteAddress(View view){
		if(addressPreInfo==null){
			return;
		}
		if(addressPreInfo.getId()<1){
			return;
		}
		new MyHttpTask<Integer>() {
			@Override
			protected void onPreExecute() {
				PromptManager.showCommonProgressDialog(AddAddressActivity.this);
				super.onPreExecute();
			}
			@Override
			protected Object doInBackground(Integer... params) {
//				AddressEngine engine = BeanFactory.getImpl(AddressEngine.class);
//				return engine.getServiceDeleteAddress(GloableParams.USERID, addressPreInfo.getId());
				return null;
			}
			protected void onPostExecute(Object result){
				super.onPostExecute(result);
				PromptManager.closeProgressDialog();
				if(result != null){
					Boolean isSuccess  = (Boolean)result;
						if(isSuccess){
							PromptManager.showToast(AddAddressActivity.this, "删除成功");
							overridePendingTransition(R.anim.tran_pre_in, R.anim.tran_pre_out);
							finish();
						}else{
							PromptManager.showToast(AddAddressActivity.this, "服务器忙，请稍后重试！！！");
						}
				}else{
					PromptManager.showToast(AddAddressActivity.this, "服务器忙，请稍后重试！！！");
				}
			}
		}.executeProxy(0);
	}
	/**
	 * 返回
	 */
	public void goBack(View view){
		finish();
		overridePendingTransition(R.anim.tran_pre_in, R.anim.tran_pre_out);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {//处理返回键
		if(keyCode == KeyEvent.KEYCODE_BACK){//返回值被点击了
			overridePendingTransition(R.anim.tran_pre_in, R.anim.tran_pre_out);
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}
	
	 public void initSpinner1(){
			new MyHttpTask<Integer>() {
				@Override
				protected void onPreExecute() {
					PromptManager.showCommonProgressDialog(AddAddressActivity.this);
					super.onPreExecute();
				}
				@Override
				protected Object doInBackground(Integer... params) {
//					AddressEngine engine = BeanFactory.getImpl(AddressEngine.class);
//					return engine.getServiceAddressSelectList(0);
					return null;
				}
				protected void onPostExecute(Object result){
					super.onPostExecute(result);
					PromptManager.closeProgressDialog();
					if(result != null){
						List<AddressInfo> addressList  = ((List<AddressInfo>)result);
						if(addressList.size()==0){
							PromptManager.showToast(AddAddressActivity.this, "服务器忙，请稍后重试！！！");
						}else{
							initSpinnerDate1(addressList);
						}
					}else{
						PromptManager.showToast(AddAddressActivity.this, "服务器忙，请稍后重试！！！");
					}
				}
			}.executeProxy(0);
		}
	 protected void initSpinnerDate1(List<AddressInfo> addressList) {
		 MyAdapter myAdapter = new MyAdapter(this,addressList);
		 spinner1.setAdapter(myAdapter);
		 
		 spinner1.setOnItemSelectedListener(new SpinnerOnSelectedListener1());
		 if(addressPreInfo!=null){
			 int tempPosition = 0;
			 
			 for (int i = 0; i < addressList.size(); i++) {
				 if(saveAddress.contains(addressList.get(i).getName())){
					 tempPosition = i;
				 }
			 }
			 spinner1.setSelection(tempPosition, true);
		 }
	}

	public void initSpinner2(final int pcode){
		new MyHttpTask<Integer>() {
			@Override
			protected void onPreExecute() {
//				PromptManager.showCommonProgressDialog(AddAddressActivity.this);
				super.onPreExecute();
			}
			@Override
			protected Object doInBackground(Integer... params) {
//				AddressEngine engine = BeanFactory.getImpl(AddressEngine.class);
//				return engine.getServiceAddressSelectList(pcode);
				return null;
			}
			protected void onPostExecute(Object result){
				super.onPostExecute(result);
//				PromptManager.closeProgressDialog();
				if(result != null){
					List<AddressInfo> addressList  = ((List<AddressInfo>)result);
					if(addressList.size()==0){
						PromptManager.showToast(AddAddressActivity.this, "服务器忙，请稍后重试！！！");
					}else{
						initSpinnerDate2(addressList.get(0).getChildlist());
					}
				}else{
					PromptManager.showToast(AddAddressActivity.this, "服务器忙，请稍后重试！！！");
				}
			}
		}.executeProxy(pcode);
	}
	 protected void initSpinnerDate2(List<AddressInfo> addressList) {
		 MyAdapter myAdapter = new MyAdapter(this,addressList);
		 spinner2.setAdapter(myAdapter);
		 spinner2.setOnItemSelectedListener(new SpinnerOnSelectedListener2());
		 
		 if(addressPreInfo!=null){
			 int tempPosition = 0;
			 
			 for (int i = 0; i < addressList.size(); i++) {
				 if(saveAddress.contains(addressList.get(i).getName())){
					 tempPosition = i;
				 }
			 }
			 spinner2.setSelection(tempPosition, true);
		 }
		 
	 }
	    public void initSpinner3(AddressInfo addressInfo, final int pcode){
	    	/*new MyHttpTask<Integer>() {
				@Override
				protected void onPreExecute() {
//					PromptManager.showCommonProgressDialog(AddAddressActivity.this);
					super.onPreExecute();
				}
				@Override
				protected Object doInBackground(Integer... params) {
					AddressEngine engine = BeanFactory.getImpl(AddressEngine.class);
					return engine.getServiceAddressSelectList(pcode);
				}
				protected void onPostExecute(Object result){
					super.onPostExecute(result);
//					PromptManager.closeProgressDialog();
					if(result != null){
						List<AddressInfo> addressList  = ((List<AddressInfo>)result);
						if(addressList.size()==0){
							PromptManager.showToast(AddAddressActivity.this, "服务器忙，请稍后重试！！！");
						}else{
							initSpinnerDate3(addressList);
						}
					}else{
						PromptManager.showToast(AddAddressActivity.this, "服务器忙，请稍后重试！！！");
					}
				}
			}.executeProxy(pcode);*/
	    	
	    	
	    	if(addressInfo!=null && addressInfo.getChildlist()!=null &&addressInfo.getChildlist().size() >0){
	    		initSpinnerDate3(addressInfo.getChildlist());
	    		spinner3.setVisibility(View.VISIBLE);
	    	}else{
	    		spinner3.setVisibility(View.GONE);
//	    		PromptManager.showToast(this, "没有选项");
	    		
	    		district = addressInfo.getName();
	    		int regId = addressInfo.getId();
				
				Editor editor = sp.edit();
				editor.putInt("regionId", regId);
				editor.commit();
	    		
	    	}
		}
	    
		protected void initSpinnerDate3(List<AddressInfo> addressList) {
			MyAdapter myAdapter = new MyAdapter(this,addressList);
			spinner3.setAdapter(myAdapter);
			spinner3.setOnItemSelectedListener(new SpinnerOnSelectedListener3());
			
			 if(addressPreInfo!=null){
				 int tempPosition = 0;
				 
				 for (int i = 0; i < addressList.size(); i++) {
					 if(saveAddress.contains(addressList.get(i).getName())){
						 tempPosition = i;
					 }
				 }
				 spinner3.setSelection(tempPosition, true);
			 }
		}

		class SpinnerOnSelectedListener1 implements OnItemSelectedListener {
			public void onItemSelected(AdapterView<?> adapterView, View view, int position,
									   long id) {
				province=((AddressInfo)adapterView.getItemAtPosition(position)).getName();
				int pcode =((AddressInfo) adapterView.getItemAtPosition(position)).getId();
				
				initSpinner2(pcode);
//				initSpinner3((AddressInfo)adapterView.getItemAtPosition(position),pcode);
			}
			public void onNothingSelected(AdapterView<?> adapterView) {
				// TODO Auto-generated method stub
			}		
		}
		class SpinnerOnSelectedListener2 implements OnItemSelectedListener {
			
			public void onItemSelected(AdapterView<?> adapterView, View view, int position,
									   long id) {
				city=((AddressInfo) adapterView.getItemAtPosition(position)).getName();
				int pcode =((AddressInfo) adapterView.getItemAtPosition(position)).getId();

				initSpinner3(((AddressInfo) adapterView.getItemAtPosition(position)),position);
			}
			public void onNothingSelected(AdapterView<?> adapterView) {
				// TODO Auto-generated method stub
			}		
		}
		
		class SpinnerOnSelectedListener3 implements OnItemSelectedListener {
			
			public void onItemSelected(AdapterView<?> adapterView, View view, int position,
									   long id) {
				district=((AddressInfo) adapterView.getItemAtPosition(position)).getName();
				
				int regId = ((AddressInfo) adapterView.getItemAtPosition(position)).getId();
				
				Editor editor = sp.edit();
				editor.putInt("regionId", regId);
				editor.commit();
				
//				PromptManager.showToastTest(AddAddressActivity.this, province+" "+city+" "+district+",regId="+regId, Toast.LENGTH_LONG).show();
			}
			public void onNothingSelected(AdapterView<?> adapterView) {
				// TODO Auto-generated method stub
			}		
		}
}
