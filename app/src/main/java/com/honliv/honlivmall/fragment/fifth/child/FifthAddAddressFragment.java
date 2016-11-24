package com.honliv.honlivmall.fragment.fifth.child;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.honliv.honlivmall.GloableParams;
import com.honliv.honlivmall.R;
import com.honliv.honlivmall.adapter.MyAdapter;
import com.honliv.honlivmall.base.BaseFragment;
import com.honliv.honlivmall.bean.AddressInfo;
import com.honliv.honlivmall.contract.FifthContract;
import com.honliv.honlivmall.model.fifth.child.FifthAddAddressModel;
import com.honliv.honlivmall.presenter.fifth.child.FifthAddAddressPresenter;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Rodin on 2016/11/16.
 */
public class FifthAddAddressFragment extends BaseFragment<FifthAddAddressPresenter, FifthAddAddressModel> implements FifthContract.FifthAddAddressView, View.OnClickListener {
    @BindView(R.id.add_address_name)
    EditText addressNameET;
    @BindView(R.id.add_address_mobile)
    EditText addressPhoneET;
    @BindView(R.id.add_address_line)
    TextView addaddresslineTV;
    @BindView(R.id.add_address_detail)
    EditText addressDetailET;
    @BindView(R.id.add_address_zipcode)
    EditText addressZipCodeET;
    AddressInfo addressInfo;
    AddressInfo addressPreInfo;//上一个界面传递过来的地址

    String[] lineitems;//线路
    @BindView(R.id.add_address_bottom_LL)
    LinearLayout addAddressBottomLL;
    @BindView(R.id.add_address_message)
    TextView addressMessageTV;
    @BindView(R.id.default_address_button)
    Button default_address_button;
    @BindView(R.id.edit_address_title)
    TextView editAddressTitle;
    @BindView(R.id.address_manager_add_text)
    TextView address_manager_add_text;

    List<AddressInfo> lineList; //线路列表
    boolean isAddressSave = true;//是否保存执行成功了
    @BindView(R.id.seleteLine_LL)
    LinearLayout seleteLineLL;

    /**********
     * 以下三级联动
     *************/
    @BindView(R.id.spinner1)
    Spinner spinner1;
    @BindView(R.id.spinner2)
    Spinner spinner2;
    @BindView(R.id.spinner3)
    Spinner spinner3;
    String province = null;
    String city = null;
    String district = null;

    String saveAddress = "";//湖南省湘西土家族苗族自治州 看看是不是有地址

    /**********
     * 以上三级联动
     *************/
    public static FifthAddAddressFragment newInstance(Bundle args) {
        FifthAddAddressFragment fragment = new FifthAddAddressFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_fifth_add_address;
    }

    /**
     * 初始化控件
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        spinner1.setPrompt("省");
        spinner2.setPrompt("城市");
        spinner3.setPrompt("地区");
        address_manager_add_text.setOnClickListener(this);
        default_address_button.setOnClickListener(this);
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
            case R.id.address_manager_add_text:
                addressSave();
                break;
            case R.id.default_address_button:
                addressSave();
                if (isAddressSave) {
                    return;
                }
                if (addressPreInfo == null || addressPreInfo.getId() < 1) {
                    return;
                }
                mPresenter.addressDefault(GloableParams.USERID, addressPreInfo.getId());
//                new BaseActivity.MyHttpTask<Integer>() {
//                    @Override
//                    protected void onPreExecute() {
//                        PromptManager.showCommonProgressDialog(AddAddressActivity.this);
//                        super.onPreExecute();
//                    }
//                    @Override
//                    protected Object doInBackground(Integer... params) {
//                        AddressEngine engine = BeanFactory.getImpl(AddressEngine.class);
//                        return engine.getServiceSetDefaultAddress(GloableParams.USERID, addressPreInfo.getId());
//                    }
//                    protected void onPostExecute(Object result){
//                        super.onPostExecute(result);
//                        PromptManager.closeProgressDialog();
//                       
//                    }
//                }.executeProxy(0);
                break;
        }
    }

    private void addressSave() {
        String addressName = addressNameET.getText().toString().trim();
        if (StringUtils.isBlank(addressName)) {
            showToast("收货人名字不能为空");

            return;
        } else if (addressName.length() < 2) {
            showToast("名字字数过少");
            return;
        }

        String mobilePhoneStr = addressPhoneET.getText().toString().trim();
        if (StringUtils.isBlank(mobilePhoneStr)) {
            showToast("手机号不能为空");
            return;
        }
        // 这里要加上一个匹配手机号的格式。。String regex = "1[3458][0-9]{9}";
        String regPhone = "^1([38][0-9]|4[57]|5[^4]|7[0678]|)\\d{8}$"; // 定义正则

        boolean flagPhone = mobilePhoneStr.matches(regPhone);
        if (!flagPhone) {
            showToast("请填写正确的手机号");
            return;
        }
        int regionId = sp.getInt("regionId", 0);
        if (regionId == 0) {
            regionId = addressPreInfo.getRegionId();
        }
        if (regionId == 0) {
            showToast("请重新选择区域,再提交");
            return;
        }

        String addressDetail = addressDetailET.getText().toString().trim();
        if (StringUtils.isBlank(addressDetail)) {
            showToast("详细地址不能为空");
            return;
        } else if (addressDetail.length() < 6) {
            showToast("详细地址字数过少");
            return;
        }

        int userId = GloableParams.USERID;//sp.getInt("isLogin", -1);
        if (userId < 0) {
            showToast("登录状态错误，请重新登录！");
            return;
        }
        addressInfo = new AddressInfo();
        if (addressPreInfo != null) {
            addressInfo.setId(addressPreInfo.getId());
        }

        addressInfo.setUserId(userId);
        addressInfo.setName(addressName);
        addressInfo.setPhone(mobilePhoneStr);
        addressInfo.setRegionId(regionId);//regionId
        addressInfo.setAreaDetail(addressDetail);
        mPresenter.getServiceProductList(addressPreInfo, addressInfo, true);
        isAddressSave = false;
    }

    protected void initLineDate() {
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("linesupplierId", 0);
        editor.commit();

        lineitems = new String[lineList.size()];

        for (int i = 0; i < lineList.size(); i++) {
            lineitems[i] = lineList.get(i).getName();
        }
    }

    public void initData() {
        Bundle arguments = getArguments();
        addressPreInfo = (AddressInfo) arguments.getSerializable("addressInfo");
//        addressPreInfo = (AddressInfo)this.getIntent().getSerializableExtra("addressInfo");

        if (addressPreInfo != null) {//编辑地址
            addressNameET.setText(addressPreInfo.getName() + "");
            addressPhoneET.setText(addressPreInfo.getPhone() + "");
            addressDetailET.setText("" + addressPreInfo.getAreaDetail() + "");
            seleteLineLL.setVisibility(View.GONE);

            if (addressPreInfo.getAddressArea() != null) {//从上个界面传递过来的省市区
                saveAddress = addressPreInfo.getAddressArea();
            } else {
                mPresenter.getServiceAddressName(addressPreInfo.getRegionId());
            }
        } else {
            addAddressBottomLL.setVisibility(View.GONE);
            addressMessageTV.setVisibility(View.GONE);
            seleteLineLL.setVisibility(View.GONE);
            editAddressTitle.setText("新增地址");
        }
        mPresenter.initSpinner1(0);
    }

//     void getServiceAddressName() {
//        new BaseActivity.MyHttpTask<Integer>() {
//            @Override
//            protected void onPreExecute() {
//                PromptManager.showCommonProgressDialog(AddAddressActivity.this);
//                super.onPreExecute();
//            }
//
//            @Override
//            protected Object doInBackground(Integer... params) {
//                AddressEngine engine = BeanFactory.getImpl(AddressEngine.class);
//                return engine.getServiceAddressSelectName(addressPreInfo.getRegionId());
//            }
//
//            @Override
//            protected void onPostExecute(Object result) {
//                // TODO Auto-generated method stub
//                super.onPostExecute(result);
//                PromptManager.closeProgressDialog();
//
//            }
//        }.executeProxy(0);
//    }

    /**
     * 新增地址时要选择线路
     *
     * @param view
     */
    public void seleteLine(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("请选择线路");
        int which = sp.getInt("linesupplierId", 0);
        builder.setSingleChoiceItems(lineitems, which,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences.Editor editor = sp.edit();
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

    @Override
    public boolean onBackPressedSupport() {
        pop();
        return true;
    }

    protected void initSpinnerDate1(List<AddressInfo> addressList) {
        MyAdapter myAdapter = new MyAdapter(getContext(), addressList);
        spinner1.setAdapter(myAdapter);

        spinner1.setOnItemSelectedListener(new SpinnerOnSelectedListener1());
        if (addressPreInfo != null) {
            int tempPosition = 0;

            for (int i = 0; i < addressList.size(); i++) {
                if (saveAddress.contains(addressList.get(i).getName())) {
                    tempPosition = i;
                }
            }
            spinner1.setSelection(tempPosition, true);
        }
    }

    protected void initSpinnerDate2(List<AddressInfo> addressList) {
        MyAdapter myAdapter = new MyAdapter(getContext(), addressList);
        spinner2.setAdapter(myAdapter);
        spinner2.setOnItemSelectedListener(new SpinnerOnSelectedListener2());

        if (addressPreInfo != null) {
            int tempPosition = 0;

            for (int i = 0; i < addressList.size(); i++) {
                if (saveAddress.contains(addressList.get(i).getName())) {
                    tempPosition = i;
                }
            }
            spinner2.setSelection(tempPosition, true);
        }

    }

    public void initSpinner3(AddressInfo addressInfo, final int pcode) {
        if (addressInfo != null && addressInfo.getChildlist() != null && addressInfo.getChildlist().size() > 0) {
            initSpinnerDate3(addressInfo.getChildlist());
            spinner3.setVisibility(View.VISIBLE);
        } else {
            spinner3.setVisibility(View.GONE);
            district = addressInfo.getName();
            int regId = addressInfo.getId();

            SharedPreferences.Editor editor = sp.edit();
            editor.putInt("regionId", regId);
            editor.commit();

        }
    }

    protected void initSpinnerDate3(List<AddressInfo> addressList) {
        MyAdapter myAdapter = new MyAdapter(getContext(), addressList);
        spinner3.setAdapter(myAdapter);
        spinner3.setOnItemSelectedListener(new SpinnerOnSelectedListener3());

        if (addressPreInfo != null) {
            int tempPosition = 0;

            for (int i = 0; i < addressList.size(); i++) {
                if (saveAddress.contains(addressList.get(i).getName())) {
                    tempPosition = i;
                }
            }
            spinner3.setSelection(tempPosition, true);
        }
    }

    @Override
    public void updateView(List<AddressInfo> result) {
        if (result != null) {
            List<AddressInfo> addressList = ((List<AddressInfo>) result);
            if (addressList.size() == 0) {
                showToast("服务器忙，请稍后重试！！！");
            } else {
                initSpinnerDate1(addressList);
            }
        } else {
            showToast("服务器忙，请稍后重试！！！");
        }
    }

    @Override
    public void updateView2(List<AddressInfo> result) {
        if (result != null) {
            List<AddressInfo> addressList = ((List<AddressInfo>) result);
            if (addressList.size() == 0) {
                showToast("服务器忙，请稍后重试！！！");
            } else {
                initSpinnerDate2(addressList.get(0).getChildlist());
            }
        } else {
            showToast("服务器忙，请稍后重试！！！");
        }
    }

    @Override
    public void updateServiceProductList(List<AddressInfo> result, boolean isSave) {
        if (result != null) {
            List<AddressInfo> addressList = ((List<AddressInfo>) result);
            if (isSave) {
                pop();
            }
        } else {
            showToast("服务器忙，请稍后重试！！！");
        }
    }

    @Override
    public void updateServiceAddressName(String result) {
        if (result != null) {
            saveAddress = (String) result;
        } else {
            showToast("服务器忙，请稍后重试！！！");
            return;
        }
    }

    @Override
    public void updateAddressDefault(Boolean result) {
        if (result != null) {
            Boolean isSuccess = (Boolean) result;
            if (isSuccess) {
                showToast("设置成功");
                GloableParams.SETDEFAULTADDRESS = true;
                pop();
            } else {
                showToast("服务器忙，请稍后重试！！！");
            }
        } else {
            showToast("服务器忙，请稍后重试！！！");
        }
    }

    class SpinnerOnSelectedListener1 implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> adapterView, View view, int position,
                                   long id) {
            province = ((AddressInfo) adapterView.getItemAtPosition(position)).getName();
            int pcode = ((AddressInfo) adapterView.getItemAtPosition(position)).getId();

            mPresenter.initSpinner2(pcode);
//				initSpinner3((AddressInfo)adapterView.getItemAtPosition(position),pcode);
        }

        public void onNothingSelected(AdapterView<?> adapterView) {
            // TODO Auto-generated method stub
        }
    }

    class SpinnerOnSelectedListener2 implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> adapterView, View view, int position,
                                   long id) {
            city = ((AddressInfo) adapterView.getItemAtPosition(position)).getName();
            int pcode = ((AddressInfo) adapterView.getItemAtPosition(position)).getId();

            initSpinner3(((AddressInfo) adapterView.getItemAtPosition(position)), position);
        }

        public void onNothingSelected(AdapterView<?> adapterView) {
            // TODO Auto-generated method stub
        }
    }

    class SpinnerOnSelectedListener3 implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> adapterView, View view, int position,
                                   long id) {
            district = ((AddressInfo) adapterView.getItemAtPosition(position)).getName();

            int regId = ((AddressInfo) adapterView.getItemAtPosition(position)).getId();

            SharedPreferences.Editor editor = sp.edit();
            editor.putInt("regionId", regId);
            editor.commit();

//				PromptManager.showToastTest(AddAddressActivity.this, province+" "+city+" "+district+",regId="+regId, Toast.LENGTH_LONG).show();
        }

        public void onNothingSelected(AdapterView<?> adapterView) {
            // TODO Auto-generated method stub
        }
    }
}
