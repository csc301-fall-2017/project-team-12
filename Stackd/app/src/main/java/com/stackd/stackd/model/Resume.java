package com.stackd.stackd.model;

import android.arch.persistence.room.ColumnInfo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.stackd.stackd.model.Tag;


/**
 * Created by lana on 11/8/17.
 * ONLY Getters and Setters
 */

public class Resume {
    private Long id;
    private Long rid;

    private String candidateName;
    private Date collectionDate;
    private String url;
    private String recruiterComments;
    private int rating;
    private List<Tag> tagList;


    public Resume(Long recId) {
        this.id = null; // ID will be set by the data base
        this.rid = recId;
        this.url = "";
        this.candidateName = "";
        this.collectionDate = null;
        this.recruiterComments = "";
        this.rating = -1;
    }

    public Long getId() {
        return id;
    }


    public Long getRid() {
        return rid;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public Date getCollectionDate() {
        return collectionDate;
    }

    public void setCollectionDate(SimpleDateFormat collectionDate) {
        this.collectionDate = collectionDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRecruiterComments() {
        return recruiterComments;
    }

    public void setRecruiterComments(String recruiterComments) {
        this.recruiterComments = recruiterComments;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    public Boolean hasTag(Tag tag){
        return this.tagList.contains(tag);
    }
}
