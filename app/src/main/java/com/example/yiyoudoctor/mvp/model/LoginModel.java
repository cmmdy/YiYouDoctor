package com.example.yiyoudoctor.mvp.model;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import com.example.yiyoudoctor.Base.App;
import com.example.yiyoudoctor.Base.DemoContext;
import com.example.yiyoudoctor.mvp.contract.LoginContract;
import com.example.yiyoudoctor.util.my.NetUtils;

import org.json.JSONException;
import org.json.JSONObject;


import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

/**
 * Created by 夏夜晚凤 on 2017/4/21.
 */

public class LoginModel implements LoginContract.Model {

    String token;
    onConnectListener onConnectListener;

    public LoginModel(LoginContract.Model.onConnectListener onConnectListener) {
        this.onConnectListener = onConnectListener;
    }


    @Override
    /**
     * 获得token
     */
    public void getToken() {

        new AsyncTask<Void, Void, String>() {

            @Override
            protected String doInBackground(Void... params) {

                String result = NetUtils.sendGetRequest("token");
                return result;
            }

            @Override
            protected void onPostExecute(String result) {

                try {
                    if (result != null) {
                        JSONObject object = new JSONObject(result);
                        JSONObject jobj = object.getJSONObject("result");

                        if (object.getInt("code") == 200) {
                            token = jobj.getString("token");
                            connect(token);

                            SharedPreferences.Editor edit = DemoContext.getInstance().getSharedPreferences().edit();
                            edit.putString("DEMO_TOKEN", token);
                            edit.apply();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.execute();
    }

    public void connect(final String token) {

        if (onConnectListener.ifApp()) {

            /**
             * IMKit SDK调用第二步,建立与服务器的连接
             */
            RongIM.connect("VqGgB3fFDbso3VxrfOeuSc92zQ3Ar7D+FvdaUdbFdKChHmQx/kgPxw1xg++ZCCnIZKK6aMdPkquoCasGlAV3fA==", new RongIMClient.ConnectCallback() {

                /**
                 * Token 错误，在线上环境下主要是因为 Token 已经过期，您需要向 App Server 重新请求一个新的 Token
                 */
                @Override
                public void onTokenIncorrect() {

                    Log.e("LoginActivity", "--onTokenIncorrect");
                }

                /**
                 * 连接融云成功
                 * @param userid 当前 token
                 */
                @Override
                public void onSuccess(String userid) {

                    Log.e("LoginActivity", "--onSuccess" + userid);
                    onConnectListener.onSuccess();

                }

                /**
                 * 连接融云失败
                 * @param errorCode 错误码，可到官网 查看错误码对应的注释
                 *                  http://www.rongcloud.cn/docs/android.html#常见错误码
                 */
                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {

                    onConnectListener.onError();
                    Log.e("LoginActivity", "--onError" + errorCode);
                }
            });
        }
    }

}
