package com.stackd.stackd.db.entities;

import java.util.List;

/**
 * Created by lana on 11/6/17.
 */

public class Company {
    /* Builder to populate the fields of a Company object */
    public static class Builder {
        private String id;
        private String name;
        private List<Tag> tags;
        private List<Recruiter> recruiters;
        private List<Resume> resumes;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder tags(List<Tag> tags) {
            this.tags = tags;
            return this;
        }

        public Builder recruiters(List<Recruiter> recruiters) {
            this.recruiters = recruiters;
            return this;
        }

        public Builder resumes(List<Resume> resumes) {
            this.resumes = resumes;
            return this;
        }

        public Company build() {
            return new Company(this);
        }
    }

    private String id;
    private String name;
    private List<Tag> tags;
    private List<Recruiter> recruiters;
    private List<Resume> resumes;

    private Company(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.tags = builder.tags;
        this.recruiters = builder.recruiters;
        this.resumes = builder.resumes;
    }

    public Company() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public List<Recruiter> getRecruiters() {
        return recruiters;
    }

    public List<Resume> getResumes() {
        return resumes;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public void setRecruiters(List<Recruiter> recruiters) {
        this.recruiters = recruiters;
    }

    public void setResumes(List<Resume> resumes) {
        this.resumes = resumes;
    }

    public void addResume(Resume resume) {
        this.resumes.add(resume);
    }
}
