package com.honliv.honlivmall.base;

import android.content.Context;

/**
 * Created by Rodin on 2016/11/15.
 */
public interface CoreBaseView {
    Context getContext();

    void showError(String msg);
}
