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

    public static List<Tag> parseTagResponse(String response)
            throws JSONException {
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

    }

    public static Resume parseResumeResponse(String response)
            throws JSONException {

        JSONObject jo = new JSONObject(response);
        JSONArray ja = jo.getJSONArray("resumes");

        List<Resume> resume;
        return null;
    }

    public static Recruiter parseRecruiterResponse(String response)
            throws JSONException{

        JSONObject jo = new JSONObject(response);
        return null;
    }

    public static Company parseCompanyResponse(String response)
            throws JSONException{
        return null;
    }
}
