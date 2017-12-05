package com.stackd.stackd.db.entities;

import java.util.List;

public class Resume {
    /* Builder to set the fields of the Resume */
    public static class Builder {
        private String id;
        private String rid;
        private int rating;
        private String url;
        private String candidateName;
        private String collectionDate;
        private String recruiterComments;
        private List<Tag> tagList;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder rid(String rid) {
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

    private String id;
    private String rid;
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
        this.tagList = builder.tagList;

    }

    public Resume() {
    }

    public String getId() {
        return id;
    }

    public String getRid() {
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

    public void setId(String id) {
        this.id = id;
    }

    public void setRid(String rid) {
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

    @Override public String toString() {
        StringBuilder sb = new StringBuilder();
        String cvsSplitBy = ",";
        sb.append(this.getCandidateName());
        sb.append(cvsSplitBy);
        sb.append(this.getCollectionDate());
        sb.append(cvsSplitBy);
        for (Tag t: tagList) {
            sb.append(t);
            sb.append(" ");
        }
        sb.append(cvsSplitBy);
        switch (this.rating) {
            case 2: sb.append("Yes"); break;// yes
            case 0: sb.append("No"); break;// no
            case 1: sb.append("Maybe"); break;// maybe
        }
        sb.append(cvsSplitBy);
        sb.append("\"" + this.getRecruiterComments() + "\"");

        sb.append("\n");
        return sb.toString();

    }
}
