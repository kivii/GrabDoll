package com.kivii.grabdoll.core.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;

@Entity
public class DailyRecord {

    @Id
    private Long id;

    private Date addTime;
    private Date time;
    private int inCoin;
    private int outToys;
    private String remark;
    private String userNum;
    private String userName;

    @Generated(hash = 909073762)
    public DailyRecord(Long id, Date addTime, Date time, int inCoin, int outToys,
            String remark, String userNum, String userName) {
        this.id = id;
        this.addTime = addTime;
        this.time = time;
        this.inCoin = inCoin;
        this.outToys = outToys;
        this.remark = remark;
        this.userNum = userNum;
        this.userName = userName;
    }
    @Generated(hash = 1812185311)
    public DailyRecord() {
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
    public Date getTime() {
        return this.time;
    }
    public void setTime(Date time) {
        this.time = time;
    }
    public int getInCoin() {
        return this.inCoin;
    }
    public void setInCoin(int inCoin) {
        this.inCoin = inCoin;
    }
    public int getOutToys() {
        return this.outToys;
    }
    public void setOutToys(int outToys) {
        this.outToys = outToys;
    }
    public String getRemark() {
        return this.remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
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
