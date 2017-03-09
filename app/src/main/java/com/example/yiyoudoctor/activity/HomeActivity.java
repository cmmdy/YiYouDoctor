package com.example.yiyoudoctor.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.ImageButton;
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

import butterknife.BindView;


public class HomeActivity extends BaseActivity {

    public static getHW gethw;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.bottom_bar)
    BottomNavigationBar mBottomNavigationBar;

    private ArrayList<Fragment> fragments;
    private static Drawable drawable;

    private Fragment fragment1 = new HomeFragment();
    private Fragment fragment2 = new OrderFragment();
    private Fragment fragment3 = new TestFragment();
    private Fragment fragment4 = new MyFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gethw = new getHW(this);

        new Thread(new Runnable() {
            @Override
            public void run() {
                MyBitmap bitmap = new MyBitmap(HomeActivity.this);
                drawable = bitmap.vague(R.drawable.viewpager1, 300);
            }
        }).start();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.home_activity;
    }

    @Override
    protected void initUI() {
        assignViews();
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initListener() {
        mBottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                switch (position) {
                    case 0:
                        setTitle("首页");
                        break;
                    case 1:
                        setTitle("预约");
                        break;
                    case 2:
                        setTitle("联系人");
                        break;
                    case 3:
                        setTitle("我的");
                        break;
                }
                smartFragmentReplace(fragments.get(position));
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

    @Override
    protected int getFragmentContentId() {
        return R.id.root;
    }

    public static Drawable getDrawable() {
        return drawable;
    }

    private void assignViews() {
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        mBottomNavigationBar.setInActiveColor(R.color.colorBlack);
        mBottomNavigationBar.setActiveColor(R.color.colorone);
        mBottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.homepager, "首页"))
                .addItem(new BottomNavigationItem(R.drawable.contact, "预约"))
                .addItem(new BottomNavigationItem(R.drawable.contact, "联系人"))
                .addItem(new BottomNavigationItem(R.drawable.mymessage, "我的"))
                .setFirstSelectedPosition(0)
                .initialise();

        fragments = getFragments();
        setDefaultFragment();//设置默认选项

    }

    private void setDefaultFragment() {
        smartFragmentReplace(fragment1);
    }

    private ArrayList<Fragment> getFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(fragment1);
        fragments.add(fragment2);
        fragments.add(fragment3);
        fragments.add(fragment4);
        return fragments;
    }

    private void setTitle(String title1) {
        title.setText(title1);
    }

}


