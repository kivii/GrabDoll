package com.kivii.grabdoll.util;

import com.kivii.grabdoll.BuildConfig;
import com.orhanobut.logger.Logger;


public class MLog {
    public static final boolean DEBUG = BuildConfig.DEBUG;

    public static void i(String str) {
        if (DEBUG) {
            Logger.i(str);
        }
    }

    public static void v(String str) {
        if (DEBUG) {
            Logger.v(str);
        }
    }

    public static void d(String str) {
        if (DEBUG) {
            Logger.d(str);
        }
    }

    public static void w(String str) {
        if (DEBUG) {
            Logger.w(str);
        }
    }

    public static void json(String str) {
        if (DEBUG) {
            Logger.json(str);
        }
    }

    public static void e(String str) {
        if (DEBUG) {
            Logger.e(str);
        }
    }
}
