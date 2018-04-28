package com.arifinfrds.ppp_kelompok;

import android.app.Application;

import com.arifinfrds.ppp_kelompok.service.RestClient;

/**
 * Created by arifinfrds on 4/28/18.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        RestClient.initialize();
    }
}
