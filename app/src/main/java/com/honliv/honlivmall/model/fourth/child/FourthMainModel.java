package com.honliv.honlivmall.model.fourth.child;

import com.honliv.honlivmall.GloableParams;
import com.honliv.honlivmall.application.MyApplication;
import com.honliv.honlivmall.bean.Product;
import com.honliv.honlivmall.contract.FourthContract;
import com.honliv.honlivmall.util.RxUtil;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;

import java.util.ArrayList;

import rx.Observable;

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
                if (GloableParams.USERID > 0) {
                    productlist = (ArrayList) db.findAll(Selector.from(Product.class)
                            .where("userId", "=", GloableParams.USERID));
                } else {
                    productlist = (ArrayList) db.findAll(Selector.from(Product.class).where("userId", "=", -100));
                }
            } catch (DbException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }//通过类型查找
            return productlist;
        }).compose(RxUtil.rxSchedulerHelper());
        return resultBean;
    }
}
