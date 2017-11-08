package com.stackd.stackd.model;

import android.content.Context;

import com.stackd.stackd.db.AppDatabase;
import com.stackd.stackd.db.entities.RecruiterEntity;
import com.stackd.stackd.db.entities.ResumeEntity;
import com.stackd.stackd.db.entities.ReviewEntity;
import com.stackd.stackd.db.entities.TagEntity;

import java.io.File;
import java.util.List;

/**
 * Created by user on 11/6/2017.
 */

public class Recruiter implements IRecruiter {
    private RecruiterEntity recruiter;
    private AppDatabase db;

    public Recruiter(Context context, Long recId) {
        this.db = AppDatabase.getAppDatabase(context);
        this.recruiter = db.recruiterDao().getRecruiter(recId);
    }

    @Override
    public Long getId() {
        return recruiter.getId();
    }

    @Override
    public Long getCid() {
        return recruiter.getCid();
    }

    @Override
    public String getFirstName() {
        return recruiter.getFirstName();
    }

    @Override
    public String getLastName() {
        return recruiter.getLastName();
    }

    @Override
    public ResumeEntity[] getAllResumes() {
        return db.recruiterResumeDao().getAllResumes(recruiter.getId());
    }

    @Override
    public ResumeEntity[] getResumesByTag(ITag tag) {
        return db.resumeTagDao().getResumesByTag(tag.getId());
    }

    @Override
    public TagEntity[] getCompanyTags(Long cid) {
        return db.companyTagDao().getCompanyTags(cid);
    }

    @Override
    public void insertResume(ResumeEntity resume) {
        db.resumeDao().insertResume(resume);
    }

//    @Override
//    public void addRating(int rating) {
//
//    }
//
//    @Override
//    public void addComment(String comment) {
//
//    }
}
