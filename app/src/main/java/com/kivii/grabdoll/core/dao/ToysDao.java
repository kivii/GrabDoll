package com.kivii.grabdoll.core.dao;

import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.SqlUtils;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

import com.kivii.grabdoll.core.bean.DailyRecord;

import com.kivii.grabdoll.core.bean.Toys;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "TOYS".
*/
public class ToysDao extends AbstractDao<Toys, Long> {

    public static final String TABLENAME = "TOYS";

    /**
     * Properties of entity Toys.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property GroupId = new Property(1, Long.class, "groupId", false, "GROUP_ID");
        public final static Property Name = new Property(2, String.class, "name", false, "NAME");
        public final static Property AddTime = new Property(3, java.util.Date.class, "addTime", false, "ADD_TIME");
        public final static Property Describe = new Property(4, String.class, "describe", false, "DESCRIBE");
        public final static Property SortNum = new Property(5, int.class, "sortNum", false, "SORT_NUM");
        public final static Property RecordId = new Property(6, Long.class, "recordId", false, "RECORD_ID");
    }

    private DaoSession daoSession;

    private Query<Toys> machineGroup_ToysListQuery;

    public ToysDao(DaoConfig config) {
        super(config);
    }
    
    public ToysDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"TOYS\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"GROUP_ID\" INTEGER," + // 1: groupId
                "\"NAME\" TEXT," + // 2: name
                "\"ADD_TIME\" INTEGER," + // 3: addTime
                "\"DESCRIBE\" TEXT," + // 4: describe
                "\"SORT_NUM\" INTEGER NOT NULL ," + // 5: sortNum
                "\"RECORD_ID\" INTEGER);"); // 6: recordId
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"TOYS\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Toys entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long groupId = entity.getGroupId();
        if (groupId != null) {
            stmt.bindLong(2, groupId);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(3, name);
        }
 
        java.util.Date addTime = entity.getAddTime();
        if (addTime != null) {
            stmt.bindLong(4, addTime.getTime());
        }
 
        String describe = entity.getDescribe();
        if (describe != null) {
            stmt.bindString(5, describe);
        }
        stmt.bindLong(6, entity.getSortNum());
 
        Long recordId = entity.getRecordId();
        if (recordId != null) {
            stmt.bindLong(7, recordId);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Toys entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long groupId = entity.getGroupId();
        if (groupId != null) {
            stmt.bindLong(2, groupId);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(3, name);
        }
 
        java.util.Date addTime = entity.getAddTime();
        if (addTime != null) {
            stmt.bindLong(4, addTime.getTime());
        }
 
        String describe = entity.getDescribe();
        if (describe != null) {
            stmt.bindString(5, describe);
        }
        stmt.bindLong(6, entity.getSortNum());
 
        Long recordId = entity.getRecordId();
        if (recordId != null) {
            stmt.bindLong(7, recordId);
        }
    }

    @Override
    protected final void attachEntity(Toys entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Toys readEntity(Cursor cursor, int offset) {
        Toys entity = new Toys( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // groupId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // name
            cursor.isNull(offset + 3) ? null : new java.util.Date(cursor.getLong(offset + 3)), // addTime
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // describe
            cursor.getInt(offset + 5), // sortNum
            cursor.isNull(offset + 6) ? null : cursor.getLong(offset + 6) // recordId
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Toys entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setGroupId(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setAddTime(cursor.isNull(offset + 3) ? null : new java.util.Date(cursor.getLong(offset + 3)));
        entity.setDescribe(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setSortNum(cursor.getInt(offset + 5));
        entity.setRecordId(cursor.isNull(offset + 6) ? null : cursor.getLong(offset + 6));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Toys entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Toys entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Toys entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "toysList" to-many relationship of MachineGroup. */
    public List<Toys> _queryMachineGroup_ToysList(Long groupId) {
        synchronized (this) {
            if (machineGroup_ToysListQuery == null) {
                QueryBuilder<Toys> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.GroupId.eq(null));
                queryBuilder.orderRaw("T.'SORT_NUM' ASC");
                machineGroup_ToysListQuery = queryBuilder.build();
            }
        }
        Query<Toys> query = machineGroup_ToysListQuery.forCurrentThread();
        query.setParameter(0, groupId);
        return query.list();
    }

    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getDailyRecordDao().getAllColumns());
            builder.append(" FROM TOYS T");
            builder.append(" LEFT JOIN DAILY_RECORD T0 ON T.\"RECORD_ID\"=T0.\"_id\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected Toys loadCurrentDeep(Cursor cursor, boolean lock) {
        Toys entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        DailyRecord record = loadCurrentOther(daoSession.getDailyRecordDao(), cursor, offset);
        entity.setRecord(record);

        return entity;    
    }

    public Toys loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();
        
        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);
        
        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }
    
    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<Toys> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<Toys> list = new ArrayList<Toys>(count);
        
        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }
    
    protected List<Toys> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<Toys> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
