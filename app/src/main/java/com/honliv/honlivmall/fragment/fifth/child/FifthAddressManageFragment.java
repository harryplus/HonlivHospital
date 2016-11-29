package com.honliv.honlivmall.fragment.fifth.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.honliv.honlivmall.R;
import com.honliv.honlivmall.base.BaseFragment;
import com.honliv.honlivmall.bean.AddressInfo;
import com.honliv.honlivmall.contract.FifthContract;
import com.honliv.honlivmall.fragment.global.GlobalAddAddressFragment;
import com.honliv.honlivmall.model.fifth.child.FifthAddressManageModel;
import com.honliv.honlivmall.presenter.fifth.child.FifthAddressManagePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Rodin on 2016/11/16.
 */
public class FifthAddressManageFragment extends BaseFragment<FifthAddressManagePresenter, FifthAddressManageModel> implements FifthContract.FifthAddressManageView, View.OnClickListener, AdapterView.OnItemLongClickListener {

    List<AddressInfo> addressList;
    @BindView(R.id.address_manage_list)
    ListView addressLV;
    @BindView(R.id.nullProductTV)
    TextView nullProductTV;

    AddressAdapter addressAdapter;
    @BindView(R.id.address_list_add_text)
    TextView addressListAddTV;
    @BindView(R.id.address_title2)
    TextView address_title2;
    @BindView(R.id.head_back_text)
    TextView head_back_text;

    public static FifthAddressManageFragment newInstance() {
        Bundle args = new Bundle();

        FifthAddressManageFragment fragment = new FifthAddressManageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_fifth_address_manage;
    }

    /**
     * 初始化控件
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        addressList = new ArrayList<>();
        address_title2.setVisibility(View.GONE);
        head_back_text.setOnClickListener(this);
        addressListAddTV.setOnClickListener(this);
    }

    @Override
    public void showError(String msg) {
        showToast(msg);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.head_back_text:
                pop();
                break;
            case R.id.address_list_add_text:
                start(GlobalAddAddressFragment.newInstance(new Bundle()));
                break;
        }
    }

    @Override
    public boolean onBackPressedSupport() {
        pop();
        return true;
    }

    public void initData() {
        addressAdapter = new AddressAdapter();
        addressLV.setAdapter(addressAdapter);
        addressLV.setOnItemLongClickListener(this);
        addressLV.setOnItemClickListener(new AddressItemClickListener());
    }

    AddressInfo addressInfo;

    @Override
    public void updateServiceAddressList(List<AddressInfo> result) {
        if (result != null) {
            addressList = result;
            if (addressList.size() == 0) {
                nullProductTV.setVisibility(View.VISIBLE);
                addressLV.setVisibility(View.GONE);
            } else {
                nullProductTV.setVisibility(View.GONE);
                addressLV.setVisibility(View.VISIBLE);
                initData();
            }
        } else {
            showToast("服务器忙，请稍后重试！！！");
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        return false;
    }

    class AddressAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return addressList.size();
        }

        @Override
        public Object getItem(int position) {
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
            Bundle data = new Bundle();
            data.putSerializable("addressInfo", addressList.get(position));
            start(GlobalAddAddressFragment.newInstance(data));
//            intent = new Intent();
//            intent.putExtra("addressInfo", addressList.get(position));
//            LogUtil.info("addressInfo   返回前==="+addressList.get(position));
//            intent.setClass(AddressManageActivity.this, AddAddressActivity.class);
//            startActivityForResult(intent, 100);


        }
    }

//    class AddressItemLongClickListener implements AdapterView.OnItemLongClickListener {
//
//        @Override
//        public boolean onItemLongClick(AdapterView<?> parent, View view,
//                                       int position, long id) {
//            return true;
//        }
//    }
}
