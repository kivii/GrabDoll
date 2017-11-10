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

import com.kivii.grabdoll.core.bean.CustomerStorage;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "CUSTOMER_STORAGE".
*/
public class CustomerStorageDao extends AbstractDao<CustomerStorage, Long> {

    public static final String TABLENAME = "CUSTOMER_STORAGE";

    /**
     * Properties of entity CustomerStorage.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property OrgId = new Property(1, Long.class, "orgId", false, "ORG_ID");
        public final static Property Number = new Property(2, int.class, "number", false, "NUMBER");
        public final static Property Name = new Property(3, String.class, "name", false, "NAME");
        public final static Property AddTime = new Property(4, java.util.Date.class, "addTime", false, "ADD_TIME");
        public final static Property Mobile = new Property(5, String.class, "mobile", false, "MOBILE");
        public final static Property Count = new Property(6, int.class, "count", false, "COUNT");
        public final static Property Top = new Property(7, int.class, "top", false, "TOP");
    }

    private DaoSession daoSession;

    private Query<CustomerStorage> organization_StorageListQuery;

    public CustomerStorageDao(DaoConfig config) {
        super(config);
    }
    
    public CustomerStorageDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"CUSTOMER_STORAGE\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"ORG_ID\" INTEGER," + // 1: orgId
                "\"NUMBER\" INTEGER NOT NULL ," + // 2: number
                "\"NAME\" TEXT," + // 3: name
                "\"ADD_TIME\" INTEGER," + // 4: addTime
                "\"MOBILE\" TEXT," + // 5: mobile
                "\"COUNT\" INTEGER NOT NULL ," + // 6: count
                "\"TOP\" INTEGER NOT NULL );"); // 7: top
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"CUSTOMER_STORAGE\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, CustomerStorage entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long orgId = entity.getOrgId();
        if (orgId != null) {
            stmt.bindLong(2, orgId);
        }
        stmt.bindLong(3, entity.getNumber());
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(4, name);
        }
 
        java.util.Date addTime = entity.getAddTime();
        if (addTime != null) {
            stmt.bindLong(5, addTime.getTime());
        }
 
        String mobile = entity.getMobile();
        if (mobile != null) {
            stmt.bindString(6, mobile);
        }
        stmt.bindLong(7, entity.getCount());
        stmt.bindLong(8, entity.getTop());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, CustomerStorage entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long orgId = entity.getOrgId();
        if (orgId != null) {
            stmt.bindLong(2, orgId);
        }
        stmt.bindLong(3, entity.getNumber());
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(4, name);
        }
 
        java.util.Date addTime = entity.getAddTime();
        if (addTime != null) {
            stmt.bindLong(5, addTime.getTime());
        }
 
        String mobile = entity.getMobile();
        if (mobile != null) {
            stmt.bindString(6, mobile);
        }
        stmt.bindLong(7, entity.getCount());
        stmt.bindLong(8, entity.getTop());
    }

    @Override
    protected final void attachEntity(CustomerStorage entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public CustomerStorage readEntity(Cursor cursor, int offset) {
        CustomerStorage entity = new CustomerStorage( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // orgId
            cursor.getInt(offset + 2), // number
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // name
            cursor.isNull(offset + 4) ? null : new java.util.Date(cursor.getLong(offset + 4)), // addTime
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // mobile
            cursor.getInt(offset + 6), // count
            cursor.getInt(offset + 7) // top
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, CustomerStorage entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setOrgId(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setNumber(cursor.getInt(offset + 2));
        entity.setName(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setAddTime(cursor.isNull(offset + 4) ? null : new java.util.Date(cursor.getLong(offset + 4)));
        entity.setMobile(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setCount(cursor.getInt(offset + 6));
        entity.setTop(cursor.getInt(offset + 7));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(CustomerStorage entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(CustomerStorage entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(CustomerStorage entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "storageList" to-many relationship of Organization. */
    public List<CustomerStorage> _queryOrganization_StorageList(Long orgId) {
        synchronized (this) {
            if (organization_StorageListQuery == null) {
                QueryBuilder<CustomerStorage> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.OrgId.eq(null));
                queryBuilder.orderRaw("T.'TOP' DESC,T.'NUMBER' ASC");
                organization_StorageListQuery = queryBuilder.build();
            }
        }
        Query<CustomerStorage> query = organization_StorageListQuery.forCurrentThread();
        query.setParameter(0, orgId);
        return query.list();
    }

}