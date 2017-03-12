package com.example.yiyoudoctor.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.yiyoudoctor.Base.BaseActivity;
import com.example.yiyoudoctor.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Authentication extends BaseActivity {

    @BindView(R.id.backtv)
    TextView backtv;
    @BindView(R.id.title)
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authentication_activity);
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.authentication_activity;
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
        backtv.setText("");
        title.setText("医师认证");
    }

    @Override
    protected int getFragmentContentId() {
        return 0;
    }


    @OnClick({R.id.backll, R.id.textView6})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backll:
                finish();
                break;
            case R.id.textView6:
                finish();
                break;
        }
    }
}
