package com.stackd.stackd.helpers;

import android.util.Log;

import com.stackd.stackd.db.entities.Company;
import com.stackd.stackd.db.entities.Recruiter;
import com.stackd.stackd.db.entities.Resume;
import com.stackd.stackd.db.entities.Tag;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tanveer on 2017-11-14.
 *
 * Parse the JSON response for the entities and create
 * a java objects from tha parsed data for the entities
 */

public class ResponseParser {

    public static List<Tag> parseTagResponse(String response)
            throws NullPointerException {
        if (response == null) throw new NullPointerException();
        try {
            JSONObject jo = new JSONObject(response);
            JSONArray ja = jo.getJSONArray("tags");

            List<Tag> tags = new ArrayList<>();
            for (int i = 0; i < ja.length(); i++) {
                JSONObject jsonTag = ja.getJSONObject(i);

                /* Parse the id and name of the tag and create the tag object */
                String tagId = jsonTag.getString("_id");
                String tagName = jsonTag.getString("name");
                tags.add(new Tag.Builder()
                        .id(tagId)
                        .name(tagName)
                        .build());
            }
            return tags;
        } catch (JSONException e) {
            return null;
        }

    }

    public static List<Resume> parseResumesResponse(String response)
            throws NullPointerException {
        if (response == null) throw new NullPointerException();
        try {
            JSONObject jo = new JSONObject(response);
            JSONArray ja = jo.getJSONArray("resumes");

            List<Resume> resume = new ArrayList<Resume>();
            for (int i = 0; i < ja.length(); i++) {
                JSONObject jsonResume = (JSONObject) ja.get(i);

                /* Parse the resume date from the json and construct the resume object */

                resume.add(new Resume.Builder()
                        .id(jsonResume.getString("_id"))
                        .candidateName(jsonResume.getString("candidateName"))
                        //.rid(jsonResume.getString("rid"))
                        .rating(jsonResume.getInt("rating"))
                        //.url(jsonResume.getString("url"))
                        .collectionDate(jsonResume.getString("collectionDate"))
                        .recruiterComments(jsonResume.getString("recruiterComments"))
                        .tagList(ResponseParser.parseTagResponse(jsonResume.toString()))
                        .build());
            }
            return resume;
        } catch (JSONException e) {
            return null;
        }
    }

    public static Resume parseResumeResponse(String response)
            throws NullPointerException {
        if (response == null) throw new NullPointerException();
        try {
            JSONObject jsonResume = new JSONObject(response);

            /* Parse the resume date from the json and construct the resume object */

            Resume resume = new Resume.Builder()
                    .id(jsonResume.getString("_id"))
                    .candidateName(jsonResume.getString("candidateName"))
                    //.rid(jsonResume.getString("rid"))
                    .rating(jsonResume.getInt("rating"))
                    //.url(jsonResume.getString("url"))
                    .collectionDate(jsonResume.getString("collectionDate"))
                    .recruiterComments(jsonResume.getString("recruiterComments"))
                    .tagList(ResponseParser.parseTagResponse(jsonResume.toString()))
                    .build();
            return resume;
        } catch (JSONException e) {
            return null;
        }
    }

    public static List<Recruiter> parseRecruiterResponse(String response)
            throws NullPointerException {
        if (response == null) throw new NullPointerException();
        try {
            JSONObject jo = new JSONObject(response);
            JSONArray ja = jo.getJSONArray("recruiters");

            List<Recruiter> recruiters = new ArrayList<>();
            for (int i = 0; i < ja.length(); i++) {
                JSONObject jsonResume = (JSONObject) ja.get(i);

                /* Construct the recruiter object and add it to the array of recruiters */
                recruiters.add(new Recruiter.Builder()
                        .recId(jsonResume.getString("_id"))
                        .compId(jsonResume.getString("cId"))
                        .firstName(jsonResume.getString("firstName"))
                        .lastName(jsonResume.getString("lastName"))
                        .email(jsonResume.getString("email"))
                        .build());
            }
            return recruiters;
        } catch (JSONException e) {
            return null;
        }
    }

    public static List<Company> parseCompanyResponse(String response)
            throws NullPointerException{
        if (response == null) throw new NullPointerException();
        try {
            JSONObject jo = new JSONObject(response);
            JSONArray ja = jo.getJSONArray("companies");

            List<Company> companies = new ArrayList<>();
            for (int i = 0; i < ja.length(); i++) {
                JSONObject jsonCompany = (JSONObject) ja.get(i);
                /* Construct the company object and add it to the array of companies */
                companies.add(new Company.Builder()
                        .id(jsonCompany.getString("_id"))
                        .name(jsonCompany.getString("name"))
                        //.tags(ResponseParser.parseTagResponse(company))
                        //.recruiters(ResponseParser.parseRecruiterResponse(company))
                        //.resumes(ResponseParser.parseResumesResponse(company))
                        .build());
            }
            return companies;
        } catch (JSONException e) {
            return null;
        }
    }

    public static JSONObject serializeCompany(Company company) {
        JSONObject jsonCompany = new JSONObject();
        try {
            // jsonCompany.put("_id", company.getId());
            jsonCompany.put("name", company.getName());
            return jsonCompany;
        }
        catch(JSONException e) {
            Log.d("DataManager", e.getMessage());
            return null;
        }
    }

    public static JSONObject serializeRecruiter(Recruiter recruiter) {
        JSONObject jsonRecruiter = new JSONObject();
        try {
            // jsonRecruiter.put("_id", recruiter.getRecId());
            jsonRecruiter.put("cId", recruiter.getCompId());
            jsonRecruiter.put("firstName", recruiter.getFirstName());
            jsonRecruiter.put("lastName", recruiter.getLastName());
            jsonRecruiter.put("email", recruiter.getEmail());
            return jsonRecruiter;
        }
        catch(JSONException e) {
            Log.d("DataManager", e.getMessage());
            return null;
        }
    }

    public static JSONObject serializeResume(Resume resume) {
        JSONObject jsonResume = new JSONObject();
        try {
            // jsonResume.put("_id", resume.getId());
            jsonResume.put("candidateName", resume.getCandidateName());
            jsonResume.put("rating", resume.getRating());
            jsonResume.put("collectionDate", resume.getCollectionDate());
            jsonResume.put("recruiterComments", resume.getRecruiterComments());
            JSONArray tagArray = new JSONArray();
            for(Tag t : resume.getTagList()) {
                JSONObject tagObject = new JSONObject();
                tagObject.put("_id", t.getId());
                tagArray.put(tagObject);
            }
            jsonResume.put("tags", tagArray);
            return jsonResume;
        }
        catch(JSONException e) {
            Log.d("DataManager", e.getMessage());
            return null;
        }
    }

    public static JSONObject serializeTag(Tag tag) {
        JSONObject jsonTag = new JSONObject();
        try {
            // jsonTag.put("_id", tag.getId());
            jsonTag.put("name", tag.getName());
            return jsonTag;
        }
        catch(JSONException e) {
            Log.d("DataManager", e.getMessage());
            return null;
        }
    }
}
