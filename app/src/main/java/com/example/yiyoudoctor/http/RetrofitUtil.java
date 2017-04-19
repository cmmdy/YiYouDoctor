package com.example.yiyoudoctor.http;

import com.example.yiyoudoctor.api.ApiManager;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.example.yiyoudoctor.bean.Gank.GankBeauty;
import com.example.yiyoudoctor.bean.Gank.GankBeautyResult;
import com.example.yiyoudoctor.bean.Gank.GankItem;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;

import com.example.yiyoudoctor.http.retrofit_rxjava.ApiService;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import com.example.yiyoudoctor.http.map.GankResultToItemsMapper;

/**
 * @author nanchen
 * @fileName RetrofitRxDemoo
 * @packageName com.nanchen.retrofitrxdemoo.util
 * @date 2016/12/12  10:38
 */

public class RetrofitUtil {

    public static final int DEFAULT_TIMEOUT = 5;

    private Retrofit mRetrofit;
    private ApiService mApiService;

    private Subscription subscription;

    private static RetrofitUtil mInstance;

    /**
     * 私有构造方法
     */
    private RetrofitUtil() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        mRetrofit = new Retrofit.Builder()
                .client(builder.build())
                .baseUrl(ApiManager.APP_HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        mApiService = mRetrofit.create(ApiService.class);
    }

    public static RetrofitUtil getInstance() {
        if (mInstance == null) {
            synchronized (RetrofitUtil.class) {
                mInstance = new RetrofitUtil();
            }
        }
        return mInstance;
    }

    /**
     * 用于获取用户信息
     *
     * @param subscriber
     */
    public void getUsers(Subscriber<GankBeautyResult<List<GankBeauty>>> subscriber, int pages) {
        mApiService.getBeautiesByRx(10, pages)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * @param page     请求页数，默认page=1
     * @param rows     每页返回的条数，默认rows = 20
     * @param observer
     */
    public Subscription getItemList(int rows, int page, Observer<List<GankItem>> observer) {

        return toSubscribe(mApiService.getBeautiesByRx(rows, page).map(GankResultToItemsMapper.getINSTENCE()), observer);
    }

    private <T> Subscription toSubscribe(Observable<T> observable, Observer<T> observer) {
        subscription = observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return subscription;
    }
}
