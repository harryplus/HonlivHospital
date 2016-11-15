package com.honliv.honlivhospital.utils;

import com.honliv.honlivhospital.application.MyApplication;

import java.io.File;

import okhttp3.Cache;

/**
 * Created by Rodin on 2016/11/15.
 */
public class HttpCache {
    private static final int HTTP_RESPONSE_DISK_CACHE_MAX_SIZE = 50 * 1024 * 1024;

    public static Cache getCache() {
        return new Cache(new File(MyApplication.getInstance().getAppContext().getCacheDir().getAbsolutePath() + File.separator + "data/NetCache"),
                HTTP_RESPONSE_DISK_CACHE_MAX_SIZE);
    }
}
