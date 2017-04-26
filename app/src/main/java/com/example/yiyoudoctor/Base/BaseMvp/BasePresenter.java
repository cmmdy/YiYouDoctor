package com.example.yiyoudoctor.Base.BaseMvp;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by 夏夜晚凤 on 2017/4/19.
 */

public class BasePresenter <V extends BaseView, M extends BaseModel> {

    protected V mView;
    protected M mModel;

    protected CompositeSubscription mCompositeSubscription;

    public void onUnsubscribe(){
        if(mView != null){
            mView = null;
        }
        if(mCompositeSubscription !=null && mCompositeSubscription.hasSubscriptions()){
            mCompositeSubscription.unsubscribe();
        }
    }

    public void addSubscription(Subscription subscription){
        if(mCompositeSubscription == null){
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

}
