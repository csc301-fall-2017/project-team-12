package com.stackd.stackd.helpers;

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

    public static List<Tag> parseTagResponse(String response) {
        try {
            JSONObject jo = new JSONObject(response);
            JSONArray ja = jo.getJSONArray("tags");

            List<Tag> tags = new ArrayList<Tag>();
            for (int i = 0; i < ja.length(); i++) {
                JSONObject jsonTag = (JSONObject) ja.get(i);

                /* Parse the id and name of the tag and create the tag object */
                Long tagId = jsonTag.getLong("_id");
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

    public static List<Resume> parseResumeResponse(String response) {
        try {
            JSONObject jo = new JSONObject(response);
            JSONArray ja = jo.getJSONArray("resumes");

            List<Resume> resume = new ArrayList<Resume>();
            for (int i = 0; i < ja.length(); i++) {
                JSONObject jsonResume = (JSONObject) ja.get(i);

                /* Parse the resume date from the json and construct the resume object */
                JSONObject jsonName = jsonResume.getJSONObject("candidateName");
                String candidateName = String.format("%s %s",
                        jsonName.getString("firstName"),
                        jsonName.getString("lastName"));

                resume.add(new Resume.Builder()
                        .id(jsonResume.getLong("_id"))
                        .rid(jsonResume.getLong("rid"))
                        .rating(jsonResume.getInt("rating"))
                        .url(jsonResume.getString("url"))
                        .collectionDate(jsonResume.getString("collectionDate"))
                        .recruiterComments(jsonResume.getString("recruiterComments"))
                        .tagList(ResponseParser.parseTagResponse(jsonResume.toString()))
                        .candidateName(candidateName)
                        .build());
            }
            return resume;
        } catch (JSONException e) {
            return null;
        }
    }

    public static List<Recruiter> parseRecruiterResponse(String response) {
        try {
            JSONObject jo = new JSONObject(response);
            JSONArray ja = jo.getJSONArray("recruiters");

            List<Recruiter> recruiters = new ArrayList<Recruiter>();
            for (int i = 0; i < ja.length(); i++) {
                JSONObject jsonResume = (JSONObject) ja.get(i);

                /* Construct the recruiter object and add it to the array of recruiters */
                recruiters.add(new Recruiter.Builder()
                        .recId(jsonResume.getLong("_id"))
                        .compId(jsonResume.getLong("cId"))
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

    public static List<Company> parseCompanyResponse(String response) {
        try {
            JSONObject jo = new JSONObject(response);
            JSONArray ja = jo.getJSONArray("companies");

            List<Company> companies = new ArrayList<Company>();
            for (int i = 0; i < ja.length(); i++) {
                JSONObject jsonCompany = (JSONObject) ja.get(i);
                String company = jsonCompany.toString();

            /* Construct the company object and add it to the array of companies */
                companies.add(new Company.Builder()
                        .id(jsonCompany.getLong("_id"))
                        .name(jsonCompany.getString("name"))
                        .tags(ResponseParser.parseTagResponse(company))
                        .recruiters(ResponseParser.parseRecruiterResponse(company))
                        .resumes(ResponseParser.parseResumeResponse(company))
                        .build());
            }
            return companies;
        } catch (JSONException e) {
            return null;
        }
    }
}
