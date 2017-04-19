package com.example.yiyoudoctor.http.map;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.yiyoudoctor.bean.Gank.GankBeauty;
import com.example.yiyoudoctor.bean.Gank.GankBeautyResult;
import com.example.yiyoudoctor.bean.Gank.GankItem;
import rx.functions.Func1;

/**
 * Created by 夏夜晚凤 on 2017/3/31.
 */

public class GankResultToItemsMapper implements Func1<GankBeautyResult, List<GankItem>> {
    private static GankResultToItemsMapper INSTENCE = new GankResultToItemsMapper();

    public GankResultToItemsMapper() {
    }

    public static GankResultToItemsMapper getINSTENCE(){
        return INSTENCE;
    }

    @Override
    public List<GankItem> call(GankBeautyResult gankBeautyResult) {
        List<GankBeauty> gankBeauties = gankBeautyResult.beauties;
        List<GankItem> gankItems = new ArrayList<>(gankBeauties.size());
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS'Z'");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
        for(GankBeauty gankBeauty : gankBeauties){
            GankItem gankItem = new GankItem();
            try {
                Date date = inputFormat.parse(gankBeauty.createdAt);
                gankItem.description = outputFormat.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            gankItem.imageUrl = gankBeauty.url;
            gankItems.add(gankItem);
        }
        Log.e("items", gankItems.size()+"");
        return gankItems;
    }
}
