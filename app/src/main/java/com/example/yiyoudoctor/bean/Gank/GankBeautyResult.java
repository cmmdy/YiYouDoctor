package com.example.yiyoudoctor.bean.Gank;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by 夏夜晚凤 on 2017/3/31.
 */

public class GankBeautyResult<T> {
    public boolean error;
    public @SerializedName("results") List<GankBeauty> beauties;
}
