package com.example.yiyoudoctor.activity;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.yiyoudoctor.Base.BaseActivity;
import com.example.yiyoudoctor.R;
import com.example.yiyoudoctor.fragment.HomeFragment;
import com.example.yiyoudoctor.fragment.MyFragment;
import com.example.yiyoudoctor.fragment.OrderFragment;
import com.example.yiyoudoctor.fragment.TestFragment;
import com.example.yiyoudoctor.other.MyBitmap;
import com.example.yiyoudoctor.other.getHW;

import java.util.ArrayList;


public class HomeActivity extends BaseActivity {

    public static getHW gethw;

    private BottomNavigationBar mBottomNavigationBar;
    private ArrayList<Fragment> fragments;
    private static Drawable drawable;

    private Fragment fragment1 = new HomeFragment();
    private Fragment fragment2 = new OrderFragment();
    private Fragment fragment3 = new TestFragment();
    private Fragment fragment4 = new MyFragment();

    private TextView title;

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


        gethw = new getHW(this);

        new Thread(new Runnable() {
            @Override
            public void run() {
                MyBitmap bitmap = new MyBitmap(HomeActivity.this);
                drawable = bitmap.vague();
            }
        }).start();

    }

    /**
     * 初始化ui
     **/
    @Override
    protected void initUI() {
        setContentView(R.layout.home_activity);
        assignViews();
        title = generateFindViewById(R.id.title);
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initListener() {
        mBottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                smartFragmentReplace(R.id.root, fragments.get(position));
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }

    @Override
    protected void initToolbar() {
        title.setText("首页");
    }

    public static Drawable getDrawable() {
        return drawable;
    }

    private void assignViews() {
        mBottomNavigationBar = generateFindViewById(R.id.bottom_bar);
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        mBottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.homepager, "首页").setInActiveColor(R.color.colorBlack).setActiveColor(R.color.colorone))
                .addItem(new BottomNavigationItem(R.drawable.contact, "预约").setInActiveColor(R.color.colorBlack).setActiveColor(R.color.colorone))
                .addItem(new BottomNavigationItem(R.drawable.contact, "联系人").setInActiveColor(R.color.colorBlack).setActiveColor(R.color.colorone))
                .addItem(new BottomNavigationItem(R.drawable.mymessage, "我的").setInActiveColor(R.color.colorBlack).setActiveColor(R.color.colorone))
                .setFirstSelectedPosition(0)
                .initialise();

        fragments = getFragments();
        setDefaultFragment();//设置默认选项
    }

    private void setDefaultFragment() {
        smartFragmentReplace(R.id.root, fragment1);
    }

    private ArrayList<Fragment> getFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(fragment1);
        fragments.add(fragment2);
        fragments.add(fragment3);
        fragments.add(fragment4);
        return fragments;
    }

}


