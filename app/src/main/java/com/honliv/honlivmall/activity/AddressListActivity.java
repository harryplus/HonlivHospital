package com.honliv.honlivmall.activity;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.honliv.honlivmall.R;
import com.honliv.honlivmall.bean.AddressInfo;
import com.honliv.honlivmall.util.PromptManager;

import java.util.List;

/**
 * 地址列表
 * @author Administrator
 *
 */
public class AddressListActivity extends BaseActivity {
	List<AddressInfo> addressList;
	private ListView addressLV;
	private TextView nullProductTV;
	private AddressAdapter addressAdapter;
	private AddressInfo addressInfo;
	private TextView addressListAddTV;
	@Override
	protected void initCreate() {
		setContentView(R.layout.address_manage_activity);
//		BottomManager.getInstanse().init(this);
		
		initView();
		getServiceProductList();
	}

	private void initView() {
//		findViewById(R.id.myCenter).setBackgroundResource(R.drawable.bar_mycenter_selected);//个人中心
//		((TextView) findViewById(R.id.tvmyCenter)).setTextColor(getResources().getColor(R.color.background_red));
		addressLV = (ListView)findViewById(R.id.address_manage_list);
		nullProductTV = (TextView)findViewById(R.id.nullProductTV);
		addressListAddTV = (TextView)findViewById(R.id.address_list_add_text);
		TextView addressmassagetitle = (TextView)findViewById(R.id.addressmassagetitle);
		addressmassagetitle.setText("地址列表");
	}
	
	private void getServiceProductList(){
		new MyHttpTask<Integer>() {
			@Override
			protected void onPreExecute() {
				PromptManager.showCommonProgressDialog(AddressListActivity.this);
				super.onPreExecute();
			}
			@Override
			protected Object doInBackground(Integer... params) {
//				AddressEngine engine = BeanFactory.getImpl(AddressEngine.class);
//				return engine.getServiceAddressList(GloableParams.USERID, 1, 50);
				return null;
			}
			protected void onPostExecute(Object result){
				super.onPostExecute(result);
				PromptManager.closeProgressDialog();
				if(result != null){
					 addressList  = ((List<AddressInfo>)result);
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
							nullProductTV.setVisibility(View.VISIBLE);
							addressLV.setVisibility(View.GONE);
//							addressListAddTV.setVisibility(View.VISIBLE);
						}else{
							nullProductTV.setVisibility(View.GONE);
							addressLV.setVisibility(View.VISIBLE);
//							addressListAddTV.setVisibility(View.GONE);
							initData();
						}
			//		}
				}else{
					PromptManager.showToast(AddressListActivity.this, "服务器忙，请稍后重试！！！");
				}
			}
		}.executeProxy(0);
	}
	protected void initData() {
		addressAdapter = new AddressAdapter();
		addressLV.setAdapter(addressAdapter);
		addressLV.setOnItemLongClickListener(new AddressItemLongClickListener());
		addressLV.setOnItemClickListener(new AddressItemClickListener());
	}
	
	private class AddressAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return addressList.size();
		}
		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}
		@Override
		public long getItemId(int position) {
			return position;
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = null;
			ViewHolder holder = null;
			if(convertView ==null){
				holder = new ViewHolder();
				view = View.inflate(getApplicationContext(), R.layout.address_manage_listitem, null);
				/*view = LayoutInflater.from(getApplicationContext()).inflate(
						R.layout.product_list_item, null);*/
				holder.addressNameTV = (TextView) view.findViewById(R.id.addressnameTV);
				holder.addressPhoneTV = (TextView) view.findViewById(R.id.addressphoneTV);
				holder.addressDetailTV = (TextView) view.findViewById(R.id.addressdetailTV);
				holder.ProvinceTV = (TextView) view.findViewById(R.id.addressProvinceTV);
				
				view.setTag(holder);
			}else{
				view = convertView;
				holder = (ViewHolder)view.getTag();
			}
			addressInfo = addressList.get(position);
			holder.addressNameTV.setText("收货人:  "+addressInfo.getName()+"");
			holder.addressPhoneTV.setText("手机号:  "+addressInfo.getPhone()+"");
			/*holder.ProvinceTV.setText("北京市:  "+addressInfo.getAreaDetail().split(",")[0]+"");
			if(addressInfo.getAreaDetail().split(",").length>1){
				holder.addressDetailTV.setText("详细地址:  "+addressInfo.getAreaDetail().split(",")[1]+"");
			}else{
				holder.addressDetailTV.setText("详细地址:  "+addressInfo.getAreaDetail()+"");
			}*/
			holder.ProvinceTV.setText("所在地区:  "+addressInfo.getAddressArea()+"");
			holder.addressDetailTV.setText("详细地址:  "+addressInfo.getAreaDetail()+"");
			return view;
		}
	}
	static class ViewHolder {
		TextView addressNameTV;//收货人姓名
		TextView addressPhoneTV;//收货人手机号
		TextView addressDetailTV;//收货人详细地址
		TextView ProvinceTV;  //省市区
	}
	private class AddressItemClickListener implements OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
								long id) {
			/*String tempStr = addressInfo.getAreaDetail().split(",")[0]+"";
			int which = 0;
			for (int i = 0; i < proviceitems.length; i++) {
				if(proviceitems[i].equals(tempStr)){
					which=i;
				}
			}
			Editor editor = sp.edit();
			editor.putInt("regionId", proviceIds[which]);
			editor.commit();*/
			
			Intent data=new Intent();
			//请求代码可以自己设置，这里设置成20  
			data.putExtra("addressInfo",addressList.get(position));
			setResult(20, data);  
			//关闭掉这个Activity  
			finish(); 
		}
	}
	private class AddressItemLongClickListener implements OnItemLongClickListener {

		@Override
		public boolean onItemLongClick(AdapterView<?> parent, View view,
									   int position, long id) {
			/*intent = new Intent();
			intent.putExtra("addressInfo", addressList.get(position));
			intent.setClass(AddressManageActivity.this, PaymentCenterActivity.class);
			startActivity(intent);
			finish();*/
//			intent = new Intent();
//			intent.putExtra("addressInfo", addressList.get(position));
//			LogUtil.info("addressInfo   返回前==="+addressList.get(position));
//			intent.setClass(AddressListActivity.this, AddAddressActivity.class);
//			startActivityForResult(intent, 100);
			return true;
		}
	}
	/**
	 * 返回
	 */
	public void goBack(View view){
		finish();
		overridePendingTransition(R.anim.tran_pre_in, R.anim.tran_pre_out);
	}
	/**
	 * add new address
	 * @param view
	 */
	public void addNewAddress(View view){
//		 intent = new Intent();
//		intent.setClass(this, AddAddressActivity.class);
//	//	startActivityForResult(intent, 100);
//		startActivity(intent);
	}
	
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		getServiceProductList();
	}
	
}