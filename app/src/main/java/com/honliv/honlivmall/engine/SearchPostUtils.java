package com.honliv.honlivmall.engine;

import com.honliv.honlivmall.util.RxUtil;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Rodin on 2016/11/21.
 */
public class SearchPostUtils {
    public static Observable<List<String>> HotKeyword() {
        Observable<List<String>> resultBean = Observable.just("").map(new Func1<String, List<String>>() {
            @Override
            public List<String> call(String r) {
//                List<String> result;
//
//                HttpClientUtil clientUtil = new HttpClientUtil();
//                Map<String, Object> params = new HashMap<String, Object>();
//
//                params.put("method", "HotKeyword");
//                String searchStr = clientUtil.sendPost(ConstantValue.URL, params);
//
//                // 对返回的结果进行解析
//                try {
//                    String resultContent = MyJSUtil.checkResponseString(searchStr);
//                    if (resultContent != null && resultContent.length() > 0) {
//                        result = JSON.parseArray(resultContent, String.class);
//                        return result;
//                    } else {// 得到错误的信息
//                        String errorMsg = MyJSUtil.getErrorMsg(searchStr);
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
                return null;
            }
        }).compose(RxUtil.rxSchedulerHelper());
        return resultBean;
    }
}
