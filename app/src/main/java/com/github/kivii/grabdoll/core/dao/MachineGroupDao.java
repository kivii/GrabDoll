package com.github.kivii.grabdoll.core.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.github.kivii.grabdoll.core.bean.MachineGroup;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "MACHINE_GROUP".
*/
public class MachineGroupDao extends AbstractDao<MachineGroup, Long> {

    public static final String TABLENAME = "MACHINE_GROUP";

    /**
     * Properties of entity MachineGroup.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        public final static Property AddTime = new Property(2, java.util.Date.class, "addTime", false, "ADD_TIME");
        public final static Property Describe = new Property(3, String.class, "describe", false, "DESCRIBE");
        public final static Property SortNum = new Property(4, int.class, "sortNum", false, "SORT_NUM");
        public final static Property OrgId = new Property(5, Long.class, "orgId", false, "ORG_ID");
    }

    private DaoSession daoSession;


    public MachineGroupDao(DaoConfig config) {
        super(config);
    }
    
    public MachineGroupDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"MACHINE_GROUP\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"NAME\" TEXT," + // 1: name
                "\"ADD_TIME\" INTEGER," + // 2: addTime
                "\"DESCRIBE\" TEXT," + // 3: describe
                "\"SORT_NUM\" INTEGER NOT NULL ," + // 4: sortNum
                "\"ORG_ID\" INTEGER);"); // 5: orgId
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"MACHINE_GROUP\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, MachineGroup entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        java.util.Date addTime = entity.getAddTime();
        if (addTime != null) {
            stmt.bindLong(3, addTime.getTime());
        }
 
        String describe = entity.getDescribe();
        if (describe != null) {
            stmt.bindString(4, describe);
        }
        stmt.bindLong(5, entity.getSortNum());
 
        Long orgId = entity.getOrgId();
        if (orgId != null) {
            stmt.bindLong(6, orgId);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, MachineGroup entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        java.util.Date addTime = entity.getAddTime();
        if (addTime != null) {
            stmt.bindLong(3, addTime.getTime());
        }
 
        String describe = entity.getDescribe();
        if (describe != null) {
            stmt.bindString(4, describe);
        }
        stmt.bindLong(5, entity.getSortNum());
 
        Long orgId = entity.getOrgId();
        if (orgId != null) {
            stmt.bindLong(6, orgId);
        }
    }

    @Override
    protected final void attachEntity(MachineGroup entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public MachineGroup readEntity(Cursor cursor, int offset) {
        MachineGroup entity = new MachineGroup( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // name
            cursor.isNull(offset + 2) ? null : new java.util.Date(cursor.getLong(offset + 2)), // addTime
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // describe
            cursor.getInt(offset + 4), // sortNum
            cursor.isNull(offset + 5) ? null : cursor.getLong(offset + 5) // orgId
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, MachineGroup entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setAddTime(cursor.isNull(offset + 2) ? null : new java.util.Date(cursor.getLong(offset + 2)));
        entity.setDescribe(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setSortNum(cursor.getInt(offset + 4));
        entity.setOrgId(cursor.isNull(offset + 5) ? null : cursor.getLong(offset + 5));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(MachineGroup entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(MachineGroup entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(MachineGroup entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
