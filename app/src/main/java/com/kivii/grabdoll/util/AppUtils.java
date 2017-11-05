package com.kivii.grabdoll.util;

import android.app.Activity;
import android.content.Intent;

public class AppUtils {

    private AppUtils(){}

    public static void backHome(Activity activity) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        activity.startActivity(intent);
    }
}
