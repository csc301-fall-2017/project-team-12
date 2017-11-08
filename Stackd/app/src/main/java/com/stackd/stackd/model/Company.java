package com.stackd.stackd.model;

import android.content.Context;

import com.stackd.stackd.db.AppDatabase;
import com.stackd.stackd.db.entities.CompanyTagEntity;
import com.stackd.stackd.db.entities.TagEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lana on 11/6/17.
 */

public class Company {

    private Long id;
    private String name;
    private List<Tag> tags;
    private List<Recruiter> recruiters;
    private List<Resume> resumes;

    public Company(Long comId, String name){
        this.id = id;
        this.name = name;
        this.tags = new ArrayList<>();
        this.resumes = new ArrayList<>();
        this.recruiters = new ArrayList<>();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Recruiter> getRecruiters() {
        return recruiters;
    }

    public void setRecruiters(List<Recruiter> recruiters) {
        this.recruiters = recruiters;
    }

    public List<Resume> getResumes() {
        return resumes;
    }

    public void setResumes(List<Resume> resumes) {
        this.resumes = resumes;
    }
}
