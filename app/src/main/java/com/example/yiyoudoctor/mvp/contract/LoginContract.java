package com.example.yiyoudoctor.mvp.contract;

import com.example.yiyoudoctor.Base.BaseMvp.BaseModel;
import com.example.yiyoudoctor.Base.BaseMvp.BasePresenter;
import com.example.yiyoudoctor.Base.BaseMvp.BaseView;

/**
 * Created by 夏夜晚凤 on 2017/4/19.
 */

public interface LoginContract {

    abstract class View extends BaseView{

    }

    interface Model extends BaseModel{

        void getToken();
        interface onConnectListener{

            boolean ifApp();
            void onSuccess();
            void onError();
        }
    }

    abstract class Presenter extends BasePresenter<View, Model>{
        public abstract void login();
    }

}
