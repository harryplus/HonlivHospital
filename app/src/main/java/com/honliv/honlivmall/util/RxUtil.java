package com.honliv.honlivmall.util;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Rodin on 2016/11/15.
 */
public class RxUtil {

    /**
     * 统一线程处理
     *
     * @param <T>
     * @return
     */
    public static <T> Observable.Transformer<T, T> rxSchedulerHelper() {    //compose简化线程
        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 统一返回结果处理
     *
     * @param <T>
     * @return
     */
    public static <T> Observable.Transformer<CoreDataResponse<T>, T> handleResult() {   //compose判断结果
        return httpResponseObservable -> httpResponseObservable.flatMap(new Func1<CoreDataResponse<T>, Observable<T>>() {
            @Override
            public Observable<T> call(CoreDataResponse<T> tMyHttpResponse) {
                if (tMyHttpResponse.getCode() == 200) {
                    return createData(tMyHttpResponse.getNewslist());
                } else {
                    return Observable.error(new CoreApiException("服务器返回error"));
                }
            }
        });
    }

    /**
     * 生成Observable
     *
     * @param <T>
     * @return
     */
    public static <T> Observable<T> createData(final T t) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                try {
                    subscriber.onNext(t);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }
}
