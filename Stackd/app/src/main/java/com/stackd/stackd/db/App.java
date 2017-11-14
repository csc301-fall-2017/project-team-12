package com.stackd.stackd.db;

import android.util.Log;

import com.stackd.stackd.db.daos.CompanyDao;
import com.stackd.stackd.db.daos.RecruiterDao;
import com.stackd.stackd.db.daos.ResumeDao;
import com.stackd.stackd.db.daos.TagDao;
import com.stackd.stackd.model.Company;
import com.stackd.stackd.model.Recruiter;
import com.stackd.stackd.model.Resume;
import com.stackd.stackd.model.Tag;

import org.json.*;


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

        //TODO, continue setting company & recruiter

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
            String companyJson = jsonObject.getString(cid.toString());
            // TODO: deal with the company string and pass it into the parser and receive a company object

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

    public void addTag(Long companyId, Tag tag) {
        //Do not need to read in a json object, MUSA what
    }

    public Recruiter getRecruiter(Long recruiterId) {
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

    public void addReview(int recId, int resId, String date, int rating) {

    }

    
}

