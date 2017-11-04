package com.kivii.grabdoll.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.kivii.grabdoll.core.dao.DaoMaster;
import com.kivii.grabdoll.core.dao.DaoSession;

public class DaoUtils {
    public static DaoSession daoSession;

    private DaoUtils() {}

    public static void init(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "JungleHappy_db");
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }
}
