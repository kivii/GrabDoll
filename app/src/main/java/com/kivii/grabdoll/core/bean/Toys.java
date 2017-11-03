package com.kivii.grabdoll.core.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.kivii.grabdoll.core.dao.DaoSession;
import com.kivii.grabdoll.core.dao.DailyRecordDao;
import com.kivii.grabdoll.core.dao.ToysDao;

@Entity
public class Toys {

    @Id
    private Long id;
    private Long groupId;
    private String name;
    private Date addTime;
    private String describe;
    private int sortNum;

    private Long recordId;
    @ToOne(joinProperty = "recordId")
    private DailyRecord record;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1201967022)
    private transient ToysDao myDao;
    @Generated(hash = 1594876335)
    public Toys(Long id, Long groupId, String name, Date addTime, String describe,
            int sortNum, Long recordId) {
        this.id = id;
        this.groupId = groupId;
        this.name = name;
        this.addTime = addTime;
        this.describe = describe;
        this.sortNum = sortNum;
        this.recordId = recordId;
    }
    @Generated(hash = 54243908)
    public Toys() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getGroupId() {
        return this.groupId;
    }
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Date getAddTime() {
        return this.addTime;
    }
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
    public String getDescribe() {
        return this.describe;
    }
    public void setDescribe(String describe) {
        this.describe = describe;
    }
    public int getSortNum() {
        return this.sortNum;
    }
    public void setSortNum(int sortNum) {
        this.sortNum = sortNum;
    }
    public Long getRecordId() {
        return this.recordId;
    }
    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }
    @Generated(hash = 818274295)
    private transient Long record__resolvedKey;
    /** To-one relationship, resolved on first access. */
    @Generated(hash = 507434600)
    public DailyRecord getRecord() {
        Long __key = this.recordId;
        if (record__resolvedKey == null || !record__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            DailyRecordDao targetDao = daoSession.getDailyRecordDao();
            DailyRecord recordNew = targetDao.load(__key);
            synchronized (this) {
                record = recordNew;
                record__resolvedKey = __key;
            }
        }
        return record;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 790177191)
    public void setRecord(DailyRecord record) {
        synchronized (this) {
            this.record = record;
            recordId = record == null ? null : record.getId();
            record__resolvedKey = recordId;
        }
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 418975284)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getToysDao() : null;
    }
}
