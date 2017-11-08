package com.kivii.grabdoll.ui.entity;

import com.kivii.grabdoll.core.bean.Image;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.kivii.grabdoll.core.dao.DaoSession;
import com.kivii.grabdoll.core.dao.ImageDao;
import com.kivii.grabdoll.core.dao.ContactDao;

@Entity
public class Contact {
    @Id
    private Long id;
    private Long avatarId;
    @ToOne(joinProperty = "avatarId")
    private Image avatar;
    private int type;//0个人 1群
    private int state;
    private Long userId;
    private String userName;
    private String content;
    private boolean unRead;
    private Date time;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 2046468181)
    private transient ContactDao myDao;
    @Generated(hash = 133308306)
    public Contact(Long id, Long avatarId, int type, int state, Long userId,
            String userName, String content, boolean unRead, Date time) {
        this.id = id;
        this.avatarId = avatarId;
        this.type = type;
        this.state = state;
        this.userId = userId;
        this.userName = userName;
        this.content = content;
        this.unRead = unRead;
        this.time = time;
    }
    @Generated(hash = 672515148)
    public Contact() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getAvatarId() {
        return this.avatarId;
    }
    public void setAvatarId(Long avatarId) {
        this.avatarId = avatarId;
    }
    public int getType() {
        return this.type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public int getState() {
        return this.state;
    }
    public void setState(int state) {
        this.state = state;
    }
    public Long getUserId() {
        return this.userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public boolean getUnRead() {
        return this.unRead;
    }
    public void setUnRead(boolean unRead) {
        this.unRead = unRead;
    }
    public Date getTime() {
        return this.time;
    }
    public void setTime(Date time) {
        this.time = time;
    }
    @Generated(hash = 1907406681)
    private transient Long avatar__resolvedKey;
    /** To-one relationship, resolved on first access. */
    @Generated(hash = 659450681)
    public Image getAvatar() {
        Long __key = this.avatarId;
        if (avatar__resolvedKey == null || !avatar__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ImageDao targetDao = daoSession.getImageDao();
            Image avatarNew = targetDao.load(__key);
            synchronized (this) {
                avatar = avatarNew;
                avatar__resolvedKey = __key;
            }
        }
        return avatar;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1276856400)
    public void setAvatar(Image avatar) {
        synchronized (this) {
            this.avatar = avatar;
            avatarId = avatar == null ? null : avatar.getId();
            avatar__resolvedKey = avatarId;
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
    @Generated(hash = 2088270543)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getContactDao() : null;
    }
}
