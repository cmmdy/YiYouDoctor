package com.example.yiyoudoctor.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yiyoudoctor.Base.BaseActivity;
import com.example.yiyoudoctor.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.accountEdittext)
    EditText accountEdittext;
    @BindView(R.id.pwdEdittext)
    EditText pwdEdittext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        ButterKnife.bind(this);
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
                openActivity(HomeActivity.class);
                onDestroy();
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
