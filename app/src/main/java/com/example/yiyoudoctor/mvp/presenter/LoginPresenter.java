package com.example.yiyoudoctor.mvp.presenter;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.yiyoudoctor.Base.App;
import com.example.yiyoudoctor.Base.BaseMvp.BasePresenter;
import com.example.yiyoudoctor.mvp.contract.LoginContract;
import com.example.yiyoudoctor.mvp.model.LoginModel;
import com.example.yiyoudoctor.ui.activity.HomeActivity;
import com.example.yiyoudoctor.ui.activity.LoginActivity;
import com.example.yiyoudoctor.util.my.NetUtils;

import java.util.HashMap;
import java.util.Map;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

/**
 * Created by 夏夜晚凤 on 2017/4/21.
 */

public class LoginPresenter extends LoginContract.Presenter implements LoginContract.Model.onConnectListener {



    public LoginPresenter(LoginContract.View view) {
        mView = view;
        mModel = new LoginModel(this);

    }

    /**
     * 用户登录，用户登录成功，获得 cookie，将cookie 保存
     */
    @Override
    public void login() {

        new AsyncTask<Void, Void, String>() {

            @Override
            protected String doInBackground(Void... params) {

                Map<String, String> requestParameter = new HashMap<String, String>();

                requestParameter.put("email", "yang115@qq.com");
                requestParameter.put("password", "123456");

                String result = NetUtils.sendPostRequest("email_login", requestParameter);
                return result;
            }

            @Override
            protected void onPostExecute(String result) {
                mModel.getToken();
            }
        }.execute();
    }


    @Override
    public boolean ifApp() {
        return mView.getApplicationInfo().packageName.equals(App.getCurProcessName(mView.getApplicationContext()));
    }

    @Override
    public void onSuccess() {
        mView.startActivity(new Intent(mView, HomeActivity.class));
        mView.finish();
    }

    @Override
    public void onError() {
        Toast.makeText(mView, "连接失败", Toast.LENGTH_SHORT).show();
    }
}
