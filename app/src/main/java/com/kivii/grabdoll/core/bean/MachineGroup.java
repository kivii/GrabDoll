package com.kivii.grabdoll.core.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.Date;
import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.kivii.grabdoll.core.dao.DaoSession;
import com.kivii.grabdoll.core.dao.ToysDao;
import com.kivii.grabdoll.core.dao.MachineGroupDao;

@Entity
public class MachineGroup {

    @Id
    private Long id;

    private String name;
    private Date addTime;
    private String describe;
    private int sortNum;

    @ToMany(referencedJoinProperty = "groupId")
    private List<Toys> toysList;

    private Long orgId;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1757420763)
    private transient MachineGroupDao myDao;

    @Generated(hash = 277620)
    public MachineGroup(Long id, String name, Date addTime, String describe,
            int sortNum, Long orgId) {
        this.id = id;
        this.name = name;
        this.addTime = addTime;
        this.describe = describe;
        this.sortNum = sortNum;
        this.orgId = orgId;
    }

    @Generated(hash = 1377162463)
    public MachineGroup() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getOrgId() {
        return this.orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 798610009)
    public List<Toys> getToysList() {
        if (toysList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ToysDao targetDao = daoSession.getToysDao();
            List<Toys> toysListNew = targetDao._queryMachineGroup_ToysList(id);
            synchronized (this) {
                if (toysList == null) {
                    toysList = toysListNew;
                }
            }
        }
        return toysList;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 77291730)
    public synchronized void resetToysList() {
        toysList = null;
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
    @Generated(hash = 1044009599)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getMachineGroupDao() : null;
    }

}
