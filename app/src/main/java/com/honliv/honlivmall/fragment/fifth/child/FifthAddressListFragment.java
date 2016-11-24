package com.honliv.honlivmall.fragment.fifth.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.honliv.honlivmall.GloableParams;
import com.honliv.honlivmall.R;
import com.honliv.honlivmall.base.BaseFragment;
import com.honliv.honlivmall.bean.AddressInfo;
import com.honliv.honlivmall.contract.FifthContract;
import com.honliv.honlivmall.model.fifth.child.FifthAddressListModel;
import com.honliv.honlivmall.presenter.fifth.child.FifthAddressListPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Rodin on 2016/11/16.
 */
public class FifthAddressListFragment extends BaseFragment<FifthAddressListPresenter, FifthAddressListModel> implements FifthContract.FifthAddressListView {
    @BindView(R.id.address_manage_list)
    ListView addressLV;
    @BindView(R.id.nullProductTV)
    TextView nullProductTV;
    @BindView(R.id.address_list_add_text)
    TextView addressListAddTV;
    @BindView(R.id.addressmassagetitle)
    TextView addressmassagetitle;

    AddressAdapter addressAdapter;
    AddressInfo addressInfo;
    List<AddressInfo> addressList;


    public static FifthAddressListFragment newInstance() {

        Bundle args = new Bundle();

        FifthAddressListFragment fragment = new FifthAddressListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_fifth_addresslist;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        addressmassagetitle.setText("地址列表");
    }

    @Override
    public void showError(String msg) {
        showToast(msg);
    }


    public void initData() {
        mPresenter.getServiceProductList(GloableParams.USERID, 1, 50);
        addressList = new ArrayList<>();
        addressAdapter = new AddressAdapter();
        addressLV.setAdapter(addressAdapter);
        addressLV.setOnItemLongClickListener(new AddressItemLongClickListener());
        addressLV.setOnItemClickListener(new AddressItemClickListener());
    }

    @Override
    public void updateServiceProductList(List<AddressInfo> result) {
        if (result != null) {
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
            if (result.size() == 0) {
                nullProductTV.setVisibility(View.VISIBLE);
                addressLV.setVisibility(View.GONE);
//							addressListAddTV.setVisibility(View.VISIBLE);
            } else {
                nullProductTV.setVisibility(View.GONE);
                addressLV.setVisibility(View.VISIBLE);
                addressList.addAll(result);
                addressAdapter.notifyDataSetChanged();
            }
            //		}
        } else {
            showToast("服务器忙，请稍后重试！！！");
        }
    }

    class AddressAdapter extends BaseAdapter {

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
            if (convertView == null) {
                holder = new ViewHolder();
                view = View.inflate(getContext(), R.layout.address_manage_listitem, null);
                /*view = LayoutInflater.from(getApplicationContext()).inflate(
                        R.layout.product_list_item, null);*/
                holder.addressNameTV = (TextView) view.findViewById(R.id.addressnameTV);
                holder.addressPhoneTV = (TextView) view.findViewById(R.id.addressphoneTV);
                holder.addressDetailTV = (TextView) view.findViewById(R.id.addressdetailTV);
                holder.ProvinceTV = (TextView) view.findViewById(R.id.addressProvinceTV);

                view.setTag(holder);
            } else {
                view = convertView;
                holder = (ViewHolder) view.getTag();
            }
            addressInfo = addressList.get(position);
            holder.addressNameTV.setText("收货人:  " + addressInfo.getName() + "");
            holder.addressPhoneTV.setText("手机号:  " + addressInfo.getPhone() + "");
            /*holder.ProvinceTV.setText("北京市:  "+addressInfo.getAreaDetail().split(",")[0]+"");
            if(addressInfo.getAreaDetail().split(",").length>1){
				holder.addressDetailTV.setText("详细地址:  "+addressInfo.getAreaDetail().split(",")[1]+"");
			}else{
				holder.addressDetailTV.setText("详细地址:  "+addressInfo.getAreaDetail()+"");
			}*/
            holder.ProvinceTV.setText("所在地区:  " + addressInfo.getAddressArea() + "");
            holder.addressDetailTV.setText("详细地址:  " + addressInfo.getAreaDetail() + "");
            return view;
        }
    }

    static class ViewHolder {
        TextView addressNameTV;//收货人姓名
        TextView addressPhoneTV;//收货人手机号
        TextView addressDetailTV;//收货人详细地址
        TextView ProvinceTV;  //省市区
    }

    class AddressItemClickListener implements AdapterView.OnItemClickListener {
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

//            Intent data = new Intent();
//            //请求代码可以自己设置，这里设置成20
//            data.putExtra("addressInfo", addressList.get(position));
//            setResult(20, data);
//            //关闭掉这个Activity
//            finish();
        }
    }

    class AddressItemLongClickListener implements AdapterView.OnItemLongClickListener {

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

    @Override
    public boolean onBackPressedSupport() {
        pop();
        return true;
    }

    /**
     * add new address
     *
     * @param view
     */
    public void addNewAddress(View view) {
//		 intent = new Intent();
//		intent.setClass(this, AddAddressActivity.class);
//	//	startActivityForResult(intent, 100);
//		startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getServiceProductList(GloableParams.USERID, 1, 50);
    }
}
