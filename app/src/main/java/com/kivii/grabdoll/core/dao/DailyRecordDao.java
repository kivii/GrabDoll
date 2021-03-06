package com.kivii.grabdoll.core.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.kivii.grabdoll.core.bean.DailyRecord;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "DAILY_RECORD".
*/
public class DailyRecordDao extends AbstractDao<DailyRecord, Long> {

    public static final String TABLENAME = "DAILY_RECORD";

    /**
     * Properties of entity DailyRecord.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property AddTime = new Property(1, java.util.Date.class, "addTime", false, "ADD_TIME");
        public final static Property Time = new Property(2, java.util.Date.class, "time", false, "TIME");
        public final static Property InCoin = new Property(3, int.class, "inCoin", false, "IN_COIN");
        public final static Property OutToys = new Property(4, int.class, "outToys", false, "OUT_TOYS");
        public final static Property Remark = new Property(5, String.class, "remark", false, "REMARK");
        public final static Property UserNum = new Property(6, String.class, "userNum", false, "USER_NUM");
        public final static Property UserName = new Property(7, String.class, "userName", false, "USER_NAME");
    }


    public DailyRecordDao(DaoConfig config) {
        super(config);
    }
    
    public DailyRecordDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"DAILY_RECORD\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"ADD_TIME\" INTEGER," + // 1: addTime
                "\"TIME\" INTEGER," + // 2: time
                "\"IN_COIN\" INTEGER NOT NULL ," + // 3: inCoin
                "\"OUT_TOYS\" INTEGER NOT NULL ," + // 4: outToys
                "\"REMARK\" TEXT," + // 5: remark
                "\"USER_NUM\" TEXT," + // 6: userNum
                "\"USER_NAME\" TEXT);"); // 7: userName
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"DAILY_RECORD\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, DailyRecord entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        java.util.Date addTime = entity.getAddTime();
        if (addTime != null) {
            stmt.bindLong(2, addTime.getTime());
        }
 
        java.util.Date time = entity.getTime();
        if (time != null) {
            stmt.bindLong(3, time.getTime());
        }
        stmt.bindLong(4, entity.getInCoin());
        stmt.bindLong(5, entity.getOutToys());
 
        String remark = entity.getRemark();
        if (remark != null) {
            stmt.bindString(6, remark);
        }
 
        String userNum = entity.getUserNum();
        if (userNum != null) {
            stmt.bindString(7, userNum);
        }
 
        String userName = entity.getUserName();
        if (userName != null) {
            stmt.bindString(8, userName);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, DailyRecord entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        java.util.Date addTime = entity.getAddTime();
        if (addTime != null) {
            stmt.bindLong(2, addTime.getTime());
        }
 
        java.util.Date time = entity.getTime();
        if (time != null) {
            stmt.bindLong(3, time.getTime());
        }
        stmt.bindLong(4, entity.getInCoin());
        stmt.bindLong(5, entity.getOutToys());
 
        String remark = entity.getRemark();
        if (remark != null) {
            stmt.bindString(6, remark);
        }
 
        String userNum = entity.getUserNum();
        if (userNum != null) {
            stmt.bindString(7, userNum);
        }
 
        String userName = entity.getUserName();
        if (userName != null) {
            stmt.bindString(8, userName);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public DailyRecord readEntity(Cursor cursor, int offset) {
        DailyRecord entity = new DailyRecord( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : new java.util.Date(cursor.getLong(offset + 1)), // addTime
            cursor.isNull(offset + 2) ? null : new java.util.Date(cursor.getLong(offset + 2)), // time
            cursor.getInt(offset + 3), // inCoin
            cursor.getInt(offset + 4), // outToys
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // remark
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // userNum
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7) // userName
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, DailyRecord entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setAddTime(cursor.isNull(offset + 1) ? null : new java.util.Date(cursor.getLong(offset + 1)));
        entity.setTime(cursor.isNull(offset + 2) ? null : new java.util.Date(cursor.getLong(offset + 2)));
        entity.setInCoin(cursor.getInt(offset + 3));
        entity.setOutToys(cursor.getInt(offset + 4));
        entity.setRemark(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setUserNum(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setUserName(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(DailyRecord entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(DailyRecord entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(DailyRecord entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
