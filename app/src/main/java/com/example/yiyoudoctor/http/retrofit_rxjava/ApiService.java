package com.example.yiyoudoctor.http.retrofit_rxjava;


import java.util.List;

import com.example.yiyoudoctor.bean.Gank.GankBeauty;
import com.example.yiyoudoctor.bean.Gank.GankBeautyResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * @author nanchen
 * @fileName RetrofitRxDemoo
 * @packageName com.nanchen.retrofitrxdemoo
 * @date 2016/12/09  17:04
 */

public interface ApiService {

    /**
     * 使用普通的retrofit方式获取数据
     * @return
     */
    @GET("data/福利/{number}/{page}")
    Call<GankBeautyResult<List<GankBeauty>>> getUsers();


    /**
     * 使用rx+retrofit的方式获取数据
     */

    @GET("data/福利/{number}/{page}")
    Observable<GankBeautyResult<List<GankBeauty>>> getBeautiesByRx(@Path("number") int number, @Path("page") int page);
}
