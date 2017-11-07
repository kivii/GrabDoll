package com.kivii.grabdoll.util;

import android.app.Activity;
import android.content.Intent;
import android.os.Environment;

import java.io.File;

public class AppUtils {

    private AppUtils(){}

    public static void backHome(Activity activity) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        activity.startActivity(intent);
    }

    public static String getAppDir() {
        File dir = new File(Environment.getExternalStorageDirectory(), "GrabDoll");
        if (!dir.exists() || !dir.isDirectory()) {
            dir.mkdirs();
        }
        return dir.getAbsolutePath();
    }

    public static String getDatabasePath() {
        File dir = new File(getAppDir(), "database");
        if (!dir.exists() || !dir.isDirectory()) {
            dir.mkdirs();
        }
        return dir.getAbsolutePath() + File.separator + DaoUtils.DB_NAME;
    }
}
