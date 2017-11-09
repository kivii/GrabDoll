package com.kivii.grabdoll.core.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;

@Entity
public class CustomerStorageRecord {
    @Id
    private Long id;
    private Date addTime;
    private int count;
    private String describe;
    private Long storageId;
    private String userNum;
    private String userName;
    @Generated(hash = 57396883)
    public CustomerStorageRecord(Long id, Date addTime, int count, String describe,
            Long storageId, String userNum, String userName) {
        this.id = id;
        this.addTime = addTime;
        this.count = count;
        this.describe = describe;
        this.storageId = storageId;
        this.userNum = userNum;
        this.userName = userName;
    }
    @Generated(hash = 39212795)
    public CustomerStorageRecord() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Date getAddTime() {
        return this.addTime;
    }
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
    public int getCount() {
        return this.count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public String getDescribe() {
        return this.describe;
    }
    public void setDescribe(String describe) {
        this.describe = describe;
    }
    public Long getStorageId() {
        return this.storageId;
    }
    public void setStorageId(Long storageId) {
        this.storageId = storageId;
    }
    public String getUserNum() {
        return this.userNum;
    }
    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

}
