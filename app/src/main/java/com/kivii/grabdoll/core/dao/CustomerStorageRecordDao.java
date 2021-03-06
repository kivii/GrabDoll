package com.kivii.grabdoll.core.dao;

import java.util.List;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

import com.kivii.grabdoll.core.bean.CustomerStorageRecord;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "CUSTOMER_STORAGE_RECORD".
*/
public class CustomerStorageRecordDao extends AbstractDao<CustomerStorageRecord, Long> {

    public static final String TABLENAME = "CUSTOMER_STORAGE_RECORD";

    /**
     * Properties of entity CustomerStorageRecord.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property AddTime = new Property(1, java.util.Date.class, "addTime", false, "ADD_TIME");
        public final static Property Count = new Property(2, int.class, "count", false, "COUNT");
        public final static Property Describe = new Property(3, String.class, "describe", false, "DESCRIBE");
        public final static Property StorageId = new Property(4, Long.class, "storageId", false, "STORAGE_ID");
        public final static Property UserNum = new Property(5, String.class, "userNum", false, "USER_NUM");
        public final static Property UserName = new Property(6, String.class, "userName", false, "USER_NAME");
    }

    private Query<CustomerStorageRecord> customerStorage_RecordListQuery;

    public CustomerStorageRecordDao(DaoConfig config) {
        super(config);
    }
    
    public CustomerStorageRecordDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"CUSTOMER_STORAGE_RECORD\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"ADD_TIME\" INTEGER," + // 1: addTime
                "\"COUNT\" INTEGER NOT NULL ," + // 2: count
                "\"DESCRIBE\" TEXT," + // 3: describe
                "\"STORAGE_ID\" INTEGER," + // 4: storageId
                "\"USER_NUM\" TEXT," + // 5: userNum
                "\"USER_NAME\" TEXT);"); // 6: userName
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"CUSTOMER_STORAGE_RECORD\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, CustomerStorageRecord entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        java.util.Date addTime = entity.getAddTime();
        if (addTime != null) {
            stmt.bindLong(2, addTime.getTime());
        }
        stmt.bindLong(3, entity.getCount());
 
        String describe = entity.getDescribe();
        if (describe != null) {
            stmt.bindString(4, describe);
        }
 
        Long storageId = entity.getStorageId();
        if (storageId != null) {
            stmt.bindLong(5, storageId);
        }
 
        String userNum = entity.getUserNum();
        if (userNum != null) {
            stmt.bindString(6, userNum);
        }
 
        String userName = entity.getUserName();
        if (userName != null) {
            stmt.bindString(7, userName);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, CustomerStorageRecord entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        java.util.Date addTime = entity.getAddTime();
        if (addTime != null) {
            stmt.bindLong(2, addTime.getTime());
        }
        stmt.bindLong(3, entity.getCount());
 
        String describe = entity.getDescribe();
        if (describe != null) {
            stmt.bindString(4, describe);
        }
 
        Long storageId = entity.getStorageId();
        if (storageId != null) {
            stmt.bindLong(5, storageId);
        }
 
        String userNum = entity.getUserNum();
        if (userNum != null) {
            stmt.bindString(6, userNum);
        }
 
        String userName = entity.getUserName();
        if (userName != null) {
            stmt.bindString(7, userName);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public CustomerStorageRecord readEntity(Cursor cursor, int offset) {
        CustomerStorageRecord entity = new CustomerStorageRecord( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : new java.util.Date(cursor.getLong(offset + 1)), // addTime
            cursor.getInt(offset + 2), // count
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // describe
            cursor.isNull(offset + 4) ? null : cursor.getLong(offset + 4), // storageId
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // userNum
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6) // userName
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, CustomerStorageRecord entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setAddTime(cursor.isNull(offset + 1) ? null : new java.util.Date(cursor.getLong(offset + 1)));
        entity.setCount(cursor.getInt(offset + 2));
        entity.setDescribe(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setStorageId(cursor.isNull(offset + 4) ? null : cursor.getLong(offset + 4));
        entity.setUserNum(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setUserName(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(CustomerStorageRecord entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(CustomerStorageRecord entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(CustomerStorageRecord entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "recordList" to-many relationship of CustomerStorage. */
    public List<CustomerStorageRecord> _queryCustomerStorage_RecordList(Long storageId) {
        synchronized (this) {
            if (customerStorage_RecordListQuery == null) {
                QueryBuilder<CustomerStorageRecord> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.StorageId.eq(null));
                queryBuilder.orderRaw("T.'ADD_TIME' DESC");
                customerStorage_RecordListQuery = queryBuilder.build();
            }
        }
        Query<CustomerStorageRecord> query = customerStorage_RecordListQuery.forCurrentThread();
        query.setParameter(0, storageId);
        return query.list();
    }

}
