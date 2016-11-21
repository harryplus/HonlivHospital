package com.honliv.honlivmall.model.fourth.child;

import com.alibaba.fastjson.JSON;
import com.honliv.honlivmall.ConstantValue;
import com.honliv.honlivmall.GloableParams;
import com.honliv.honlivmall.application.MyApplication;
import com.honliv.honlivmall.bean.Product;
import com.honliv.honlivmall.contract.FourthContract;
import com.honliv.honlivmall.util.LogUtil;
import com.honliv.honlivmall.util.MyJSUtil;
import com.honliv.honlivmall.util.RxUtil;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;

import java.util.ArrayList;
import java.util.List;

import jsonrpc.JSONRPCClient;
import jsonrpc.JSONRPCParams;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Rodin on 2016/11/15.
 */
public class FourthMainModel implements FourthContract.FourthMainModel  {
    @Override
    public Observable<ArrayList<Product>> getNativeAllShopCart(int userId) {
        Observable<ArrayList<Product>> resultBean = Observable.just(userId).map(result->{
            DbUtils db = DbUtils.create(MyApplication.getInstance().getAppContext());
            ArrayList<Product> productlist = null;
            try {
                Product product = new Product();
                //product.setUserId(GloableParams.USERID);
                //	productlist = db.findAll(Product.class);
                if (GloableParams.USERID > 0) {
                    productlist = (ArrayList) db.findAll(Selector.from(Product.class)
                            .where("userId", "=", GloableParams.USERID));
                } else {
                    productlist = (ArrayList) db.findAll(Selector.from(Product.class).where("userId", "=", -100));
                }
                LogUtil.info("product 111db ===" + productlist);
            } catch (DbException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }//通过类型查找
            return productlist;
        }).compose(RxUtil.rxSchedulerHelper());
        return resultBean;
    }
}
