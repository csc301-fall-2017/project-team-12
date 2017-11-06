package com.stackd.stackd.model;

import com.stackd.stackd.db.entities.Tag;

import java.util.List;

/**
 * Created by Musa on 11/6/2017.
 */

public interface Resume {
    List<Resume> gitAllResumes();
    List<Resume> gitResumesByTag(Tag tag);
    List<Resume> getResume(String recruiterOrDate);
    boolean hasTag(Tag tag);
    void insertResume(Resume resume);
    void addReview(int recId, int resId, String date, String comment, int rating);
}