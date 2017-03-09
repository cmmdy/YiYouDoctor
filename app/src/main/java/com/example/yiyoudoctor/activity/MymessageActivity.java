package com.example.yiyoudoctor.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yiyoudoctor.Base.BaseActivity;
import com.example.yiyoudoctor.R;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

public class MymessageActivity extends BaseActivity {
    @BindView(R.id.backiv)
    ImageView backiv;
    @BindView(R.id.backtv)
    TextView backtv;
    @BindView(R.id.backll)
    LinearLayout backll;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.civ1)
    CircleImageView civ1;
    @BindView(R.id.imageLayout)
    RelativeLayout imagerl;
    @BindView(R.id.name_tv)
    TextView nametv;
    @BindView(R.id.phone_tv)
    TextView phonetv;
    @BindView(R.id.sex_tv)
    TextView sextv;
    @BindView(R.id.age_tv)
    TextView agetv;
    @BindView(R.id.as_tv)
    TextView astv;
    @BindView(R.id.al_tv)
    TextView altv;
    @BindView(R.id.adress_tv)
    TextView matv;
    @BindView(R.id.person_tv)
    TextView persontv;
    @BindView(R.id.civ2)
    CircleImageView civ2;
    @BindView(R.id.messageiv)
    ImageView messageiv;
    @BindView(R.id.messagename_tv)
    TextView mntv;
    @BindView(R.id.messagedoctor_tv1)
    TextView mdtv1;
    @BindView(R.id.messagescore_tv)
    TextView mstv;
    @BindView(R.id.messagephone_tv)
    TextView mptv;
    @BindView(R.id.phonell)
    LinearLayout phonell;
    @BindView(R.id.addressll)
    LinearLayout addressll;
    @BindView(R.id.civ3)
    CircleImageView civ3;
    @BindView(R.id.messagedoctor_tv2)
    TextView mdtv2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.mymessage_activity;
    }


    @Override
    protected void initUI() {

        imagerl = (RelativeLayout) findViewById(R.id.imageLayout);
        civ1 = (CircleImageView) findViewById(R.id.civ1);

        nametv = (TextView) findViewById(R.id.name_tv);
        phonetv = (TextView) findViewById(R.id.phone_tv);
        sextv = (TextView) findViewById(R.id.sex_tv);
        agetv = (TextView) findViewById(R.id.age_tv);
        astv = (TextView) findViewById(R.id.as_tv);
        altv = (TextView) findViewById(R.id.al_tv);

        civ2 = (CircleImageView) findViewById(R.id.civ2);
        persontv = (TextView) findViewById(R.id.person_tv);

        messageiv = (ImageView) findViewById(R.id.messageiv);
        mntv = (TextView) findViewById(R.id.messagename_tv);
        mdtv1 = (TextView) findViewById(R.id.messagedoctor_tv1);
        mstv = (TextView) findViewById(R.id.messagescore_tv);
        phonell = (LinearLayout) findViewById(R.id.phonell);
        mptv = (TextView) findViewById(R.id.messagephone_tv);
        addressll = (LinearLayout) findViewById(R.id.addressll);
        matv = (TextView) findViewById(R.id.adress_tv);
        civ3 = (CircleImageView) findViewById(R.id.civ3);
        mdtv2 = (TextView) findViewById(R.id.messagedoctor_tv2);

        backll = (LinearLayout) findViewById(R.id.backll);
        backtv = generateFindViewById(R.id.backtv);
        title = generateFindViewById(R.id.title);
    }

    @Override
    protected void initData() {

        imagerl.setBackground(HomeActivity.getDrawable());

        Glide.with(this).load(R.drawable.viewpager1).into(civ1);

        nametv.setText("姓名");
        phonetv.setText("电话");
        sextv.setText("性别");
        agetv.setText("年龄");
        astv.setText("主治科目");
        altv.setText("认证等级");

        Glide.with(this).load(R.drawable.viewpager3).into(civ2);
        persontv.setText("王医师");

        Glide.with(this).load(R.drawable.viewpager4).into(messageiv);
        mntv.setText("中医世家");
        mdtv1.setText("负责医师:王医师");
        mstv.setText("评分：5.0");
        mptv.setText("88888888");
        matv.setText("下沙街道");
        Glide.with(this).load(R.drawable.viewpager2).into(civ3);
        mdtv2.setText("王医师");

    }

    @Override
    protected void initListener() {
        backll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initToolbar() {
        backtv.setText("个人信息");
        title.setText("我的");
    }

    @Override
    protected int getFragmentContentId() {
        return 0;
    }

}
