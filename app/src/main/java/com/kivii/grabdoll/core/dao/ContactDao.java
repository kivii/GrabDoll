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

import com.kivii.grabdoll.core.bean.Image;

import com.kivii.grabdoll.ui.entity.Contact;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "CONTACT".
*/
public class ContactDao extends AbstractDao<Contact, Long> {

    public static final String TABLENAME = "CONTACT";

    /**
     * Properties of entity Contact.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property AvatarId = new Property(1, Long.class, "avatarId", false, "AVATAR_ID");
        public final static Property Type = new Property(2, int.class, "type", false, "TYPE");
        public final static Property State = new Property(3, int.class, "state", false, "STATE");
        public final static Property UserId = new Property(4, Long.class, "userId", false, "USER_ID");
        public final static Property UserName = new Property(5, String.class, "userName", false, "USER_NAME");
        public final static Property Content = new Property(6, String.class, "content", false, "CONTENT");
        public final static Property UnRead = new Property(7, boolean.class, "unRead", false, "UN_READ");
        public final static Property Time = new Property(8, java.util.Date.class, "time", false, "TIME");
    }

    private DaoSession daoSession;


    public ContactDao(DaoConfig config) {
        super(config);
    }
    
    public ContactDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"CONTACT\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"AVATAR_ID\" INTEGER," + // 1: avatarId
                "\"TYPE\" INTEGER NOT NULL ," + // 2: type
                "\"STATE\" INTEGER NOT NULL ," + // 3: state
                "\"USER_ID\" INTEGER," + // 4: userId
                "\"USER_NAME\" TEXT," + // 5: userName
                "\"CONTENT\" TEXT," + // 6: content
                "\"UN_READ\" INTEGER NOT NULL ," + // 7: unRead
                "\"TIME\" INTEGER);"); // 8: time
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"CONTACT\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Contact entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long avatarId = entity.getAvatarId();
        if (avatarId != null) {
            stmt.bindLong(2, avatarId);
        }
        stmt.bindLong(3, entity.getType());
        stmt.bindLong(4, entity.getState());
 
        Long userId = entity.getUserId();
        if (userId != null) {
            stmt.bindLong(5, userId);
        }
 
        String userName = entity.getUserName();
        if (userName != null) {
            stmt.bindString(6, userName);
        }
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(7, content);
        }
        stmt.bindLong(8, entity.getUnRead() ? 1L: 0L);
 
        java.util.Date time = entity.getTime();
        if (time != null) {
            stmt.bindLong(9, time.getTime());
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Contact entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long avatarId = entity.getAvatarId();
        if (avatarId != null) {
            stmt.bindLong(2, avatarId);
        }
        stmt.bindLong(3, entity.getType());
        stmt.bindLong(4, entity.getState());
 
        Long userId = entity.getUserId();
        if (userId != null) {
            stmt.bindLong(5, userId);
        }
 
        String userName = entity.getUserName();
        if (userName != null) {
            stmt.bindString(6, userName);
        }
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(7, content);
        }
        stmt.bindLong(8, entity.getUnRead() ? 1L: 0L);
 
        java.util.Date time = entity.getTime();
        if (time != null) {
            stmt.bindLong(9, time.getTime());
        }
    }

    @Override
    protected final void attachEntity(Contact entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Contact readEntity(Cursor cursor, int offset) {
        Contact entity = new Contact( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // avatarId
            cursor.getInt(offset + 2), // type
            cursor.getInt(offset + 3), // state
            cursor.isNull(offset + 4) ? null : cursor.getLong(offset + 4), // userId
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // userName
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // content
            cursor.getShort(offset + 7) != 0, // unRead
            cursor.isNull(offset + 8) ? null : new java.util.Date(cursor.getLong(offset + 8)) // time
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Contact entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setAvatarId(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setType(cursor.getInt(offset + 2));
        entity.setState(cursor.getInt(offset + 3));
        entity.setUserId(cursor.isNull(offset + 4) ? null : cursor.getLong(offset + 4));
        entity.setUserName(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setContent(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setUnRead(cursor.getShort(offset + 7) != 0);
        entity.setTime(cursor.isNull(offset + 8) ? null : new java.util.Date(cursor.getLong(offset + 8)));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Contact entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Contact entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Contact entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getImageDao().getAllColumns());
            builder.append(" FROM CONTACT T");
            builder.append(" LEFT JOIN IMAGE T0 ON T.\"AVATAR_ID\"=T0.\"_id\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected Contact loadCurrentDeep(Cursor cursor, boolean lock) {
        Contact entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        Image avatar = loadCurrentOther(daoSession.getImageDao(), cursor, offset);
        entity.setAvatar(avatar);

        return entity;    
    }

    public Contact loadDeep(Long key) {
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
    public List<Contact> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<Contact> list = new ArrayList<Contact>(count);
        
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
    
    protected List<Contact> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<Contact> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
