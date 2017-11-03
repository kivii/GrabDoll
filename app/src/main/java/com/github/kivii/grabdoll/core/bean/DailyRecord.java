package com.github.kivii.grabdoll.core.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class DailyRecord {

    @Id
    private Long id;

    private Date addTime;
    private Date time;
    private int inCoin;
    private int outToys;
    private String remark;
    @Generated(hash = 264416909)
    public DailyRecord(Long id, Date addTime, Date time, int inCoin, int outToys,
            String remark) {
        this.id = id;
        this.addTime = addTime;
        this.time = time;
        this.inCoin = inCoin;
        this.outToys = outToys;
        this.remark = remark;
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

}
