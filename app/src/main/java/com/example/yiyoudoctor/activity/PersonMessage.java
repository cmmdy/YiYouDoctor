package com.example.yiyoudoctor.activity;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yiyoudoctor.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class PersonMessage extends AppCompatActivity{

    public static double WIDTH;

    public static double HEIGHT;

    RelativeLayout imagerl;
    CircleImageView civ1;

    TextView nametv;
    TextView phonetv;
    TextView sextv;
    TextView agetv;
    TextView astv;
    TextView altv;

    CircleImageView civ2;
    TextView persontv;

    ImageView messageiv;
    TextView mntv;
    TextView mdtv1;
    TextView mstv;
    LinearLayout phonell;
    TextView mptv;
    LinearLayout addressll;
    TextView matv;
    CircleImageView civ3;
    TextView mdtv2;

    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }

        setContentView(R.layout.activity_person_message);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        LinearLayout backll = (LinearLayout) findViewById(R.id.backll);
        backll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//        getSupportActionBar().setDisplayShowHomeEnabled(true);

        initView();
    }

    public void initView(){
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



//        imagerl.getLayoutParams().height = (int) (HomeActivity.gethw.getHEIGHT() / 4);
//        civ1.getLayoutParams().height = (int) (HomeActivity.gethw.getHEIGHT() / 5.5);
//
//        nametv.getLayoutParams().height = (int) (HomeActivity.gethw.getHEIGHT() / 11);
//        phonetv.getLayoutParams().height = (int) (HomeActivity.gethw.getHEIGHT() / 11);
//        sextv.getLayoutParams().height = (int) (HomeActivity.gethw.getHEIGHT() / 11);
//        agetv.getLayoutParams().height = (int) (HomeActivity.gethw.getHEIGHT() / 11);
//        altv.getLayoutParams().height = (int) (HomeActivity.gethw.getHEIGHT() / 11);
//        astv.getLayoutParams().height = (int) (HomeActivity.gethw.getHEIGHT() / 11);
//
//        civ2.getLayoutParams().height = (int) (HomeActivity.gethw.getHEIGHT() / 6);
//        persontv.getLayoutParams().height = (int) (HomeActivity.gethw.getHEIGHT() / 11);
//
//        messageiv.getLayoutParams().height = (int) (HomeActivity.gethw.getHEIGHT() / 5);
//        mntv.getLayoutParams().height = (int) (HomeActivity.gethw.getHEIGHT() / 9);
//        mdtv1.getLayoutParams().height = (int) (HomeActivity.gethw.getHEIGHT() / 9);
//        mstv.getLayoutParams().height = (int) (HomeActivity.gethw.getHEIGHT() / 10);
//        phonell.getLayoutParams().height = (int) (HomeActivity.gethw.getHEIGHT() / 8);
//        mptv.getLayoutParams().height = (int) (HomeActivity.gethw.getHEIGHT() / 10);
//        addressll.getLayoutParams().height = (int) (HomeActivity.gethw.getHEIGHT() / 8);
//        matv.getLayoutParams().height = (int) (HomeActivity.gethw.getHEIGHT() / 10);
//        civ3.getLayoutParams().height = (int) (HomeActivity.gethw.getHEIGHT() / 6);
//        mdtv2.getLayoutParams().height = (int) (HomeActivity.gethw.getHEIGHT() / 11);


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






//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()){
//            case android.R.id.home:
//                finish();
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
