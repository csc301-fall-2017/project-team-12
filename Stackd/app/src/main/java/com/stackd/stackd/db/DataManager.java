package com.stackd.stackd.db;

import com.stackd.stackd.model.Company;
import com.stackd.stackd.model.Recruiter;
import com.stackd.stackd.model.Resume;
import com.stackd.stackd.model.Tag;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Communicates with the database to get and insert the required data
 *
 * Created by Musa on 11/14/2017.
 */

public interface DataManager {
    /** Returns an instance the data manager to communicate with the database
     * It takes the companyId and recruiterId so every communication is on behalf
     * of this recruiter
     */
    DataManager getDataManager(Long companyId, Long recruiterId);
    /** Returns the company that this data manager communicates with*/
    Company getCompany();
    /** Returns the list of tags used by this company*/
    List<Tag> getCompanyTags();
    /** Add a tag to the list of tags of this company*/
    void addTag(Tag tag);
    /** Returns a recruiter instance of the current recruiter who logged in*/
    Recruiter getRecruiter();
    /** Returns a list of resumes tagges by this tag*/
    List<Resume> getResumesWithTag(Tag tag);
    /** Inserts a resume to the records of this company
     * recruiterId will be set to the one logged in
     */
    void insertResume(Resume resume);
    /** Adds a rating to the resume*/
    void addReview(Long resId, SimpleDateFormat date, int rating);
}
