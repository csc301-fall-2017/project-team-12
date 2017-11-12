package com.stackd.stackd.db;

import com.stackd.stackd.db.daos.CompanyDao;
import com.stackd.stackd.db.daos.RecruiterDao;
import com.stackd.stackd.db.daos.ResumeDao;
import com.stackd.stackd.db.daos.TagDao;
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
    private static App app = null;
    private CompanyDao companyDao;
    private RecruiterDao recruiterDao;
    private ResumeDao resumeDao;
    private TagDao tagDao;
    //TODO: set these during authentication
    public static Company company = new Company();
    public static Recruiter recruiter = null; //new Recruiter();

    private App(Long compnyID, Long recruiterId) {
        // Instantiate Daos:
        this.companyDao = CompanyDao.getCompanyDao();
        this.recruiterDao = RecruiterDao.getRecruiterDao();
        this.resumeDao = ResumeDao.getResumeDao();
        this.tagDao = TagDao.getTagDao();

        company.setId(compnyID);

        //TODO, continue setting company & recriuter

    }

    public static App getApp(Long companyId, Long recruiterId) {
        if (app == null) {
            app = new App(companyId, recruiterId);
            return app;
        } else {
            return app;
        }
    }
    public Company getCompany(Long cid) {
        // Fetch the company from JSON file, for example:
        // String jsonFileAsString, read it to jsonObject
        // JSONObject fileAsJosnObject = new JSONObject(jsonFile)
        // Get the required compnay (see the documentation of JSONObjects and how to use them,
        // I still don't know them well yet sorry :P
        // Then pass the companyAsJsonString to  parsere to get back a Company instance
        // and return it!
        // And yay!
        
        return null;
    }

    public List<Tag> getCompanyTags(){
        // TODO: use the App.company ID
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

