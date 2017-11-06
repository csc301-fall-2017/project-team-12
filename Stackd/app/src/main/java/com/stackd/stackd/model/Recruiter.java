package com.stackd.stackd.model;

/**
 * Created by Musa on 11/6/2017.
 */

public interface Recruiter {
    Long getId();
    Long getCid();
    void setCid();
    String getFirstName();
    void setFirstName(String name);
    String getLastName();
    void setLastName();
}
