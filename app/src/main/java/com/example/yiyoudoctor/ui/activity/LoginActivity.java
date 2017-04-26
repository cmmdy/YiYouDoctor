package com.example.yiyoudoctor.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yiyoudoctor.R;
import com.example.yiyoudoctor.mvp.contract.LoginContract;
import com.example.yiyoudoctor.mvp.dagger2.component.DaggerLoginComponent;
import com.example.yiyoudoctor.mvp.dagger2.component.LoginComponent;
import com.example.yiyoudoctor.mvp.dagger2.module.PresenterModule;


import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

public class LoginActivity extends LoginContract.View{

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.accountEdittext)
    EditText accountEdittext;
    @BindView(R.id.pwdEdittext)
    EditText pwdEdittext;

    @Inject
    public LoginContract.Presenter loginPresenter;


    /**
     * token 的主要作用是身份授权和安全，因此不能通过客户端直接访问融云服务器获取 token，
     * 您必须通过 Server API 从融云服务器 获取 token 返回给您的 App，并在之后连接时使用
     */
    private String token;
    private String account;
    private String password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        ButterKnife.bind(this);
        LoginComponent loginComponent = DaggerLoginComponent
                .builder()
                .presenterModule(new PresenterModule(this))
                .build();
        loginComponent.inject(this);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.login_activity;
    }

    @Override
    protected void initUI() {

    }

    @Override
    protected void initData() {
        account = accountEdittext.getText().toString();
        password = pwdEdittext.getText().toString();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initToolbar() {
        title.setText("用户登录");
    }

    @Override
    protected int getFragmentContentId() {
        return 0;
    }

    @OnClick({R.id.login_button, R.id.forget, R.id.sign})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_button:
                loginPresenter.login();
                break;
            case R.id.forget:
                Bundle bundle = new Bundle();
                bundle.putBoolean("forget", true);
                openActivity(SignFirstActivity.class, bundle);
                break;
            case R.id.sign:
                openActivity(SignFirstActivity.class);
                break;
        }
    }


}
