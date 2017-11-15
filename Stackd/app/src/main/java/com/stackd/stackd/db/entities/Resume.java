package com.stackd.stackd.db.entities;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by lana on 11/8/17.
 */

public class Resume {
    /* Builder to set the fields of the Resume */
    public static class Builder {
        private long id;
        private long rid;
        private int rating;
        private String url;
        private String candidateName;
        private String collectionDate;
        private String recruiterComments;
        private List<Tag> tagList;

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder rid(long rid) {
            this.rid = rid;
            return this;
        }

        public Builder candidateName(String candidateName) {
            this.candidateName = candidateName;
            return this;
        }

        public Builder collectionDate(String collectionDate) {
            this.collectionDate = collectionDate;
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder recruiterComments(String recruiterComments) {
            this.recruiterComments = recruiterComments;
            return this;
        }

        public Builder rating(int rating) {
            this.rating = rating;
            return this;
        }

        public Builder tagList(List<Tag> tagList) {
            this.tagList = tagList;
            return this;
        }

        public Resume build() {
            return new Resume(this);
        }


    }

    private long id;
    private long rid;
    private String url;
    private String candidateName;
    private String recruiterComments;
    private int rating;
    private String collectionDate;
    private List<Tag> tagList;

    private Resume(Builder builder) {
        this.id = builder.id;
        this.rid = builder.rid;
        this.url = builder.url;
        this.candidateName = builder.candidateName;
        this.collectionDate = builder.collectionDate;
        this.recruiterComments = builder.recruiterComments;
        this.rating = builder.rating;
    }

    public Resume() {
    }

    public long getId() {
        return id;
    }

    public long getRid() {
        return rid;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public String getCollectionDate() {
        return collectionDate;
    }

    public String getUrl() {
        return url;
    }

    public String getRecruiterComments() {
        return recruiterComments;
    }

    public int getRating() {
        return rating;
    }

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setRid(long rid) {
        this.rid = rid;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public void setRecruiterComments(String recruiterComments) {
        this.recruiterComments = recruiterComments;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setCollectionDate(String collectionDate) {
        this.collectionDate = collectionDate;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }
}
