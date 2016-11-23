package com.honliv.honlivmall.model.fifth.child;

import com.honliv.honlivmall.bean.Help;
import com.honliv.honlivmall.bean.UserInfo;
import com.honliv.honlivmall.contract.FifthContract;
import com.honliv.honlivmall.engine.HelpPostUtils;
import com.honliv.honlivmall.engine.UserPostUtils;

import java.util.List;

import rx.Observable;

/**
 * Created by Rodin on 2016/11/22.
 */
public class FifthHelpListModel implements FifthContract.FifthHelpListModel {
    @Override
    public Observable<List<Help>> HelpList(int i) {
        Observable<List<Help>> result = HelpPostUtils.HelpList(i);
        return result;
    }
}
