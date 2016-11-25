package com.honliv.honlivmall.model.fifth.child;

import android.content.Context;
import android.widget.Toast;

import com.honliv.honlivmall.bean.UserInfo;
import com.honliv.honlivmall.contract.FifthContract;
import com.honliv.honlivmall.engine.UserPostUtils;
import com.honliv.honlivmall.util.RxUtil;
import com.honliv.honlivmall.util.ToastUtils;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;

import rx.Observable;

/**
 * Created by Rodin on 2016/11/22.
 */
public class FifthEditPersonInfoModel implements FifthContract.FifthEditPersonInfoModel {
    @Override
    public Observable<UserInfo> getServicePersonal(Context context) {
        return Observable.just(context).map(t -> {
            DbUtils db = DbUtils.create(context);
            try {
                UserInfo dbUserInfo = db.findFirst(UserInfo.class);
                if (dbUserInfo == null) {
                    ToastUtils.showShortToast( "登陆状态错误,请退出重进");
                } else {
                    return dbUserInfo;
                }
            } catch (DbException e) {
                e.printStackTrace();
            }
            return null;
        }).compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Observable<Boolean> updateUserInfo(UserInfo info) {
        Observable<Boolean> result = UserPostUtils.updateUserInfo(info);
        return result;
    }
}
