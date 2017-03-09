package com.example.yiyoudoctor.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yiyoudoctor.Base.BaseFragment;
import com.example.yiyoudoctor.R;

public class TestFragment extends BaseFragment {

    private String title = "联系人";

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_test;
    }

    public String getTitle() {
        return title;
    }
}