package com.honliv.honlivmall.model.fifth.child;

import com.honliv.honlivmall.contract.FifthContract;
import com.honliv.honlivmall.engine.UserPostUtils;

import rx.Observable;

/**
 * Created by Rodin on 2016/11/22.
 */
public class FifthFeedBackModel implements FifthContract.FifthFeedBackModel {
    @Override
    public Observable<Boolean> LiveMessage(int userId, String telephone, String email, String content) {
        Observable<Boolean> result = UserPostUtils.LiveMessage(userId,telephone,email,content);
        return result;
    }
}
