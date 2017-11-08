package com.stackd.stackd.model;

import com.stackd.stackd.db.entities.ResumeEntity;
import com.stackd.stackd.db.entities.TagEntity;

import java.io.File;
import java.util.List;

/**
 * Created by Musa on 11/6/2017.
 */

public interface IRecruiter {
    /**
     * Attributes of one row from the recruiter table
     */
    Long getId();
    Long getCid();
    String getFirstName();
    String getLastName();
    ResumeEntity[] getAllResumes();
    ResumeEntity[] getResumesByTag(ITag tag);
    //ResumeEntity[] getResume(String recruiterOrDate);
    TagEntity[] getCompanyTags(Long cid);
    void insertResume(ResumeEntity resume);
    // TODO: Should these two be in resume insetad?
    //void addRating(int rating); // 0: No, 1: Maybe, 2: Yes
    //void addComment(String comment);

}
