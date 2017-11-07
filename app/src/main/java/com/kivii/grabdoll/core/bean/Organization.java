package com.kivii.grabdoll.core.bean;

import com.kivii.grabdoll.core.dao.CustomerStorageDao;
import com.kivii.grabdoll.core.dao.DaoSession;
import com.kivii.grabdoll.core.dao.ImageDao;
import com.kivii.grabdoll.core.dao.MachineGroupDao;
import com.kivii.grabdoll.core.dao.OrganizationDao;
import com.kivii.grabdoll.core.dao.UserDao;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.Date;
import java.util.List;

@Entity
public class Organization {

    @Id
    private Long id;

    private String name;
    private String address;
    private String describe;
    private Date addTime;

    private Long logoId;
    @ToOne(joinProperty = "logoId")
    private Image logo;

    @ToMany(referencedJoinProperty = "orgId")
    private List<User> userList;

    @ToMany(referencedJoinProperty = "orgId")
    private List<MachineGroup> groupList;

    @ToMany(referencedJoinProperty = "orgId")
    private List<CustomerStorage> storageList;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 49964946)
    private transient OrganizationDao myDao;

    @Generated(hash = 2098096601)
    public Organization(Long id, String name, String address, String describe,
            Date addTime, Long logoId) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.describe = describe;
        this.addTime = addTime;
        this.logoId = logoId;
    }

    @Generated(hash = 27039612)
    public Organization() {
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

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescribe() {
        return this.describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Date getAddTime() {
        return this.addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Long getLogoId() {
        return this.logoId;
    }

    public void setLogoId(Long logoId) {
        this.logoId = logoId;
    }

    @Generated(hash = 188632100)
    private transient Long logo__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1160788698)
    public Image getLogo() {
        Long __key = this.logoId;
        if (logo__resolvedKey == null || !logo__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ImageDao targetDao = daoSession.getImageDao();
            Image logoNew = targetDao.load(__key);
            synchronized (this) {
                logo = logoNew;
                logo__resolvedKey = __key;
            }
        }
        return logo;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1787618745)
    public void setLogo(Image logo) {
        synchronized (this) {
            this.logo = logo;
            logoId = logo == null ? null : logo.getId();
            logo__resolvedKey = logoId;
        }
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 276705300)
    public List<User> getUserList() {
        if (userList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            UserDao targetDao = daoSession.getUserDao();
            List<User> userListNew = targetDao._queryOrganization_UserList(id);
            synchronized (this) {
                if (userList == null) {
                    userList = userListNew;
                }
            }
        }
        return userList;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1517531020)
    public synchronized void resetUserList() {
        userList = null;
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
    @Generated(hash = 1328120336)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getOrganizationDao() : null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 712142180)
    public List<MachineGroup> getGroupList() {
        if (groupList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            MachineGroupDao targetDao = daoSession.getMachineGroupDao();
            List<MachineGroup> groupListNew = targetDao._queryOrganization_GroupList(id);
            synchronized (this) {
                if (groupList == null) {
                    groupList = groupListNew;
                }
            }
        }
        return groupList;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 114754500)
    public synchronized void resetGroupList() {
        groupList = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1671511862)
    public List<CustomerStorage> getStorageList() {
        if (storageList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            CustomerStorageDao targetDao = daoSession.getCustomerStorageDao();
            List<CustomerStorage> storageListNew = targetDao._queryOrganization_StorageList(id);
            synchronized (this) {
                if (storageList == null) {
                    storageList = storageListNew;
                }
            }
        }
        return storageList;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1191090564)
    public synchronized void resetStorageList() {
        storageList = null;
    }

}
