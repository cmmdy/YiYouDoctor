package com.example.yiyoudoctor.mvp.dagger2.module;

import com.example.yiyoudoctor.mvp.contract.LoginContract;
import com.example.yiyoudoctor.mvp.presenter.LoginPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 夏夜晚凤 on 2017/4/25.
 */


@Module
public class PresenterModule {

    LoginContract.View view;

    public PresenterModule(LoginContract.View view) {
        this.view = view;
    }

    @Provides
    public LoginContract.View provideLoginContractView(){
        return view;
    }

    @Singleton
    @Provides
    public LoginContract.Presenter provideLoginPresenter(LoginContract.View view) {
        return new LoginPresenter(view);
    }
}
