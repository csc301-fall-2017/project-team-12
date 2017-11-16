package com.stackd.stackd.db;

import com.stackd.stackd.db.daos.CompanyDao;
import com.stackd.stackd.db.daos.RecruiterDao;
import com.stackd.stackd.db.daos.ResumeDao;
import com.stackd.stackd.db.daos.TagDao;
import com.stackd.stackd.db.entities.Company;
import com.stackd.stackd.db.entities.Recruiter;
import com.stackd.stackd.db.entities.Resume;
import com.stackd.stackd.db.entities.Tag;
import com.stackd.stackd.helpers.ResponseParser;

import org.json.JSONException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;

import java.util.List;

/**
 * A global class for the whole dataManager resposible for getting the required data
 * from the database to the dataManager users, as objects.
 *
 * Created by Musa on 11/8/2017.
 */

public class DataManager {
    private static DataManager dataManager = null;
    private CompanyDao companyDao;
    private RecruiterDao recruiterDao;
    private ResumeDao resumeDao;
    private TagDao tagDao;
    //TODO: set these during authentication
    private Company company; //TODO get cid from login activity
    private Recruiter recruiter; // TODO: get rid from login

    private DataManager(Long companyID, Long recruiterId) {
        // Instantiate Daos:
        this.companyDao = CompanyDao.getCompanyDao();
        this.recruiterDao = RecruiterDao.getRecruiterDao();
        this.resumeDao = ResumeDao.getResumeDao();
        this.tagDao = TagDao.getTagDao();

        this.company = getCompany(new Long(1));
        this.recruiter = getRecruiter(new Long(21));

    }

    public static DataManager getDataManager(Long companyId, Long recruiterId) {
        if (dataManager == null) {
            dataManager = new DataManager(companyId, recruiterId);
            return dataManager;
        } else {
            return dataManager;
        }
    }

    private Company getCompany(Long cId) {
        Company result = null;
        // Fetch the company from JSON file, for example:
        String companyAsJsonString = readFile("company_response.json");
        try {
            result = ResponseParser.parseCompanyResponse(companyAsJsonString).get(0);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        return result;
    }


    private Recruiter getRecruiter(Long rId) {
        Recruiter result = null;
        // Fetch the company from JSON file, for example:
        String recruiterAsJsonString = readFile("recruiter_response.json");
        try {
            result = ResponseParser.parseRecruiterResponse(recruiterAsJsonString).get(0);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }

    public Company getCompany() {
        return this.company;
    }

    public Recruiter getRecruiter() {
        return this.recruiter;
    }

    public List<Resume> getResumes() {
        List<Resume> result = null;
        // Fetch the company from JSON file, for example:
        String resumesAsJsonString = readFile("resume_response.json");
        try {
            result = ResponseParser.parseResumeResponse(resumesAsJsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;
    }
    public List<Tag> getCompanyTags(){
        return this.company.getTags();
    }

    public void addTag(Long companyId, Tag tag) {


    }

    public void insertResume(Resume resume) {

    }

    public void addReview(Long resId, String date, int rating) {

    }

    private String readFile(String path) {
        String result = null;
        try {
            File file = new File(path);
            FileInputStream is = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            is.close();
            reader.close();

            result = sb.toString();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}