package com.example.yiyoudoctor.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yiyoudoctor.Base.BaseActivity;
import com.example.yiyoudoctor.Base.BaseFragment;
import com.example.yiyoudoctor.R;
import com.example.yiyoudoctor.adapter.OrderAdapter;
import com.example.yiyoudoctor.model.Patient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 夏夜晚凤 on 2017/3/2.
 */

public class OrderFragment extends BaseFragment {

    private String title = "预约";

    private List<Patient> patientList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        initPatients();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        OrderAdapter adapter = new OrderAdapter(patientList, getContext());
        recyclerView.setAdapter(adapter);
    }

    public String getTitle() {
        return title;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.order_fragment;
    }

    private void initPatients() {
        for (int i = 0; i < 4; i++) {
            Patient patient1 = new Patient(R.drawable.viewpager3, "王患者", "预约时间", "20", "男", "情况");
            patientList.add(patient1);
        }
    }


}
