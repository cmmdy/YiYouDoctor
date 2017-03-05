package com.example.yiyoudoctor;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yiyoudoctor.ui.HomeFragment;
import com.example.yiyoudoctor.ui.MyFragment;
import com.example.yiyoudoctor.ui.MyMessage;
import com.example.yiyoudoctor.ui.OrderAdapter;
import com.example.yiyoudoctor.ui.OrderFragment;


public class HomeActivity extends AppCompatActivity implements TabHost.OnTabChangeListener {

    public static double WIDTH;

    public static double HEIGHT;

    /**
     * FragmentTabhost
     */
    private FragmentTabHost tabHost;

    /**
     * 布局填充器
     */

    private TextView tv;
    private ImageView iv;

    private String[] tabText = {"首页", "预约", "联系人", "我的"};
    private int[] imageRes = new int[]{R.drawable.homepager, R.drawable.contact, R.drawable.contact, R.drawable.mymessage};
    private Class[] fragments = new Class[]{HomeFragment.class, OrderFragment.class, MyFragment.class, MyFragment.class};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        if (Build.VERSION.SDK_INT >= 21) {
//            View decorView = getWindow().getDecorView();
//            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
//            decorView.setSystemUiVisibility(option);
//            getWindow().setStatusBarColor(Color.TRANSPARENT); //也可以设置成灰色透明的，比较符合Material Design的风格
//        }
//        if(Build.VERSION.SDK_INT >= 21){
//            View decorView = getWindow().getDecorView();
//            decorView.setSystemUiVisibility(
//                    View.SYSTEM_UI_FLAG_FULLSCREEN
//                    |View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//            );
//            getWindow().setStatusBarColor(Color.BLACK);
//        }

//        //全透状态栏
//        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Window window = getWindow();
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
//                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
//            window.setNavigationBarColor(Color.TRANSPARENT);
//        }
        //隐藏虚拟按键，并且全屏
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

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        //获得屏幕大小
        DisplayMetrics dm = getResources().getDisplayMetrics();
        WIDTH = dm.widthPixels;
        HEIGHT = dm.heightPixels;

        setContentView(R.layout.activity_home);

        tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        tabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        initTab();
        tabHost.setOnTabChangedListener(this);
        tabHost.setCurrentTab(0);

        //更改第一个选项卡颜色
        TextView tv1 = (TextView) tabHost.getTabWidget().getChildAt(0).findViewById(R.id.tab_tv);
        ImageView iv1 = (ImageView) tabHost.getTabWidget().getChildAt(0).findViewById(R.id.tab_iv);
        Glide.with(this).load(R.drawable.homepager_click).into(iv1);
        tv1.setTextColor(getResources().getColor(R.color.colorone));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    private void initTab() {
        for (int i = 0; i < tabText.length; i++) {

            View view = LayoutInflater.from(this).inflate(R.layout.item_tab, null, false);
            tv = (TextView) view.findViewById(R.id.tab_tv);
            tv.setText(tabText[i]);
            iv = (ImageView) view.findViewById(R.id.tab_iv);

            iv.getLayoutParams().height = (int) (HEIGHT / 20);
            iv.getLayoutParams().width = iv.getLayoutParams().height;
            Glide.with(this).load(imageRes[i]).into(iv);

            TabHost.TabSpec tabSpec = tabHost.newTabSpec(tabText[i]).setIndicator(view);
            tabHost.addTab(tabSpec, fragments[i], null);
            tabHost.setTag(i);
        }


    }


    @Override
    public void onTabChanged(String tabId) {
        TextView toolbar_text = (TextView) findViewById(R.id.toolbar_text);
        TextView tv1 = (TextView) tabHost.getTabWidget().getChildAt(0).findViewById(R.id.tab_tv);
        ImageView iv1 = (ImageView) tabHost.getTabWidget().getChildAt(0).findViewById(R.id.tab_iv);
        TextView tv2 = (TextView) tabHost.getTabWidget().getChildAt(1).findViewById(R.id.tab_tv);
        ImageView iv2 = (ImageView) tabHost.getTabWidget().getChildAt(1).findViewById(R.id.tab_iv);
        TextView tv3 = (TextView) tabHost.getTabWidget().getChildAt(2).findViewById(R.id.tab_tv);
        ImageView iv3 = (ImageView) tabHost.getTabWidget().getChildAt(2).findViewById(R.id.tab_iv);
        TextView tv4 = (TextView) tabHost.getTabWidget().getChildAt(3).findViewById(R.id.tab_tv);
        ImageView iv4 = (ImageView) tabHost.getTabWidget().getChildAt(3).findViewById(R.id.tab_iv);
        switch (tabId) {
            case "首页":
                Glide.with(this).load(R.drawable.homepager_click).into(iv1);
                tv1.setTextColor(getResources().getColor(R.color.colorone));
                Glide.with(this).load(R.drawable.contact).into(iv2);
                tv2.setTextColor(getResources().getColor(R.color.colorBlack));
                Glide.with(this).load(R.drawable.contact).into(iv3);
                tv3.setTextColor(getResources().getColor(R.color.colorBlack));
                Glide.with(this).load(R.drawable.mymessage).into(iv4);
                tv4.setTextColor(getResources().getColor(R.color.colorBlack));
                toolbar_text.setText("首页");
                break;
            case "预约":
                Glide.with(this).load(R.drawable.homepager).into(iv1);
                tv1.setTextColor(getResources().getColor(R.color.colorBlack));
                Glide.with(this).load(R.drawable.contact_click).into(iv2);
                tv2.setTextColor(getResources().getColor(R.color.colorone));
                Glide.with(this).load(R.drawable.contact).into(iv3);
                tv3.setTextColor(getResources().getColor(R.color.colorBlack));
                Glide.with(this).load(R.drawable.mymessage).into(iv4);
                tv4.setTextColor(getResources().getColor(R.color.colorBlack));
                toolbar_text.setText("预约");
                break;
            case "联系人":
                Glide.with(this).load(R.drawable.homepager).into(iv1);
                tv1.setTextColor(getResources().getColor(R.color.colorBlack));
                Glide.with(this).load(R.drawable.contact).into(iv2);
                tv2.setTextColor(getResources().getColor(R.color.colorBlack));
                Glide.with(this).load(R.drawable.contact_click).into(iv3);
                tv3.setTextColor(getResources().getColor(R.color.colorone));
                Glide.with(this).load(R.drawable.mymessage).into(iv4);
                tv4.setTextColor(getResources().getColor(R.color.colorBlack));
                toolbar_text.setText("联系人");
                break;
            case "我的":
                Glide.with(this).load(R.drawable.homepager).into(iv1);
                tv1.setTextColor(getResources().getColor(R.color.colorBlack));
                Glide.with(this).load(R.drawable.contact).into(iv2);
                tv2.setTextColor(getResources().getColor(R.color.colorBlack));
                Glide.with(this).load(R.drawable.contact).into(iv3);
                tv3.setTextColor(getResources().getColor(R.color.colorBlack));
                Glide.with(this).load(R.drawable.mymessage_click).into(iv4);
                tv4.setTextColor(getResources().getColor(R.color.colorone));
                toolbar_text.setText("我的");
                break;

        }

    }


}


