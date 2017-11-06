package com.stackd.stackd.model;

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
    void setFirstName(String firstName);
    String getLastName();
    void setLastName(String lastName);
    List<IResume> getAllResumes();
    List<IResume> getResumesByTag(ITag tag);
    List<IResume> getResume(String recruiterOrDate);
    List<ITag> getCompanyTags();
    void insertResume(File resume, String data);
    void addRating(int rating); // 0: No, 1: Maybe, 2: Yes
    void addComment(String comment);

}
