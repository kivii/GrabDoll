package com.kivii.grabdoll.core.bean;

import com.kivii.grabdoll.core.dao.DaoSession;
import com.kivii.grabdoll.core.dao.ImageDao;
import com.kivii.grabdoll.core.dao.UserDao;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.Date;

@Entity
public class User {
    @Id
    private Long id;
    private String name;
    private int sex;
    private Date birthday;
    private String address;
    private Date addTime;
    private String mobile;
    private String number;
    private String password;
    private Long avatarId;
    @ToOne(joinProperty = "avatarId")
    private Image image;

    private Long orgId;
    private int level;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1507654846)
    private transient UserDao myDao;
    @Generated(hash = 1005167554)
    public User(Long id, String name, int sex, Date birthday, String address,
            Date addTime, String mobile, String number, String password,
            Long avatarId, Long orgId, int level) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.birthday = birthday;
        this.address = address;
        this.addTime = addTime;
        this.mobile = mobile;
        this.number = number;
        this.password = password;
        this.avatarId = avatarId;
        this.orgId = orgId;
        this.level = level;
    }
    @Generated(hash = 586692638)
    public User() {
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
    public int getSex() {
        return this.sex;
    }
    public void setSex(int sex) {
        this.sex = sex;
    }
    public Date getBirthday() {
        return this.birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
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
    public String getNumber() {
        return this.number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Long getAvatarId() {
        return this.avatarId;
    }
    public void setAvatarId(Long avatarId) {
        this.avatarId = avatarId;
    }
    public Long getOrgId() {
        return this.orgId;
    }
    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }
    public int getLevel() {
        return this.level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    @Generated(hash = 1517498479)
    private transient Long image__resolvedKey;
    /** To-one relationship, resolved on first access. */
    @Generated(hash = 950189638)
    public Image getImage() {
        Long __key = this.avatarId;
        if (image__resolvedKey == null || !image__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ImageDao targetDao = daoSession.getImageDao();
            Image imageNew = targetDao.load(__key);
            synchronized (this) {
                image = imageNew;
                image__resolvedKey = __key;
            }
        }
        return image;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 295323985)
    public void setImage(Image image) {
        synchronized (this) {
            this.image = image;
            avatarId = image == null ? null : image.getId();
            image__resolvedKey = avatarId;
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
    @Generated(hash = 2059241980)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getUserDao() : null;
    }
}
