package com.stackd.stackd.model;

/**
 * Created by user on 11/6/2017.
 */

public class Recruiter  {
    private long recId;
    private long compId;
    private String firstName;
    private String lastName;
    private String email;



    public Recruiter() {

    }

    public Recruiter(long recId, long compId) {
        this.recId = recId;
        this.compId = compId;
    }

    public long getRecId() {
        return recId;
    }

    public void setRecId(long recId) {
        this.recId = recId;
    }

    public long getCompId() {
        return compId;
    }

    public void setCompId(long compId) {
        this.compId = compId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
