package com.stackd.stackd.db;

import com.stackd.stackd.model.Company;
import com.stackd.stackd.model.Recruiter;
import com.stackd.stackd.model.Resume;
import com.stackd.stackd.model.Tag;

import java.util.List;

/**
 * A global class for the whole app resposible for getting the required data
 * from the database to the app users, as objects.
 *
 * Created by Musa on 11/8/2017.
 */

public class App {
//    private CompanyDao companyDao;
//    private RecruiterDao recruiterDao;
    private static App app = null;
    //TODO: set these during authentication
    public static Company company = new Company();
    public static Recruiter recruiter = new Recruiter();

    private App(Long compnyID, Long recruiterId) {
        company.setId(compnyID);
        //TODO, continue setting company & recriuter
    }

    public static App getApp(Long companyId, Long recruiterId) {
        if (app == null) {
            return new App(companyId, recruiterId);
        } else {
            return app;
        }
    }
    public Company getCompany(Long cid) {
        // Fetch the company from database, JSON CompanyDao.getCompany(
        return null;
    }

    public List<Tag> getTags(Long companyId){
        return null;
    }

    public void addTag(Long companyId, Tag tag) {

    }

    public Recruiter getRecruiter(Long recruiterId) {
        return null;
    }

    public List<Resume> getResumesWithTag(Tag tag) {
        return null;
    }

    public void insertResume(Resume resume) {

    }

    public void addReview(int recId, int resId, String date, int rating) {

    }

    
}

