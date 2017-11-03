package com.github.kivii.grabdoll;

import android.app.Application;

import com.github.kivii.grabdoll.core.util.DaoUtils;


public class CApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DaoUtils.init(this);
    }
}
