package com.stackd.stackd.model;

import java.io.File;
import java.util.List;

/**
 * Created by Musa on 11/6/2017.
 */

public interface Recruiter {
    Long getId();
    Long getCid();
    String getFirstName();
    void setFirstName(String firstName);
    String getLastName();
    void setLastName(String lastName);
    List<Resume> getAllResumes();
    List<Resume> getResumesByTag(Tag tag);
    List<Resume> getResume(String recruiterOrDate);
    List<Tag> getCompanyTags();
    void insertResume(File resume, String data);
    void addRating(int rating); // 0: No, 1: Maybe, 2: Yes
    void addComment(String comment);

}
