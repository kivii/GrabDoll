package com.kivii.grabdoll;

import android.app.Application;

import com.kivii.grabdoll.util.DaoUtils;
import com.kivii.grabdoll.util.SPUtils;

public class CApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DaoUtils.init(this);
        SPUtils.init(this);
    }
}
