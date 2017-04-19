package com.example.yiyoudoctor.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.yiyoudoctor.Base.BaseActivity;
import com.example.yiyoudoctor.R;

import butterknife.BindView;
import butterknife.OnClick;

public class FeedbackActivity extends BaseActivity {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.backtv)
    TextView backtv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.feedback_activity;
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
        title.setText("反馈门诊结果");
        backtv.setText("");

    }

    @Override
    protected int getFragmentContentId() {
        return 0;
    }

    @OnClick({R.id.backll, R.id.feedback})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backll:
                finish();
                break;
            case R.id.feedback:
                finish();
                break;
        }
    }
}
