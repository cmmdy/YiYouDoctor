package com.example.yiyoudoctor.Base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.yiyoudoctor.R;

import java.util.Stack;

/**
 * BaseActivity
 *
 * @author RaphetS
 */
public abstract class BaseActivity extends AppCompatActivity {

    /**
     * 用来保存所有已打开的Activity
     */
    private static Stack<Activity> listActivity = new Stack<Activity>();

    /**
     * 标题栏
     */
    public Toolbar toolbar;

    /**
     * 提示信息
     **/
    private Toast mToast;

    /**
     * 记录上次点击按钮的时间
     **/
    private long lastClickTime;
    /**
     * 按钮连续点击最低间隔时间 单位：毫秒
     **/
    public final static int CLICK_TIME = 500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置activity为无标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 将activity推入栈中
        listActivity.push(this);
        // 初始化ui
        initUI();
        // 初始化数据
        initData();
        // 事件监听
        initListener();
        //标题栏
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        initToolbar();
        setSupportActionBar(toolbar);

    }


    /**
     * 初始化ui
     **/
    protected abstract void initUI();

    /**
     * 初始化数据
     **/
    protected abstract void initData();

    /**
     * 初始化监听
     **/
    protected abstract void initListener();

    /**
     * 初始化标题栏
     **/
    protected abstract void initToolbar();

    /**
     * 获得toolbar对象
     * @return
     */
    public Toolbar getToolbar(){
        return toolbar;
    }

    protected <T extends View> T generateFindViewById(int id) {
        //return返回view时,加上泛型T
        return (T) findViewById(id);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    /**
     * 保存activity状态
     **/
    protected void saveInstanceState(Bundle outState) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    public void onBack(View v) {
        finish();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 从栈中移除当前activity
        if (listActivity.contains(this)) {
            listActivity.remove(this);
        }

    }

    /********************** activity跳转 **********************************/
    public void openActivity(Class<?> targetActivityClass) {
        openActivity(targetActivityClass, null);
    }

    public void openActivity(Class<?> targetActivityClass, Bundle bundle) {
        Intent intent = new Intent(this, targetActivityClass);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public void openActivityAndCloseThis(Class<?> targetActivityClass) {
        openActivity(targetActivityClass);
        this.finish();
    }

    public void openActivityAndCloseThis(Class<?> targetActivityClass, Bundle bundle) {
        openActivity(targetActivityClass, bundle);
        this.finish();
    }

    /***************************************************************/

    /**
     * 验证上次点击按钮时间间隔，防止重复点击
     */
    public boolean verifyClickTime() {
        if (System.currentTimeMillis() - lastClickTime <= CLICK_TIME) {
            return false;
        }
        lastClickTime = System.currentTimeMillis();
        return true;
    }

    /**
     * 收起键盘
     */
    public void closeInputMethod() {
        // 收起键盘
        View view = getWindow().peekDecorView();// 用于判断虚拟软键盘是否是显示的
        if (view != null) {
            InputMethodManager inputmanger = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 获取string
     *
     * @param mRid
     * @return
     */
    public String getStringMethod(int mRid) {
        return this.getResources().getString(mRid);
    }

    /**
     * 获取demin
     *
     * @param mRid
     * @return
     */
    protected int getDemonIntegerMethod(int mRid) {
        return (int) this.getResources().getDimension(mRid);
    }

    /**
     * 关闭所有(前台、后台)Activity,注意：请已BaseActivity为父类
     */
    protected static void finishAll() {
        int len = listActivity.size();
        for (int i = 0; i < len; i++) {
            Activity activity = listActivity.pop();
            activity.finish();
        }
    }

    /***************** 双击退出程序 ************************************************/
    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (KeyEvent.KEYCODE_BACK == keyCode) {
            // 判断是否在两秒之内连续点击返回键，是则退出，否则不退出
            if (System.currentTimeMillis() - exitTime > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                // 将系统当前的时间赋值给exitTime
                exitTime = System.currentTimeMillis();
            } else {
                finishAll();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /*
     * ************Fragement相关方法************************************************
     *
     */
    private Fragment currentFragment;

    /**
     * Fragment替换(当前destrory,新的create)
     */
    public void fragmentReplace(int target, Fragment toFragment, boolean backStack) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        String toClassName = toFragment.getClass().getSimpleName();
        if (manager.findFragmentByTag(toClassName) == null) {
            transaction.replace(target, toFragment, toClassName);
            if (backStack) {
                transaction.addToBackStack(toClassName);
            }
            transaction.commit();
        }
    }

    /**
     * Fragment替换(核心为隐藏当前的,显示现在的,用过的将不会destrory与create)
     */
    public void smartFragmentReplace(int target, Fragment toFragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        // 如有当前在使用的->隐藏当前的
        if (currentFragment != null) {
            transaction.hide(currentFragment);
        }
        String toClassName = toFragment.getClass().getSimpleName();
        // toFragment之前添加使用过->显示出来
        if (manager.findFragmentByTag(toClassName) != null) {
            transaction.show(toFragment);
        } else {// toFragment还没添加使用过->添加上去
            transaction.add(target, toFragment, toClassName);
        }
        transaction.commit();
        // toFragment更新为当前的
        currentFragment = toFragment;
    }

    /***********************************************************************/
}