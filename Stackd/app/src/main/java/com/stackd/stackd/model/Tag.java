package com.stackd.stackd.model;

/**
 * Created by lana on 11/6/17.
 */

public class Tag  {

    private long id;
    private String name;

    public Tag() {

    }
    public Tag(long id, String name){
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
