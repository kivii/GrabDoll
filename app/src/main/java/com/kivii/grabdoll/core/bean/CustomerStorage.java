package com.kivii.grabdoll.core.bean;

import com.kivii.grabdoll.core.dao.CustomerStorageDao;
import com.kivii.grabdoll.core.dao.CustomerStorageRecordDao;
import com.kivii.grabdoll.core.dao.DaoSession;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.OrderBy;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.Date;
import java.util.List;

@Entity
public class CustomerStorage {
    @Id
    private Long id;
    private Long orgId;
    private int number;
    private String name;
    private Date addTime;
    private String mobile;
    private int count;
    private int top;

    @ToMany(referencedJoinProperty = "storageId")
    @OrderBy("addTime DESC")
    private List<CustomerStorageRecord> recordList;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 469369479)
    private transient CustomerStorageDao myDao;

    @Generated(hash = 834718839)
    public CustomerStorage(Long id, Long orgId, int number, String name, Date addTime, String mobile,
            int count, int top) {
        this.id = id;
        this.orgId = orgId;
        this.number = number;
        this.name = name;
        this.addTime = addTime;
        this.mobile = mobile;
        this.count = count;
        this.top = top;
    }

    @Generated(hash = 1413369181)
    public CustomerStorage() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrgId() {
        return this.orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public int getNumber() {
        return this.number;
    }

    public void setNumber(int number) {
        this.number = number;
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

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1643043418)
    public List<CustomerStorageRecord> getRecordList() {
        if (recordList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            CustomerStorageRecordDao targetDao = daoSession
                    .getCustomerStorageRecordDao();
            List<CustomerStorageRecord> recordListNew = targetDao
                    ._queryCustomerStorage_RecordList(id);
            synchronized (this) {
                if (recordList == null) {
                    recordList = recordListNew;
                }
            }
        }
        return recordList;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1700181837)
    public synchronized void resetRecordList() {
        recordList = null;
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
    @Generated(hash = 1183166237)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getCustomerStorageDao() : null;
    }

    public int getTop() {
        return this.top;
    }

    public void setTop(int top) {
        this.top = top;
    }
}
