package com.github.kivii.grabdoll.core.dao;

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

import com.github.kivii.grabdoll.core.bean.Image;

import com.github.kivii.grabdoll.core.bean.User;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "USER".
*/
public class UserDao extends AbstractDao<User, Long> {

    public static final String TABLENAME = "USER";

    /**
     * Properties of entity User.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        public final static Property Sex = new Property(2, int.class, "sex", false, "SEX");
        public final static Property Birthday = new Property(3, java.util.Date.class, "birthday", false, "BIRTHDAY");
        public final static Property Address = new Property(4, String.class, "address", false, "ADDRESS");
        public final static Property AddTime = new Property(5, java.util.Date.class, "addTime", false, "ADD_TIME");
        public final static Property Mobile = new Property(6, String.class, "mobile", false, "MOBILE");
        public final static Property Number = new Property(7, String.class, "number", false, "NUMBER");
        public final static Property Password = new Property(8, String.class, "password", false, "PASSWORD");
        public final static Property AvatarId = new Property(9, Long.class, "avatarId", false, "AVATAR_ID");
        public final static Property OrgId = new Property(10, Long.class, "orgId", false, "ORG_ID");
        public final static Property Level = new Property(11, int.class, "level", false, "LEVEL");
    }

    private DaoSession daoSession;

    private Query<User> organization_UserListQuery;

    public UserDao(DaoConfig config) {
        super(config);
    }
    
    public UserDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"USER\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"NAME\" TEXT," + // 1: name
                "\"SEX\" INTEGER NOT NULL ," + // 2: sex
                "\"BIRTHDAY\" INTEGER," + // 3: birthday
                "\"ADDRESS\" TEXT," + // 4: address
                "\"ADD_TIME\" INTEGER," + // 5: addTime
                "\"MOBILE\" TEXT," + // 6: mobile
                "\"NUMBER\" TEXT," + // 7: number
                "\"PASSWORD\" TEXT," + // 8: password
                "\"AVATAR_ID\" INTEGER," + // 9: avatarId
                "\"ORG_ID\" INTEGER," + // 10: orgId
                "\"LEVEL\" INTEGER NOT NULL );"); // 11: level
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"USER\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, User entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
        stmt.bindLong(3, entity.getSex());
 
        java.util.Date birthday = entity.getBirthday();
        if (birthday != null) {
            stmt.bindLong(4, birthday.getTime());
        }
 
        String address = entity.getAddress();
        if (address != null) {
            stmt.bindString(5, address);
        }
 
        java.util.Date addTime = entity.getAddTime();
        if (addTime != null) {
            stmt.bindLong(6, addTime.getTime());
        }
 
        String mobile = entity.getMobile();
        if (mobile != null) {
            stmt.bindString(7, mobile);
        }
 
        String number = entity.getNumber();
        if (number != null) {
            stmt.bindString(8, number);
        }
 
        String password = entity.getPassword();
        if (password != null) {
            stmt.bindString(9, password);
        }
 
        Long avatarId = entity.getAvatarId();
        if (avatarId != null) {
            stmt.bindLong(10, avatarId);
        }
 
        Long orgId = entity.getOrgId();
        if (orgId != null) {
            stmt.bindLong(11, orgId);
        }
        stmt.bindLong(12, entity.getLevel());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, User entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
        stmt.bindLong(3, entity.getSex());
 
        java.util.Date birthday = entity.getBirthday();
        if (birthday != null) {
            stmt.bindLong(4, birthday.getTime());
        }
 
        String address = entity.getAddress();
        if (address != null) {
            stmt.bindString(5, address);
        }
 
        java.util.Date addTime = entity.getAddTime();
        if (addTime != null) {
            stmt.bindLong(6, addTime.getTime());
        }
 
        String mobile = entity.getMobile();
        if (mobile != null) {
            stmt.bindString(7, mobile);
        }
 
        String number = entity.getNumber();
        if (number != null) {
            stmt.bindString(8, number);
        }
 
        String password = entity.getPassword();
        if (password != null) {
            stmt.bindString(9, password);
        }
 
        Long avatarId = entity.getAvatarId();
        if (avatarId != null) {
            stmt.bindLong(10, avatarId);
        }
 
        Long orgId = entity.getOrgId();
        if (orgId != null) {
            stmt.bindLong(11, orgId);
        }
        stmt.bindLong(12, entity.getLevel());
    }

    @Override
    protected final void attachEntity(User entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public User readEntity(Cursor cursor, int offset) {
        User entity = new User( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // name
            cursor.getInt(offset + 2), // sex
            cursor.isNull(offset + 3) ? null : new java.util.Date(cursor.getLong(offset + 3)), // birthday
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // address
            cursor.isNull(offset + 5) ? null : new java.util.Date(cursor.getLong(offset + 5)), // addTime
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // mobile
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // number
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // password
            cursor.isNull(offset + 9) ? null : cursor.getLong(offset + 9), // avatarId
            cursor.isNull(offset + 10) ? null : cursor.getLong(offset + 10), // orgId
            cursor.getInt(offset + 11) // level
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, User entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setSex(cursor.getInt(offset + 2));
        entity.setBirthday(cursor.isNull(offset + 3) ? null : new java.util.Date(cursor.getLong(offset + 3)));
        entity.setAddress(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setAddTime(cursor.isNull(offset + 5) ? null : new java.util.Date(cursor.getLong(offset + 5)));
        entity.setMobile(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setNumber(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setPassword(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setAvatarId(cursor.isNull(offset + 9) ? null : cursor.getLong(offset + 9));
        entity.setOrgId(cursor.isNull(offset + 10) ? null : cursor.getLong(offset + 10));
        entity.setLevel(cursor.getInt(offset + 11));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(User entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(User entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(User entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "userList" to-many relationship of Organization. */
    public List<User> _queryOrganization_UserList(Long orgId) {
        synchronized (this) {
            if (organization_UserListQuery == null) {
                QueryBuilder<User> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.OrgId.eq(null));
                organization_UserListQuery = queryBuilder.build();
            }
        }
        Query<User> query = organization_UserListQuery.forCurrentThread();
        query.setParameter(0, orgId);
        return query.list();
    }

    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getImageDao().getAllColumns());
            builder.append(" FROM USER T");
            builder.append(" LEFT JOIN IMAGE T0 ON T.\"AVATAR_ID\"=T0.\"_id\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected User loadCurrentDeep(Cursor cursor, boolean lock) {
        User entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        Image image = loadCurrentOther(daoSession.getImageDao(), cursor, offset);
        entity.setImage(image);

        return entity;    
    }

    public User loadDeep(Long key) {
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
    public List<User> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<User> list = new ArrayList<User>(count);
        
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
    
    protected List<User> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<User> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}