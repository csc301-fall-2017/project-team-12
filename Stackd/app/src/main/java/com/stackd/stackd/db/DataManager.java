package com.stackd.stackd.db;

import com.stackd.stackd.db.entities.Company;
import com.stackd.stackd.db.entities.Recruiter;
import com.stackd.stackd.db.entities.Resume;
import com.stackd.stackd.db.entities.Tag;
import com.stackd.stackd.helpers.ResponseParser;
import com.stackd.stackd.temp.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;

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

    // a variable for producing resume ids locally
    private static long currentResumeId = 100;

    private static String fs = File.separator;
    private static String PROJECT_PATH = new File(".").getPath();
    private static String RESOURCE_FILE = new Formatter(new StringBuilder(), Locale.CANADA)
            .format("%1$1s%2$1sapp" +
                            "%2$1ssrc" +
                            "%2$1stest" +
                            "%2$1sjava" +
                            "%2$1scom" +
                            "%2$1sstackd" +
                            "%2$1sstackd" +
                            "%2$1sresources" +
                            "%2$1s",
                    PROJECT_PATH, fs).toString();

    /* Path of the json files containing a dummy response from the api */
    private static String TAG_RESPONSE_FILE = String.format(
            "%stag_response.json",
            RESOURCE_FILE);

    public static String COMPANY_RESPONSE_FILE = String.format(
            "%scompany_response.json",
            RESOURCE_FILE);

    public static String RECRUITER_RESPONSE_FILE = String.format(
            "%srecruiter_response.json",
            RESOURCE_FILE);

    public static String RESUME_RESPONSE_FILE = String.format(
            "%sresume_response.json",
            RESOURCE_FILE);


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
        //TODO: replace this with the commented line
        List<Resume> result = ResponseParser.parseResumeResponse(resumesAsJsonString);
        if (this.company.getResumes() != null){
            for (Resume resume: this.company.getResumes()) {
                if (!result.contains(resume)) {
                    result.add(resume);
                }
            }
        }
        return result;
        //return ResponseParser.parseResumeResponse(resumesAsJsonString);
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
        if(this.company.getResumes() == null)
            this.company.setResumes(new ArrayList<Resume>());
        this.company.addResume(resume);
    }

    public void addReview(Long resId, String date, int rating) {
        // To be implemented...
    }

    public static long getNextResumeId() {
        return currentResumeId++;
    }
}
