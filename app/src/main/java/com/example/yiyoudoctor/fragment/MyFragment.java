package com.example.yiyoudoctor.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yiyoudoctor.activity.HomeActivity;
import com.example.yiyoudoctor.activity.PersonMessage;
import com.example.yiyoudoctor.R;
import com.example.yiyoudoctor.adapter.MyAdapter;
import com.example.yiyoudoctor.model.MyMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 夏夜晚凤 on 2017/3/5.
 */

public class MyFragment extends Fragment {
    private int[] imgIdArray;

    private List<MyMessage> mmList = new ArrayList<>();

    private View rootView;
    private View viewheadmm;

    private RelativeLayout head_rl;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (viewheadmm == null) {
            viewheadmm = inflater.inflate(R.layout.my_listhead, null, false);
            head_rl = (RelativeLayout) viewheadmm.findViewById(R.id.mmheadrl);
            ImageView head_iv = (ImageView) viewheadmm.findViewById(R.id.mmheadiv);
            ImageView head_back_iv = (ImageView) viewheadmm.findViewById(R.id.backheadiv);
            TextView head_tv = (TextView) viewheadmm.findViewById(R.id.mmheadtv);
            head_iv.getLayoutParams().height = (int) (HomeActivity.gethw.getHEIGHT() / 14);
            head_iv.getLayoutParams().width = head_iv.getLayoutParams().height;
            head_back_iv.getLayoutParams().height = (int) (HomeActivity.gethw.getHEIGHT() / 45);
            head_back_iv.getLayoutParams().width = head_back_iv.getLayoutParams().height;
            Glide.with(getActivity()).load(R.drawable.mymessage_click).into(head_iv);
            Glide.with(getActivity()).load(R.drawable.to).into(head_back_iv);
            head_tv.setText("王患者");
        }

        if (rootView == null) {
            rootView = inflater.inflate(R.layout.my_fragment, null, false);
            imgIdArray = new int[]{R.drawable.doctor, R.drawable.publisharticle, R.drawable.collection, R.drawable.maillist, R.drawable.setting};
            MyAdapter myAdapter = new MyAdapter(getActivity(), R.layout.my_toolbar, mmList);
            ListView mmListView = (ListView) rootView.findViewById(R.id.mmListView);
            initmmList();

            mmListView.addHeaderView(viewheadmm);
            mmListView.setAdapter(myAdapter);
        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }

        head_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PersonMessage.class);
                startActivity(intent);
            }
        });

        return rootView;
    }

    public void initmmList() {
        MyMessage mm1 = new MyMessage(imgIdArray[0], "医师认证", R.drawable.to);
        mmList.add(mm1);
        MyMessage mm2 = new MyMessage(imgIdArray[1], "发布文章", R.drawable.to);
        mmList.add(mm2);
        MyMessage mm3 = new MyMessage(imgIdArray[2], "我的收藏", R.drawable.to);
        mmList.add(mm3);
        MyMessage mm4 = new MyMessage(imgIdArray[3], "通讯录", R.drawable.to);
        mmList.add(mm4);
        MyMessage mm5 = new MyMessage(imgIdArray[4], "设置", R.drawable.to);
        mmList.add(mm5);
    }


    public void myMessage(View view) {
        Intent intent = new Intent(getActivity(), PersonMessage.class);
        startActivity(intent);
    }
}

