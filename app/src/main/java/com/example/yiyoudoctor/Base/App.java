package com.example.yiyoudoctor.Base;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

import io.rong.imkit.RongIM;


/**
 * Created by 夏夜晚凤 on 2017/3/22.
 */

public class App extends Application {

    @Override
    public void onCreate() {

        super.onCreate();

        /**
         *
         * OnCreate 会被多个进程重入，这段保护代码，确保只有您需要使用 RongIM 的进程和 Push 进程执行了 init。
         * io.rong.push 为融云 push 进程名称，不可修改。
         //         */


        /**
         * IMKit SDK调用第一步 初始化
         */
        RongIM.init(this);

        if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext()))) {

            DemoContext.init(this);
        }
    }

    /**
     * 获得当前进程的名字
     *
     * @param context
     * @return
     */
    public static String getCurProcessName(Context context) {

        int pid = android.os.Process.myPid();

        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);

        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
                .getRunningAppProcesses()) {

            if (appProcess.pid == pid) {

                return appProcess.processName;
            }
        }
        return null;
    }


}
