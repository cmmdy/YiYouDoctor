package com.example.yiyoudoctor.mvp.dagger2.component;

import com.example.yiyoudoctor.mvp.dagger2.module.PresenterModule;
import com.example.yiyoudoctor.ui.activity.LoginActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by 夏夜晚凤 on 2017/4/25.
 */

@Singleton
@Component(modules = PresenterModule.class)
public interface LoginComponent {

    void inject(LoginActivity activity);
}
