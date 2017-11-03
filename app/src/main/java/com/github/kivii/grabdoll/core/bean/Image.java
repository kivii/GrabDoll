package com.github.kivii.grabdoll.core.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Image {
    @Id
    private Long id;
    private String path;
    private String url;
    private Date addTime;
    @Generated(hash = 871146079)
    public Image(Long id, String path, String url, Date addTime) {
        this.id = id;
        this.path = path;
        this.url = url;
        this.addTime = addTime;
    }
    @Generated(hash = 1590301345)
    public Image() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getPath() {
        return this.path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public Date getAddTime() {
        return this.addTime;
    }
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

}
