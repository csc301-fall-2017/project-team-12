package com.stackd.stackd.db;

import com.stackd.stackd.db.entities.Company;
import com.stackd.stackd.db.entities.Recruiter;
import com.stackd.stackd.db.entities.Resume;
import com.stackd.stackd.db.entities.Tag;
import com.stackd.stackd.helpers.ResponseParser;
import com.stackd.stackd.temp.Utils;

import java.util.List;

/**
 * A global class for the whole dataManager resposible for getting the required data
 * from the database to the dataManager users, as objects.
 *
 * Created by Musa on 11/8/2017.
 *
 * -----------------------------------
 * TODO's
 * - set these during authentication
 * - get cid from login activity
 * - get rid from login
 * -----------------------------------
 */

public class DataManager {
    private static DataManager dataManager = null;
    private Company company;
    private Recruiter recruiter;

    private DataManager(Long companyID, Long recruiterId) {
        this.company = getCompany(Long.valueOf(1));
        this.recruiter = getRecruiter(Long.valueOf(21));
    }

    public static DataManager getDataManager(Long companyId, Long recruiterId) {
        if (dataManager == null)
            dataManager = new DataManager(companyId, recruiterId);
        return dataManager;
    }

    /* Return the Company entity given the company id iff the json response is valid,
     * null otherwise.
     **/
    private Company getCompany(Long cId) {
        // "cId" is unused for the demo but will be used when making calls to the api
        String companyAsJsonString = Utils.getCompanyResponse();
        List<Company> result = ResponseParser.parseCompanyResponse(companyAsJsonString);

        return result != null ? result.get(0): null;
    }

    /* Return the Recruiter entity given the recruiter id iff the json response is valid,
     * null otherwise.
     **/
    private Recruiter getRecruiter(Long rId) {
        // "rId" is unused for the demo but will be used when making calls to the api
        String recruiterAsJsonString = Utils.getRecruiterResponse();
        List<Recruiter> result = ResponseParser.parseRecruiterResponse(recruiterAsJsonString);

        return result != null ? result.get(0): null;
    }

    /* Return the list of resumes the json response is valid, null otherwise.
     **/
    public List<Resume> getResumes() {
        String resumesAsJsonString = Utils.getResumeResponse();
        return ResponseParser.parseResumeResponse(resumesAsJsonString);
    }

    public Company getCompany() {
        return this.company;
    }

    public Recruiter getRecruiter() {
        return this.recruiter;
    }

    public List<Tag> getCompanyTags(){
        return this.company.getTags();
    }

    public void addTag(Long companyId, Tag tag) {
        // To be implemented...
    }

    public void insertResume(Resume resume) {
        // To be implemented...
    }

    public void addReview(Long resId, String date, int rating) {
        // To be implemented...
    }
}
