package com.joe.camera2recorddemo.Entity;

public class FilterInfo {

    private final String name;
    private final int type;
    private final int rid;

    public FilterInfo(int type, String name, int rid) {
        this.name = name;
        this.type = type;
        this.rid = rid;
    }

    public String getName() {
        return name;
    }


    public int getType() {
        return type;
    }


    public int getRid() {
        return rid;
    }
}
