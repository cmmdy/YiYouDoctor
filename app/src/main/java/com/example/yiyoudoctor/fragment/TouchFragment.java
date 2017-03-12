package com.example.yiyoudoctor.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yiyoudoctor.Base.BaseFragment;
import com.example.yiyoudoctor.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TouchFragment extends BaseFragment {


    private String title = "联系人";


    @Override
    protected int getLayoutId() {
        return R.layout.contact_fragment;
    }

    public String getTitle() {
        return title;
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

}