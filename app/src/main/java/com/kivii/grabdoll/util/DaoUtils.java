package com.kivii.grabdoll.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.kivii.grabdoll.core.dao.DaoMaster;
import com.kivii.grabdoll.core.dao.DaoSession;

public class DaoUtils {
    public static final String DB_NAME = "GrabDoll.db";
    public static DaoSession daoSession;
    public static SQLiteDatabase database;

    private DaoUtils() {}

    public static void init(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, DB_NAME);
        database = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(database);
        daoSession = daoMaster.newSession();
    }
}
