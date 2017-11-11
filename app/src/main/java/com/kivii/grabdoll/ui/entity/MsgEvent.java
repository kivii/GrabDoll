package com.kivii.grabdoll.ui.entity;

public class MsgEvent {
    public String message;

    public static final String REFRESH_DATA = "refreshData";

    public MsgEvent(String message) {
        this.message = message;
    }
}
