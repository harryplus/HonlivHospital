package com.honliv.honlivmall.listener;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;


import com.honliv.honlivmall.ConstantValue;
import com.honliv.honlivmall.GloableParams;
import com.honliv.honlivmall.bean.Product;
import com.honliv.honlivmall.fragment.first.child.FirstProductDetailFragment;
import com.honliv.honlivmall.util.BuilderTools;
import com.honliv.honlivmall.util.RxManager;

import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Rodin on 2016/11/1.
 */
public class GVItemListener implements AdapterView.OnItemClickListener {

    private final List<Product> data;
    private final boolean flag;
    private final SupportFragment fragment;
    private final RxManager mRxManager;
    private Context mContext;


    public GVItemListener(Context mContext, SupportFragment fragment, RxManager mRxManager, List<Product> data, boolean flag) {
        this.mContext = mContext;
        this.data = data;
        this.flag = flag;
        this.fragment=fragment;
        this.mRxManager=mRxManager;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {

        if (GloableParams.USERID < 0 && flag) {
            //弹出登陆按钮
            BuilderTools.showLoginDialog(mContext,fragment,"需要登录");
        } else {
            if (data.size() > 0) {
                position = position % data.size();
            }
            Product selProduct = data.get(position);

//            Intent intent = new Intent();
////            intent.setClass(mContext, ProductDetailActivity.class);
//            if (flag) {
//                intent.putExtra("privilegeProduct", selProduct);
//            }
//            intent.putExtra("pId", selProduct.getId());
//            mContext.startActivity(intent);
            mRxManager.post(ConstantValue.msg_privilegeproduct,selProduct);
            fragment. start(FirstProductDetailFragment.newInstance());
        }
    }
}
