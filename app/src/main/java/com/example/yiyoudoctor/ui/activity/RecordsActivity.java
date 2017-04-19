package com.example.yiyoudoctor.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yiyoudoctor.Base.BaseActivity;
import com.example.yiyoudoctor.R;

import butterknife.BindView;
import butterknife.OnClick;

public class RecordsActivity extends BaseActivity {

    @BindView(R.id.records)
    TextView records;
    @BindView(R.id.backtv)
    TextView backtv;
    @BindView(R.id.backll)
    LinearLayout backll;
    @BindView(R.id.title)
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.records_activity;
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
        title.setText("门诊记录");
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
                openActivity(FeedbackActivity.class);
                break;
        }
    }
}
