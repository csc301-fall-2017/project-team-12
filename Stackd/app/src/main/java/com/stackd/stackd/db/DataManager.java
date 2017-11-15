package com.stackd.stackd.db;

import com.stackd.stackd.db.daos.CompanyDao;
import com.stackd.stackd.db.daos.RecruiterDao;
import com.stackd.stackd.db.daos.ResumeDao;
import com.stackd.stackd.db.daos.TagDao;
import com.stackd.stackd.db.entities.Company;
import com.stackd.stackd.db.entities.Recruiter;
import com.stackd.stackd.db.entities.Resume;
import com.stackd.stackd.db.entities.Tag;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * A global class for the whole app resposible for getting the required data
 * from the database to the app users, as objects.
 *
 * Created by Musa on 11/8/2017.
 */

public class DataManager {
    private static DataManager app = null;
    private CompanyDao companyDao;
    private RecruiterDao recruiterDao;
    private ResumeDao resumeDao;
    private TagDao tagDao;
    //TODO: set these during authentication
    public static Company company = new Company();
    public static Recruiter recruiter = new Recruiter(); //new Recruiter();

    private DataManager(Long companyID, Long recruiterId) {
        // Instantiate Daos:
        this.companyDao = CompanyDao.getCompanyDao();
        this.recruiterDao = RecruiterDao.getRecruiterDao();
        this.resumeDao = ResumeDao.getResumeDao();
        this.tagDao = TagDao.getTagDao();

        company.setId(companyID);
        recruiter.setRecId(recruiterId);
        //TODO, continue setting company & recriuter

    }

    public static DataManager getApp(Long companyId, Long recruiterId) {
        if (app == null) {
            app = new DataManager(companyId, recruiterId);
            return app;
        } else {
            return app;
        }
    }
    public Company getCompany() {

        //Assuming the jsonFileAsString = {"123": "info", "234":"info}
        //where "123", "234" are the cid's of the company
        //and "info" is the data that we can use to convert the company into an object


        String jsonFileAsString = "temp";
        //The above json string should look something like below:
        //String jsonFileAsString = {"fetch":"this","from":"somewhere","else":"replace"};
        Company company = null;
        //
        try{
            JSONObject jsonObject = new JSONObject(jsonFileAsString);
            //String companyJson = jsonObject.getString(company.getId().toString());
            // TODO: deal with the company string and pass it into the parser and receive a company object
            return company;

        } catch (JSONException e){
            e.printStackTrace();
        }


        //Musa's original comments:
        // Fetch the company from JSON file, for example:
        // String jsonFileAsString, read it to jsonObject
        // JSONObject fileAsJosnObject = new JSONObject(jsonFile)
        // Get the required compnay (see the documentation of JSONObjects and how to use them,
        // I still don't know them well yet sorry :P
        // Then pass the companyAsJsonString to  parsere to get back a Company instance
        // and return it!
        // And yay!



        return company;
    }

    public List<Tag> getCompanyTags(){
        String jsonFileAsString = "temp";
        //The above json string should look something like below:
        //String jsonFileAsString = {"fetch":"this","from":"somewhere","else":"replace"};
        Company company = null;
        //
        try{
            JSONObject jsonObject = new JSONObject(jsonFileAsString);
            //String companyJson = jsonObject.getString(.toString());
            // TODO: deal with the company string and pass it into the parser and receive a company tags

        } catch (JSONException e){
            e.printStackTrace();
        }
        return null;
    }

    public void addTag(Tag tag) {
        //Do not need to read in a json object, MUSA what
    }

    public Recruiter getRecruiter() {
        // TODO: use the App.company ID
        String jsonFileAsString = "temp";
        //The above json string should look something like below:
        //String jsonFileAsString = {"fetch":"this","from":"somewhere","else":"replace"};
        Company company = null;
        //
        try{
            JSONObject jsonObject = new JSONObject(jsonFileAsString);
            //String companyJson = jsonObject.getString(.toString());
            // TODO: deal with the company string and pass it into the parser and receive a company object

        } catch (JSONException e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Resume> getResumesWithTag(Tag tag) {

        // TODO: use the App.company ID
        String jsonFileAsString = "temp";
        //The above json string should look something like below:
        //String jsonFileAsString = {"fetch":"this","from":"somewhere","else":"replace"};
        Company company = null;
        //
        try{
            JSONObject jsonObject = new JSONObject(jsonFileAsString);
            //String companyJson = jsonObject.getString(.toString());
            // TODO: deal with the company string and pass it into the parser and receive a company object

        } catch (JSONException e){
            e.printStackTrace();
        }
        return null;
    }

    public void insertResume(Resume resume) {

    }

    public void addReview(Long resId, SimpleDateFormat date, int rating) {

    }

    
}