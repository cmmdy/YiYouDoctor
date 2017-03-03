package com.example.yiyoudoctor;

import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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
import com.example.yiyoudoctor.ui.OrderAdapter;

import static android.R.attr.id;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;
import static com.example.yiyoudoctor.R.id.iv;
import static com.example.yiyoudoctor.R.id.tv;


public class HomeActivity extends AppCompatActivity {

    /**
     * FragmentTabhost
     */
    private FragmentTabHost tabHost;

    /**
     * 布局填充器
     */
    private LayoutInflater mLayoutInflater;

    private TextView tv;
    private ImageView iv;

    private String[] tabText = {"首页", "预约", "联系人", "我的"};
    private int[] imageRes = new int[]{R.drawable.homepager, R.drawable.contact, R.drawable.contact, R.drawable.mymessage};
    private Class[] fragments = new Class[]{HomeFragment.class, HomeFragment.class, HomeFragment.class, HomeFragment.class};

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
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initTab();
    }




    private void initTab() {
        tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        tabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        for (int i = 0; i < tabText.length; i++) {

            View view = LayoutInflater.from(this).inflate(R.layout.item_tab, null);
            tv = (TextView) view.findViewById(R.id.tv);
            tv.setText(tabText[i]);
            iv = (ImageView) view.findViewById(R.id.iv);
            Glide.with(this).load(imageRes[i]).into(iv);
            RelativeLayout linearLayout = (RelativeLayout) view.findViewById(R.id.ll);
            linearLayout.getLayoutParams().height = (int) (HomeFragment.HEIGHT / 15);


//            RelativeLayout.LayoutParams params_tv = (RelativeLayout.LayoutParams) tv.getLayoutParams();
//            params_tv.addRule(RelativeLayout.BELOW, R.id.iv); //在iv下方
//            params_tv.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE); //设置文字居中对齐
//            RelativeLayout.LayoutParams params_iv = (RelativeLayout.LayoutParams) iv.getLayoutParams();
//            params_iv.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE); //设置文字居中对齐


            TabHost.TabSpec tabSpec = tabHost.newTabSpec(tabText[i]).setIndicator(view);
            tabHost.addTab(tabSpec, fragments[i], null);
            tabHost.setTag(i);
        }


    }


//    //自动把getCurrentTabView下的所有子View的selected状态设为true. 牛逼!
//    @Override
//    public void onTabChanged(String tabId) {
//
//        final TabWidget tabWidget = tabHost.getTabWidget(); //获取整个底部Tab的布局, 可以通过tabWidget.getChildCount和tabWidget.getChildAt来获取某个子View
//        int pos = tabHost.getCurrentTab(); //获取当前tab的位置
//
//    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(android.R.id.tabcontent, fragment);
        transaction.commit();
    }

}


